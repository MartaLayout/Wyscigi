import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

public class Bonus extends ImageView {
    // class specifically for tryb nitro aka if you collect like ten of these, you get nitro activated immediately

    //at this point i think Imma just make it so that you can have one bonus at the same time on the screen
    // aka only if you pick one up can new one appear and have a counter for how many times has one been picked up
    //it will reset to zero once you enter nitro

    private static int collectedCounter;
    static Image image = new Image("file:Tokyo/Energy4Nitro.png");

    private Image maska = new Image("file:maska.png");
    private PixelReader maskaReader = maska.getPixelReader();

    public Bonus() {
        super(image);
        setLayoutX(100);
        setLayoutY(500);
        setFitHeight(50);
        setFitWidth(50);

    }
    public static int getCollectedCounter() {
        return collectedCounter;
    }

    public static void setCollectedCounter(int collectedCounter) {
        Bonus.collectedCounter = collectedCounter;
    }

    public void appear(){
        double NewX = Math.random()*Main.WIDTH;
        double NewY = Math.random()*Main.HEIGHT;
        boolean foundNewPlace = false;
        while (foundNewPlace == false){

            PixelReader maskaReader = maska.getPixelReader();
            if (maskaReader == null) {
                System.out.println("Error: Cannot read pixels from the mask image.");
                return;
            }


            Color maskaColor = maskaReader.getColor((int) (NewX - this.getFitWidth() - 10), (int) (NewY - this.getFitHeight()));
//            System.out.println("("+ NewX + NewY+ ")"+ maskaColor);
            if (maskaColor.equals(Color.rgb(0, 0, 0, 1))){
                NewX = Math.random()*Main.WIDTH;
                NewY = Math.random()*Main.HEIGHT;
            }
            else{
                foundNewPlace = true;
                TokyoDriftTheme.level1Root.getChildren().remove(this);
                setLayoutX(NewX);
                setLayoutY(NewY);
                TokyoDriftTheme.level1Root.getChildren().add(this);
                collectedCounter ++;


            }
        }
    }
}