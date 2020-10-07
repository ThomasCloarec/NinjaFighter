import java.awt.Color;

public class Ennemi extends Rectangle {
    public static int points;
    private static final float randomHelp = 0.002f;

    public Ennemi(Panneau panneau, int largeur, int hauteur, int x, int y, int vitesseX, int vitesseY) {
        super(panneau, Color.RED, largeur, hauteur, x, y, vitesseX, vitesseY);
    }

    @Override
    public void checkAndUpdateSpeed(Panneau panneau) {
        this.couleur = Color.RED;
        if (Math.random() < Ennemi.randomHelp) {
            try {
                this.couleur = Color.ORANGE;
                Thread.sleep(2000);
                panneau.saveOne();
                this.couleur = Color.RED;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (panneau.overlaps(this, Type.JOUEUR)) {
            this.couleur = Color.BLACK;
        }
        panneau.overlapsAndWait(this, true, Type.JOUEUR);
        this.updateSpeed(panneau);

        if (panneau.overlaps(this, Type.BASE)) {
            Ennemi.points++;
            //System.out.println("Vos ennemis ont : " + Ennemi.points);
        }
    }
}
