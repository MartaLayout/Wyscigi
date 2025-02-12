import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.*;


public class TokyoDriftTheme {
    //hubhubhu

    static final ImageView background = new ImageView(new Image("file:")); //add
    static AnchorPane rootTokyoStart = new AnchorPane();
    static boolean activeGenerateScene = false;
    static AnchorPane level1 = new AnchorPane();
    static Scene level1Tokyo = new Scene(level1, Main.WIDTH, Main.HEIGHT);

    public static void generateTokyo(){
        ImageView imageViewStartTokyo = new ImageView(new Image("file:")); //add

        Rectangle rectangleLevel1 = new Rectangle(100, 100, 100, 100);
        rectangleLevel1.setOnMouseClicked(event -> {
            level1();
        });
        Rectangle rectangleLevel2 = new Rectangle(400, 100, 100, 100);
        Rectangle rectangleLevel3 = new Rectangle(700, 100, 100, 100);

        Button menuTokyo = new Button("MENU");
        menuTokyo.setLayoutX(1088);
        menuTokyo.setLayoutY(10);
        menuTokyo.setPrefSize(100, 50);
        menuTokyo.setOnAction(event -> {
            activeGenerateScene = true;
            Main.menu();
        });
        Main.activeChooseThemeScene = false;
        Main.activeFabulaScene = false;
        Main.activeSamouczekScene = false;
        activeGenerateScene = false;

        rootTokyoStart.getChildren().addAll(imageViewStartTokyo, rectangleLevel1, rectangleLevel2, rectangleLevel3, menuTokyo);

        Scene startSceneTokyo = new Scene(rootTokyoStart, Main.WIDTH, Main.HEIGHT);
        Main.stage.setTitle("TokyoDrift Start");
        Main.stage.setScene(startSceneTokyo);
    }

    public static void level1(){
        ImageView imageViewTorLevel1Tokyo = new ImageView(new Image("file:Tokyo/torInfinity1.png"));

        Player player = new Player(200,298);
        //player.setRotate(180);

        Image imageTowerShooter = new Image("file:Tokyo/shooter.png");
        Shooter shooter = new Shooter(288, 347, imageTowerShooter);
        Shooter.bullets(300, 300, player.getX(), player.getY());

        Rectangle rectangle = new Rectangle(10, 10);


        level1.getChildren().addAll(imageViewTorLevel1Tokyo, rectangle, player, shooter);

        //Scene startSceneTokyo = new Scene(level1, Main.WIDTH, Main.HEIGHT);
        Main.stage.setTitle("Level 1 Tokyo");
        Main.stage.setScene(level1Tokyo);


    }




}
