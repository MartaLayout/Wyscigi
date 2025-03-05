import javafx.scene.image.Image;
// Pojazd pierwszy porusza się z maksymalną prędkością po liniach prostych i mocno hamuje przed każdym zakrętem

//sprawdza w pętli pod spodem ile jest pixeli do sciany --> <200 zwalnia a jak jest 50 od sciany to skreca
//sprawdzamy w którą stronę się obracamy
public class Opponent1 extends Opponents implements OpponentsAI{
    public Opponent1(double x, double y, Image imageView) {
        super(x, y, imageView);
    }

    @Override
    public void move() {

    }

    @Override
    public void collide() {

    }
}
