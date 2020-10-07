import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;

public class Panneau {
    public final List<Rectangle> rectangleList = new ArrayList<>();
    private final JFrame frame = new JFrame("Panneau");
    private JPanel mainPanel;
    private final JLabel scores = new JLabel("0");

    public Panneau(int largeur, int hauteur) {
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
                        try {
                            for (Rectangle rectangle : Panneau.this.rectangleList) {
                                g.setColor(rectangle.getCouleur());
                                g.fillRect(rectangle.getX(), rectangle.getY(), rectangle.getLargeur(), rectangle.getHauteur());
                            }

                            Panneau.this.scores.setText(Integer.toString(Ennemi.points));
                            Panneau.this.scores.setLocation(240,20);
                        } catch (ConcurrentModificationException ignored) {

                        }
                    }
                };

                this.mainPanel.add(this.scores);
                this.frame.setContentPane(this.mainPanel);
                this.mainPanel.setLayout(new GridLayout(1, 2));

                this.frame.setVisible(true);
            });
        } catch (InterruptedException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void addRectangle(Rectangle rectangle) {
        this.rectangleList.add(rectangle);
    }

    public void refreshFrame() {
        SwingUtilities.invokeLater(() -> {
            this.frame.revalidate();
            this.frame.repaint();
        });
    }

    public synchronized void saveOne() {
        this.notify();
    }

    public synchronized boolean overlaps(Rectangle r, Rectangle.Type... typesDeCollision) {
        boolean overlap = false;

        for (Rectangle rectangle : this.rectangleList) {
            if (rectangle != null && r != rectangle && !overlap && Arrays.asList(typesDeCollision).contains(rectangle.type)) {
                overlap = r.getX() < rectangle.getX() + rectangle.getLargeur() && r.getX() + r.getLargeur() > rectangle.getX() && r.getY() < rectangle.getY() + rectangle.getHauteur() && r.getY() + r.getHauteur() > rectangle.getY();
            }
        }

        return overlap;
    }

    public synchronized void overlapsAndWait(Rectangle r, boolean stopFlag, Rectangle.Type... typesDeCollision) {
        boolean overlap = this.overlaps(r, typesDeCollision);

        if (overlap && stopFlag) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public int getListSize() {
        return this.rectangleList.size();
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }
}
