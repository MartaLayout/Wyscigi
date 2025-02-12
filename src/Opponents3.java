import javafx.scene.image.Image;
//Pojazd trzeci porusza się podobnie do mistrza prostej, ale stara się zawsze dogonić gracza najkrótszą trasą
// i uderzyć go z boku aby wybić go ze ścieżki ruchu.

public class Opponents3 extends Opponents implements OpponentsAI{
    public Opponents3(double x, double y, Image imageView) {
        super(x, y, imageView);
    }

    @Override
    public void move() {

    }
}
