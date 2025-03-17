import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Shooter extends ImageView{

    public double xTower = 300;
    public double yTower = 300;
    public double rotationAngle = 0; //pod jakim kątem jest teraz obrazek
    public double stepAngle = Math.PI/180 * 10; //1 stopień //co ile się odwraca

    private Timeline timeline;

    private List<Bullet> bullets = new ArrayList<>();



    //konstruktor Wieży do Shootowania
    public Shooter(double x, double y, Image imageViewShootingTower) {
        super(imageViewShootingTower);
        xTower = x;
        yTower = y;

        this.setLayoutX(xTower);
        this.setLayoutY(yTower);
//        TokyoDriftTheme.level1.getChildren().addAll();

        timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            move();
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void fireBullet(double targetX, double targetY, double speed) {
        double distance = Math.hypot(targetX - xTower, targetY - yTower);
        double dx = speed * (targetX - xTower) / distance;
        double dy = speed * (targetY - yTower) / distance;

        Bullet bullet= new Bullet(dx, dy, xTower, yTower);

        bullets.add(bullet);

        TokyoDriftTheme.level1Root.getChildren().add(bullet); //pojawia się na planszy
    }

    public void move() {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).move();
        }

        //jeżeli trafi w car (collision)

    }

    public void rotateToTarget(Player player) {
        double angle = Math.atan((player.carY - getLayoutY()) / (player.carX - getLayoutX()));
        double lufaAngle = getRotate();

        if(Math.abs(angle - lufaAngle) < 0.1){
            //do nothing
        } else if (angle > lufaAngle) {
            setRotate(getRotate() + stepAngle);
        } else {
            setRotate(getRotate() - stepAngle);
        }
    }
}

