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
    protected Color couleur;
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
     * @param couleur  the color of this rectangle
     * @param width  the width of this rectangle
     * @param height  the height of this rectangle
     * @param x        the x position of this rectangle
     * @param y        the y position of this rectangle
     * @param XSpeed the x speed of this rectangle
     * @param YSpeed the y speed of this rectangle
     */
    public Rectangle(Board board, Color couleur, int width, int height, int x, int y, int XSpeed, int YSpeed) {
        this.board = board;
        this.couleur = couleur;
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
     * Gets couleur.
     *
     * @return the couleur
     */
    public Color getCouleur() {
        return this.couleur;
    }

    /**
     * Gets height.
     *
     * @return the height
     */
    public int getHauteur() {
        return this.height;
    }

    /**
     * Sets height.
     *
     * @param height the height
     */
    public void setHauteur(int height) {
        this.height = height;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getLargeur() {
        return this.width;
    }

    /**
     * Sets width.
     *
     * @param width the width
     */
    public void setLargeur(int width) {
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
     * Gets vitesse x.
     *
     * @return the vitesse x
     */
    public int getVitesseX() {
        return this.XSpeed;
    }

    /**
     * Sets vitesse x.
     *
     * @param XSpeed the vitesse x
     */
    public void setVitesseX(int XSpeed) {
        this.XSpeed = XSpeed;
    }

    /**
     * Gets vitesse y.
     *
     * @return the vitesse y
     */
    public int getVitesseY() {
        return this.YSpeed;
    }

    /**
     * Sets vitesse y.
     *
     * @param YSpeed the vitesse y
     */
    public void setVitesseY(int YSpeed) {
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
         * Ennemy type.
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
                this.rectangle = new Ennemy(board, Rectangle.DEFAULT_WIDTH, Rectangle.DEFAULT_HEIGHT, 0, 0, Rectangle.DEFAULT_SPEED_X, Rectangle.DEFAULT_SPEED_Y);
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
        public Builder setHauteur(int height) {
            this.rectangle.setHauteur(height);
            return this;
        }

        /**
         * Sets width.
         *
         * @param width the width
         * @return the width
         */
        public Builder setLargeur(int width) {
            this.rectangle.setLargeur(width);
            return this;
        }

        /**
         * Sets vitesse x.
         *
         * @param XSpeed the vitesse x
         * @return the vitesse x
         */
        public Builder setVitesseX(int XSpeed) {
            this.rectangle.setVitesseX(XSpeed);
            return this;
        }

        /**
         * Sets vitesse y.
         *
         * @param YSpeed the vitesse y
         * @return the vitesse y
         */
        public Builder setVitesseY(int YSpeed) {
            this.rectangle.setVitesseY(YSpeed);
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