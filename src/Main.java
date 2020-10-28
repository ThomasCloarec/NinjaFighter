import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Toolkit;

public class Main {
    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final boolean IS_UNIX_OS = Main.OS.contains("nix") || Main.OS.contains("nux") || Main.OS.contains("aix");

    /**
     * The entry point of application.
     *
     * @param args the input arguments from the command line
     */
    public static void main(String[] args) {
        Board board = new Board(500, 500);

        new Rectangle.Builder(Rectangle.Type.JOUEUR, board).
                setX(235).
                setY(150).
                setXSpeed(5).
                setYSpeed(5).
                start();

        Enemy.generateEnemies(board, 15);

        new Rectangle.Builder(Rectangle.Type.BASE, board).
                setX(210).
                setY(210).
                setHeight(80).
                setWidth(80).
                setXSpeed(0).
                setYSpeed(0).
                start();

        new Audio("/resources/sounds/ninja.wav").play();

        boolean running = true;
        while (running) {
            try {
                Thread.sleep(30);
                board.refreshFrame();
                if (Main.IS_UNIX_OS)
                    Toolkit.getDefaultToolkit().sync();

                boolean done = true;

                int i = 0;
                while (done && i < board.rectangleList.size()) {
                    if (board.rectangleList.get(i).type == Rectangle.Type.ENNEMI) {
                        done = board.rectangleList.get(i).color == Color.BLACK;
                    }
                    i++;
                }

                if (done) {
                    JOptionPane.showMessageDialog(board.getFrame(), "Félicitations, mais vos ennemis ont atteint " + Enemy.points + " !");
                    running = false;
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        System.exit(0);
    }
}
