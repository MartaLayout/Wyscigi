import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class Car extends ImageView {

    //TODO checkpoints
    protected double velocity;
    protected int maxVelocity;
    private boolean isSpeeding;
    private boolean slowingDown;
    private boolean nitro;
    private double x;
    private double y;
    protected double direction;


    //here changed car initial position
    public double carX = -100;  // Initial X position
    public double carY = -200;  // Initial Y position
    protected double carAngle = 0; // Initial rotation angle (in degrees)
    protected final double speed = 3; // Movement speed
    protected final double rotationSpeed = 5; // Rotation speed (degrees)
    protected  double speedIncrimentation = 1.0000;
    protected  double speedIncrimentationBackwards = 1.0000;

    private enum Surface {
        ASFALT, SNOW, GRUZ
    }

    public Car(double x, double y, Image imageView) {
        super(imageView);
        this.carX = x;
        this.carY = y;

        this.setTranslateX(carX);
        this.setTranslateY(carY);
        this.setRotate(carAngle);

        //direction = getRotate();
        //idk how this is made to be in main and racetrack, detect surface basically
        //Surface = Main.roadget
//        if (velocity > 0){
//            isSpeeding = true;
//        }




    }

//TODO
//method for nie wychodzenie poza tor --> kolorki
//method for odbijanie od ściany --> kolizje



    protected void speedUP() {
//        x = direction * x / velocity;
//        y = direction * y / velocity;
        //if (speedIncrimentation<4){
            speedIncrimentation += 0.1;
//            speedIncrimentation = Math.round(speedIncrimentation *100);
//            speedIncrimentation/=100;
        //}




    }

    protected void speedUpBackwards(){
        speedIncrimentationBackwards += 0.1;
    }

    protected void slowDown() {
        if (speedIncrimentation>=0) {
            speedIncrimentation -= 0.1;
//            speedIncrimentation = Math.round(speedIncrimentation *100);
//            speedIncrimentation/=100;
        }
        if (speedIncrimentation <0.01){
            speedIncrimentation =0;
        }
    }



    protected void slowDownBackwards() {
        if (speedIncrimentationBackwards>=0) {
            speedIncrimentationBackwards -= 0.1;
//            speedIncrimentation = Math.round(speedIncrimentation *100);
//            speedIncrimentation/=100;
        }
        if (speedIncrimentationBackwards <0.01){
            speedIncrimentationBackwards =0;
        }
    }


    protected void turnRight() {
//        if (isSpeeding){
//            direction += 1;
//            if (direction > 180){
//                direction = -179;
//            }
//            setRotate(direction);
//        }

    }

    protected void turnLeft() {
//        if (isSpeeding){
//            direction -= 1;
//            if (direction < -179){
//                direction = 180;
//            }
//            setRotate(direction);
//        }
    }
    //later add variations of move on different surfaces, with slightly different parameters
}
