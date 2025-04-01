import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyCode;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
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
    Image maska = new Image("file:maskaNowa.png");
    private Color surface;



    public boolean rightWay;
    public boolean finish;
    public static boolean start;
    public static int lap = 0;






    //here changed car initial position
    public double carX = -100;  // Initial X position
    public double carY = -200;  // Initial Y position
    protected double carAngle = 0; // Initial rotation angle (in degrees)
    //NOTE HOW SPEED SHOULD NOT BE CHANGED (UNLESS FOR NITRO MODE OBVI)
    protected double speed = 0.5; // Movement speed   //3
    protected final double rotationSpeed = 1; // Rotation speed (degrees) //5
    protected  double speedIncrimentation = 0.0001; //1.0000
    protected  double speedIncrimentationBackwards = 0.0001;

    protected boolean checkpointPOM = false;
    protected boolean checkJEDEN = false;
    protected boolean checkDWA = false;
    protected boolean checkpointZIEL = false;
    protected boolean checkpointRED = false;
    protected boolean checkpointFIOL = false;
//    protected boolean
    boolean moveBackwards;
    static boolean moveForward;
    boolean moveLeft;
    boolean moveRight;
    Rectangle checkpoint1 = new Rectangle(1019, 364, 160, 1);
    Rectangle checkpoint2 = new Rectangle(31, 343, 153, 1);
    Rectangle meta = new Rectangle(264, 30, 40, 110);

    private enum Surface {
        ASFALT, SNOW, GRUZ
    }

    public Car(double x, double y, Image imageView) {
        super(imageView);
//        System.out.println("created");
        this.carX = x;
        this.carY = y;



        this.setTranslateX(carX);
        this.setTranslateY(carY);
        this.setRotate(carAngle);
//        mask.setVisible(false);
//        TokyoDriftTheme.level1Root.getChildren().add(mask);
        if (carX >= Main.WIDTH){
            carX = Main.WIDTH-10;
        }
        if (carX <= 1){
            carX = 10;
        }
        if (carY >= Main.HEIGHT){
            carY = Main.HEIGHT-10;
        }
        if (carY <= 1){
            carY = 10;
        }

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
        kolorMaski();
        checkpoint();
    }



    protected void moveBackwardsAppliedForce(){
        carX -= speed * Math.cos(Math.toRadians(carAngle))*speedIncrimentationBackwards;
        carY -= speed * Math.sin(Math.toRadians(carAngle))*speedIncrimentationBackwards;
        speedUpBackwards();
        //System.out.println(speedIncrimentationBackwards);
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


//so i think here is the code for speeding up when the Player is in Nitro (part of the code at least)
    protected void EnterNitroModeWroom(){
        speed *=5;
//        System.out.println("EnterNitroModeWroom");
    }

    protected void ExitNitroModeNotWroom(){
        speed = 0.5;
    }

    private void checkpoint() {
        if (checkpoint1.intersects(carX, carY, 10, 10)){
            checkJEDEN = true;
        }
        if (checkpoint2.intersects(carX, carY, 10, 10)){
            checkDWA = true;
        }
        if (meta.intersects(carX, carY, 10, 10) && checkJEDEN && checkDWA){
            finalCheckMetaLAP();
        }
    }

    private void kolorMaski() {
        if (!Main.activeSamouczekScene) {
//            System.out.println("kolorMaski() method called!");
            PixelReader maskaReader = maska.getPixelReader();
            if (maskaReader == null) {
                System.out.println("Error: Cannot read pixels from the mask image.");
                return;
            }


            int x = (int) carX;
            int y = (int) carY;
            //System.out.println(carX + "     " + carY);
            if (x < 0 || y < 0 || x >= maska.getWidth() || y >= maska.getHeight()) {
                System.out.println("Error: Coordinates out of bounds.");
                return;
            }

            Color maskaColor = maskaReader.getColor(x, y);
//            System.out.println(maskaColor);

            if (maskaColor.equals(Color.rgb(0, 0, 0, 1))) {
                System.out.println("BLACK detected.");
                collide();
            }

//            else if (maskaColor.equals(Color.rgb(255, 100, 0, 1))) {
////            checkpointFIRST();
//                checkpointPOM = true;
//                return;
////            System.out.println("ORANGE detected.");
//            }
//
//            else if (maskaColor.equals(Color.rgb(255, 0, 255, 1))) {
//                System.out.println("PURPLE detected.");
//                checkpointFIOL = true;
//                lap++;
//                if (checkJEDEN && checkDWA) {
//                    finalCheckMetaLAP();
//                }
//                return;
//
//            }
//
//            else if (maskaColor.equals(Color.rgb(255, 41, 47, 1))) {
//                if (checkpointZIEL) {
//                    checkpointRED = true;
//                    checkJEDEN = true;
//                    checkDWA = true;
//                }
//                System.out.println("RED detected.");
//                return;
//            }
//
//            else if (maskaColor.equals(Color.rgb(100, 255, 0, 1))) {
//                checkpointZIEL = true;
//                System.out.println("GREEN detected.");
//                return;
//            }

        }
    }

    private void finalCheckMetaLAP() {
        lap++;
        System.out.println("lap                 lap            lap" + lap);
        checkpointPOM = false;
        checkpointZIEL = false;
        checkpointFIOL = false;
        checkpointRED = false;
        checkJEDEN = false;
        checkDWA = false;
//        System.out.println("NEEEEEEEEEEEEEEEWWWWWWWWWWWWWWWWWWWWWWWWWW LLLLLLLLLLLLLLLAP");
//        TokyoDriftTheme.level1Root.getChildren().remove(TokyoDriftTheme.lapText);
//        TokyoDriftTheme.lapTimerText = new Text("" + lap);
//        TokyoDriftTheme.level1Root.getChildren().add(TokyoDriftTheme.lapText);
    }

    private void collide() {
//        System.out.println("Collision detected!");
        slowDown();

//        carAngle = getRotate() + Math.abs(360 - 2*getRotate());
        carAngle = getRotate() + 20;
        // Add collision handling logic here
    }

    //later add variations of move on different surfaces, with slightly different parameters
}
