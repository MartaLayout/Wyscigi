import javafx.scene.image.Image;
// Pojazd drugi porusza się ze stałą średnią prędkością, ale stara się trzymać środka toru

public class Opponent2 extends Opponents implements OpponentsAI{
    public Opponent2(double x, double y, Image imageView) {
        super(x, y, imageView);
    }

    @Override
    public void move() {

    }
}
