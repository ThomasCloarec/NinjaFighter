import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Toolkit;

/**
 * The type Main.
 */
public class Main {
    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final boolean IS_UNIX_OS = Main.OS.contains("nix") || Main.OS.contains("nux") || Main.OS.contains("aix");

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Panneau panneau = new Panneau(500, 500);

        Rectangle joueur = new Rectangle.Builder(Rectangle.Type.JOUEUR, panneau).
                setX(235).
                setY(150).
                setVitesseX(5).
                setVitesseY(5).
                start();

        Ennemi.generateEnemies(panneau, 1000);

        Rectangle base = new Rectangle.Builder(Rectangle.Type.BASE, panneau).
                setX(210).
                setY(210).
                setHauteur(80).
                setLargeur(80).
                setVitesseX(0).
                setVitesseY(0).
                start();

        new Audio("/data/sounds/ninja.wav").play();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(30);
                    panneau.refreshFrame();
                    if (Main.IS_UNIX_OS)
                        Toolkit.getDefaultToolkit().sync();

                    boolean finish = true;

                    int i = 0;
                    while (finish && i < panneau.rectangleList.size()) {
                        if (panneau.rectangleList.get(i).type == Rectangle.Type.ENNEMI) {
                            finish = panneau.rectangleList.get(i).couleur == Color.BLACK;
                        }
                        i++;
                    }

                    if (finish) {
                        JOptionPane.showMessageDialog(panneau.getFrame(), "Félicitations, mais vos ennemis ont atteint " + Ennemi.points + " !");
                        System.exit(0);
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();
    }
}
