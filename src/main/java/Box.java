//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Box extends JPanel {
    Logica logica;
    char[][] field;
    private static int cell_width;
    private static int cell_height;

    public Box(Logica logica) {
        this.logica = logica;
        this.field = logica.getField();
        this.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                Box.this.userClickMethod(e);
            }
        });
    }

    public void userClickMethod(MouseEvent e) {
        this.logica.userClickMethod(e.getY(), e.getX());
        this.repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.printBox(g);
        this.printGamers(g);
        if (this.logica.userIsWinner()) {
            this.printUserWin(g);
        }

        if (this.logica.computerIsWinner()) {
            this.printComputerWin(g);
        }

    }

    public void printUserWin(Graphics g) {
        try {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(25, 150, 600, 300);
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(3);
            frame.setLocation(525, 180);
            frame.setTitle("User Win");
            frame.setResizable(false);
            frame.setSize(620, 400);
            frame.setVisible(true);
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon("src/main/resources/picture/youwin.jpg"));
            frame.add(label);
        } catch (Exception var4) {
        }

    }

    public void printComputerWin(Graphics g) {
        try {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(25, 150, 600, 300);
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(3);
            frame.setLocation(562, 220);
            frame.setTitle("Computer Win");
            frame.setResizable(false);
            frame.setSize(540, 270);
            frame.setVisible(true);
            JLabel label = new JLabel();
            label.setIcon(new ImageIcon("src/main/resources/picture/you_lose.png"));
            frame.add(label);
        } catch (Exception var4) {
        }

    }

    public void printBox(Graphics g) {
        cell_width = this.getWidth() / 8;
        cell_height = this.getHeight() / 8;
        g.setColor(Color.BLACK);

        int i;
        int j;
        for(i = 0; i < 8; ++i) {
            j = i * cell_width;
            g.drawLine(0, j, this.getWidth(), j);
        }

        for(i = 0; i < 8; ++i) {
            j = i * cell_height;
            g.drawLine(j, 0, j, this.getHeight());
        }

        for(i = 0; i < 8; ++i) {
            for(j = 0; j < 8; ++j) {
                g.setColor(Color.GRAY);
                if (i % 2 == 0 && j % 2 != 0 || i % 2 != 0 && j % 2 == 0) {
                    g.fillRect(i * cell_width, j * cell_height, cell_width, cell_height);
                }
            }
        }

        for(i = 0; i < 8; ++i) {
            for(j = 0; j < 8; ++j) {
                g.setColor(Color.GREEN);
                if (this.logica.getField()[j][i] == '3') {
                    g.fillRect(i * cell_width, j * cell_height, cell_width, cell_height);
                }
            }
        }

        for(i = 0; i < 8; ++i) {
            for(j = 0; j < 8; ++j) {
                g.setColor(Color.BLUE);
                if (this.logica.getField()[j][i] == '6') {
                    g.fillRect(i * cell_width, j * cell_height, cell_width, cell_height);
                }
            }
        }

        for(i = 0; i < 8; ++i) {
            for(j = 0; j < 8; ++j) {
                g.setColor(Color.ORANGE);
                if (this.logica.getField()[j][i] == '9') {
                    g.fillRect(i * cell_width, j * cell_height, cell_width, cell_height);
                }
            }
        }

    }

    public void printGamers(Graphics g) {
        this.field = this.logica.getField();

        for(int i = 0; i < this.field.length; ++i) {
            for(int j = 0; j < this.field[0].length; ++j) {
                if (this.field[i][j] == '1') {
                    g.setColor(Color.DARK_GRAY);
                    g.fillOval(j * cell_width + 2, i * cell_height + 2, cell_width - 4, cell_height - 4);
                } else if (this.field[i][j] != '2' && this.field[i][j] != '3') {
                    if (this.field[i][j] == '4') {
                        g.setColor(Color.DARK_GRAY);
                        g.fillOval(j * cell_width + 2, i * cell_height + 2, cell_width - 4, cell_height - 4);
                        g.setColor(Color.WHITE);
                        g.fillOval(j * cell_width + 17, i * cell_height + 17, cell_width - 33, cell_height - 33);
                    } else if (this.field[i][j] == '5' || this.field[i][j] == '6') {
                        g.setColor(Color.RED);
                        g.fillOval(j * cell_width + 2, i * cell_height + 2, cell_width - 4, cell_height - 4);
                        g.setColor(Color.WHITE);
                        g.fillOval(j * cell_width + 17, i * cell_height + 17, cell_width - 33, cell_height - 33);
                    }
                } else {
                    g.setColor(Color.RED);
                    g.fillOval(j * cell_width + 2, i * cell_height + 2, cell_width - 4, cell_height - 4);
                }
            }
        }

    }
}
