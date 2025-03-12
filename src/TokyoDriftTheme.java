import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static javafx.animation.Animation.INDEFINITE;
import static javafx.scene.text.Font.font;


public class TokyoDriftTheme {
    //hubhubhu
    static Text timerText = new Text(" ");
    static int seconds = 0;
    static int minutes = 0;
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

        rootTokyoStart.getChildren().addAll(imageViewStartTokyo, rectangleLevel1, rectangleLevel2, rectangleLevel3, menuTokyo);

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
      
        timerText.setX(337);
        timerText.setY(805);
        Font font = Font.loadFont("file:Minecraftia-Regular.ttf",25);
        timerText.setFont(font);
        timerText.setFill(Color.MAGENTA);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> updateTimer())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();



        Player player = new Player(200,298);
        //player.setRotate(180);

        Image imageTowerShooter = new Image("file:Tokyo/shooter.png");
        Shooter shooter = new Shooter(288, 347, imageTowerShooter);
        Timeline bulletTimeline = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            Shooter.bullets(player.getTranslateX() + 20,player.getTranslateY() + 20,player.getX(),player.getY());
            shooter.setRotate(-30);




        }));
        bulletTimeline.setCycleCount(INDEFINITE);
        bulletTimeline.play();

        Rectangle rectangle = new Rectangle(10, 10);


        level1.getChildren().addAll(imageViewTorLevel1Tokyo, rectangle, player, shooter, menuTokyoDrift,timerText);

        //Scene startSceneTokyo = new Scene(level1, Main.WIDTH, Main.HEIGHT);
        Main.stage.setTitle("Level 1 Tokyo");
        Main.stage.setScene(level1Tokyo);

        Rectangle checkpointMeta = new Rectangle(227, 16, 50, 255);
        Rectangle checkpoint = new Rectangle(227, 16, 25, 255);


    }

    private static void updateTimer() {
        seconds++;
        if ( seconds >= 0 && seconds < 10) {
            timerText.setText(minutes + ":0" + seconds);
        } else {
            if (seconds > 59) {
                seconds = 0;
                minutes++;
            }
            timerText.setText(minutes + ":" + seconds);
        }

    }}


