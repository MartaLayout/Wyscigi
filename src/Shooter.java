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
    public double rotationAngle = 0;
    public double stepAngle = Math.toRadians(5);

    private Timeline timeline;
    public int timer;

    public static List<Bullet> bullets = new ArrayList<>();

    //konstruktor Wieży do Shootowania
    public Shooter(double x, double y, Image imageViewShootingTower) {
        super(imageViewShootingTower);
        xTower = x;
        yTower = y;

        this.setLayoutX(xTower);
        this.setLayoutY(yTower);

        timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            move();
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void fireBullet(double targetX, double targetY, double speed) {

        double rotationInRadians = Math.toRadians(getRotate());

        double shooterCenterX = getLayoutX() + getBoundsInParent().getWidth() / 2;
        double shooterCenterY = getLayoutY() + getBoundsInParent().getHeight() / 2;

        double bulletOffset = 7;

        double bulletStartX = shooterCenterX + Math.cos(rotationInRadians) * bulletOffset;
        double bulletStartY = shooterCenterY + Math.sin(rotationInRadians) * bulletOffset;

        double distance = Math.hypot(targetX - bulletStartX, targetY - bulletStartY);
        double dx = speed * (targetX - bulletStartX) / distance;
        double dy = speed * (targetY - bulletStartY) / distance;

        Bullet bullet = new Bullet(dx, dy, bulletStartX, bulletStartY);

        bullets.add(bullet);
        if(TokyoDriftTheme.activeLevel1Scene){
            TokyoDriftTheme.level1Root.getChildren().add(bullet);
        } else if (TokyoDriftTheme.activeLevel2Scene) {
            TokyoDriftTheme.level2Root.getChildren().add(bullet);
        } else if(TokyoDriftTheme.activeLevel3Scene){
            TokyoDriftTheme.level3Root.getChildren().add(bullet);
        }
    }

    public void move() {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).move();
        }

    }

    public void rotateToTarget(Player player) {
        double targetAngle = Math.toDegrees(Math.atan2(player.carY - getLayoutY(), player.carX - getLayoutX()));

        targetAngle -= 270; //225


        if (targetAngle < 0) {
            targetAngle += 360;
        }

        double currentAngle = getRotate();
        if (currentAngle < 0) {
            currentAngle += 360;
        }

        double angleDifference = targetAngle - currentAngle;
        if (angleDifference > 180) {
            angleDifference -= 360;
        } else if (angleDifference < -180) {
            angleDifference += 360;
        }

        if (Math.abs(angleDifference) <= Math.toDegrees(stepAngle)) {
            setRotate(targetAngle);
        } else if (angleDifference > 0) {
            setRotate(currentAngle + Math.toDegrees(stepAngle));
        } else {
            setRotate(currentAngle - Math.toDegrees(stepAngle));
        }
    }


    public void setTimer(int timer) {
        this.timer = timer;
    }
}

