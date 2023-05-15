public class Human extends Animal {
    private boolean specialAbility;
    private int counter;

    public Human(int force, int age, int initiative, World currentWorld, coordinate position, boolean specialAbility, int counter) {
        super(force, age, initiative, currentWorld, position);
        this.specialAbility = specialAbility;
        this.counter = counter;
    }

    public boolean isSpecialAbility() {
        return specialAbility;
    }

    public void setSpecialAbility(boolean specialAbility) {
        this.specialAbility = specialAbility;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public void Action() {
        setCounter(getCounter() + 1);
        if (isSpecialAbility()) {
            if (getCounter() > 5) {
                setSpecialAbility(false);
                setCounter(0);
            }
        }
        super.Action();
    }

    @Override
    public String GetName() {
        return ("Czlowiek");
    }

    @Override
    public void PrintOrganism() {

    }

    @Override
    public Organism Multiplication(coordinate pos) {
        return new Human(5, 1, 4, getCurrentWorld(), pos, false, 5);
    }

//    @Override
//    public coordinate Move() {
//        KeyMove m = new KeyMove(this);
//        ;
//
//        return this.getPosition();
//    }
}

