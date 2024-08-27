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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame {
    private final int LOCATION_X = 50;
    private final int LOCATION_Y = 350;
    private final int WIDTH = 300;
    private final int HEIGHT = 300;
    private String Name;
    JButton login = new JButton("Login");
    JButton send = new JButton("Send");
    JTextField userName;
    JTextField lineMessage;
    JTextArea areaMessage;

    public Client(final Server server) {
        this.setLocation(50, 350);
        this.setSize(300, 300);
        this.setTitle("Client");
        this.userName = new JTextField();
        this.lineMessage = new JTextField();
        this.areaMessage = new JTextArea();
        this.addClient(server);
        final JPanel panelLogin = new JPanel(new GridLayout(1, 2));
        panelLogin.add(this.userName);
        panelLogin.add(this.login);
        this.add(panelLogin, "North");
        JPanel panelSendMessage = new JPanel(new GridLayout(1, 2));
        panelSendMessage.add(this.lineMessage);
        panelSendMessage.add(this.send);
        this.add(panelSendMessage, "South");
        this.add(this.areaMessage, "West");
        this.login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (server.getIsWorking()) {
                    Client.this.setName(Client.this.userName.getText());
                    panelLogin.setVisible(false);
                    Client.this.getRegistration(server, Client.this.Name);
                }

            }
        });
        this.send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Client.this.lineMessage.getText().length() != 0 && server.startMessage() && !Client.this.Name.equals((Object)null)) {
                    Client.this.getMessage(server, Client.this.Name);
                }

            }
        });
        this.setVisible(true);
    }

    public void getRegistration(Server server, String name) {
        server.yourRegistration(this.Name);
    }

    public void getMessage(Server server, String name) {
        server.yourMessage(this.lineMessage.getText(), this.Name);
    }

    public void addClient(Server server) {
        server.addClientList(this);
    }

    public void setName(String textName) {
        this.Name = textName;
    }

    public String getName() {
        return this.Name;
    }
}
