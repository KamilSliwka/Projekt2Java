import java.io.*;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;


public class World {
    private int x;
    private int y;
    private int roundCounter;
    private boolean game;
    private Organism[][] array;
    private AppGUI.boardField[][] board;
    private AppGUI appGUI;
    private Organism human;


    public World(AppGUI appGUI) {
        this.x = 20;
        this.y = 20;
        this.roundCounter = 1;
        this.appGUI = appGUI;
        this.array = new Organism[20][20];
        this.board = new AppGUI.boardField[20][20];
        this.game = true;

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                board[i][j] = new AppGUI.boardField();
            }
        }
        this.human = new Human(5, 1, 4, this, null, false, 5);
        RandomPlace(human);
        for (int i = 0; i < 5; i++) {
            RandomPlace(new Antylopa(4, 1, 4, this, null));
            RandomPlace(new Wilk(9, 1, 5, this, null));
            RandomPlace(new Owca(4, 1, 4, this, null));
            RandomPlace(new Zolw(2, 1, 1, this, null));
            RandomPlace(new Lis(3, 1, 7, this, null));
            RandomPlace(new BarszczSosnowskiego(10, 1, 0, this, null));
            RandomPlace(new WilczeJagody(99, 1, 0, this, null));
            RandomPlace(new Mlecz(0, 1, 0, this, null));
            RandomPlace(new Trawa(0, 1, 0, this, null));
            RandomPlace(new Guarana(0, 1, 0, this, null));

        }

    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getRoundCounter() {
        return roundCounter;
    }

    public void setRoundCounter(int roundCounter) {
        this.roundCounter = roundCounter;
    }

    public boolean isGame() {
        return game;
    }

    public void setGame(boolean game) {
        this.game = game;
    }

    public AppGUI getAppGUI() {
        return appGUI;
    }

    public void setAppGUI(AppGUI appGUI) {
        this.appGUI = appGUI;
    }

    public Organism getOrganismFromArray(int x, int y) {
        if (x < 1 || x > this.x || y < 1 || y > this.y) {
            return null;
        } else {
            return array[x - 1][y - 1];
        }
    }

    public void setOrganismOnArray(Organism org, int x, int y) {
        this.array[x - 1][y - 1] = org;
    }

    public AppGUI.boardField getFieldFromBoard(int x, int y) {
        if (x < 1 || x > this.x || y < 1 || y > this.y) {
            return null;
        } else {
            return board[x - 1][y - 1];
        }
    }

    public void setFieldOnBoard(AppGUI.boardField field, int x, int y) {
        this.board[x - 1][y - 1] = field;
    }

    public void RandomPlace(Organism org) {
        coordinate newPosition = new coordinate(1, 1);


        while (true) {
            Random rand = new Random();
            newPosition.setX(rand.nextInt(20) + 1);
            newPosition.setY(rand.nextInt(20) + 1);
            if (getOrganismFromArray(newPosition.getX(), newPosition.getY()) == null) {
                setOrganismOnArray(org, newPosition.getX(), newPosition.getY());
                org.setPosition(newPosition);
                break;
            }
        }
    }

    public void Round(int m) {
        setRoundCounter(getRoundCounter() + 1);
        Vector<Organism> move = new Vector<Organism>();

        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 20; j++) {
                Organism org = null;
                org = getOrganismFromArray(i, j);
                if (org != null) {
                    if (org instanceof Human && org.getAge() == -1) {
                        setGame(false);
                    }
                    move.add(org);
                }
            }
        }
        for (Organism organism : move) {
            organism.setAge(organism.getAge() + 1);
        }

        move.sort(new Comparator<Organism>() {
            @Override
            public int compare(Organism o1, Organism o2) {
                if (o2.getInitiative() != o1.getInitiative()) {
                    return o2.getInitiative() - o1.getInitiative();
                } else {
                    return o2.getAge() - o1.getAge();
                }
            }
        });

        while (!move.isEmpty()) {
            Organism org = move.lastElement();
            if (org.getAge() != -1) {
                if (org instanceof Human) {
                    ((Human) org).Action(m);
                    if (org.getAge() == -1) {
                        setGame(false);
                    }
                } else {
                    org.Action();
                }
            }
            if (org instanceof Human && org.getAge() == -1) {
                setGame(false);
                String message = " KONIEC GRY";
                getAppGUI().returnInformationContainer().addMessage(message);
            }
            move.remove(move.size() - 1);
            //delete
        }
    }


    public World(AppGUI appGUI, String fileName) throws FileNotFoundException {
        Scanner worldSave = new Scanner(new File(fileName));

        this.appGUI = appGUI;
        this.y = 20;
        this.x = 20;
        this.roundCounter = worldSave.nextInt();
        this.setGame(true);
        boolean x;
        int ability = worldSave.nextInt();
        if (ability == 1) {
            x = true;
        } else {
            x = false;
        }
        int c = worldSave.nextInt();

        array = new Organism[20][20];
        board = new AppGUI.boardField[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                board[i][j] = new AppGUI.boardField();
            }
        }

        while (worldSave.hasNext()) {
            String name = worldSave.next();
            int strength_val = worldSave.nextInt();
            int initiative_val = worldSave.nextInt();
            int positionX_val = worldSave.nextInt();
            int positionY_val = worldSave.nextInt();
            int age_val = worldSave.nextInt();
            coordinate pos = new coordinate(positionX_val, positionY_val);
            switch (name) {
                case "X" -> {
                    Organism org = new Human(strength_val, age_val, initiative_val, this, pos, x, c);
                    this.human = org;
                    setOrganismOnArray(org, positionX_val, positionY_val);
                }
                case "A" -> setOrganismOnArray(new Antylopa(strength_val, age_val, initiative_val, this, pos), positionX_val, positionY_val);
                case "L" -> setOrganismOnArray(new Lis(strength_val, age_val, initiative_val, this, pos), positionX_val, positionY_val);
                case "W" -> setOrganismOnArray(new Wilk(strength_val, age_val, initiative_val, this, pos), positionX_val, positionY_val);
                case "Z" -> setOrganismOnArray(new Zolw(strength_val, age_val, initiative_val, this, pos), positionX_val, positionY_val);
                case "O" -> setOrganismOnArray(new Owca(strength_val, age_val, initiative_val, this, pos), positionX_val, positionY_val);
                case "T" -> setOrganismOnArray(new Trawa(strength_val, age_val, initiative_val, this, pos), positionX_val, positionY_val);
                case "M" -> setOrganismOnArray(new Mlecz(strength_val, age_val, initiative_val, this, pos), positionX_val, positionY_val);
                case "G" -> setOrganismOnArray(new Guarana(strength_val, age_val, initiative_val, this, pos), positionX_val, positionY_val);
                case "B" -> setOrganismOnArray(new BarszczSosnowskiego(strength_val, age_val, initiative_val, this, pos), positionX_val, positionY_val);
                case "J" -> setOrganismOnArray(new WilczeJagody(strength_val, age_val, initiative_val, this, pos), positionX_val, positionY_val);
            }
        }
        worldSave.close();
    }

    public void saveWorld(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));
            String message = "Saving world to file: " + fileName;
            getAppGUI().returnInformationContainer().addMessage(message);

            writer.println(roundCounter);
            boolean x = human.GetSpecialAbility();
            int ability;
            if (x) {
                ability = 1;
            } else {
                ability = 0;
            }
            writer.println(ability);
            writer.println(human.getCounter());

            for (int i = 1; i <= 20; i++) {
                for (int j = 1; j <= 20; j++) {
                    if (getOrganismFromArray(i, j) != null) {
                        Organism org = getOrganismFromArray(i, j);
                        writer.println(org.GetName() + " " + org.getForce() + " " + org.getInitiative() + " " + org.getPosition().getX() + " " + org.getPosition().getY() + " " + org.getAge());
                    }
                }
            }

            message = "Gra zostala pomyslnie zapisana do pliku : " + fileName;
            getAppGUI().returnInformationContainer().addMessage(message);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}



