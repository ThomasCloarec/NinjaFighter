import java.awt.Color;

/**
 * The type Ennemi.
 */
public class Ennemi extends Rectangle {
    private static final float RANDOM_HELP = 0.002f;
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
        for (int i = 0; i < count; i++) {
            new Rectangle.Builder(Type.ENNEMI, panneau).setX((int) (Math.random() * panneau.getMainPanel().getWidth())).setY((int) (Math.random() * panneau.getMainPanel().getHeight())).setVitesseX((int) (Math.random() * 6 + 1)).setVitesseY((int) (Math.random() * 6 + 1)).start();
        }
    }

    @Override
    public void checkAndUpdateSpeed(Panneau panneau) {
        this.couleur = Color.RED;
        if (Math.random() < Ennemi.RANDOM_HELP) {
            try {
                this.couleur = Color.ORANGE;
                Thread.sleep(2000);
                panneau.saveOne();
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
            Ennemi.points++;
        }
    }
}
