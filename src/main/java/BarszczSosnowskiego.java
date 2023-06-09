import java.awt.*;

public class BarszczSosnowskiego extends Plant {
    public BarszczSosnowskiego(int force, int age, int initiative, World currentWorld, coordinate position) {
        super(force, age, initiative, currentWorld, position);
    }

    @Override
    public String GetName() {
        return ("B");
    }

    @Override
    public String Name() {
        return ("Barszcz");
    }

    @Override
    public Color GetColor() {
        return (Color.yellow);
    }

    @Override
    public void PrintOrganism() {

    }

    @Override
    public Organism Multiplication(coordinate pos) {
        return new BarszczSosnowskiego(10, 1, 0, getCurrentWorld(), pos);
    }

    @Override
    public void Action() {
        coordinate death = new coordinate(getPosition().getX(), getPosition().getY());
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i == 0 && j == 0) {
                } else {
                    Organism org = getCurrentWorld().getOrganismFromArray(death.getX() + i, death.getY() + j);
                    if (org != null && ifPlant(org)) {

                    } else if (org != null) {
                        if (ifHuman(org) && org.GetSpecialAbility()) {

                        } else {
                            org.setAge(-1);
                            String message = "Barszcz zabija " + org.Name();
                            getCurrentWorld().getAppGUI().returnInformationContainer().addMessage(message);
                            getCurrentWorld().setOrganismOnArray(null, death.getX() + i, death.getY() + j);
                        }
                    }
                }
            }
        }
        super.Action();
    }

}
