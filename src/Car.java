import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
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

    public int checkpointCounter = 15;
    public boolean rightWay;
    public boolean finish;
    public int lap;




    //here changed car initial position
    public double carX = -100;  // Initial X position
    public double carY = -200;  // Initial Y position
    protected double carAngle = 0; // Initial rotation angle (in degrees)
    protected final double speed = 2; // Movement speed   //3
    protected final double rotationSpeed = 3; // Rotation speed (degrees) //5
    protected  double speedIncrimentation = 0.01; //1.0000
    protected  double speedIncrimentationBackwards = 0.01;

    boolean moveBackwards;
    boolean moveForward;
    boolean moveLeft;
    boolean moveRight;

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

//        Timeline timelineCar = new Timeline(new KeyFrame(Duration.millis(10), event -> {


//            if (moveBackwards){
//                carX -= speed * Math.cos(Math.toRadians(carAngle))*speedIncrimentationBackwards;
//                carY -= speed * Math.sin(Math.toRadians(carAngle))*speedIncrimentationBackwards;
//                speedUpBackwards();
//                System.out.println(speedIncrimentationBackwards);
//
//            }
//            else{
//                carX -= speed * Math.cos(Math.toRadians(carAngle))*speedIncrimentationBackwards;
//                carY -= speed * Math.sin(Math.toRadians(carAngle))*speedIncrimentationBackwards;
//                slowDownBackwards();
//            }
//            if (moveForward){
//                carX += speed * Math.cos(Math.toRadians(carAngle))* speedIncrimentation;
//                carY += speed * Math.sin(Math.toRadians(carAngle))*speedIncrimentation;
//                speedUP();
//                //System.out.println(speedIncrimentation);
//            }
//            else {
//                carX += speed * Math.cos(Math.toRadians(carAngle))* speedIncrimentation;
//                carY += speed * Math.sin(Math.toRadians(carAngle))*speedIncrimentation;
//                slowDown();
//            }


//            if (moveLeft){
//                // Rotate left
//                //+-->-
//                carAngle -= rotationSpeed;
//                //- --> +
//                carX += speed * Math.cos(Math.toRadians(carAngle));
//                carY += speed * Math.sin(Math.toRadians(carAngle));
//                if (moveForward){
//                    slowDown();
//                }
//                if (moveBackwards){
//                    slowDownBackwards();
//                }
//            }

//            if (moveRight){
//                carAngle += rotationSpeed;
//                //- --> +
//                carX += speed * Math.cos(Math.toRadians(carAngle));
//                carY +=speed * Math.sin(Math.toRadians(carAngle));
//                if (moveForward){}
//            }

            //            speedIncrimentation -= 0.1;


//            this.setTranslateX(carX);
//            this.setTranslateY(carY);
//            this.setRotate(carAngle);
//
//        }));
//        timelineCar.setCycleCount(Animation.INDEFINITE);
//        timelineCar.play();


    }

//TODO
//method for nie wychodzenie poza tor --> kolorki
//method for odbijanie od ściany --> kolizje

    //methods for movement - copied from player
    // if the method has "Applied Force", the car is not only moving but is also increasing it's speed
    //if the method has "Neg Force" the car is moving but due to the negative force, it is slowing down its movement
    protected boolean canMoveOnSelf(double xStart, double xEnd, double yStart, double yEnd) {
        if (carX <= xStart) {
            return false;
        }
        if(carX >= xEnd){
            return false;
        }
        if(carY <= yStart){
            return false;
        }
        if(carY >= yEnd){
            return false;
        }
        return true;
    }

    protected void moveForwardAppliedForce(){
        carX += speed * Math.cos(Math.toRadians(carAngle))* speedIncrimentation;
        carY += speed * Math.sin(Math.toRadians(carAngle))*speedIncrimentation;
        speedUP();
        //System.out.println(speedIncrimentation);
    }

    protected void moveForwardNegForce(){
        carX += speed * Math.cos(Math.toRadians(carAngle))* speedIncrimentation;
        carY += speed * Math.sin(Math.toRadians(carAngle))*speedIncrimentation;
        slowDown();
    }

    protected void moveBackwardsAppliedForce(){
        carX -= speed * Math.cos(Math.toRadians(carAngle))*speedIncrimentationBackwards;
        carY -= speed * Math.sin(Math.toRadians(carAngle))*speedIncrimentationBackwards;
        speedUpBackwards();
        System.out.println(speedIncrimentationBackwards);
    }

    protected void moveBackwardsNegForce(){
        carX -= speed * Math.cos(Math.toRadians(carAngle))*speedIncrimentationBackwards;
        carY -= speed * Math.sin(Math.toRadians(carAngle))*speedIncrimentationBackwards;
        slowDownBackwards();
    }

    protected void turnLeft(){
        // Rotate left
        //+-->-
        carAngle -= rotationSpeed;
        //- --> +
        carX += speed * Math.cos(Math.toRadians(carAngle));
        carY += speed * Math.sin(Math.toRadians(carAngle));
        if (moveForward){
            slowDown();
        }
        if (moveBackwards){
            slowDownBackwards();
        }
    }

    protected void turnRight() {
        carAngle += rotationSpeed;
        //- --> +
        carX += speed * Math.cos(Math.toRadians(carAngle));
        carY += speed * Math.sin(Math.toRadians(carAngle));
        if (moveForward) {
            slowDown();
        }
        if (moveBackwards) {
            slowDownBackwards();
        }
    }

    protected void speedUP() {
        speedIncrimentation += 0.1;
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


    protected boolean BonusCollision(Bonus bonus){
        if (this.intersects(bonus.getX(), bonus.getY(), bonus.getFitWidth(), bonus.getFitHeight())){
            return true;
        }
        return false;
    }




    protected void kolorMaski(){
        Image maska = new Image("file:maska.png");
        PixelReader maskaReader = maska.getPixelReader();
        Color maskaColor = maskaReader.getColor((int) carX, (int) carY);
//        if (maskaColor == Color.WHITE){
//
//        }
        if(maskaColor == Color.color(1,0.16,0.18));



    }
    //later add variations of move on different surfaces, with slightly different parameters
}
