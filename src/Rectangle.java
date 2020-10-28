import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;

/**
 * The type Rectangle.
 */
public abstract class Rectangle {
    private static final int DEFAULT_HEIGHT = 30;
    private static final int DEFAULT_SPEED_X = 2;
    private static final int DEFAULT_SPEED_Y = 2;
    private static final int DEFAULT_WIDTH = 30;
    private final Board board;
    /**
     * The Color of this rectangle.
     */
    protected Color color;
    /**
     * The Type.
     */
    protected Type type;
    /**
     * The x speed.
     */
    protected int XSpeed;
    /**
     * The y speed.
     */
    protected int YSpeed;
    /**
     * The X position.
     */
    protected int x;
    /**
     * The Y position.
     */
    protected int y;
    private int height;
    private int width;

    /**
     * Instantiates a new Rectangle.
     *
     * @param board  the panel where the recatngle will be display
     * @param color  the color of this rectangle
     * @param width  the width of this rectangle
     * @param height  the height of this rectangle
     * @param x        the x position of this rectangle
     * @param y        the y position of this rectangle
     * @param XSpeed the x speed of this rectangle
     * @param YSpeed the y speed of this rectangle
     */
    public Rectangle(Board board, Color color, int width, int height, int x, int y, int XSpeed, int YSpeed) {
        this.board = board;
        this.color = color;
        this.width = width;
        this.height = height;
        this.XSpeed = XSpeed;
        this.YSpeed = YSpeed;

        this.x = x;
        this.y = y;
    }

    /**
     * Check and update speed.
     *
     * @param board the board
     */
    public abstract void checkAndUpdateSpeed(Board board);

    /**
     * Update speed.
     *
     * @param p the p
     */
    public void updateSpeed(Board p) {
        JPanel mainPanel = p.getMainPanel();

        int speedX = this.XSpeed;
        int speedY = this.YSpeed;

        if (this.x - this.XSpeed <= 0) {
            if (speedX < 0) {
                speedX = -(this.XSpeed);
            }
        } else if ((this.x + this.width) >= mainPanel.getWidth()) {
            if (speedX > 0) {
                speedX = -(this.XSpeed);
            }
        }

        if (this.y - this.YSpeed <= 0) {
            if (speedY < 0) {
                speedY = -(this.YSpeed);
            }
        } else if ((this.y + this.height) >= mainPanel.getHeight()) {
            if (speedY > 0) {
                speedY = -(this.YSpeed);
            }
        }

        this.XSpeed = speedX;
        this.YSpeed = speedY;

        this.x += this.XSpeed;
        this.y += this.YSpeed;
    }

    /**
     * Move rectangle.
     *
     * @param p the p
     */
    public void moveRectangle(Board p) {
        this.checkAndUpdateSpeed(p);
    }

    /**
     * Rectangle activity.
     *
     * @param p the p
     */
    public void rectangleActivity(Board p) {
        this.moveRectangle(p);
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * Gets x speed.
     *
     * @return the x speed
     */
    public int getXSpeed() {
        return this.XSpeed;
    }

    /**
     * Sets x speed.
     *
     * @param XSpeed the x speed
     */
    public void setXSpeed(int XSpeed) {
        this.XSpeed = XSpeed;
    }

    /**
     * Gets y speed.
     *
     * @return the y speed
     */
    public int getYSpeed() {
        return this.YSpeed;
    }

    /**
     * Sets y speed.
     *
     * @param YSpeed the y speed
     */
    public void setYSpeed(int YSpeed) {
        this.YSpeed = YSpeed;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Sets x.
     *
     * @param x the x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Sets y.
     *
     * @param y the y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Is in window boolean.
     *
     * @return the boolean
     */
    public boolean isInWindow() {
        JPanel mainPanel = this.board.getMainPanel();

        boolean ret;
        ret = ((this.x + this.width) < mainPanel.getWidth()) && this.x >= 0; // horizontal check
        ret = ret && this.y >= 0 && ((this.y + this.height) < mainPanel.getHeight()); // vertical check

        return ret;
    }

    /**
     * The enum Type.
     */
    public enum Type {
        /**
         * Player type.
         */
        JOUEUR("resources/images/tatitatoo.png"),
        /**
         * Enemy type.
         */
        ENNEMI(),
        /**
         * Base type.
         */
        BASE("resources/images/maya.png");
        /**
         * The Image.
         */
        public Image image;

        Type(String imagePath) {
            try {
                this.image = ImageIO.read(Board.class.getResourceAsStream(imagePath));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }

        Type() {

        }
    }

    /**
     * The type Builder.
     */
    static class Builder {
        private final Rectangle rectangle;

        /**
         * Instantiates a new Builder.
         *
         * @param type    the type
         * @param board the board
         */
        Builder(Type type, Board board) {
            if (type == Type.JOUEUR) {
                this.rectangle = new Player(board, Rectangle.DEFAULT_WIDTH, Rectangle.DEFAULT_HEIGHT, 0, 0, Rectangle.DEFAULT_SPEED_X, Rectangle.DEFAULT_SPEED_Y);
                this.rectangle.type = Type.JOUEUR;
            } else if (type == Type.BASE) {
                this.rectangle = new Base(board, Rectangle.DEFAULT_WIDTH, Rectangle.DEFAULT_HEIGHT, 0, 0, Rectangle.DEFAULT_SPEED_X, Rectangle.DEFAULT_SPEED_Y);
                this.rectangle.type = Type.BASE;
            } else {
                this.rectangle = new Enemy(board, Rectangle.DEFAULT_WIDTH, Rectangle.DEFAULT_HEIGHT, 0, 0, Rectangle.DEFAULT_SPEED_X, Rectangle.DEFAULT_SPEED_Y);
                this.rectangle.type = Type.ENNEMI;
            }
        }

        /**
         * Build rectangle.
         *
         * @return the rectangle
         */
        public Rectangle build() {
            this.rectangle.board.addRectangle(this.rectangle);
            return this.rectangle;
        }

        /**
         * Start rectangle.
         *
         * @return the rectangle
         */
        public Rectangle start() {
            Rectangle rectangle = this.build();
            new Activity(this.rectangle, this.rectangle.board).start();
            return rectangle;
        }

        /**
         * Sets height.
         *
         * @param height the height
         * @return the height
         */
        public Builder setHeight(int height) {
            this.rectangle.setHeight(height);
            return this;
        }

        /**
         * Sets width.
         *
         * @param width the width
         * @return the width
         */
        public Builder setWidth(int width) {
            this.rectangle.setWidth(width);
            return this;
        }

        /**
         * Sets x speed.
         *
         * @param XSpeed the x speed
         * @return the x speed
         */
        public Builder setXSpeed(int XSpeed) {
            this.rectangle.setXSpeed(XSpeed);
            return this;
        }

        /**
         * Sets y speed.
         *
         * @param YSpeed the y speed
         * @return the y speed
         */
        public Builder setYSpeed(int YSpeed) {
            this.rectangle.setYSpeed(YSpeed);
            return this;
        }

        /**
         * Sets x.
         *
         * @param x the x
         * @return the x
         */
        public Builder setX(int x) {
            this.rectangle.setX(x);
            return this;
        }

        /**
         * Sets y.
         *
         * @param y the y
         * @return the y
         */
        public Builder setY(int y) {
            this.rectangle.setY(y);
            return this;
        }
    }
}