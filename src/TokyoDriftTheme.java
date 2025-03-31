import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static javafx.animation.Animation.INDEFINITE;

public class TokyoDriftTheme {
    //hubhubhu
    public static boolean start = false;

    static Text timerText = new Text(" ");
    public static Text lapTimerText = new Text(" ");
    static Text lapText = new Text(" ");
    static int seconds = 0;
    static int minutes = 0;
    static int lapSeconds = 0;
    static int lapMinutes = 0;
    static int lap = Player.lap;

    static AnchorPane rootTokyoStart = new AnchorPane();
    static AnchorPane level1Root = new AnchorPane();
    static AnchorPane level2Root = new AnchorPane();
    static AnchorPane level3Root = new AnchorPane();

    static ImageView menuButton = new ImageView(new Image("file:Tokyo/menuButtonChooseLevel.png"));

    static ImageView menuTokyoDriftLevel1 = new ImageView(new Image("file:Tokyo/menuButtonTokyo.png"));

    static boolean activeGenerateScene = false;
    static Scene startSceneTokyo = new Scene(rootTokyoStart, Main.WIDTH, Main.HEIGHT);

    static boolean activeLevel1Scene = false;
    static Scene level1TokyoScene = new Scene(level1Root, Main.WIDTH, Main.HEIGHT);

    static boolean activeLevel2Scene = false;
    static Scene level2TokyoScene = new Scene(level2Root, Main.WIDTH, Main.HEIGHT);

    static boolean activeLevel3Scene = false;
    static Scene level3TokyoScene = new Scene(level3Root, Main.WIDTH, Main.HEIGHT);
    private static boolean isTimerRunning;

    static Player player = new Player(200, 180);
   public static Timeline timelineTimer;
   public static Timeline timelineFiring;

    static int iloscOkrazen = 200; //TODO change to 0 here once we finish setting everything up

    static Bonus bonus = new Bonus();

    public static void generateTokyo(){
        Main.activeSamouczekScene = false;
        Main.activeFabulaScene = false;
        Main.activeChooseThemeScene = false;
        activeGenerateScene = true;

        activeLevel1Scene = false;
        activeLevel2Scene = false;
        activeLevel3Scene = false;


        rootTokyoStart.getChildren().clear();

        ImageView background = new ImageView(new Image("file:Tokyo/backgroundGenerate.png"));
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
        textNaGorze.setFill(Color.color(0.11, 0.54, 0.67));
        textNaGorze.setFont(fontTokyo);
        textNaGorze.setLayoutX(400);
        textNaGorze.setLayoutY(200);

        ImageView imageViewLevel1 = new ImageView(new Image("file:Tokyo/level1Cover.png"));
        imageViewLevel1.setLayoutX(100);
        imageViewLevel1.setLayoutY(300);
        imageViewLevel1.setFitHeight(300);
        imageViewLevel1.setFitWidth(300);
        imageViewLevel1.setOnMouseClicked(event ->
                level1());

        ImageView klodkaLevel2 = new ImageView(new Image("file:Tokyo/klodka.png"));
        klodkaLevel2.setFitWidth(300);
        klodkaLevel2.setFitHeight(300);
        klodkaLevel2.setLayoutX(450);
        klodkaLevel2.setLayoutY(275);


        ImageView imageViewLevel2 = new ImageView(new Image("file:Tokyo/level2Cover.png"));
        imageViewLevel2.setLayoutX(450);
        imageViewLevel2.setLayoutY(300);
        imageViewLevel2.setFitHeight(300);
        imageViewLevel2.setFitWidth(300);

        ImageView klodkaLevel3 = new ImageView(new Image("file:Tokyo/klodka.png"));
        klodkaLevel3.setFitWidth(300);
        klodkaLevel3.setFitHeight(300);
        klodkaLevel3.setLayoutX(800);
        klodkaLevel3.setLayoutY(275);

        ImageView imageViewLevel3 = new ImageView(new Image("file:Tokyo/level3Cover.png"));
        imageViewLevel3.setLayoutX(800);
        imageViewLevel3.setLayoutY(300);
        imageViewLevel3.setFitHeight(300);
        imageViewLevel3.setFitWidth(300);

        //TODO kłó∂ka --> when we know after how many points(idk) the next one unlocks

        menuButton.setLayoutX(1088);
        menuButton.setLayoutY(10);
        menuButton.setOnMouseClicked(event -> Main.menu());

        rootTokyoStart.getChildren().addAll(textNaGorze, imageViewLevel1, imageViewLevel2, imageViewLevel3, menuButton, klodkaLevel2, klodkaLevel3);

        //level 2 --> po 5 okrazen w pierwszym
        //level 3 --> po 5 okrazeniach w drugim
        final Text textTlumaczenie = new Text("Level 2 odblokowuje się po ukończeniu 5 okrążeń w levelu 1, a level 3 po ukończeniu 5 okrążeń w levelu 2 :))");
        textTlumaczenie.setWrappingWidth(500);
        textTlumaczenie.setLayoutX(-100);
        textTlumaczenie.setLayoutY(-100);
        Font font = Font.loadFont("file:Minecraftia-Regular.ttf",15);
        klodkaLevel2.setOnMouseClicked(event -> {
            textTlumaczenie.setFont(font);
            textTlumaczenie.setLayoutX(487);
            textTlumaczenie.setLayoutY(650);
        });
        klodkaLevel3.setOnMouseClicked(event -> {
            textTlumaczenie.setFont(font);
            textTlumaczenie.setLayoutX(487);
            textTlumaczenie.setLayoutY(650);
        });

        if(iloscOkrazen >= 5){
            rootTokyoStart.getChildren().remove(klodkaLevel2);
            imageViewLevel2.setOnMouseClicked(event -> level2());
        }

        if(iloscOkrazen >= 5){ //ilosc okrazen od levelu 2
            rootTokyoStart.getChildren().remove(klodkaLevel3);
            imageViewLevel3.setOnMouseClicked(event -> level3());
        }
        rootTokyoStart.getChildren().add(textTlumaczenie);
    }

    public static void level1(){
//        Main.activeSamouczekScene = false;
//        Main.activeFabulaScene = false;
//        Main.activeChooseThemeScene = false;
        activeGenerateScene = false;

        activeLevel1Scene = true;
        activeLevel2Scene = false;
        activeLevel3Scene = false;

        //Main.mediaPlayer.stop();
        //Main.mediaPlayer = new MediaPlayer(Main.racingMusic);
        //Main.mediaPlayer.play();

        level1Root.getChildren().clear();

        ImageView imageViewTorLevel1Tokyo = new ImageView(new Image("file:Tokyo/tory/torduzy.png"));
        imageViewTorLevel1Tokyo.setFitHeight(800);
        imageViewTorLevel1Tokyo.setFitWidth(1200);
        level1Root.getChildren().add(imageViewTorLevel1Tokyo);

        level1Setup();

        Main.stage.setTitle("Level 1 Tokyo");
        Main.stage.setScene(level1TokyoScene);
    }

    public static void level1Setup(){
        Player player = new Player(200,180);

        menuTokyoDriftLevel1.setLayoutX(1030);
        menuTokyoDriftLevel1.setLayoutY(744);
        menuTokyoDriftLevel1.setFitWidth(100);
        menuTokyoDriftLevel1.setFitHeight(50);

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
        lapText.setX(685);
        lapText.setY(805);
        lapTimerText.setFill(Color.color(0.5,0,0.9));

         timelineTimer = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    if (start) {
                        isTimerRunning = true;
                        updateTimer();
                        updateLapTimer();
                        lap();
                    }
                })

        );
        timelineTimer.setCycleCount(Timeline.INDEFINITE);
        timelineTimer.play();

//        //not working
//        menuTokyoDriftLevel1.setOnMouseClicked(event -> {
//            timelineTimer.stop();
//        });

//        Main.wyjdzZMenu.setOnMouseClicked(event -> {
//            timelineTimer.play();
//        });

        bonus.appear();

        Image imageTowerShooter = new Image("file:Tokyo/shooter.png");

        //tworzymy wieżę - działa
        Shooter shooter = new Shooter(283, 300, imageTowerShooter);

         timelineFiring = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            shooter.fireBullet(player.carX, player.carY, 20);

        }));
        timelineFiring.setCycleCount(INDEFINITE);
        timelineFiring.play();

        Timeline rotationTimeline = new Timeline(new KeyFrame(Duration.millis(20), event -> {
            shooter.rotateToTarget(player);
        }));
        rotationTimeline.setCycleCount(INDEFINITE);
        rotationTimeline.play();

        menuTokyoDriftLevel1.setOnMouseClicked(event -> {
            Main.menu();
            timelineTimer.stop();
            timelineFiring.stop();
        });

        if(activeLevel1Scene == false){
            timelineTimer.stop();
            timelineFiring.stop();
        }

        Rectangle rectangle = new Rectangle(10, 10);

        Rectangle checkpointMeta = new Rectangle(227, 16, 50, 255);
        Rectangle checkpoint = new Rectangle(227, 16, 25, 255);

        level1Root.getChildren().addAll(rectangle, player, shooter, menuTokyoDriftLevel1, timerText, lapTimerText);
        Puddle.puddleFactory(level1Root);
    }


    private static void updateLapTimer() {
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
        } if (lap == 1) {
            timelineTimer.stop();
           String firstLapTimer = lapTimerText.getText();
            lapSeconds = 0;
            lapMinutes = 0;
            timelineTimer.play();
        } if (lap == 2) {
            timelineTimer.stop();
            String secondLapTimer = lapTimerText.getText();
            lapSeconds = 0;
            lapMinutes = 0;
            timelineTimer.play();
        } if (lap == 3) {
            timelineTimer.stop();
            String thirdLapTimer = lapTimerText.getText();
            lapSeconds = 0;
            lapMinutes = 0;
            timelineTimer.play();
        }if (lap == 4) {
            timelineTimer.stop();
            String fourthLapTimer = lapTimerText.getText();
            lapSeconds = 0;
            lapMinutes = 0;
            timelineTimer.play();
        } if (lap == 5) {
            timelineTimer.stop();
            String fifthLapTimer = lapTimerText.getText();

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
    private static void lap() {
        lapText.setText(String.valueOf(lap + 1));
    }


    public static void level2(){
        activeGenerateScene = false;

        activeLevel1Scene = false;
        activeLevel2Scene = true;
        activeLevel3Scene = false;

        level2Root.getChildren().clear();

        ImageView imageViewTorLevel2Tokyo = new ImageView(new Image("file:Tokyo/tory/level2Tor.png"));
        imageViewTorLevel2Tokyo.setFitHeight(800);
        imageViewTorLevel2Tokyo.setFitWidth(1200);
        level2Root.getChildren().add(imageViewTorLevel2Tokyo);

        level2Setup();

        Main.stage.setTitle("Level 2 Tokyo");
        Main.stage.setScene(level2TokyoScene);
    }

    public static void level2Setup(){
        ImageView menuTokyoDrift2 = new ImageView(new Image("file:Tokyo/menuButtonTokyo.png"));
        menuTokyoDrift2.setLayoutX(1030);
        menuTokyoDrift2.setLayoutY(744);
        menuTokyoDrift2.setFitWidth(100);
        menuTokyoDrift2.setFitHeight(50);
        menuTokyoDrift2.setOnMouseClicked(event -> Main.menu());

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
                    updateLapTimer();
                })

        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        Player player = new Player(200,180);

        //ObstacleDoor obstacleDoor = new ObstacleDoor(100,100);

        ObstacleDoor.doorCreation();

        Image imageTowerShooter = new Image("file:Tokyo/shooter.png");

        //tworzymy wieżę - działa
        Shooter shooter = new Shooter(283, 300, imageTowerShooter);

        Timeline timelineFiring = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            shooter.fireBullet(player.carX, player.carY, 20);

        }));
        timelineFiring.setCycleCount(INDEFINITE);
        timelineFiring.play();

        Timeline rotationTimeline = new Timeline(new KeyFrame(Duration.millis(20), event -> {
            shooter.rotateToTarget(player);
        }));
        rotationTimeline.setCycleCount(INDEFINITE);
        rotationTimeline.play();

        level2Root.getChildren().addAll( player, shooter, menuTokyoDrift2, timerText, lapTimerText);
    }

    public static void level3(){
        activeGenerateScene = false;
        activeLevel1Scene = false;
        activeLevel2Scene = false;
        activeLevel3Scene = true;

        level3Root.getChildren().clear();

        ImageView imageViewTorLevel3Tokyo = new ImageView(new Image("file:Tokyo/tory/level3Tor.png"));
        imageViewTorLevel3Tokyo.setFitHeight(800);
        imageViewTorLevel3Tokyo.setFitWidth(1200);
        level3Root.getChildren().add(imageViewTorLevel3Tokyo);

        level3Setup();

        Main.stage.setTitle("Level 3 Tokyo");
        Main.stage.setScene(level3TokyoScene);
    }

    public static void level3Setup(){
        ImageView menuTokyoDrift3 = new ImageView(new Image("file:Tokyo/menuButtonTokyo.png"));
        menuTokyoDrift3.setLayoutX(1030);
        menuTokyoDrift3.setLayoutY(744);
        menuTokyoDrift3.setFitWidth(100);
        menuTokyoDrift3.setFitHeight(50);
        menuTokyoDrift3.setOnMouseClicked(event -> Main.menu());

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
                    updateLapTimer();
                })

        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        Puddle.puddleFactory(level1Root);
        Player player = new Player(200,180);

        Image imageTowerShooter = new Image("file:Tokyo/shooter.png");

        //tworzymy wieżę - działa
        Shooter shooter = new Shooter(283, 300, imageTowerShooter);

        Timeline timelineFiring = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            shooter.fireBullet(player.carX, player.carY, 20);

        }));
        timelineFiring.setCycleCount(INDEFINITE);
        timelineFiring.play();

        Timeline rotationTimeline = new Timeline(new KeyFrame(Duration.millis(20), event -> {
            shooter.rotateToTarget(player);
        }));
        rotationTimeline.setCycleCount(INDEFINITE);
        rotationTimeline.play();


        level3Root.getChildren().addAll(player, shooter, menuTokyoDrift3, timerText, lapTimerText);
    }

}




