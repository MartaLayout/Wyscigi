import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bonus extends ImageView {
    // class specifically for tryb nitro aka if you collect like ten of these, you get nitro activated immediately

    //at this poit i think Imma just make it so that you can have one bonus at the same time on the screen
    // aka only if you pick one up can new one appear and have a counter for how many times has one been picked up
    //it will reset to zero once you enter nitro

    private int collectedCounter;
    static Image image = new Image("file:Tokyo/car.png");


    public Bonus() {
        super(image);
        setLayoutX(100);
        setLayoutY(500);
        setFitHeight(50);
        setFitWidth(50);

    }

    public void appear(){
        setLayoutX(Math.random()*Main.WIDTH);
        setLayoutY(Math.random()*Main.HEIGHT);
    }






}
