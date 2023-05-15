import java.awt.*;

public class Mlecz extends Plant {
    public Mlecz(int force, int age, int initiative, World currentWorld, coordinate position) {
        super(force, age, initiative, currentWorld, position);
    }

    @Override
    public String GetName() {
        return ("M");
    }


    @Override
    public Color GetColor() {
        return (Color.white);
    }

    @Override
    public void PrintOrganism() {

    }

    @Override
    public Organism Multiplication(coordinate pos) {
        return new Mlecz(0, 1, 0, getCurrentWorld(), pos);
    }

    @Override
    public void Action() {
        for (int i = 0; i < 3; i++) {
            super.Action();
        }
        setAge(getAge() - 2);
    }
}
