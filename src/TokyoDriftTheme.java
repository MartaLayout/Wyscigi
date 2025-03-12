import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.util.Timer;

import static javafx.animation.Animation.INDEFINITE;


public class TokyoDriftTheme {
    //hubhubhu

    static final ImageView background = new ImageView(new Image("file:")); //add
    static AnchorPane rootTokyoStart = new AnchorPane();
    static boolean activeGenerateScene = false;
    static boolean activeLevel1Scene = false;
    static boolean timerON = true;
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
        Player player1 = new Player(200,298);

        rootTokyoStart.getChildren().addAll(imageViewStartTokyo, rectangleLevel1, rectangleLevel2, rectangleLevel3, menuTokyo, player1);

        Scene startSceneTokyo = new Scene(rootTokyoStart, Main.WIDTH, Main.HEIGHT);
        Main.stage.setTitle("TokyoDrift Start");
        Main.stage.setScene(startSceneTokyo);
    }

    public static void level1(){
        ImageView imageViewTorLevel1Tokyo = new ImageView(new Image("file:Tokyo/torduzy.png"));
        imageViewTorLevel1Tokyo.setFitHeight(800);
        imageViewTorLevel1Tokyo.setFitWidth(1200);

        ImageView menuTokyoDrift = new ImageView(new Image("file:imagesStart/menuSamouczek.png"));
        menuTokyoDrift.setLayoutX(1010);
        menuTokyoDrift.setLayoutY(730);
        menuTokyoDrift.setFitWidth(100);
        menuTokyoDrift.setFitHeight(50);
        menuTokyoDrift.setOnMouseClicked(event -> {
            activeLevel1Scene = true;
            Main.menu();
        });
        Main.activeChooseThemeScene = false;
        Main.activeFabulaScene = false;
        Main.activeSamouczekScene = false;
        activeGenerateScene = false;
        activeLevel1Scene = false;
        Text timerText = new Text(" ");
        timerText.setX(500);
        timerText.setY(500);
        timerText.setFont(Font.font(30));

        // nati bawi sie timerem tu
        if (timerON) {
            int interval = 0;
            int min = 0;

            for (int i = 0; i < 60; i++) {
                interval++;
            }
            if(interval == 60) {
                interval = 0;
                min++;
            }
            timerText.setText(min + ":" + interval);
        }


        Player player = new Player(200,298);
        //player.setRotate(180);

        Image imageTowerShooter = new Image("file:Tokyo/shooter.png");
        Shooter shooter = new Shooter(288, 347, imageTowerShooter);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            Shooter.bullets(player.getTranslateX() + 20,player.getTranslateY() + 20,player.getX(),player.getY());
            shooter.setRotate(-30);




        }));
        timeline.setCycleCount(INDEFINITE);
        timeline.play();

        Rectangle rectangle = new Rectangle(10, 10);


        level1.getChildren().addAll(imageViewTorLevel1Tokyo, rectangle, player, shooter, menuTokyoDrift,timerText);

        //Scene startSceneTokyo = new Scene(level1, Main.WIDTH, Main.HEIGHT);
        Main.stage.setTitle("Level 1 Tokyo");
        Main.stage.setScene(level1Tokyo);

        Rectangle checkpointMeta = new Rectangle(227, 16, 50, 255);
        Rectangle checkpoint = new Rectangle(227, 16, 25, 255);


    }




}
