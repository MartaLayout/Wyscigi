import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet extends ImageView {
    double dx;
    double dy;

    public static final Image IMAGE = new Image("file:Tokyo/shoot.png");

    public Bullet(double dx, double dy, double x, double y) {
        super(IMAGE);
        setLayoutX(x);
        setLayoutY(y);
        this.dx = dx;
        this.dy = dy;
    }

    public void move(){
       setLayoutX(getLayoutX() + dx);
       setLayoutY(getLayoutY() + dy);
    }

    public boolean outOfPlansza(){
//        if(getLayoutX() )
        return true;
    }
}
