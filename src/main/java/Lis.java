import java.util.Random;

public class Lis extends Animal {
    public Lis(int force, int age, int initiative, World currentWorld, coordinate position) {
        super(force, age, initiative, currentWorld, position);
    }

    @Override
    public coordinate Move() {
        coordinate newPosition = new coordinate(getPosition().getX(), getPosition().getY());
        Random rand = new Random();
        int randomNumber = rand.nextInt(4);
        Organism org;
        for (int i = 0; i < 4; i++) {
            if (randomNumber == 0 && newPosition.getX() != 1) {//gora
                org = getCurrentWorld().getOrganismFromArray(getPosition().getX() - 1, getPosition().getY());
                if (org == null || org.getForce() <= this.getForce()) {
                    newPosition.setX(newPosition.getX() - 1);
                    break;
                }

            } else if (randomNumber == 1 && newPosition.getX() != getCurrentWorld().getX()) {//dol
                org = getCurrentWorld().getOrganismFromArray(getPosition().getX() + 1, getPosition().getY() + 1);
                if (org == null || org.getForce() <= this.getForce()) {
                    newPosition.setX(newPosition.getX() + 1);
                    break;
                }

            } else if (randomNumber == 2 && newPosition.getY() != 1) {//lewo
                org = getCurrentWorld().getOrganismFromArray(getPosition().getX(), getPosition().getY() - 1);
                if (org == null || org.getForce() <= this.getForce()) {
                    newPosition.setY(newPosition.getY() - 1);
                    break;
                }

            } else if (randomNumber == 3 && newPosition.getY() != getCurrentWorld().getY()) {
                org = getCurrentWorld().getOrganismFromArray(getPosition().getX(), getPosition().getY() + 1);
                if (org == null || org.getForce() <= this.getForce()) {
                    newPosition.setY(newPosition.getY() + 1);
                    break;
                }

            }
            randomNumber = (randomNumber + 1) % 4;
        }
        return newPosition;

    }

    @Override
    public String GetName() {
        return ("Lis");
    }

    @Override
    public void PrintOrganism() {

    }

    @Override
    public Organism Multiplication(coordinate pos) {
        return new Lis(3, 1, 7, getCurrentWorld(), pos);
    }
}
