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
    //public double stepAngle = Math.PI/180 * 10; //1 stopień //co ile się odwraca
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
//        TokyoDriftTheme.level1.getChildren().addAll();

        timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            move();
        }));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void fireBullet(double targetX, double targetY, double speed) {
//        double distance = Math.hypot(targetX - xTower, targetY - yTower);
//        double dx = speed * (targetX - xTower) / distance;
//        double dy = speed * (targetY - yTower) / distance;
//
//        Bullet bullet= new Bullet(dx, dy, (xTower + 20), (yTower + 10));
//
//        bullets.add(bullet);
//
//        TokyoDriftTheme.level1Root.getChildren().add(bullet); //pojawia się na planszy

        // Convert the shooter's rotation angle from degrees to radians
        double rotationInRadians = Math.toRadians(getRotate());

        // Get the center of the shooter image
        double shooterCenterX = getLayoutX() + getBoundsInParent().getWidth() / 2;
        double shooterCenterY = getLayoutY() + getBoundsInParent().getHeight() / 2;

        // Move the bullet slightly in front of the shooter (based on the rotation)
        double bulletOffset = 7;  // Fine-tune this value as needed

        // Calculate the bullet's starting position in front of the shooter
        double bulletStartX = shooterCenterX + Math.cos(rotationInRadians) * bulletOffset;
        double bulletStartY = shooterCenterY + Math.sin(rotationInRadians) * bulletOffset;

        // Calculate the direction vector for the bullet to move toward the target
        double distance = Math.hypot(targetX - bulletStartX, targetY - bulletStartY);
        double dx = speed * (targetX - bulletStartX) / distance;
        double dy = speed * (targetY - bulletStartY) / distance;

        // Create the bullet
        Bullet bullet = new Bullet(dx, dy, bulletStartX, bulletStartY);

        // Add the bullet to the game scene
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
        //jeżeli trafi w car (collision)

    }

    public void rotateToTarget(Player player) {
        // Calculate the target angle in degrees
        double targetAngle = Math.toDegrees(Math.atan2(player.carY - getLayoutY(), player.carX - getLayoutX()));

        // Add a 180-degree offset to align with the shooter's orientation
        targetAngle -= 270; //225

        // Normalize targetAngle to [0, 360]
        if (targetAngle < 0) {
            targetAngle += 360;
        }

        // Get the current rotation in [0, 360]
        double currentAngle = getRotate();
        if (currentAngle < 0) {
            currentAngle += 360;
        }

        // Calculate the shortest rotation direction
        double angleDifference = targetAngle - currentAngle;
        if (angleDifference > 180) {
            angleDifference -= 360;
        } else if (angleDifference < -180) {
            angleDifference += 360;
        }

        // Rotate incrementally by stepAngle or snap if close
        if (Math.abs(angleDifference) <= Math.toDegrees(stepAngle)) {
            setRotate(targetAngle); // Snap to target
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

