import javafx.scene.image.Image;

public class Opponents extends Car{

    public Opponents(double x, double y, Image imageView) {
        super(x, y, imageView);


    }

 // Pojazd pierwszy porusza się z maksymalną prędkością po liniach prostych i mocno hamuje przed każdym zakrętem
    private static void Opponent1 (){

    }

// Pojazd drugi porusza się ze stałą średnią prędkością, ale stara się trzymać środka toru
    private static void Opponent2(){

    }

//Pojazd trzeci porusza się podobnie do mistrza prostej, ale stara się zawsze dogonić gracza najkrótszą trasą i uderzyć go z boku aby wybić go ze ścieżki ruchu.
    private static void Opponent3(){

    }

//Pojazd czwarty również dogania gracza jednak robi to jak pojazd drugi, blisko środka
//toru i z maksymalną prędkością, ale kiedy go prześcignie zwalnia i stara się zajechać
//mu drogę wymuszając uderzenie w swój tył. Jeśli odległość do pozycji gracza jest
// zbyt duża, komputer może zdecydować, że “poczeka” jadąc bardzo wolno. Nie zrobi tego kiedy gracz będzie wykonywał ostatnie okrążenie
    private static void Opponent4(){

    }
}
