import javafx.scene.image.Image;

//Pojazd czwarty również dogania gracza jednak robi to jak pojazd drugi, blisko środka
//toru i z maksymalną prędkością, ale kiedy go prześcignie zwalnia i stara się zajechać
//mu drogę wymuszając uderzenie w swój tył. Jeśli odległość do pozycji gracza jest
// zbyt duża, komputer może zdecydować, że “poczeka” jadąc bardzo wolno. Nie zrobi tego kiedy gracz będzie wykonywał ostatnie okrążenie

public class Opponent4 extends Opponents implements OpponentsAI{
    public Opponent4(double x, double y, Image imageView) {
        super(x, y, imageView);
    }

    @Override
    public void move() {

    }

    @Override
    public void collide() {

    }
}
