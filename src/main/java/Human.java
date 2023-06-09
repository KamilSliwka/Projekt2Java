import java.awt.*;

public class Human extends Animal {
    private boolean specialAbility;
    private int counter;

    public Human(int force, int age, int initiative, World currentWorld, coordinate position, boolean specialAbility, int counter) {
        super(force, age, initiative, currentWorld, position);
        this.specialAbility = specialAbility;
        this.counter = counter;
    }

    @Override
    public String Name() {
        return ("Czlowiek");
    }

    @Override
    public boolean GetSpecialAbility() {
        return specialAbility;
    }

    @Override
    public void setSpecialAbility(boolean specialAbility) {
        this.specialAbility = specialAbility;
    }

    @Override
    public int getCounter() {
        return counter;
    }

    @Override
    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public void Action() {
        setCounter(getCounter() + 1);
        if (GetSpecialAbility()) {
            if (getCounter() > 5) {
                setSpecialAbility(false);
                setCounter(0);
            }
        }
        super.Action();
    }

    public void Action(int move) {
        setAge(getAge() + 1);
        coordinate newPosition;

        newPosition = Move(move);
        if (getCurrentWorld().getOrganismFromArray(newPosition.getX(), newPosition.getY()) == null) {
            getCurrentWorld().setOrganismOnArray(this, newPosition.getX(), newPosition.getY());
            getCurrentWorld().setOrganismOnArray(null, this.getPosition().getX(), this.getPosition().getY());
            setPosition(newPosition);

        } else {
            if (!(newPosition.equals(getPosition())))
                Collision(newPosition);
        }
    }

    public coordinate Move(int move) {
        setCounter(getCounter() + 1);
        if (GetSpecialAbility()) {
            if (getCounter() > 5) {
                setSpecialAbility(false);
                setCounter(0);
            }
        }
        int x = getPosition().getX();
        int y = getPosition().getY();

        if (move == 0 && x != 1) {
            x--;
        } else if (move == 1 && x != 20) {
            x++;
        } else if (move == 2 && y != 1) {
            y--;
        } else if (move == 3 && y != 20) {
            y++;
        } else if (move == 4) {
            if (getCounter() > 5) {
                setSpecialAbility(true);
                setCounter(0);
                String message = "Super moc aktywowana ";
                getCurrentWorld().getAppGUI().returnInformationContainer().addMessage(message);
            } else {
                String message = "Nie mozna aktywowac super mocy ";
                getCurrentWorld().getAppGUI().returnInformationContainer().addMessage(message);
            }
        }
        coordinate c = new coordinate(x, y);
        return c;
    }

    @Override
    public String GetName() {
        return ("X");
    }

    @Override
    public Color GetColor() {
        return (Color.red);
    }

    @Override
    public void PrintOrganism() {

    }

    @Override
    public Organism Multiplication(coordinate pos) {
        return new Human(5, 1, 4, getCurrentWorld(), pos, false, 5);
    }

}

