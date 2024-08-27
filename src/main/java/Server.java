//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Server extends JFrame {
    private final int LOCATION_X = 50;
    private final int LOCATION_Y = 20;
    private final int WIDTH = 300;
    private final int HEIGHT = 300;
    private boolean isWorking = false;
    private static final String REGISTRATION_PHRASE = "Registration new user: ";
    JButton start = new JButton("Start");
    JButton stop = new JButton("Stop");
    JButton clear = new JButton("Clear");
    JTextArea areaMessage;
    List<Client> listClient = new ArrayList();

    public Server() {
        this.setLocation(50, 20);
        this.setSize(300, 300);
        this.setTitle("Server");
        this.areaMessage = new JTextArea();
        this.add(this.areaMessage, "West");
        JPanel panel = new JPanel(new GridLayout(1, 3));
        panel.add(this.start);
        panel.add(this.stop);
        panel.add(this.clear);
        this.add(panel, "South");
        this.start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Server.this.isWorking = true;
                Server.this.printTextStartStop();
            }
        });
        this.stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Server.this.isWorking = false;
                Server.this.printTextStartStop();
            }
        });
        this.clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Server.this.areaMessage.setText("");
                Iterator var2 = Server.this.listClient.iterator();

                while(var2.hasNext()) {
                    Client client = (Client)var2.next();
                    client.areaMessage.setText("");
                }

            }
        });
        this.setVisible(true);
    }

    public boolean startMessage() {
        return this.isWorking;
    }

    public void printTextStartStop() {
        Iterator var1;
        Client client;
        if (this.isWorking) {
            this.areaMessage.append("Server Working\n");
            var1 = this.listClient.iterator();

            while(var1.hasNext()) {
                client = (Client)var1.next();
                client.areaMessage.append("Server Working\n");
            }
        } else {
            this.areaMessage.append("Server Stopped\n");
            var1 = this.listClient.iterator();

            while(var1.hasNext()) {
                client = (Client)var1.next();
                client.areaMessage.append("Server Stopped\n");
            }
        }

    }

    public void yourMessage(String text, String name) {
        this.areaMessage.append(name + ": " + text + "\n");
        Iterator var3 = this.listClient.iterator();

        while(var3.hasNext()) {
            Client client = (Client)var3.next();
            client.areaMessage.append(name + ": " + text + "\n");
        }

    }

    public void yourRegistration(String name) {
        this.areaMessage.append("Registration new user: " + name + "\n");
        Iterator var2 = this.listClient.iterator();

        while(var2.hasNext()) {
            Client client = (Client)var2.next();
            client.areaMessage.append("Registration new user: " + name + "\n");
        }

    }

    public boolean getIsWorking() {
        return this.isWorking;
    }

    public void addClientList(Client client) {
        this.listClient.add(client);
    }
}
