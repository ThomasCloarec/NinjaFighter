public class Activity implements Runnable {
    private final Panneau panneau;
    private final Rectangle rectangle;

    public Activity(Rectangle r, Panneau p) {
        this.rectangle = r;
        this.panneau = p;
    }

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