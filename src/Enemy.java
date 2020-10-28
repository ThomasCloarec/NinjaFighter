import javax.swing.JPanel;
import java.awt.Color;

/**
 * The type Enemy.
 */
public class Enemy extends Rectangle {
    private static final float RANDOM_HELP = 0.002f;
    private static final int DEFAULT_MAX_SPEED = 6;
    private static final int RESTART_DURATION = 50;
    private static final int SAVING_DURATION = 2000;
    /**
     * The constant points.
     */
    public static int points;

    /**
     * Instantiates a new Enemy.
     *
     * @param board  the panel where the ennemy will be display
     * @param width  the width of the ennemy
     * @param height the height of the ennemy
     * @param x      the x position of the ennemy
     * @param y      the y position of the ennemy
     * @param XSpeed the x speed of the ennemy to make it move
     * @param YSpeed the y speed of the ennemy to make it move
     */
    public Enemy(Board board, int width, int height, int x, int y, int XSpeed, int YSpeed) {
        super(board, Color.RED, width, height, x, y, XSpeed, YSpeed);
    }

    /**
     * Generate enemies.
     *
     * @param board the panel where the ennemy will be display
     * @param count   the number of ennemies to generate
     */
    public static void generateEnemies(Board board, int count) {
        JPanel mainPanel = board.getMainPanel();

        for (int i = 0; i < count; i++) {
            new Rectangle.Builder(Type.ENNEMI, board)
                    .setX((int) (Math.random() * mainPanel.getWidth()))
                    .setY((int) (Math.random() * mainPanel.getHeight()))
                    .setXSpeed((int) (Math.random() * Enemy.DEFAULT_MAX_SPEED + 1))
                    .setYSpeed((int) (Math.random() * Enemy.DEFAULT_MAX_SPEED + 1))
                    .start();
        }
    }

    /**
     * Increment points.
     */
    public static void incrementPoints() {
        Enemy.points++;
    }

    @Override
    public void checkAndUpdateSpeed(Board board) {
        this.color = Color.RED;
        if (Math.random() < Enemy.RANDOM_HELP) {
            try {
                this.color = Color.ORANGE;
                Thread.sleep(Enemy.SAVING_DURATION);
                board.saveOne();
                Thread.sleep(Enemy.RESTART_DURATION);
                this.color = Color.RED;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (board.overlaps(false, this, Type.JOUEUR)) {
            this.color = Color.BLACK;
        }
        board.overlaps(true, this, Type.JOUEUR);
        this.updateSpeed(board);

        if (board.overlaps(false, this, Type.BASE)) {
            Enemy.incrementPoints();
        }
    }
}
