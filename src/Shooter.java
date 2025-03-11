import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Shooter extends ImageView{

    public static double xTower = 300;
    public static double yTower = 300;

    public static final Image IMAGE = new Image("file:Tokyo/shoot.png");
    public static ImageView bullet = new ImageView(IMAGE);
    private final static double step = 5;
    private static double dh = 1;
    private static double dx;
    private static double dy;
    private static double distance;
    private static Timeline timeline;
    private static double ahoraDistance;



    //Marta 09.02: wyświetla się ale problem jest taki jak w Car, że respi się u góry i jakby nic z tym się nie da zrobić :(((
    //Nati: Juz naprawione
    public Shooter(double x, double y, Image imageViewShootingTower) {
        super(imageViewShootingTower);
        xTower = x;
        yTower = y;

        this.setTranslateX(xTower);
        this.setTranslateY(yTower);
       TokyoDriftTheme.level1.getChildren().addAll();

    }

    public static void bullets(double x , double y, double targetX, double targetY) {
        bullet = new ImageView(IMAGE);
        bullet.setX(x);
        bullet.setY(y);
        List<ImageView> bullets = new ArrayList<>();
        bullets.add(bullet);

        TokyoDriftTheme.level1.getChildren().addAll(bullets);

        distance = Math.hypot(targetX - x, targetY - y);
        double step = 5;
        dx = step * (targetX - x) / distance;
        dy = step * (targetY - y) / distance;
        timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            move();
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

