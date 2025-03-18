import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
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
    static Text lapTimerText = new Text(" ");
    static int seconds = 0;
    static int minutes = 0;
    static int lapSeconds = 0;
    static int lapMinutes = 0;
    static int lap = 0;
    static final ImageView background = new ImageView(new Image("file:")); //add
    static AnchorPane rootTokyoStart = new AnchorPane();
    static boolean activeGenerateScene = false;

    static boolean activeLevel1Scene = false;
    static AnchorPane level1Root = new AnchorPane();
    static Scene level1TokyoScene = new Scene(level1Root, Main.WIDTH, Main.HEIGHT);

    static boolean activeLevel2Scene = false;
    static AnchorPane level2Root = new AnchorPane();
    static Scene level2TokyoScene = new Scene(level2Root, Main.WIDTH, Main.HEIGHT);

    static boolean activeLevel3Scene = false;
    static AnchorPane level3Root = new AnchorPane();
    static Scene level3TokyoScene = new Scene(level3Root, Main.WIDTH, Main.HEIGHT);


    public static void generateTokyo(){

        Text textNaGorze = new Text("Wybierz level :");
        Font fontTokyo = Font.loadFont("file:Minecraftia-Regular.ttf",45);
        textNaGorze.setStyle("-fx-font-weight: bold;");
        textNaGorze.setFill(Color.color(0.11, 0.54, 0.67)); //TODO Nati
        textNaGorze.setFont(fontTokyo);
//        textNaGorze.setFont(Font.font(50));
        textNaGorze.setLayoutX(400);
        textNaGorze.setLayoutY(200);

        ImageView background = new ImageView(new Image("file:Tokyo/backgroundGenerate.png")); //add
        background.setFitWidth(Main.WIDTH);
        background.setFitHeight(Main.HEIGHT);
        rootTokyoStart.getChildren().add(background);

        ImageView imageViewLevel1 = new ImageView(new Image("file:Tokyo/level1Cover.png"));
        imageViewLevel1.setLayoutX(100);
        imageViewLevel1.setLayoutY(300);
        imageViewLevel1.setFitHeight(300);
        imageViewLevel1.setFitWidth(300);
        imageViewLevel1.setOnMouseClicked(event -> {
            level1();
        });

        ImageView imageViewLevel2 = new ImageView(new Image("file:Tokyo/level2Cover.png"));
        imageViewLevel2.setLayoutX(450);
        imageViewLevel2.setLayoutY(300);
        imageViewLevel2.setFitHeight(300);
        imageViewLevel2.setFitWidth(300);
        imageViewLevel2.setOnMouseClicked(event -> {
            level2();
        });

        Rectangle rectangle2 = new Rectangle(450, 300, 300, 300 );
        rectangle2.setOnMouseClicked(event -> {
            level2();
        });

        ImageView imageViewLevel3 = new ImageView(new Image("file:Tokyo/level3Cover.png"));
        imageViewLevel3.setLayoutX(800);
        imageViewLevel3.setLayoutY(300);
        imageViewLevel3.setFitHeight(300);
        imageViewLevel3.setFitWidth(300);
        imageViewLevel3.setOnMouseClicked(event -> {
            level3();
        });


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

        rootTokyoStart.getChildren().addAll(imageViewLevel1, imageViewLevel2, imageViewLevel3, menuTokyo, rectangle2, textNaGorze);

        Scene startSceneTokyo = new Scene(rootTokyoStart, Main.WIDTH, Main.HEIGHT);
        Main.stage.setTitle("TokyoDrift Start");
        Main.stage.setScene(startSceneTokyo);
    }


    public static void level1(){
        ImageView imageViewTorLevel1Tokyo = new ImageView(new Image("file:Tokyo/torduzy.png"));
        imageViewTorLevel1Tokyo.setFitHeight(800);
        imageViewTorLevel1Tokyo.setFitWidth(1200);

        ImageView menuTokyoDrift = new ImageView(new Image("file:Tokyo/menuButtonTokyo.png"));
        menuTokyoDrift.setLayoutX(1030);
        menuTokyoDrift.setLayoutY(744);
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

        Bonus bonus = new Bonus();
        //bonus.appear(); -> dziwnie zacina program



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


        //TODO loophole
        level1Root.getChildren().addAll(imageViewTorLevel1Tokyo, rectangle, player, shooter, menuTokyoDrift, timerText, lapTimerText, bonus);

        //Scene startSceneTokyo = new Scene(level1, Main.WIDTH, Main.HEIGHT);
        Main.stage.setTitle("Level 1 Tokyo");
        Main.stage.setScene(level1TokyoScene);

        Rectangle checkpointMeta = new Rectangle(227, 16, 50, 255);
        Rectangle checkpoint = new Rectangle(227, 16, 25, 255);


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
        ImageView imageViewTorLevel2Tokyo = new ImageView(new Image("file:Tokyo/torduzy.png")); //TODO plik
        imageViewTorLevel2Tokyo.setFitHeight(800);
        imageViewTorLevel2Tokyo.setFitWidth(1200);

        ImageView menuTokyoDrift = new ImageView(new Image("file:Tokyo/menuButtonTokyo.png"));
        menuTokyoDrift.setLayoutX(1030);
        menuTokyoDrift.setLayoutY(744);
        menuTokyoDrift.setFitWidth(100);
        menuTokyoDrift.setFitHeight(50);
        menuTokyoDrift.setOnMouseClicked(event -> {
            activeLevel2Scene = true;
            Main.menu();
        });
        Main.activeChooseThemeScene = false;
        Main.activeFabulaScene = false;
        Main.activeSamouczekScene = false;
        activeGenerateScene = false;
        activeLevel1Scene = false;
        activeLevel2Scene = false;
        activeLevel3Scene = false;

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


        level2Root.getChildren().addAll(imageViewTorLevel2Tokyo, rectangle, player, shooter, menuTokyoDrift, timerText, lapTimerText);

        //Scene startSceneTokyo = new Scene(level1, Main.WIDTH, Main.HEIGHT);
        Main.stage.setTitle("Level 2 Tokyo");
        Main.stage.setScene(level2TokyoScene);
    }

    public static void level3(){
        ImageView imageViewTorLevel3Tokyo = new ImageView(new Image("file:Tokyo/torduzy.png")); //TODO plik
        imageViewTorLevel3Tokyo.setFitHeight(800);
        imageViewTorLevel3Tokyo.setFitWidth(1200);

        ImageView menuTokyoDrift = new ImageView(new Image("file:Tokyo/menuButtonTokyo.png"));
        menuTokyoDrift.setLayoutX(1030);
        menuTokyoDrift.setLayoutY(744);
        menuTokyoDrift.setFitWidth(100);
        menuTokyoDrift.setFitHeight(50);
        menuTokyoDrift.setOnMouseClicked(event -> {
            activeLevel3Scene = true;
            Main.menu();
        });
        Main.activeChooseThemeScene = false;
        Main.activeFabulaScene = false;
        Main.activeSamouczekScene = false;
        activeGenerateScene = false;
        activeLevel1Scene = false;
        activeLevel2Scene = false;
        activeLevel3Scene = false;

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


        level3Root.getChildren().addAll(imageViewTorLevel3Tokyo, rectangle, player, shooter, menuTokyoDrift, timerText, lapTimerText);

        //Scene startSceneTokyo = new Scene(level1, Main.WIDTH, Main.HEIGHT);
        Main.stage.setTitle("Level 3 Tokyo");
        Main.stage.setScene(level3TokyoScene);
    }

}




