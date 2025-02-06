import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Shooter {
    public static final Image IMAGE = new Image("file:Tokyo\\shoot.png");
    private static ImageView bullet = new ImageView(IMAGE);
    private final static double step = 5;
    private static double dh = 1;
    private static double dx;
    private static double dy;
    private static double distance;
    private static Timeline timeline;
    private static double ahoraDistance;


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
            //TokyoDriftTheme.level1Root.getChildren().remove(bullet);
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

