import java.awt.*;

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
        if (isSpecialAbility()) {
            if (getCounter() > 5) {
                setSpecialAbility(false);
                setCounter(0);
            }
        }
        int x = getPosition().getX();
        int y = getPosition().getY();

        if (move == 0 && x != 1) {
            x--;
//            coordinate c = new coordinate(x,y);
//            setPosition(c);

        } else if (move == 1 && x != 20) {
            x++;
//            coordinate c = new coordinate(x,y);
//            setPosition(c);
        } else if (move == 2 && y != 1) {
            y--;
//            coordinate c = new coordinate(x,y);
//            setPosition(c);
        } else if (move == 3 && y != 20) {
            y++;
//            coordinate c = new coordinate(x,y);
//            setPosition(c);
        } else if (move == 4 && getCounter() > 5) {
            setSpecialAbility(true);
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

