
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;



public class World {
    private int x;
    private int y;
    private int roundCounter;
    private boolean game;
    private Organism[][] array;
    //private JFrame frame;

    public World() {
        this.x = 20;
        this.y = 20;
        this.roundCounter = 1;
        this.array = new Organism[20][20];
        //this.frame = new JFrame();
        this.game = true;


//        for(int i=0;i<20;i++){
//            for(int j=0;j<20;j++){
//                array[i][j]=null;
//            }
//        }
        // random organism
        //coordinate tmp =null;
        RandomPlace(new Human(5, 1, 4, this, null, false, 5));
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

    public void Round() {
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
        while (!move.isEmpty()) {
            Organism org = move.lastElement();
            if (org.getAge() != -1) {
                org.Action();

            } else {
                setOrganismOnArray(null, org.getPosition().getX(), org.getPosition().getY());
            }
            move.remove(move.size() - 1);
            //delete


        }

    }

    public void Game() {
        //PrintBoard();
        while (isGame()) {
            setRoundCounter(getRoundCounter() + 1);
            PrintBoard();
            Round();
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



