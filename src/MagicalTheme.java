import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MagicalTheme {

    static final ImageView background = new ImageView(new Image("file:")); //add

    public static void generateMagical(){
        AnchorPane rootMagicalStart = new AnchorPane();
        ImageView imageViewStartMagical = new ImageView(new Image("file:")); //add



        rootMagicalStart.getChildren().addAll(imageViewStartMagical);

        Scene startSceneMagical = new Scene(rootMagicalStart, Main.WIDTH, Main.HEIGHT);
        Main.stage.setTitle("Magical Start");
        Main.stage.setScene(startSceneMagical);

    }

}
