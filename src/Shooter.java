import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Shooter extends ImageView{

    public static double xTower = 300;
    public static double yTower = 300;

    public static final Image IMAGE = new Image("file:Tokyo/shoot.png");
    private static ImageView bullet = new ImageView(IMAGE);
    private final static double step = 5;
    private static double dh = 1;
    private static double dx;
    private static double dy;
    private static double distance;
    private static Timeline timeline;
    private static double ahoraDistance;


    //Marta 09.02: wyświetla się ale problem jest taki jak w Car, że respi się u góry i jakby nic z tym się nie da zrobić :(((
    public Shooter(double x, double y, Image imageViewShootingTower) {
        super(imageViewShootingTower);
        xTower = x;
        yTower = y;
        //TokyoDriftTheme.level1.getChildren().addAll(towerShooting);
       // bullets(300, 300, 200, 200);
    }

    public static void bullets(double x , double y, double targetX, double targetY) {
        bullet = new ImageView(IMAGE);
        bullet.setX(x);
        bullet.setY(y);

        //TokyoDriftTheme.level1Root.getChildren().addAll(bullet);

        distance = Math.hypot(targetX - x, targetY - y);
        double step = 5;
        dx = step * (targetX - x) / distance;
        dy = step * (targetY - y) / distance;
        timeline = new Timeline(new KeyFrame(Duration.millis(100 / 60), event -> {
            move();
            TokyoDriftTheme.level1.getChildren().remove(bullet);
            timeline.stop();
            timeline = null;
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static void move() {
        ahoraDistance += step;
        bullet.setX(bullet.getX() + dx);
        bullet.setY(bullet.getY() + dy);
        if (ahoraDistance < distance / 2) {
            bullet.setY(bullet.getY() + dy - dh);
        }
        else {
            bullet.setY(bullet.getY() + dy + dh);

        }
    }
}

