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

    private int nitroMode = 0;



    static Timeline timelineCar;

    public Player(double x,double y) {
        super(x, y, image);

        //do samouczka ruszanie
        //TODO żeby działało
//        if(canMoveOnSelf(Main.torMalutkiSamouczek.getLayoutX(),
//                (Main.torMalutkiSamouczek.getLayoutX() + Main.torMalutkiSamouczek.getWidth()),
//                Main.torMalutkiSamouczek.getLayoutY(),
//                (Main.torMalutkiSamouczek.getLayoutY() + Main.torMalutkiSamouczek.getHeight()))) {
        if(Main.activeSamouczekScene) {
            //pressed
            Main.samouczekScene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.DOWN) {
                    //slowDown();
                    // Move backward
                    moveBackwards = true;
                }
                if (event.getCode() == KeyCode.UP) {
                    //speedUP();
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
            //released
            Main.samouczekScene.setOnKeyReleased(event -> {

                if (event.getCode() == KeyCode.DOWN) {
                    // Move backward
                    moveBackwards = false;
                }
                if (event.getCode() == KeyCode.UP) {
                    //speedUP();
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
            //     }
        } else if (TokyoDriftTheme.activeLevel1Scene) {
            TokyoDriftTheme.level1TokyoScene.setOnKeyPressed(event -> {
                //UP z DOWN zamienione bo musiałam zrobić rotate imageView
                //i right z left też
                if (event.getCode() == KeyCode.DOWN) {
                    //slowDown();
                    // Move backward
                    moveBackwards = true;
                    TokyoDriftTheme.start = true;

                }
                if (event.getCode() == KeyCode.UP) {
                    //speedUP();
                    moveForward = true;
                    TokyoDriftTheme.start = true;

                }
                if (event.getCode() == KeyCode.LEFT) {
                    //turnRight();
                    moveLeft = true;
                    TokyoDriftTheme.start = true;

                }
                if (event.getCode() == KeyCode.RIGHT) {
                    //turnLeft();
                    moveRight = true;
                    TokyoDriftTheme.start = true;

                }
            });

            TokyoDriftTheme.level1TokyoScene.setOnKeyReleased(event -> {
                if (event.getCode() == KeyCode.DOWN) {
                    // Move backward
                    moveBackwards = false;
                }
                if (event.getCode() == KeyCode.UP) {
                    //speedUP();
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
        }
        timelineCar = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            if (nitroMode >0){
                nitroMode --;
            }
            if (nitroMode<=0){
                //this.ExitNitroModeNotWroom();
            }
            if (moveBackwards){
                moveBackwardsAppliedForce();
            }
            else{
                moveBackwardsNegForce();
            }
            if (moveForward){
                moveForwardAppliedForce();
            }
            else {
                moveForwardNegForce();
            }

//            if (!moveForward){
//                carX += speed * Math.cos(Math.toRadians(carAngle))* speedIncrimentation;
//                carY += speed * Math.sin(Math.toRadians(carAngle))* speedIncrimentation;
//                if (speedIncrimentation>=0){
//                    slowDown();
//                    System.out.println(speedIncrimentation);
//                }
//                else{
//                    speedIncrimentation =0;
//                }
            //}
            if (moveLeft){
                turnLeft();
            }

            if (moveRight){
                turnRight();
            }

            //            speedIncrimentation -= 0.1;

            //borders
            if (carX < 0) carX = 0;
            if (carX > 1200 - Main.playerSamouczek.getFitWidth()) carX = 1200 - Main.playerSamouczek.getFitWidth();
            if (carY < 0) carY = 0;
            if (carY > 800 - Main.playerSamouczek.getFitHeight()) carY = 800 - Main.playerSamouczek.getFitHeight();

            this.setTranslateX(carX);
            this.setTranslateY(carY);
            this.setRotate(carAngle);

            //checking if player intersects puddle
            for (int i = 0; i < Puddle.puddleList.size(); i++) {
                if (Puddle.puddleList.get(i).collision(this)){
                    this.slowDown();
                }
            }

        }));
        timelineCar.setCycleCount(Animation.INDEFINITE);
        timelineCar.play();


    }


    protected boolean BonusCollision(Bonus bonus){
        if (this.intersects(bonus.getX(), bonus.getY(), bonus.getFitWidth(), bonus.getFitHeight())){
           if (bonus.getCollectedCounter() == 10){
               nitroMode = 500;
               //this.EnterNitroModeWroom();
               bonus.setCollectedCounter(0);
           }
            return true;
        }
        return false;
    }




}





