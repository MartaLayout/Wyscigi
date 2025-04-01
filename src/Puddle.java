import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class Puddle extends ImageView implements Obstacle{


    public static final Image waterPuddle = new Image("file:Tokyo/przeszkody/kaluzaWoda.png");
    public static final Image oilPuddle = new Image("file:Tokyo/przeszkody/plamaOleju.png");
    public static List<Puddle> puddleList = new ArrayList<>();
    public static AnchorPane root;
    public int timer;

    private Image maska = new Image("file:maska.png");

    public static void puddleFactory(AnchorPane anchorPane){
        root = anchorPane;
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            Puddle puddle = new Puddle(Math.random() * Main.WIDTH, Math.random() * (Main.HEIGHT - 200), Math.random() < 0.5 );
            puddle.getPuddlesInRightRange();
            puddleList.add(puddle);
            root.getChildren().add(puddle);

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }


    @Override
    public void movement() {

    }

    @Override
    public void delete() {
        puddleList.remove(this);
        root.getChildren().remove(this);

    }

    @Override
    public boolean collision(Car car) {
        return this.getBoundsInParent().intersects(car.getBoundsInParent());
    }


    public Puddle(double x, double y, boolean isOil) {
        super();
        int random = (int) (Math.random()*300 + 300);
        if (isOil){
            setImage(oilPuddle);
            setLayoutX( x - oilPuddle.getWidth() / 2);
            setLayoutY(y - oilPuddle.getHeight() / 2);
            timer = random;
        }
        else {
            setImage(waterPuddle);
            setLayoutX(x - waterPuddle.getWidth() / 2);
            setLayoutY(y - waterPuddle.getHeight() / 2);
            timer = random;
        }

    }

    public void getPuddlesInRightRange(){
        double NewX = Math.random()*Main.WIDTH;
        double NewY = Math.random()*Main.HEIGHT;
        boolean foundNewPlace = false;
        while (foundNewPlace == false){

            PixelReader maskaReader = maska.getPixelReader();
            if (maskaReader == null) {

                return;
            }


            Color maskaColor = maskaReader.getColor((int) (NewX - this.getFitWidth()), (int) (NewY - this.getFitHeight()));
//            System.out.println("("+ NewX + NewY+ ")"+ maskaColor);
            if (maskaColor.equals(Color.rgb(0, 0, 0, 1))){
                NewX = Math.random()*Main.WIDTH;
                NewY = Math.random()*Main.HEIGHT;
            }
            else{
                foundNewPlace = true;
                TokyoDriftTheme.level1Root.getChildren().remove(this);
                setLayoutX(NewX);
                setLayoutY(NewY);
                TokyoDriftTheme.level1Root.getChildren().add(this);



            }
        }
    }


    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}

