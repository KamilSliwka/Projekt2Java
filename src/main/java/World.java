
import javax.swing.*;
import java.awt.*;
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
        //this.frame = new JFrame();
        this.game = true;

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                board[i][j] = new AppGUI.boardField();
            }
        }


//        for(int i=0;i<20;i++){
//            for(int j=0;j<20;j++){
//                array[i][j]=null;
//            }
//        }
        // random organism
        //coordinate tmp =null;
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

        //appGUI.getBoardContainer().refreshBoard();
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
                //setFieldOnBoard(org.draw(), newPosition.getX(), newPosition.getY());
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

        // Sort organisms by initiative
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
//        System.out.println("Loading world from file: " + fileName);
//        String message = "Loading world from file: " + fileName;
//        getAppGUI().returnInformationContainer().addMessage(message);

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
        //this.human.setSpecialAbility(x);
        int c = worldSave.nextInt();

        array = new Organism[20][20];
//        for (int i = 0; i < 20; i++) {
//            for (int j = 0; j < 20; j++) {
//                setOrganismOnArray(null, i, j);
//            }
//        }

        //create and fill Board vector
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
                case "X" -> setOrganismOnArray(new Human(strength_val, age_val, initiative_val, this, pos, x, c), positionX_val, positionY_val);
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

            //writer.println(height);
            // writer.println(width);
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

            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    if (getOrganismFromArray(i, j) != null) {
                        Organism org = getOrganismFromArray(i, j);
                        writer.println(org.GetName() + " " + org.getForce() + " " + org.getInitiative() + " " + org.getPosition().getX() + " " + org.getPosition().getY() + " " + org.getAge());
                    }
                }
            }

            message = "Game has been successfully saved to file: " + fileName;
            getAppGUI().returnInformationContainer().addMessage(message);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    ///

    public void Game() {
        //PrintBoard();
        while (isGame()) {
            setRoundCounter(getRoundCounter() + 1);
            PrintBoard();
            Round(1);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Wciśnij Enter, aby kontynuować...");
            scanner.nextLine(); // odczytuje całą linijkę wprowadzoną przez użytkownika (włącznie z Enterem)
            System.out.println("Dalsza część programu...");
            //ClearBoard();

        }

    }

    public void PrintArray() {
        JPanel panel5 = new JPanel();
        panel5.setBackground(Color.lightGray);

        panel5.setLayout(new BorderLayout());
        panel5.setPreferredSize(new Dimension(405, 405));

        panel5.setLayout(null);
        Organism org = null;
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 20; i++) {
                int finalI = i;
                JPanel SquarePanel = new JPanel();
                org = getOrganismFromArray(j + 1, i + 1);
                boolean visited = false;
                if (org instanceof Human) {
                    SquarePanel.setBackground(Color.RED);
                    visited = true;
                } else if (org instanceof Trawa) {
                    SquarePanel.setBackground(Color.green);
                    visited = true;
                } else if (org instanceof Mlecz) {
                    SquarePanel.setBackground(Color.white);
                    visited = true;
                } else if (org instanceof Guarana) {
                    SquarePanel.setBackground(Color.orange);
                    visited = true;
                } else if (org instanceof BarszczSosnowskiego) {
                    SquarePanel.setBackground(Color.yellow);
                    visited = true;
                } else if (org instanceof WilczeJagody) {
                    SquarePanel.setBackground(Color.black);
                    visited = true;
                } else if (org instanceof Wilk) {
                    SquarePanel.setBackground(Color.gray);
                    visited = true;
                } else if (org instanceof Lis) {
                    SquarePanel.setBackground(new Color(255, 88, 27));
                    visited = true;
                } else if (org instanceof Owca) {
                    SquarePanel.setBackground(new Color(139, 255, 240));
                    visited = true;
                } else if (org instanceof Antylopa) {
                    SquarePanel.setBackground(new Color(158, 87, 57));
                    visited = true;
                } else if (org instanceof Zolw) {
                    SquarePanel.setBackground(new Color(108, 152, 108));
                    visited = true;
                }


                if (visited) {
                    SquarePanel.setBounds(5 + i * 20, 5 + j * 20, 15, 15); //ustawienie pozycji i wymiarów kwadratu
                    panel5.add(SquarePanel);
                }

            }
        }
        //frame.add(panel5, BorderLayout.CENTER);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Wciśnij Enter, aby kontynuować...");
        scanner.nextLine(); // odczytuje całą linijkę wprowadzoną przez użytkownika (włącznie z Enterem)
        System.out.println("Dalsza część programu...");

        JPanel SquarePanel = new JPanel();
        SquarePanel.setBounds(0, 0, 405, 405); //ustawienie pozycji i wymiarów kwadratu
        panel5.add(SquarePanel);

    }


    public void PrintBoard() {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 650);
        frame.setLayout(new BorderLayout(10, 10));
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setTitle("Gra w życie");

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        panel1.setBackground(Color.darkGray);
        panel2.setBackground(Color.darkGray);
        panel3.setBackground(Color.darkGray);
        panel4.setBackground(Color.darkGray);
        panel5.setBackground(Color.lightGray);

        panel5.setLayout(new BorderLayout());

        panel1.setPreferredSize(new Dimension(0, 100));
        panel2.setPreferredSize(new Dimension(50, 100));
        panel3.setPreferredSize(new Dimension(50, 100));
        panel4.setPreferredSize(new Dimension(0, 50));
        panel5.setPreferredSize(new Dimension(405, 405));

        panel5.setLayout(null);
        Organism org = null;
        for (int j = 0; j < 20; j++) {
            for (int i = 0; i < 20; i++) {
                int finalI = i;
                JPanel SquarePanel = new JPanel();
                org = getOrganismFromArray(j + 1, i + 1);
                boolean visited = false;
                if (org instanceof Human) {
                    SquarePanel.setBackground(Color.RED);
                    visited = true;
                } else if (org instanceof Trawa) {
                    SquarePanel.setBackground(Color.green);
                    visited = true;
                } else if (org instanceof Mlecz) {
                    SquarePanel.setBackground(Color.white);
                    visited = true;
                } else if (org instanceof Guarana) {
                    SquarePanel.setBackground(Color.orange);
                    visited = true;
                } else if (org instanceof BarszczSosnowskiego) {
                    SquarePanel.setBackground(Color.yellow);
                    visited = true;
                } else if (org instanceof WilczeJagody) {
                    SquarePanel.setBackground(Color.black);
                    visited = true;
                } else if (org instanceof Wilk) {
                    SquarePanel.setBackground(Color.gray);
                    visited = true;
                } else if (org instanceof Lis) {
                    SquarePanel.setBackground(new Color(255, 88, 27));
                    visited = true;
                } else if (org instanceof Owca) {
                    SquarePanel.setBackground(new Color(139, 255, 240));
                    visited = true;
                } else if (org instanceof Antylopa) {
                    SquarePanel.setBackground(new Color(158, 87, 57));
                    visited = true;
                } else if (org instanceof Zolw) {
                    SquarePanel.setBackground(new Color(108, 152, 108));
                    visited = true;
                }


                if (visited) {
                    SquarePanel.setBounds(5 + i * 20, 5 + j * 20, 15, 15); //ustawienie pozycji i wymiarów kwadratu
                    panel5.add(SquarePanel);
                }

            }
        }


        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.WEST);
        frame.add(panel3, BorderLayout.EAST);
        frame.add(panel4, BorderLayout.SOUTH);
        frame.add(panel5, BorderLayout.CENTER);
        frame.pack();

    }

//    public void ClearBoard() {
//        frame.getContentPane().removeAll();  //clear
//        frame.getContentPane().revalidate();
//        frame.getContentPane().repaint();
//    }

}



