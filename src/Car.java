import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

public class Car extends ImageView {

    private double velocity;
    private int maxVelocity;
    private boolean isSpeeding;
    private boolean slowingDown;
    private boolean nitro;
    private double x;
    private double y;
    private double direction;

    //here chnaged car initial position
    public double carX = -100;  // Initial X position
    public double carY = -200;  // Initial Y position
    private double carAngle = 0; // Initial rotation angle (in degrees)
    private final double speed = 5; // Movement speed
    private final double rotationSpeed = 5; // Rotation speed (degrees)

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
        TokyoDriftTheme.level1Tokyo.setOnKeyPressed(event -> {
        //ej a może zamiast if użyć while?
            //UP z DOWN zamienione bo musiałam zrobić rotate imageView
            //i right z left też
            if (event.getCode() == KeyCode.DOWN) {
                slowDown();
                // Move backward
                carX -= speed * Math.cos(Math.toRadians(carAngle));
                carY -= speed * Math.sin(Math.toRadians(carAngle));
            }
            if (event.getCode() == KeyCode.UP) {
                speedUP();
                carX += speed * Math.cos(Math.toRadians(carAngle));
                carY += speed * Math.sin(Math.toRadians(carAngle));
            }
            if (event.getCode() == KeyCode.LEFT) {
                //turnRight();
                // Rotate left
                //+-->-
                carAngle -= rotationSpeed;
                //- --> +
                carX += speed * Math.cos(Math.toRadians(carAngle));
                carY += speed * Math.sin(Math.toRadians(carAngle));
            }
            if (event.getCode() == KeyCode.RIGHT) {
                //turnLeft();
                carAngle += rotationSpeed;
                //- --> +
                carX += speed * Math.cos(Math.toRadians(carAngle));
                carY +=speed * Math.sin(Math.toRadians(carAngle));
            }


            this.setTranslateX(carX);
            this.setTranslateY(carY);
            this.setRotate(carAngle);
        });

    }


    private void speedUP() {
//        x = direction * x / velocity;
//        y = direction * y / velocity;
    }

    private void slowDown() {
        //velocity--;
    }

    private void turnRight() {
//        if (isSpeeding){
//            direction += 1;
//            if (direction > 180){
//                direction = -179;
//            }
//            setRotate(direction);
//        }

    }

    private void turnLeft() {
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
