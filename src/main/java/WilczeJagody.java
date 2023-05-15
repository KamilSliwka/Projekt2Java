import java.awt.*;

public class WilczeJagody extends Plant {
    public WilczeJagody(int force, int age, int initiative, World currentWorld, coordinate position) {
        super(force, age, initiative, currentWorld, position);
    }

    @Override
    public String GetName() {
        return ("J");
    }


    @Override
    public Color GetColor() {
        return (Color.black);
    }

    @Override
    public void PrintOrganism() {

    }

    @Override
    public Organism Multiplication(coordinate pos) {
        return new WilczeJagody(99, 1, 0, getCurrentWorld(), pos);
    }
}

