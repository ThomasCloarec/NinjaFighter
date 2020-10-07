import java.awt.Color;

public class Base extends Rectangle {
    public static final Color couleur = Color.GREEN;

    public Base(Panneau panneau, int largeur, int hauteur, int x, int y, int vitesseX, int vitesseY) {
        super(panneau, Base.couleur, largeur, hauteur, x, y, vitesseX, vitesseY);
    }

    @Override
    public void checkAndUpdateSpeed(Panneau panneau) {
    }
}
