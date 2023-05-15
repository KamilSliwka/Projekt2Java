import java.util.Random;

public class Antylopa extends Animal {
    public Antylopa(int force, int age, int initiative, World currentWorld, coordinate position) {
        super(force, age, initiative, currentWorld, position);
    }

    @Override
    public String GetName() {
        return ("Antylopa");
    }

    @Override
    public void PrintOrganism() {

    }

    @Override
    public Organism Multiplication(coordinate pos) {
        return new Antylopa(4, 1, 4, getCurrentWorld(), pos);
    }

    @Override
    public coordinate Move() {
        coordinate newPosition = new coordinate(getPosition().getX(), getPosition().getY());

        Random rand = new Random();
        int randomNumber = rand.nextInt(8);
        for (int i = 0; i < 8; i++) {
            if (randomNumber == 0 && newPosition.getX() != 1) {//gora
                newPosition.setX(newPosition.getX() - 1);
                break;
            } else if (randomNumber == 1 && newPosition.getX() != getCurrentWorld().getX()) {//dol
                newPosition.setX(newPosition.getX() + 1);
                break;
            } else if (randomNumber == 2 && newPosition.getY() != 1) {//lewo
                newPosition.setY(newPosition.getY() - 1);
                break;
            } else if (randomNumber == 3 && newPosition.getY() != getCurrentWorld().getY()) {//prawo
                newPosition.setY(newPosition.getY() + 1);
                break;
            }
            if (randomNumber == 4 && newPosition.getX() > 2) {//gora o 2
                newPosition.setX(newPosition.getX() - 2);
                break;
            } else if (randomNumber == 5 && newPosition.getX() < getCurrentWorld().getX() - 1) {//dol o 2
                newPosition.setX(newPosition.getX() + 2);
                break;
            } else if (randomNumber == 6 && newPosition.getY() > 2) {//lewo o 2
                newPosition.setY(newPosition.getY() - 2);
                break;
            } else if (randomNumber == 7 && newPosition.getY() < getCurrentWorld().getY() - 1) {// prawo o 2
                newPosition.setY(newPosition.getY() + 2);
                break;
            }
        }

        return newPosition;
    }
}
