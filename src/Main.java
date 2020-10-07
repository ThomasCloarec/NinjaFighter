import javax.sound.sampled.*;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

public class Main {
    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final boolean IS_UNIX_OS = Main.OS.contains("nix") || Main.OS.contains("nux") || Main.OS.contains("aix");

    public static void main(String[] args) {

        Panneau panneau = new Panneau(500, 500);

        Rectangle joueur = new Rectangle.Builder(Rectangle.Type.JOUEUR, panneau).
                setX(235).
                setY(150).
                setVitesseX(3).
                setVitesseY(3).
                build();

        Rectangle rectangle1 = new Rectangle.Builder(Rectangle.Type.ENNEMI, panneau).
                setX(30).
                setY(30).
                build();

        Rectangle rectangle2 = new Rectangle.Builder(Rectangle.Type.ENNEMI, panneau).
                setX(430).
                setY(30).
                build();

        Rectangle rectangle3 = new Rectangle.Builder(Rectangle.Type.ENNEMI, panneau).
                setX(30).
                setY(430).
                build();

        Rectangle rectangle4 = new Rectangle.Builder(Rectangle.Type.ENNEMI, panneau).
                setX(430).
                setY(430).
                build();

        Rectangle rectangle5 = new Rectangle.Builder(Rectangle.Type.ENNEMI, panneau).
                setX(100).
                setY(100).
                build();

        Rectangle rectangle6 = new Rectangle.Builder(Rectangle.Type.ENNEMI, panneau).
                setX(360).
                setY(100).
                build();

        Rectangle rectangle7 = new Rectangle.Builder(Rectangle.Type.ENNEMI, panneau).
                setX(100).
                setY(360).
                build();

        Rectangle rectangle8 = new Rectangle.Builder(Rectangle.Type.ENNEMI, panneau).
                setX(360).
                setY(360).
                build();

        Rectangle rectangle9 = new Rectangle.Builder(Rectangle.Type.ENNEMI, panneau).
                setX(225).
                setY(50).
                build();

        Rectangle rectangle10 = new Rectangle.Builder(Rectangle.Type.ENNEMI, panneau).
                setX(225).
                setY(420).
                build();

        Rectangle rectangle11 = new Rectangle.Builder(Rectangle.Type.ENNEMI, panneau).
                setX(50).
                setY(225).
                build();

        Rectangle rectangle12 = new Rectangle.Builder(Rectangle.Type.ENNEMI, panneau).
                setX(420).
                setY(225).
                build();

        Rectangle base = new Rectangle.Builder(Rectangle.Type.BASE, panneau).
                setX(210).
                setY(210).
                setHauteur(80).
                setLargeur(80).
                setVitesseX(0).
                setVitesseY(0).
                build();

        new Activity(joueur, panneau).start();
        new Activity(rectangle1, panneau).start();
        new Activity(rectangle2, panneau).start();
        new Activity(rectangle3, panneau).start();
        new Activity(rectangle4, panneau).start();
        new Activity(rectangle5, panneau).start();
        new Activity(rectangle6, panneau).start();
        new Activity(rectangle7, panneau).start();
        new Activity(rectangle8, panneau).start();
        new Activity(rectangle9, panneau).start();
        new Activity(rectangle10, panneau).start();
        new Activity(rectangle11, panneau).start();
        new Activity(rectangle12, panneau).start();
        new Activity(base, panneau).start();

        // Une belle musique libre de droit... car faite maison !

        Audio music = new Audio("/data/sounds/ninja.wav");
        music.play();

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
                        JOptionPane.showMessageDialog(panneau.getFrame(), "Félicitations, mais vos ennemis ont atteint " + Ennemi.points + " ! :O");
                        // Codé en 1h30, code le plus sale de ma vie, mais ça fonctionne... Lorsqu'on est un développeur, il faut savoir bien coder et parfois savoir foncer. Ici, je crois que vous savez ce que j'ai dû faire.
                        System.exit(0);
                    }
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }).start();
    }
}
