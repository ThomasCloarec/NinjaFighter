import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The type Panneau.
 */
public class Panneau {
    /**
     * The Rectangle list.
     */
    public final List<Rectangle> rectangleList = new ArrayList<>();
    private final JFrame frame = new JFrame("NinjaFighter");
    private final JLabel scores = new JLabel("0");
    private JPanel mainPanel;

    /**
     * Instantiates a new Panneau.
     *
     * @param largeur the width of the panel
     * @param hauteur the height of the panel
     */
    public Panneau(int largeur, int hauteur) {
        this.scores.setForeground(Color.WHITE);
        try {
            SwingUtilities.invokeAndWait(() -> {
                this.frame.setSize(largeur, hauteur);
                this.frame.setLocationRelativeTo(null);
                this.frame.setResizable(false);
                this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

                this.mainPanel = new JPanel() {
                    @Override
                    protected void paintComponent(Graphics g) {
                        super.paintComponent(g);

                        Rectangle rectangle;
                        for (int i = 0; i < Panneau.this.rectangleList.size(); i++) {
                            rectangle = Panneau.this.rectangleList.get(i);

                            if (rectangle.getType().image == null) {
                                g.setColor(rectangle.getCouleur());
                                g.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getLargeur(), rectangle.getHauteur());
                            } else {
                                g.drawImage(rectangle.getType().image, rectangle.getX(), rectangle.getY(), rectangle.getLargeur(), rectangle.getHauteur(), this);
                            }
                        }

                        Panneau.this.scores.setText(Integer.toString(Ennemi.points));
                        int labelWidth = g.getFontMetrics().stringWidth(Panneau.this.scores.getText());
                        Panneau.this.scores.setLocation(250 - labelWidth / 2, 20);
                    }
                };

                this.mainPanel.add(this.scores);
                this.frame.setContentPane(this.mainPanel);
                this.mainPanel.setLayout(new GridLayout(1, 2));
                this.mainPanel.setBackground(new Color(52, 155, 207));

                this.frame.setVisible(true);
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Add rectangle.
     *
     * @param rectangle the rectangle
     */
    public void addRectangle(Rectangle rectangle) {
        this.rectangleList.add(rectangle);
    }

    /**
     * Refresh frame.
     */
    public void refreshFrame() {
        SwingUtilities.invokeLater(() -> {
            this.frame.revalidate();
            this.frame.repaint();
        });
    }

    /**
     * Save one.
     */
    public synchronized void saveOne() {
        this.notify();
    }

    /**
     * Overlaps boolean.
     *
     * @param waitFlag         the wait flag
     * @param r                the r
     * @param typesDeCollision the types de collision
     * @return the boolean
     */
    public synchronized boolean overlaps(boolean waitFlag, Rectangle r, Rectangle.Type... typesDeCollision) {
        boolean overlap = false;

        Rectangle rectangle;
        for (int i = 0; i < this.rectangleList.size(); i++) {
            rectangle = this.rectangleList.get(i);
            if (rectangle != null && r != rectangle && !overlap && Arrays.asList(typesDeCollision).contains(rectangle.type)) {
                overlap = r.getX() < rectangle.getX() + rectangle.getLargeur() && r.getX() + r.getLargeur() > rectangle.getX() && r.getY() < rectangle.getY() + rectangle.getHauteur() && r.getY() + r.getHauteur() > rectangle.getY();
            }
        }

        if (overlap && waitFlag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return overlap;
    }

    /**
     * Gets frame.
     *
     * @return the frame
     */
    public JFrame getFrame() {
        return this.frame;
    }

    /**
     * Gets main panel.
     *
     * @return the main panel
     */
    public JPanel getMainPanel() {
        return this.mainPanel;
    }
}