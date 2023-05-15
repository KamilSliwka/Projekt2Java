public class Wilk extends Animal {
    public Wilk(int force, int age, int initiative, World currentWorld, coordinate position) {
        super(force, age, initiative, currentWorld, position);
    }

    @Override
    public String GetName() {
        return ("Wilk");
    }

    @Override
    public void PrintOrganism() {

    }

    @Override
    public Organism Multiplication(coordinate pos) {
        return new Wilk(9, 1, 5, getCurrentWorld(), pos);
    }
}

