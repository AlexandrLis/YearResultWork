//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Graphica extends JFrame {
    Logica logica;
    private static final int GRAPHICA_LOCATION_X = 500;
    private static final int GRAPHICA_LOCATION_Y = 20;
    private static final int GRAPHICA_WIDTH = 664;
    private static final int GRAPHICA_HEIGHT = 713;
    Box box;
    JButton btnStart = new JButton("Start Game");
    JButton btnExit = new JButton("Exit");

    public Graphica(Logica logica) {
        this.logica = logica;
        this.setDefaultCloseOperation(3);
        this.setLocation(500, 20);
        this.setSize(664, 713);
        this.setTitle("Shaski");
        this.setResizable(false);
        this.setVisible(true);
        JPanel buttons = new JPanel(new GridLayout(1, 2));
        buttons.add(this.btnStart);
        buttons.add(this.btnExit);
        this.add(buttons, "South");
        this.box = new Box(logica);
        this.add(this.box);
        this.box.setVisible(false);
        this.btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Graphica.this.box.setVisible(true);
            }
        });
        this.btnExit.addActionListener(new ActionListener(this) {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
