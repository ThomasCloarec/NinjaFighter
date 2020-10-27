import java.awt.Color;

/**
 * The type Base.
 */
public class Base extends Rectangle {
    /**
     * Instantiates a new Base.
     *
     * @param board  the panel where the base will be display
     * @param width  the width of the base
     * @param height  the height of the base
     * @param x        the x position of the base
     * @param y        the y position of the base
     * @param XSpeed the x speed of the base to make it move (always 0)
     * @param YSpeed the y speed of the base to make it move (always 0)
     */
    public Base(Board board, int width, int height, int x, int y, int XSpeed, int YSpeed) {
        super(board, Color.GREEN, width, height, x, y, XSpeed, YSpeed);
    }

    @Override
    public void checkAndUpdateSpeed(Board board) {
    }
}
