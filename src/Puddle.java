
import javafx.scene.image.ImageView;



public class Puddle extends ImageView implements Obstacle{


    public static final ImageView waterPuddle = new ImageView("file:Tokyo/przeszkody/kaluzaWoda.png");
    public static final ImageView oilPuddle = new ImageView("file:Tokyo/przeszkody/plamaOleju.png");

    @Override
    public void movement() {

    }

    @Override
    public void delete() {

    }

    @Override
    public boolean collision() {
        return true;
    }


    public Puddle() {

    }
}
