import java.awt.Color;

public abstract class Rectangle {
    private final Panneau panneau;
    protected Type type;
    protected int vitesseX;
    protected int vitesseY;
    protected int x;
    protected int y;
    protected Color couleur;
    private int hauteur;
    private int largeur;

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

    public abstract void checkAndUpdateSpeed(Panneau panneau);

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

    public void moveRectangle(Panneau p) {
        this.checkAndUpdateSpeed(p);
    }

    public void rectangleActivity(Panneau p) {
        this.moveRectangle(p);
    }

    public Color getCouleur() {
        return this.couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }

    public int getHauteur() {
        return this.hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public int getLargeur() {
        return this.largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getVitesseX() {
        return this.vitesseX;
    }

    public void setVitesseX(int vitesseX) {
        this.vitesseX = vitesseX;
    }

    public int getVitesseY() {
        return this.vitesseY;
    }

    public void setVitesseY(int vitesseY) {
        this.vitesseY = vitesseY;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isInWindow() {
        return (((this.x + this.largeur) < this.panneau.getFrame().getWidth()) && this.x >= 0 && this.y >= 0 && ((this.y + this.hauteur) < this.panneau.getFrame().getContentPane().getHeight()));
    }

    public enum Type {
        JOUEUR,
        ENNEMI,
        BASE
    }

    static class Builder {
        private final Rectangle rectangle;

        Builder(Type type, Panneau panneau) {
            if (type == Type.JOUEUR) {
                this.rectangle = new Joueur(panneau, 30, 30, 0, 0, 2, 2);
                this.rectangle.type = Type.JOUEUR;
            } else if (type == Type.BASE) {
                this.rectangle = new Base(panneau, 30, 30, 0, 0, 2, 2);
                this.rectangle.type = Type.BASE;
            } else {
                this.rectangle = new Ennemi(panneau, 30, 30, 0, 0, 2, 2);
                this.rectangle.type = Type.ENNEMI;
            }
        }

        public Rectangle build() {
            this.rectangle.panneau.addRectangle(this.rectangle);
            return this.rectangle;
        }

        public Builder setHauteur(int hauteur) {
            this.rectangle.setHauteur(hauteur);
            return this;
        }

        public Builder setLargeur(int largeur) {
            this.rectangle.setLargeur(largeur);
            return this;
        }

        public Builder setVitesseX(int vitesseX) {
            this.rectangle.setVitesseX(vitesseX);
            return this;
        }

        public Builder setVitesseY(int vitesseY) {
            this.rectangle.setVitesseY(vitesseY);
            return this;
        }

        public Builder setX(int x) {
            this.rectangle.setX(x);
            return this;
        }

        public Builder setY(int y) {
            this.rectangle.setY(y);
            return this;
        }
    }
}