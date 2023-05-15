public class Trawa extends Plant {
    public Trawa(int force, int age, int initiative, World currentWorld, coordinate position) {
        super(force, age, initiative, currentWorld, position);
    }

    @Override
    public void PrintOrganism() {

    }

    @Override
    public Organism Multiplication(coordinate pos) {
        return new Trawa(0, 1, 0, getCurrentWorld(), pos);
    }

    @Override
    public String GetName() {
        return ("Trawa");
    }
}
