/**
 * The type Activity.
 */
public class Activity implements Runnable {
    private final Panneau panneau;
    private final Rectangle rectangle;

    /**
     * Instantiates a new Activity.
     *
     * @param r the rectangle to apply activity
     * @param p the panel where rectangle will move
     */
    public Activity(Rectangle r, Panneau p) {
        this.rectangle = r;
        this.panneau = p;
    }

    /**
     * Start the activity.
     */
    public void start() {
        new Thread(this).start();
    }

    @Override
    public void run() {
        while (true) {
            this.rectangle.rectangleActivity(this.panneau);

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}