import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class Puddle extends ImageView implements Obstacle{


    public static final Image waterPuddle = new Image("file:Tokyo/przeszkody/kaluzaWoda.png");
    public static final Image oilPuddle = new Image("file:Tokyo/przeszkody/plamaOleju.png");
    public static List<Puddle> puddleList = new ArrayList<>();
    public static AnchorPane root;
    public int timer;

    public static void puddleFactory(AnchorPane anchorPane){
        root = anchorPane;
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            Puddle puddle = new Puddle(Math.random() * Main.WIDTH, Math.random() * (Main.HEIGHT - 200), Math.random() < 0.5 );
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


    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }
}

