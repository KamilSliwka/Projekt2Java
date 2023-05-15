import java.awt.*;

public class Zolw extends Animal {
    public Zolw(int force, int age, int initiative, World currentWorld, coordinate position) {
        super(force, age, initiative, currentWorld, position);
    }

    @Override
    public void Action() {
        boolean move = RandomProbability(25);
        if (move) {
            super.Action();
        }
    }

    @Override
    public String GetName() {
        return ("Z");
    }


    @Override
    public Color GetColor() {
        return (new Color(108, 152, 108));
    }

    @Override
    public void PrintOrganism() {

    }

    @Override
    public Organism Multiplication(coordinate pos) {
        return new Zolw(2, 1, 1, getCurrentWorld(), pos);
    }
}

