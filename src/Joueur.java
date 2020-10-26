import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * The type Joueur.
 */
public class Joueur extends Rectangle {
    private static final String RELEASE_LEFT = "Release.left", RELEASE_RIGHT = "Release.right", PRESS_LEFT = "Press.left", PRESS_RIGHT = "Press.right",
            RELEASE_UP = "Release.up", RELEASE_DOWN = "Release.down", PRESS_UP = "Press.up", PRESS_DOWN = "Press.down";
    private boolean moveLeft, moveRight, moveUp, moveDown;

    /**
     * Instantiates a new Joueur.
     *
     * @param panneau  the panel where the player will be display
     * @param largeur  the width of the player
     * @param hauteur  the height of the player
     * @param x        the x position of the player
     * @param y        the y position of the player
     * @param vitesseX the x speed of the player
     * @param vitesseY the y speed of the player
     */
    public Joueur(Panneau panneau, int largeur, int hauteur, int x, int y, int vitesseX, int vitesseY) {
        super(panneau, Color.BLUE, largeur, hauteur, x, y, vitesseX, vitesseY);

        InputMap IM = panneau.getMainPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap AM = panneau.getMainPanel().getActionMap();

        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, true), Joueur.RELEASE_LEFT);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), Joueur.RELEASE_LEFT);

        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), Joueur.RELEASE_RIGHT);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), Joueur.RELEASE_RIGHT);

        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), Joueur.RELEASE_DOWN);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), Joueur.RELEASE_DOWN);

        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0, true), Joueur.RELEASE_UP);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), Joueur.RELEASE_UP);

        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), Joueur.PRESS_RIGHT);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), Joueur.PRESS_RIGHT);

        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, false), Joueur.PRESS_LEFT);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), Joueur.PRESS_LEFT);

        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), Joueur.PRESS_DOWN);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), Joueur.PRESS_DOWN);

        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0, false), Joueur.PRESS_UP);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), Joueur.PRESS_UP);

        AM.put(Joueur.RELEASE_LEFT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Joueur.this.moveLeft = false;
            }
        });

        AM.put(Joueur.RELEASE_RIGHT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Joueur.this.moveRight = false;
            }
        });

        AM.put(Joueur.RELEASE_DOWN, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Joueur.this.moveDown = false;
            }
        });

        AM.put(Joueur.RELEASE_UP, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Joueur.this.moveUp = false;
            }
        });

        AM.put(Joueur.PRESS_LEFT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Joueur.this.moveLeft = true;
            }
        });

        AM.put(Joueur.PRESS_RIGHT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Joueur.this.moveRight = true;
            }
        });

        AM.put(Joueur.PRESS_DOWN, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Joueur.this.moveDown = true;
            }
        });

        AM.put(Joueur.PRESS_UP, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Joueur.this.moveUp = true;
            }
        });
    }

    @Override
    public void checkAndUpdateSpeed(Panneau panneau) {
        int lastX = this.x, lastY = this.y;

        if (this.moveLeft) {
            this.x -= this.vitesseX;
        }

        if (this.moveRight) {
            this.x += this.vitesseX;
        }

        if (this.moveDown) {
            this.y += this.vitesseY;
        }

        if (this.moveUp) {
            this.y -= this.vitesseY;
        }

        if (!this.isInWindow()) {
            this.x = lastX;
            this.y = lastY;
        }
    }
}
