import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class Player extends Car{

    static Image image = new Image("file:Tokyo/cars/car.png");
    private boolean moveForward;
    private boolean moveRight;
    private boolean moveLeft;
    private boolean moveBackwards;

    private int nitroMode = 0;



    static Timeline timelineCar;

    public Player(double x,double y) {
        super(x, y, image);

        if (Main.activeSamouczekScene) {
            Main.samouczekScene.setOnKeyPressed(event -> {
                if (carX < 0) carX = 0;
                if (carX > 1200 - Main.playerSamouczek.getFitWidth()) carX = 1200 - Main.playerSamouczek.getFitWidth();
                if (carY < 0) carY = 0;
                if (carY > 800 - Main.playerSamouczek.getFitHeight()) carY = 800 - Main.playerSamouczek.getFitHeight();

                if (event.getCode() == KeyCode.DOWN) {
                    moveBackwards = true;
                }
                if (event.getCode() == KeyCode.UP) {
                    moveForward = true;
                }
                if (event.getCode() == KeyCode.LEFT) {
                    moveLeft = true;
                }
                if (event.getCode() == KeyCode.RIGHT) {
                    moveRight = true;
                }
            });
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
        } //koniec Main
            else if (TokyoDriftTheme.activeLevel1Scene) {
                TokyoDriftTheme.level1TokyoScene.setOnKeyPressed(event -> {
                //UP z DOWN zamienione bo musiałam zrobić rotate imageView
                //i right z left też

                    BonusCollision(TokyoDriftTheme.bonus);
                    if (event.getCode() == KeyCode.DOWN) {
                    moveBackwards = true;
                    TokyoDriftTheme.start = true;
                    }
                    if (event.getCode() == KeyCode.UP) {
                        moveForward = true;
                        TokyoDriftTheme.start = true;
                    }
                    if (event.getCode() == KeyCode.LEFT) {
                    moveLeft = true;
                    TokyoDriftTheme.start = true;
                    }
                    if (event.getCode() == KeyCode.RIGHT) {
                    moveRight = true;
                    TokyoDriftTheme.start = true;
                    }
                });
                TokyoDriftTheme.level1TokyoScene.setOnKeyReleased(event -> {
                    if (event.getCode() == KeyCode.DOWN) {
                    moveBackwards = false;
                    }
                    if (event.getCode() == KeyCode.UP) {
                    moveForward = false;
                    }
                    if (event.getCode() == KeyCode.LEFT) {
                    moveLeft = false;
                    }
                    if (event.getCode() == KeyCode.RIGHT) {
                    moveRight = false;
                    }
                });
            } //koniec level1
            else if (TokyoDriftTheme.activeLevel2Scene) {
                TokyoDriftTheme.level2TokyoScene.setOnKeyPressed(event -> {
                     if (event.getCode() == KeyCode.DOWN) {
                        moveBackwards = true;
                        TokyoDriftTheme.start = true;
                     }
                     if (event.getCode() == KeyCode.UP) {
                        moveForward = true;
                        TokyoDriftTheme.start = true;
                     }
                     if (event.getCode() == KeyCode.LEFT) {
                        moveLeft = true;
                        TokyoDriftTheme.start = true;
                     }
                     if (event.getCode() == KeyCode.RIGHT) {
                         moveRight = true;
                        TokyoDriftTheme.start = true;
                     }
                });
                TokyoDriftTheme.level2TokyoScene.setOnKeyReleased(event -> {
                    if (event.getCode() == KeyCode.DOWN) {
                        moveBackwards = false;
                    }
                    if (event.getCode() == KeyCode.UP) {
                        moveForward = false;
                    }
                    if (event.getCode() == KeyCode.LEFT) {
                        moveLeft = false;
                    }
                    if (event.getCode() == KeyCode.RIGHT) {
                        moveRight = false;
                    }
                });
            } //koniec level2
            else if (TokyoDriftTheme.activeLevel3Scene) {
                TokyoDriftTheme.level3TokyoScene.setOnKeyPressed(event -> {
                    if (event.getCode() == KeyCode.DOWN) {
                        moveBackwards = true;
                        TokyoDriftTheme.start = true;
                    }
                    if (event.getCode() == KeyCode.UP) {
                        moveForward = true;
                        TokyoDriftTheme.start = true;
                    }
                    if (event.getCode() == KeyCode.LEFT) {
                        moveLeft = true;
                        TokyoDriftTheme.start = true;
                    }
                    if (event.getCode() == KeyCode.RIGHT) {
                        moveRight = true;
                        TokyoDriftTheme.start = true;
                    }
                });
                TokyoDriftTheme.level3TokyoScene.setOnKeyReleased(event -> {
                    if (event.getCode() == KeyCode.DOWN) {
                        moveBackwards = false;
                    }
                    if (event.getCode() == KeyCode.UP) {
                        moveForward = false;
                    }
                    if (event.getCode() == KeyCode.LEFT) {
                        moveLeft = false;
                    }
                    if (event.getCode() == KeyCode.RIGHT) {
                        moveRight = false;
                    }
                });
            }


        timelineCar = new Timeline(new KeyFrame(Duration.millis(10), event -> {
            if (nitroMode >0){
                nitroMode --;
                if (nitroMode<=0){
                    this.ExitNitroModeNotWroom();
                }
            }


            if (moveBackwards) {
                moveBackwardsAppliedForce();
            }
            else {
                moveBackwardsNegForce();
            }
            if (moveForward) {
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
                if (moveLeft) {
                    turnLeft();
                }

                if (moveRight) {
                    turnRight();
                }

                //            speedIncrimentation -= 0.1;


                this.setTranslateX(carX);
                this.setTranslateY(carY);
                this.setRotate(carAngle);

            //checking if player intersects puddle
            for (int i = 0; i < Puddle.puddleList.size(); i++) {
                Puddle.puddleList.get(i).setTimer(Puddle.puddleList.get(i).getTimer() -1);
                if (Puddle.puddleList.get(i).collision(this)){
                    this.slowDown();
                    System.out.println("SPEED:" + speed);

                }
                if (Puddle.puddleList.get(i).getTimer() <= 0){
                    Puddle.puddleList.get(i).delete();
                    System.out.println("SHOULD BE DELETED");
                }
            }

            }));
            timelineCar.setCycleCount(Animation.INDEFINITE);
            timelineCar.play();


        }


    protected boolean BonusCollision(Bonus bonus){

        if  (this.getBoundsInParent().intersects(bonus.getBoundsInParent())){

            bonus.appear();
            System.out.println("first if - sees collision");
            System.out.println(bonus.getCollectedCounter());

            if (bonus.getCollectedCounter() >= 10){
                System.out.println("second if - sees the change in the counter");
                //normally the if condition would be 10, but for the sake of testing its 1
                nitroMode = 500;
                this.EnterNitroModeWroom();
                System.out.println("speed" + speed);
                bonus.setCollectedCounter(0);
                System.out.println("if entered");
           }
            return true;
        }

        return false;
    }

}




