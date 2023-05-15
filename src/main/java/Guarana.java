import java.awt.*;

public class Guarana extends Plant {
    public Guarana(int force, int age, int initiative, World currentWorld, coordinate position) {
        super(force, age, initiative, currentWorld, position);
    }

    @Override
    public String GetName() {
        return ("G");
    }

    @Override
    public Color GetColor() {
        return (Color.orange);
    }

    @Override
    public void PrintOrganism() {

    }

    @Override
    public Organism Multiplication(coordinate pos) {
        return new Guarana(0, 1, 0, getCurrentWorld(), pos);
    }
}
