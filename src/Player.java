//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//
//public class Player extends Car {
//
//    static Image image = new Image("file:imageStart//car.png");
//    static ImageView imageView  = new ImageView(image);;
//
//
//
//    public Player(double x, double y) {
//
//        super(x, y, imageView);
//
//    }
//
//    public static ImageView getImageView() {
//        return imageView;
//    }
//}


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class Player extends Car{

    static Image image = new Image("file:Tokyo/car.png");
    private boolean moveForward;
    private boolean moveRight;
    private boolean moveLeft;
    private boolean moveBackwards;

    public Player(double x,double y) {
        super(x, y, image);

        TokyoDriftTheme.level1Tokyo.setOnKeyPressed(event -> {
            //ej a może zamiast if użyć while?
            //UP z DOWN zamienione bo musiałam zrobić rotate imageView
            //i right z left też
            if  (event.getCode() == KeyCode.DOWN) {
                slowDown();
                // Move backward
                moveBackwards = true;

            }
            if (event.getCode() == KeyCode.UP) {
                speedUP();

                moveForward = true;


            }
            if (event.getCode() == KeyCode.LEFT) {
                //turnRight();

                moveLeft = true;


            }
            if (event.getCode() == KeyCode.RIGHT) {
                //turnLeft();
                moveRight = true;

            }



        });

        TokyoDriftTheme.level1Tokyo.setOnKeyReleased(event -> {

            if  (event.getCode() == KeyCode.DOWN) {
                slowDown();
                // Move backward
                moveBackwards = false;

            }
            if (event.getCode() == KeyCode.UP) {
                speedUP();

                moveForward = false;


            }
            if (event.getCode() == KeyCode.LEFT) {
                //turnRight();

                moveLeft = false;


            }
            if (event.getCode() == KeyCode.RIGHT) {
                //turnLeft();
                moveRight = false;

            }
        });

        Timeline timelineCar = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            if (moveBackwards){
                carX -= speed * Math.cos(Math.toRadians(carAngle)*speedIncrimentation);
                carY -= speed * Math.sin(Math.toRadians(carAngle))*speedIncrimentation;
                speedUP();
            }
            if (moveForward){
                carX += speed * Math.cos(Math.toRadians(carAngle))* speedIncrimentation;
                carY += speed * Math.sin(Math.toRadians(carAngle));
            }
            if (moveLeft){
                // Rotate left
                //+-->-
                carAngle -= rotationSpeed;
                //- --> +
                carX += speed * Math.cos(Math.toRadians(carAngle));
                carY += speed * Math.sin(Math.toRadians(carAngle));
            }

            if (moveRight){
                carAngle += rotationSpeed;
                //- --> +
                carX += speed * Math.cos(Math.toRadians(carAngle));
                carY +=speed * Math.sin(Math.toRadians(carAngle));
            }


            this.setTranslateX(carX);
            this.setTranslateY(carY);
            this.setRotate(carAngle);

        }));
        timelineCar.setCycleCount(Animation.INDEFINITE);
        timelineCar.play();


    }}


