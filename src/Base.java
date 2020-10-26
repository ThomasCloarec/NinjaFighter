import java.awt.Color;

/**
 * The type Base.
 */
public class Base extends Rectangle {
    /**
     * Instantiates a new Base.
     *
     * @param panneau  the panel where the base will be display
     * @param largeur  the width of the base
     * @param hauteur  the height of the base
     * @param x        the x position of the base
     * @param y        the y position of the base
     * @param vitesseX the x speed of the base to make it move (always 0)
     * @param vitesseY the y speed of the base to make it move (always 0)
     */
    public Base(Panneau panneau, int largeur, int hauteur, int x, int y, int vitesseX, int vitesseY) {
        super(panneau, Color.GREEN, largeur, hauteur, x, y, vitesseX, vitesseY);
    }

    @Override
    public void checkAndUpdateSpeed(Panneau panneau) {
    }
}
