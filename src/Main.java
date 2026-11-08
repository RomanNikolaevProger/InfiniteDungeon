import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        GameProcessor GP = new GameProcessor();
        GameWindow GW = new GameWindow(GP);
        while (true) {
            GP.tick();
            GW.repaint();
            Thread.sleep(16);
        }
    }
}