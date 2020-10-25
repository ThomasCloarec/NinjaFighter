import javax.imageio.ImageIO;
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
    private final Panneau panneau;
    /**
     * The Couleur.
     */
    protected Color couleur;
    /**
     * The Type.
     */
    protected Type type;
    /**
     * The Vitesse x.
     */
    protected int vitesseX;
    /**
     * The Vitesse y.
     */
    protected int vitesseY;
    /**
     * The X.
     */
    protected int x;
    /**
     * The Y.
     */
    protected int y;
    private int hauteur;
    private int largeur;

    /**
     * Instantiates a new Rectangle.
     *
     * @param panneau  the panneau
     * @param couleur  the couleur
     * @param largeur  the largeur
     * @param hauteur  the hauteur
     * @param x        the x
     * @param y        the y
     * @param vitesseX the vitesse x
     * @param vitesseY the vitesse y
     */
    public Rectangle(Panneau panneau, Color couleur, int largeur, int hauteur, int x, int y, int vitesseX, int vitesseY) {
        this.panneau = panneau;
        this.couleur = couleur;
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.vitesseX = vitesseX;
        this.vitesseY = vitesseY;

        this.x = x;
        this.y = y;
    }

    /**
     * Check and update speed.
     *
     * @param panneau the panneau
     */
    public abstract void checkAndUpdateSpeed(Panneau panneau);

    /**
     * Update speed.
     *
     * @param p the p
     */
    public void updateSpeed(Panneau p) {
        int speedX = this.vitesseX;
        int speedY = this.vitesseY;

        if (this.x - this.vitesseX <= 0) {
            if (speedX < 0) {
                speedX = -(this.vitesseX);
            }
        } else if ((this.x + this.largeur) >= p.getFrame().getWidth()) {
            if (speedX > 0) {
                speedX = -(this.vitesseX);
            }
        }

        if (this.y - this.vitesseY <= 0) {
            if (speedY < 0) {
                speedY = -(this.vitesseY);
            }
        } else if ((this.y + this.hauteur) >= p.getFrame().getContentPane().getHeight()) {
            if (speedY > 0) {
                speedY = -(this.vitesseY);
            }
        }

        this.vitesseX = speedX;
        this.vitesseY = speedY;

        this.x += this.vitesseX;
        this.y += this.vitesseY;
    }

    /**
     * Move rectangle.
     *
     * @param p the p
     */
    public void moveRectangle(Panneau p) {
        this.checkAndUpdateSpeed(p);
    }

    /**
     * Rectangle activity.
     *
     * @param p the p
     */
    public void rectangleActivity(Panneau p) {
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
     * Gets hauteur.
     *
     * @return the hauteur
     */
    public int getHauteur() {
        return this.hauteur;
    }

    /**
     * Sets hauteur.
     *
     * @param hauteur the hauteur
     */
    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    /**
     * Gets largeur.
     *
     * @return the largeur
     */
    public int getLargeur() {
        return this.largeur;
    }

    /**
     * Sets largeur.
     *
     * @param largeur the largeur
     */
    public void setLargeur(int largeur) {
        this.largeur = largeur;
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
        return this.vitesseX;
    }

    /**
     * Sets vitesse x.
     *
     * @param vitesseX the vitesse x
     */
    public void setVitesseX(int vitesseX) {
        this.vitesseX = vitesseX;
    }

    /**
     * Gets vitesse y.
     *
     * @return the vitesse y
     */
    public int getVitesseY() {
        return this.vitesseY;
    }

    /**
     * Sets vitesse y.
     *
     * @param vitesseY the vitesse y
     */
    public void setVitesseY(int vitesseY) {
        this.vitesseY = vitesseY;
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
        return (((this.x + this.largeur) < this.panneau.getFrame().getWidth()) && this.x >= 0 && this.y >= 0 && ((this.y + this.hauteur) < this.panneau.getFrame().getContentPane().getHeight()));
    }

    /**
     * The enum Type.
     */
    public enum Type {
        /**
         * Joueur type.
         */
        JOUEUR("data/images/tatitatoo.png"),
        /**
         * Ennemi type.
         */
        ENNEMI(),
        /**
         * Base type.
         */
        BASE("data/images/maya.png");
        /**
         * The Image.
         */
        public Image image;

        Type(String imagePath) {
            try {
                this.image = ImageIO.read(Panneau.class.getResourceAsStream(imagePath));
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
         * @param panneau the panneau
         */
        Builder(Type type, Panneau panneau) {
            if (type == Type.JOUEUR) {
                this.rectangle = new Joueur(panneau, Rectangle.DEFAULT_WIDTH, Rectangle.DEFAULT_HEIGHT, 0, 0, Rectangle.DEFAULT_SPEED_X, Rectangle.DEFAULT_SPEED_Y);
                this.rectangle.type = Type.JOUEUR;
            } else if (type == Type.BASE) {
                this.rectangle = new Base(panneau, Rectangle.DEFAULT_WIDTH, Rectangle.DEFAULT_HEIGHT, 0, 0, Rectangle.DEFAULT_SPEED_X, Rectangle.DEFAULT_SPEED_Y);
                this.rectangle.type = Type.BASE;
            } else {
                this.rectangle = new Ennemi(panneau, Rectangle.DEFAULT_WIDTH, Rectangle.DEFAULT_HEIGHT, 0, 0, Rectangle.DEFAULT_SPEED_X, Rectangle.DEFAULT_SPEED_Y);
                this.rectangle.type = Type.ENNEMI;
            }
        }

        /**
         * Build rectangle.
         *
         * @return the rectangle
         */
        public Rectangle build() {
            this.rectangle.panneau.addRectangle(this.rectangle);
            return this.rectangle;
        }

        /**
         * Start rectangle.
         *
         * @return the rectangle
         */
        public Rectangle start() {
            Rectangle rectangle = this.build();
            new Activity(this.rectangle, this.rectangle.panneau).start();
            return rectangle;
        }

        /**
         * Sets hauteur.
         *
         * @param hauteur the hauteur
         * @return the hauteur
         */
        public Builder setHauteur(int hauteur) {
            this.rectangle.setHauteur(hauteur);
            return this;
        }

        /**
         * Sets largeur.
         *
         * @param largeur the largeur
         * @return the largeur
         */
        public Builder setLargeur(int largeur) {
            this.rectangle.setLargeur(largeur);
            return this;
        }

        /**
         * Sets vitesse x.
         *
         * @param vitesseX the vitesse x
         * @return the vitesse x
         */
        public Builder setVitesseX(int vitesseX) {
            this.rectangle.setVitesseX(vitesseX);
            return this;
        }

        /**
         * Sets vitesse y.
         *
         * @param vitesseY the vitesse y
         * @return the vitesse y
         */
        public Builder setVitesseY(int vitesseY) {
            this.rectangle.setVitesseY(vitesseY);
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