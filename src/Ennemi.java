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
     * @param panneau  the panneau
     * @param largeur  the largeur
     * @param hauteur  the hauteur
     * @param x        the x
     * @param y        the y
     * @param vitesseX the vitesse x
     * @param vitesseY the vitesse y
     */
    public Ennemi(Panneau panneau, int largeur, int hauteur, int x, int y, int vitesseX, int vitesseY) {
        super(panneau, Color.RED, largeur, hauteur, x, y, vitesseX, vitesseY);
    }

    /**
     * Generate enemies.
     *
     * @param panneau the panneau
     * @param count   the count
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
