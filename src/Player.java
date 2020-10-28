import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * The type Player.
 */
public class Player extends Rectangle {
    private static final String RELEASE_LEFT = "Release.left", RELEASE_RIGHT = "Release.right", PRESS_LEFT = "Press.left", PRESS_RIGHT = "Press.right",
            RELEASE_UP = "Release.up", RELEASE_DOWN = "Release.down", PRESS_UP = "Press.up", PRESS_DOWN = "Press.down";
    private boolean moveLeft, moveRight, moveUp, moveDown;

    /**
     * Instantiates a new Player.
     *
     * @param board  the board where the player will be display
     * @param width  the width of the player
     * @param height  the height of the player
     * @param x        the x position of the player
     * @param y        the y position of the player
     * @param XSpeed the x speed of the player
     * @param YSpeed the y speed of the player
     */
    public Player(Board board, int width, int height, int x, int y, int XSpeed, int YSpeed) {
        super(board, Color.BLUE, width, height, x, y, XSpeed, YSpeed);

        InputMap IM = board.getMainPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap AM = board.getMainPanel().getActionMap();

        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, true), Player.RELEASE_LEFT);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, true), Player.RELEASE_LEFT);

        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), Player.RELEASE_RIGHT);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, true), Player.RELEASE_RIGHT);

        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), Player.RELEASE_DOWN);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, true), Player.RELEASE_DOWN);

        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0, true), Player.RELEASE_UP);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, true), Player.RELEASE_UP);

        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), Player.PRESS_RIGHT);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), Player.PRESS_RIGHT);

        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, false), Player.PRESS_LEFT);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), Player.PRESS_LEFT);

        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), Player.PRESS_DOWN);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), Player.PRESS_DOWN);

        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, 0, false), Player.PRESS_UP);
        IM.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), Player.PRESS_UP);

        AM.put(Player.RELEASE_LEFT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player.this.moveLeft = false;
            }
        });

        AM.put(Player.RELEASE_RIGHT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player.this.moveRight = false;
            }
        });

        AM.put(Player.RELEASE_DOWN, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player.this.moveDown = false;
            }
        });

        AM.put(Player.RELEASE_UP, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player.this.moveUp = false;
            }
        });

        AM.put(Player.PRESS_LEFT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player.this.moveLeft = true;
            }
        });

        AM.put(Player.PRESS_RIGHT, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player.this.moveRight = true;
            }
        });

        AM.put(Player.PRESS_DOWN, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player.this.moveDown = true;
            }
        });

        AM.put(Player.PRESS_UP, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Player.this.moveUp = true;
            }
        });
    }

    @Override
    public void checkAndUpdateSpeed(Board board) {
        int lastX = this.x, lastY = this.y;

        if (this.moveLeft) {
            this.x -= this.XSpeed;
        }

        if (this.moveRight) {
            this.x += this.XSpeed;
        }

        if (this.moveDown) {
            this.y += this.YSpeed;
        }

        if (this.moveUp) {
            this.y -= this.YSpeed;
        }

        if (!this.isInWindow()) {
            this.x = lastX;
            this.y = lastY;
        }
    }
}
