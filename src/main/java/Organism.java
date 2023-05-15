import java.util.Random;

public abstract class Organism {
    private int force;
    private int age;
    private int initiative;
    private World currentWorld;
    private coordinate position;

    public Organism(int force, int age, int initiative, World currentWorld, coordinate position) {
        this.force = force;
        this.age = age;
        this.initiative = initiative;
        this.currentWorld = currentWorld;
        this.position = position;
    }

    public abstract String GetName();

    public abstract void Action();

    public abstract void Collision(coordinate pos);

    public abstract void PrintOrganism();

    public abstract Organism Multiplication(coordinate pos);

    public coordinate Move() {
        coordinate newPosition = new coordinate(getPosition().getX(), getPosition().getY());


        Random rand = new Random();
        int randomNumber = rand.nextInt(4);
        for (int i = 0; i < 4; i++) {
            if (randomNumber == 0 && newPosition.getX() != 1) {//gora
                newPosition.setX(newPosition.getX() - 1);
                break;
            } else if (randomNumber == 1 && newPosition.getX() != currentWorld.getX()) {//dol
                newPosition.setX(newPosition.getX() + 1);
                break;
            } else if (randomNumber == 2 && newPosition.getY() != 1) {//lewo
                newPosition.setY(newPosition.getY() - 1);
                break;
            } else if (randomNumber == 3 && newPosition.getY() != currentWorld.getY()) {
                newPosition.setY(newPosition.getY() + 1);
                break;
            }
        }

        return newPosition;
    }

    public coordinate FindFreeField() {
        coordinate newPosition = new coordinate(getPosition().getX(), getPosition().getY());


        if (newPosition.getY() != 1 && currentWorld.getOrganismFromArray(newPosition.getX(), newPosition.getY() - 1) == null) {
            newPosition.setY(newPosition.getY() - 1);
        } else if (newPosition.getY() != currentWorld.getY() && currentWorld.getOrganismFromArray(newPosition.getX(), newPosition.getY() + 1) == null) {
            newPosition.setY(newPosition.getY() + 1);
        } else if (newPosition.getX() != currentWorld.getX() && currentWorld.getOrganismFromArray(newPosition.getX() + 1, newPosition.getY()) == null) {
            newPosition.setX(newPosition.getX() + 1);

        } else if (newPosition.getX() != 1 && currentWorld.getOrganismFromArray(newPosition.getX() - 1, newPosition.getY()) == null) {
            newPosition.setX(newPosition.getX() - 1);
        } else {
            newPosition.setY(-1);
            newPosition.setX(-1);
        }

        return newPosition;
    }

    public boolean RandomProbability(int var) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(100);
        if (randomNumber < var) {
            return true;
        } else {
            return false;
        }
    }


    public boolean GetSpecialAbility() {
        return false;
    }

    public int GetCounterAbility() {
        return 0;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public World getCurrentWorld() {
        return currentWorld;
    }

    public void setCurrentWorld(World currentWorld) {
        this.currentWorld = currentWorld;
    }

    public coordinate getPosition() {
        return position;
    }

    public void setPosition(coordinate position) {
        this.position = position;

    }
}
