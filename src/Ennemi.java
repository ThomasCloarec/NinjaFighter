import javax.swing.JPanel;
import java.awt.Color;

/**
 * The type Ennemi.
 */
public class Ennemi extends Rectangle {
    private static final float RANDOM_HELP = 0.002f;
    private static final int DEFAULT_MAX_SPEED = 6;
    private static final int RESTART_DURATION = 50;
    private static final int SAVING_DURATION = 2000;
    /**
     * The constant points.
     */
    public static int points;

    /**
     * Instantiates a new Ennemi.
     *
     * @param panneau  the panel where the ennemy will be display
     * @param largeur  the width of the ennemy
     * @param hauteur  the height of the ennemy
     * @param x        the x position of the ennemy
     * @param y        the y position of the ennemy
     * @param vitesseX the x speed of the ennemy to make it move
     * @param vitesseY the y speed of the ennemy to make it move
     */
    public Ennemi(Panneau panneau, int largeur, int hauteur, int x, int y, int vitesseX, int vitesseY) {
        super(panneau, Color.RED, largeur, hauteur, x, y, vitesseX, vitesseY);
    }

    /**
     * Generate enemies.
     *
     * @param panneau the panel where the ennemy will be display
     * @param count   the number of ennemies to generate
     */
    public static void generateEnemies(Panneau panneau, int count) {
        JPanel mainPanel = panneau.getMainPanel();

        for (int i = 0; i < count; i++) {
            new Rectangle.Builder(Type.ENNEMI, panneau)
                    .setX((int) (Math.random() * mainPanel.getWidth()))
                    .setY((int) (Math.random() * mainPanel.getHeight()))
                    .setVitesseX((int) (Math.random() * Ennemi.DEFAULT_MAX_SPEED + 1))
                    .setVitesseY((int) (Math.random() * Ennemi.DEFAULT_MAX_SPEED + 1))
                    .start();
        }
    }

    /**
     * Increment points.
     */
    public static void incrementPoints() {
        Ennemi.points++;
    }

    @Override
    public void checkAndUpdateSpeed(Panneau panneau) {
        this.couleur = Color.RED;
        if (Math.random() < Ennemi.RANDOM_HELP) {
            try {
                this.couleur = Color.ORANGE;
                Thread.sleep(Ennemi.SAVING_DURATION);
                panneau.saveOne();
                Thread.sleep(Ennemi.RESTART_DURATION);
                this.couleur = Color.RED;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (panneau.overlaps(false, this, Type.JOUEUR)) {
            this.couleur = Color.BLACK;
        }
        panneau.overlaps(true, this, Type.JOUEUR);
        this.updateSpeed(panneau);

        if (panneau.overlaps(false, this, Type.BASE)) {
            Ennemi.incrementPoints();
        }
    }
}
