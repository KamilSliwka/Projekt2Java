public abstract class Animal extends Organism {

    public Animal(int force, int age, int initiative, World currentWorld, coordinate position) {
        super(force, age, initiative, currentWorld, position);
    }

    @Override
    public void Action() {

        setAge(getAge() + 1);
        coordinate newPosition;

        newPosition = new coordinate(Move());
        if (getCurrentWorld().getOrganismFromArray(newPosition.getX(), newPosition.getY()) == null) {
            getCurrentWorld().setOrganismOnArray(this, newPosition.getX(), newPosition.getY());
            getCurrentWorld().setOrganismOnArray(null, this.getPosition().getX(), this.getPosition().getY());
            setPosition(newPosition);

        } else {
            if (!(newPosition.equals(getPosition())))
                Collision(newPosition);
        }
    }

    @Override
    public void Collision(coordinate pos) {
        Organism org = getCurrentWorld().getOrganismFromArray(pos.getX(), pos.getY());
        if (ifTheSameOrganism(org)) {
            Birth();
        } else if (this instanceof Human && this.GetSpecialAbility() && this.getForce() <= org.getForce()) {

        } else if (org instanceof Human && org.getForce() <= this.getForce() && org.GetSpecialAbility()) {

        } else if (org instanceof Zolw && this.getForce() >= 5) {
            org.setAge(-1);
            ChangePosition(pos);
        } else if (org instanceof Antylopa && this.getForce() >= org.getForce()) {
            coordinate newPosition = new coordinate(org.FindFreeField());
            boolean antylopeEscape = org.RandomProbability(50);
            if (newPosition.getX() == -1 && newPosition.getY() == -1) {
                org.setAge(-1);
                ChangePosition(pos);
            } else if (antylopeEscape) {
                org.getCurrentWorld().setOrganismOnArray(org, newPosition.getX(), newPosition.getY());
                org.setPosition(newPosition);
                ChangePosition(pos);
            } else {
                org.setAge(-1);
                ChangePosition(pos);
            }
        } else if (org instanceof Guarana) {
            this.setForce(getForce() + 3);
            ChangePosition(pos);
            String message = this.Name() + " zjada Guarane";
            getCurrentWorld().getAppGUI().returnInformationContainer().addMessage(message);

        } else if (org instanceof WilczeJagody) {
            getCurrentWorld().setOrganismOnArray(null, getPosition().getX(), getPosition().getY());
            this.setAge(-1);
            String message = "Wilcze jagody zabijaja " + this.Name();
            getCurrentWorld().getAppGUI().returnInformationContainer().addMessage(message);

        } else if (org instanceof BarszczSosnowskiego) {
            getCurrentWorld().setOrganismOnArray(null, getPosition().getX(), getPosition().getY());
            this.setAge(-1);
            String message = "Barszcz  zabija " + this.Name();
            getCurrentWorld().getAppGUI().returnInformationContainer().addMessage(message);
        } else if (this.getForce() >= org.getForce()) {
            org.setAge(-1);
            ChangePosition(pos);
            String message = this.Name() + " zabija " + org.Name();
            getCurrentWorld().getAppGUI().returnInformationContainer().addMessage(message);
        } else if (this.getForce() < org.getForce()) {
            this.setAge(-1);
            if (this instanceof Human) {
                getCurrentWorld().setGame(false);
            }
            String message = org.Name() + " zabija " + this.Name();
            getCurrentWorld().getAppGUI().returnInformationContainer().addMessage(message);
            getCurrentWorld().setOrganismOnArray(null, getPosition().getX(), getPosition().getY());
        }

    }

    public String GetName(Organism org) {
        return org.GetName();
    }

    public boolean ifTheSameOrganism(Organism org) {
        if (GetName().equals(GetName(org))) {
            return true;
        } else {
            return false;
        }
    }

    public void Birth() {
        coordinate newPosition = new coordinate(FindFreeField());
        //coordinate newPosition = new coordinate(FindFreeField().getX(), FindFreeField().getY());
        //newPosition = FindFreeField();
        if (newPosition.getX() == -1 && newPosition.getY() == -1) {
            //nie ma narodzin
            return;
        }
        getCurrentWorld().setOrganismOnArray(Multiplication(newPosition), newPosition.getX(), newPosition.getY());
    }

    public void ChangePosition(coordinate pos) {
        getCurrentWorld().setOrganismOnArray(this, pos.getX(), pos.getY());
        getCurrentWorld().setOrganismOnArray(null, getPosition().getX(), getPosition().getY());
        this.setPosition(pos);
    }


}
