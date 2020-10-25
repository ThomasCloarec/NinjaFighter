import java.awt.Color;

/**
 * The type Base.
 */
public class Base extends Rectangle {
    /**
     * Instantiates a new Base.
     *
     * @param panneau  the panneau
     * @param largeur  the largeur
     * @param hauteur  the hauteur
     * @param x        the x
     * @param y        the y
     * @param vitesseX the vitesse x
     * @param vitesseY the vitesse y
     */
    public Base(Panneau panneau, int largeur, int hauteur, int x, int y, int vitesseX, int vitesseY) {
        super(panneau, Color.GREEN, largeur, hauteur, x, y, vitesseX, vitesseY);
    }

    @Override
    public void checkAndUpdateSpeed(Panneau panneau) {
    }
}
