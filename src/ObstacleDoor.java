import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class ObstacleDoor {

    static private List<Image> images = new ArrayList<>();

    public static List<ObstacleDoor> doorsList = new ArrayList<>();

    static {
        for (int i = 1; i < 9; i++) {

            images.add(new Image("file:Tokyo/przeszkody/greenWall" + i + ".png"));
        }
        for (int i = 8; i > 0 ; i--) {
            images.add(new Image("file:Tokyo/przeszkody/greenWall" + i + ".png"));
        }


    }

    private static int count = 0;
    private int id;
    private ImageView imageView;
    private double x;
    private double y;

    private int currentImage;

    private Timeline timeline;

    public static AnchorPane root;


    public ObstacleDoor(double x, double y) {

        this.x = x;
        this.y = y;

        imageView = new ImageView(images.get(0));

        imageView.setX(x);
        imageView.setY(y);

        TokyoDriftTheme.level2Root.getChildren().add(imageView);

        timeline = new Timeline(new KeyFrame(Duration.millis(50), event -> {
            currentImage = (currentImage + 1) % images.size();
            imageView.setImage(images.get(currentImage));
            imageView.setFitWidth(150);
            this.collision(TokyoDriftTheme.player);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static void doorCreation() {

        for (int i = 0; i < 3; i++) {
            ObstacleDoor obstacleDoor = new ObstacleDoor(i*500+40,Main.HEIGHT/2 -20);
            doorsList.add(obstacleDoor);

        }

    }


    public void collision(Player player){
        if (this.imageView.getBoundsInParent().intersects(player.getBoundsInParent())){
            System.out.println("collision with doors");
            if (currentImage <=3 || currentImage>= 14){
                System.out.println("pass");
            }
        }
    }

}
