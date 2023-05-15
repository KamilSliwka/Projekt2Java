
public class Owca extends Animal {
    public Owca(int force, int age, int initiative, World currentWorld, coordinate position) {
        super(force, age, initiative, currentWorld, position);
    }

    @Override
    public String GetName() {
        return ("Owca");
    }

    @Override
    public void PrintOrganism() {

    }

    @Override
    public Organism Multiplication(coordinate pos) {
        return new Owca(4, 1, 4, getCurrentWorld(), pos);
    }
}
