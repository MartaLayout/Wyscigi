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
import javafx.scene.text.Text;
import javafx.util.Duration;

import static javafx.animation.Animation.INDEFINITE;
import static javafx.scene.text.Font.font;
import static javafx.scene.text.Font.getFamilies;


public class TokyoDriftTheme {
    //hubhubhu
    static Text timerText = new Text(" ");
    static Text lapTimerText = new Text(" ");
    static int seconds = 0;
    static int minutes = 0;
    static int lapSeconds = 0;
    static int lapMinutes = 0;
    static int lap = 0;

    static AnchorPane rootTokyoStart = new AnchorPane();
    static AnchorPane level1Root = new AnchorPane();
    static AnchorPane level2Root = new AnchorPane();
    static AnchorPane level3Root = new AnchorPane();

    static boolean activeGenerateScene = false;
    static Scene startSceneTokyo = new Scene(rootTokyoStart, Main.WIDTH, Main.HEIGHT);

    static boolean activeLevel1Scene = false;
    static Scene level1TokyoScene = new Scene(level1Root, Main.WIDTH, Main.HEIGHT);

    static boolean activeLevel2Scene = false;
    static Scene level2TokyoScene = new Scene(level2Root, Main.WIDTH, Main.HEIGHT);

    static boolean activeLevel3Scene = false;
    static Scene level3TokyoScene = new Scene(level3Root, Main.WIDTH, Main.HEIGHT);


    public static void generateTokyo(){
        Main.activeSamouczekScene = false;
        Main.activeFabulaScene = false;
        Main.activeChooseThemeScene = false;
        activeGenerateScene = true;

        activeLevel1Scene = false;
        activeLevel2Scene = false;
        activeLevel3Scene = false;


        rootTokyoStart.getChildren().clear();

        ImageView background = new ImageView(new Image("file:Tokyo/backgroundGenerate.png")); //add
        background.setFitWidth(Main.WIDTH);
        background.setFitHeight(Main.HEIGHT);
        rootTokyoStart.getChildren().add(background);


        generateTokyoSetup();

        Main.stage.setTitle("TokyoDrift Start");
        Main.stage.setScene(startSceneTokyo);
    }

    public static void generateTokyoSetup(){

        Text textNaGorze = new Text("Wybierz level :");
        Font fontTokyo = Font.loadFont("file:Minecraftia-Regular.ttf",45);
        textNaGorze.setStyle("-fx-font-weight: bold;");
        textNaGorze.setFill(Color.color(0.11, 0.54, 0.67)); //TODO Nati
        textNaGorze.setFont(fontTokyo);
        textNaGorze.setLayoutX(400);
        textNaGorze.setLayoutY(200);

        ImageView imageViewLevel1 = new ImageView(new Image("file:Tokyo/level1Cover.png"));
        imageViewLevel1.setLayoutX(100);
        imageViewLevel1.setLayoutY(300);
        imageViewLevel1.setFitHeight(300);
        imageViewLevel1.setFitWidth(300);
        imageViewLevel1.setOnMouseClicked(event -> level1());

        ImageView imageViewLevel2 = new ImageView(new Image("file:Tokyo/level2Cover.png"));
        imageViewLevel2.setLayoutX(450);
        imageViewLevel2.setLayoutY(300);
        imageViewLevel2.setFitHeight(300);
        imageViewLevel2.setFitWidth(300);
        imageViewLevel2.setOnMouseClicked(event -> level2());

        Rectangle rectangle2 = new Rectangle(450, 300, 300, 300 );
        rectangle2.setOnMouseClicked(event -> level2());

        ImageView imageViewLevel3 = new ImageView(new Image("file:Tokyo/level3Cover.png"));
        imageViewLevel3.setLayoutX(800);
        imageViewLevel3.setLayoutY(300);
        imageViewLevel3.setFitHeight(300);
        imageViewLevel3.setFitWidth(300);
        imageViewLevel3.setOnMouseClicked(event -> level3());

        //TODO kłó∂ka --> when we know after how many points(idk) the next one unlocks

        Button menuTokyo = new Button("MENU");
        menuTokyo.setLayoutX(1088);
        menuTokyo.setLayoutY(10);
        menuTokyo.setPrefSize(100, 50);
        menuTokyo.setOnAction(event -> Main.menu());

        rootTokyoStart.getChildren().addAll(textNaGorze, imageViewLevel1, imageViewLevel2, imageViewLevel3, menuTokyo, rectangle2);
    }

    public static void level1(){
//        Main.activeSamouczekScene = false;
//        Main.activeFabulaScene = false;
//        Main.activeChooseThemeScene = false;
        activeGenerateScene = false;

        activeLevel1Scene = true;
        activeLevel2Scene = false;
        activeLevel3Scene = false;

        level1Root.getChildren().clear();

        ImageView imageViewTorLevel1Tokyo = new ImageView(new Image("file:Tokyo/torduzy.png"));
        imageViewTorLevel1Tokyo.setFitHeight(800);
        imageViewTorLevel1Tokyo.setFitWidth(1200);
        level1Root.getChildren().add(imageViewTorLevel1Tokyo);

        level1Setup();

        Main.stage.setTitle("Level 1 Tokyo");
        Main.stage.setScene(level1TokyoScene);
    }

    public static void level1Setup(){
        ImageView menuTokyoDrift = new ImageView(new Image("file:Tokyo/menuButtonTokyo.png"));
        menuTokyoDrift.setLayoutX(1030);
        menuTokyoDrift.setLayoutY(744);
        menuTokyoDrift.setFitWidth(100);
        menuTokyoDrift.setFitHeight(50);
        menuTokyoDrift.setOnMouseClicked(event -> Main.menu());

        //timer
        timerText.setX(337);
        timerText.setY(805);
        Font font = Font.loadFont("file:Minecraftia-Regular.ttf",25);
        timerText.setFont(font);
        timerText.setFill(Color.color(1, 0, 1));
        lapTimerText.setX(575);
        lapTimerText.setY(805);
        lapTimerText.setFont(font);
        lapTimerText.setFill(Color.color(1,0,0.7));
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    updateTimer();
                    setLapTimer();
                })

        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        Player player = new Player(200,180);
        //player.setRotate(180);

        Image imageTowerShooter = new Image("file:Tokyo/shooter.png");

        //tworzymy wieżę - działa
        Shooter shooter = new Shooter(288, 300, imageTowerShooter);

        Timeline timelineShooter = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            shooter.fireBullet(player.carX, player.carY, 20);
            shooter.rotateToTarget(player);


        }));
        timelineShooter.setCycleCount(INDEFINITE);
        timelineShooter.play();

        Rectangle rectangle = new Rectangle(10, 10);

        Rectangle checkpointMeta = new Rectangle(227, 16, 50, 255);
        Rectangle checkpoint = new Rectangle(227, 16, 25, 255);

        level1Root.getChildren().addAll(rectangle, player, shooter, menuTokyoDrift, timerText, lapTimerText);
    }


    private static void setLapTimer() {
        if (lap >= 0) {
            lapSeconds++; }
        if ( lapSeconds >= 0 && lapSeconds < 10) {
            lapTimerText.setText(lapMinutes + ":0" + lapSeconds);
        } else {
            if (lapSeconds > 59) {
                lapSeconds = 0;
                lapMinutes++;
            }
            lapTimerText.setText(lapMinutes + ":" + lapSeconds);
        }
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

    }

    public static void level2(){
//        Main.activeSamouczekScene = false;
//        Main.activeFabulaScene = false;
//        Main.activeChooseThemeScene = false;
        activeGenerateScene = false;

        activeLevel1Scene = false;
        activeLevel2Scene = true;
        activeLevel3Scene = false;

        level2Root.getChildren().clear();

        level2Setup();

        ImageView imageViewTorLevel2Tokyo = new ImageView(new Image("file:Tokyo/torduzy.png")); //TODO plik
        imageViewTorLevel2Tokyo.setFitHeight(800);
        imageViewTorLevel2Tokyo.setFitWidth(1200);
        level2Root.getChildren().add(imageViewTorLevel2Tokyo);

        Main.stage.setTitle("Level 2 Tokyo");
        Main.stage.setScene(level2TokyoScene);
    }

    public static void level2Setup(){
        ImageView menuTokyoDrift = new ImageView(new Image("file:Tokyo/menuButtonTokyo.png"));
        menuTokyoDrift.setLayoutX(1030);
        menuTokyoDrift.setLayoutY(744);
        menuTokyoDrift.setFitWidth(100);
        menuTokyoDrift.setFitHeight(50);
        menuTokyoDrift.setOnMouseClicked(event -> Main.menu());

        //timer
        timerText.setX(337);
        timerText.setY(805);
        Font font = Font.loadFont("file:Minecraftia-Regular.ttf",25);
        timerText.setFont(font);
        timerText.setFill(Color.color(1, 0, 1));
        lapTimerText.setX(575);
        lapTimerText.setY(805);
        lapTimerText.setFont(font);
        lapTimerText.setFill(Color.color(1,0,0.7));
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    updateTimer();
                    setLapTimer();
                })

        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        Player player = new Player(200,180);
        //player.setRotate(180);

        Image imageTowerShooter = new Image("file:Tokyo/shooter.png");

        //tworzymy wieżę - działa
        Shooter shooter = new Shooter(288, 300, imageTowerShooter);

        Timeline timelineShooter = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            shooter.fireBullet(player.carX, player.carY, 20);
            shooter.rotateToTarget(player);


        }));
        timelineShooter.setCycleCount(INDEFINITE);
        timelineShooter.play();

        Rectangle rectangle = new Rectangle(10, 10);


        level2Root.getChildren().addAll( rectangle, player, shooter, menuTokyoDrift, timerText, lapTimerText);
    }


    public static void level3(){
//        Main.activeSamouczekScene = false;
//        Main.activeFabulaScene = false;
//        Main.activeChooseThemeScene = false;
        activeGenerateScene = false;

        activeLevel1Scene = false;
        activeLevel2Scene = false;
        activeLevel3Scene = true;

        rootTokyoStart.getChildren().clear();

        ImageView imageViewTorLevel3Tokyo = new ImageView(new Image("file:Tokyo/torduzy.png")); //TODO plik
        imageViewTorLevel3Tokyo.setFitHeight(800);
        imageViewTorLevel3Tokyo.setFitWidth(1200);
        level3Root.getChildren().add(imageViewTorLevel3Tokyo);

        level3Setup();

        Main.stage.setTitle("Level 3 Tokyo");
        Main.stage.setScene(level3TokyoScene);
    }

    public static void level3Setup(){
        ImageView menuTokyoDrift = new ImageView(new Image("file:Tokyo/menuButtonTokyo.png"));
        menuTokyoDrift.setLayoutX(1030);
        menuTokyoDrift.setLayoutY(744);
        menuTokyoDrift.setFitWidth(100);
        menuTokyoDrift.setFitHeight(50);
        menuTokyoDrift.setOnMouseClicked(event -> Main.menu());

        //timer
        timerText.setX(337);
        timerText.setY(805);
        Font font = Font.loadFont("file:Minecraftia-Regular.ttf",25);
        timerText.setFont(font);
        timerText.setFill(Color.color(1, 0, 1));
        lapTimerText.setX(575);
        lapTimerText.setY(805);
        lapTimerText.setFont(font);
        lapTimerText.setFill(Color.color(1,0,0.7));
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    updateTimer();
                    setLapTimer();
                })

        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        Player player = new Player(200,180);
        //player.setRotate(180);

        Image imageTowerShooter = new Image("file:Tokyo/shooter.png");

        //tworzymy wieżę - działa
        Shooter shooter = new Shooter(288, 300, imageTowerShooter);

        Timeline timelineShooter = new Timeline(new KeyFrame(Duration.millis(1000), event -> {
            shooter.fireBullet(player.carX, player.carY, 20);
            shooter.rotateToTarget(player);


        }));
        timelineShooter.setCycleCount(INDEFINITE);
        timelineShooter.play();

        Rectangle rectangle = new Rectangle(10, 10);


        level3Root.getChildren().addAll(rectangle, player, shooter, menuTokyoDrift, timerText, lapTimerText);
    }

}




