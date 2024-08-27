//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        (new Controller()).startProject();
        Server server = new Server();
        new Client(server);
        new Client(server);
    }
}
