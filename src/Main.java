import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main extends Application {

    static final int WIDTH = 1200;
    static final int HEIGHT = 800;
    static Stage stage;

    static boolean activeSamouczekScene = false;
    static boolean activeFabulaScene = false;
    static boolean activeChooseThemeScene = false;

    static AnchorPane rootSamouczek = new AnchorPane();
    static AnchorPane rootFabula = new AnchorPane();
    static AnchorPane rootChooseThemeScene = new AnchorPane();

    static ImageView wyjdzZMenu = new ImageView(new Image("file:imagesStart/menu/menueXit.png"));

    static Scene samouczekScene = new Scene(rootSamouczek, WIDTH, HEIGHT);
    static Scene fabulaScene = new Scene(rootFabula, WIDTH, HEIGHT);
    static Scene chooseThemeScene = new Scene(rootChooseThemeScene, WIDTH, HEIGHT);

    static Player playerSamouczek;

    static List<Projectile> projectiles = new ArrayList<>();

    static Image musicOff = new Image("file:imagesStart/menu/muzyka0.png");
    static Image musicOn = new Image("file:imagesStart/menu/muzyka1.png");
    static boolean isMusicOn = true;

    static String musicFile1 = "music/music.mp3";
    static String musicFile2 = "music/race.mp3";
    static String musicFile3 = "music/Car starting sound effect.mp3";
    //public static Media starterMusic = new Media(new File(musicFile1).toURI().toString());

    //public static Media racingMusic = new Media(new File(musicFile2).toURI().toString());
    //public static Media vroom = new Media(new File(musicFile3).toURI().toString());

    //public static MediaPlayer mediaPlayerStarter = new MediaPlayer(starterMusic);

    //public static MediaPlayer mediaPlayerRacer = new MediaPlayer(racingMusic);

    //public static MediaPlayer mediaPlayerStartingCar = new MediaPlayer(vroom);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        startScene();
        stage.show();
        //mediaPlayerStarter.setCycleCount(MediaPlayer.INDEFINITE);
        //mediaPlayerStarter.play();
    }

    public static void startScene() {
        AnchorPane rootStart = new AnchorPane();
        ImageView imageViewStartScene = new ImageView(new Image("file:imagesStart/GRANDMA (STER).png"));
        imageViewStartScene.setFitHeight(HEIGHT);
        imageViewStartScene.setFitWidth(WIDTH);

        ImageView imageViewStartButton = new ImageView(new Image("file:imagesStart/start.png"));
        imageViewStartButton.setLayoutX(440);
        imageViewStartButton.setLayoutY(250);
        imageViewStartButton.setFitWidth(300);
        imageViewStartButton.setFitHeight(120);
        imageViewStartButton.setOnMouseClicked(event -> fabulaScene());

        ImageView imageViewButtonSamouczek = new ImageView(new Image("file:imagesStart/samouczek.png"));
        imageViewButtonSamouczek.setLayoutX(440);
        imageViewButtonSamouczek.setLayoutY(410);
        imageViewButtonSamouczek.setFitWidth(300);
        imageViewButtonSamouczek.setFitHeight(50);
        imageViewButtonSamouczek.setOnMouseClicked(event -> samouczekScene());

        rootStart.getChildren().addAll(imageViewStartScene, imageViewButtonSamouczek, imageViewStartButton);

        Scene startScene = new Scene(rootStart, WIDTH, HEIGHT);
        stage.setTitle("Ekran główny");
        stage.setScene(startScene);
    }

    public static void menu() {

        AnchorPane rootMenu = new AnchorPane();

        ImageView backgroundMenu = new ImageView(new Image("file:Tokyo/backgroundGenerate.png"));
        backgroundMenu.setFitHeight(500);
        backgroundMenu.setFitWidth(750);
        rootMenu.getChildren().add(backgroundMenu);

        ImageView samouczekButtonMenu = new ImageView(new Image("file:imagesStart/menu/samouczekMenu.png"));
        samouczekButtonMenu.setLayoutX(220);
        samouczekButtonMenu.setLayoutY(250);
        samouczekButtonMenu.setFitHeight(80);
        samouczekButtonMenu.setFitWidth(300);

        ImageView powrotNaStartButton = new ImageView(new Image("file:imagesStart/menu/powrotNaStart.png"));
        powrotNaStartButton.setLayoutX(220);
        powrotNaStartButton.setLayoutY(135);
        powrotNaStartButton.setFitHeight(80);
        powrotNaStartButton.setFitWidth(300);

        wyjdzZMenu.setLayoutX(30);
        wyjdzZMenu.setLayoutY(30);
        wyjdzZMenu.setFitWidth(100);
        wyjdzZMenu.setFitHeight(50);

        ImageView wyjdzZGry = new ImageView(new Image("file:imagesStart/menu/wyjdzZGry.png"));
        wyjdzZGry.setLayoutX(220);
        wyjdzZGry.setLayoutY(365);
        wyjdzZGry.setFitHeight(80);
        wyjdzZGry.setFitWidth(300);

        ImageView musicOnOffButton = new ImageView(musicOn);
        musicOnOffButton.setLayoutX(618);
        musicOnOffButton.setLayoutY(30);
        isMusicOn = true;

        musicOnOffButton.setOnMouseClicked(event -> { //if it works it works guys :))
            if(isMusicOn == false){
                musicOnOffButton.setImage(musicOff);
                isMusicOn = true;
                //mediaPlayerStarter.setMute(true);
                //mediaPlayerRacer.setMute(true);

            } else {
                musicOnOffButton.setImage(musicOn);
                isMusicOn = false;
                //mediaPlayerStarter.setMute(false);
                //mediaPlayerRacer.setMute(false);
            }
        });


        rootMenu.getChildren().addAll(samouczekButtonMenu, powrotNaStartButton, wyjdzZMenu, wyjdzZGry, musicOnOffButton);

        Scene menu = new Scene(rootMenu, 750, 500);
        Stage stageMenu = new Stage();
        stageMenu.setX(370);
        stageMenu.setY(170);
        stageMenu.setTitle("MENU");
        stageMenu.setScene(menu);
        stageMenu.show();
        stageMenu.setAlwaysOnTop(true);

        if(TokyoDriftTheme.activeLevel1Scene || TokyoDriftTheme.activeLevel2Scene || TokyoDriftTheme.activeLevel3Scene || activeChooseThemeScene || TokyoDriftTheme.activeGenerateScene){
            ImageView cofnijButton = new ImageView(new Image("file:imagesStart/menu/menuCofnij.png"));
            cofnijButton.setLayoutX(30);
            cofnijButton.setLayoutY(100);
            cofnijButton.setFitWidth(100);
            cofnijButton.setFitHeight(50);
            if(TokyoDriftTheme.activeLevel1Scene || TokyoDriftTheme.activeLevel2Scene || TokyoDriftTheme.activeLevel3Scene){
                cofnijButton.setOnMouseClicked(event -> {
                    exitDuringGame(stageMenu, fabulaScene);
                    TokyoDriftTheme.generateTokyo();
                    stageMenu.close();
                });
            }
            if(TokyoDriftTheme.activeGenerateScene){
                cofnijButton.setOnMouseClicked(event -> {
                    chooseThemeScene();
                    stageMenu.close();
                });
            }
            if(activeChooseThemeScene){
                cofnijButton.setOnMouseClicked(event -> {
                    fabulaScene();
                    stageMenu.close();
                });
            }
            rootMenu.getChildren().add(cofnijButton);
        }

        powrotNaStartButton.setOnMouseClicked(event -> {
            startScene();
            stageMenu.close();
        });

        samouczekButtonMenu.setOnMouseClicked(event -> {
            samouczekScene();
            stageMenu.close();
        });

        wyjdzZMenu.setOnMouseClicked(event -> {
            handleExitMenu(stageMenu);
        });

        wyjdzZGry.setOnMouseClicked(event -> {
            handleExitGame(stageMenu);
        });
    }

    private static void exitDuringGame(Stage stageMenu, Scene whichScene) {
        stageMenu.setAlwaysOnTop(false);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Czy na pewno chcesz wyjść z rozgrywki? Twój postęp nie zostanie zapisany.");
        alert.setTitle("");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage.setScene(whichScene);
            stageMenu.close();
        } else {
            alert.close();
        }
    }

    private static void handleExitMenu(Stage stageMenu) {
        if (activeSamouczekScene) {
            samouczekScene();
            stageMenu.close();
        } else if (activeFabulaScene) {
            fabulaScene();
            stageMenu.close();
        } else if (activeChooseThemeScene) {
            chooseThemeScene();
            stageMenu.close();
        } else if (TokyoDriftTheme.activeGenerateScene) {
            TokyoDriftTheme.generateTokyo();
            stageMenu.close();
        } else if (TokyoDriftTheme.activeLevel1Scene) {
            stageMenu.close();
             TokyoDriftTheme.timelineTimer.play();
             TokyoDriftTheme.timelineFiring.play();
        } else if (TokyoDriftTheme.activeLevel2Scene) {
            stageMenu.close();
        } else if (TokyoDriftTheme.activeLevel3Scene) {
            stageMenu.close();
        }
    }

    private static void handleExitGame(Stage stageMenu) {
        stageMenu.setAlwaysOnTop(false);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Czy na pewno chcesz wyjść z gry?");
        alert.setTitle("");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage.close();
            stageMenu.close();
        } else {
            alert.close();
        }
    }

    public static void samouczekScene() {
        activeSamouczekScene = true;
        activeFabulaScene = false;
        activeChooseThemeScene = false;

        rootSamouczek.getChildren().clear();
        ImageView background = new ImageView(new Image("file:imagesStart/samouczek_fabula/backgroundSamouczek.png"));
        background.setFitHeight(HEIGHT);
        background.setFitWidth(WIDTH);
        rootSamouczek.getChildren().add(background);

        setupSamouczekUI();

        stage.setTitle("Samouczek");
        stage.setScene(samouczekScene);
    }

    public static void setupSamouczekUI() {
        ImageView imageViewPominSamouczek = new ImageView(new Image("file:imagesStart/pominSamouczek.png"));
        imageViewPominSamouczek.setLayoutX(1088);
        imageViewPominSamouczek.setLayoutY(740);
        imageViewPominSamouczek.setFitHeight(50);
        imageViewPominSamouczek.setFitWidth(100);
        imageViewPominSamouczek.setOnMouseClicked(event -> fabulaScene());

        ImageView imageViewMenuSamouczek = new ImageView(new Image("file:imagesStart/menuSamouczek.png"));
        imageViewMenuSamouczek.setLayoutX(1088);
        imageViewMenuSamouczek.setLayoutY(10);
        imageViewMenuSamouczek.setFitWidth(100);
        imageViewMenuSamouczek.setFitHeight(50);
        imageViewMenuSamouczek.setOnMouseClicked(event -> menu());

        ImageView dymekSamouczek = new ImageView(new Image("file:imagesStart/samouczek_fabula/dymek.png"));
        dymekSamouczek.setLayoutX(170);
        dymekSamouczek.setLayoutY(31);
        dymekSamouczek.setFitWidth(400); //hmm?
        dymekSamouczek.setFitHeight(90);

        String[] dymekContentSamouczek = {"Witaj młody graczu!", "Aby poznać podstawy tej gry,", "najeżdżaj na poszczególne przyciski.",
                "Jeżeli chcesz pojeździć autkiem,", "naciśnij na przeznaczony", "do tego przycisk.", "Pamiętaj, że zawsze możesz", "pominąć samouczek przyciskiem", "'pomiń' na dole ekranu :)"};
        final Text textDymekSamouczek = new Text("");
        Font font = Font.loadFont("file:Minecraftia-Regular.ttf",15);
        textDymekSamouczek.setFont(font);
        textDymekSamouczek.setLayoutX(194);
        textDymekSamouczek.setLayoutY(75);

        Text textPodBabcia = new Text("Click me");
        textPodBabcia.setTextAlignment(TextAlignment.CENTER);
        textPodBabcia.setLayoutX(80);
        textPodBabcia.setLayoutY(208);
        textPodBabcia.setFont(font);
        rootSamouczek.getChildren().add(textPodBabcia);

        ImageView babciaSamouczek = new ImageView(new Image("file:imagesStart/samouczek_fabula/babcia.png"));
        babciaSamouczek.setLayoutX(50);
        babciaSamouczek.setLayoutY(22);
        babciaSamouczek.setFitHeight(165);
        babciaSamouczek.setFitWidth(123);
        babciaSamouczek.setOnMouseClicked(event -> {
            rootSamouczek.getChildren().remove(textPodBabcia);
            rootSamouczek.getChildren().addAll(dymekSamouczek, textDymekSamouczek);
            animateTextUsingTimeline(dymekContentSamouczek, textDymekSamouczek, 3.6, 3.6);
        });

        //buttony do opisywania
        //start, samouczek, wyjdz z gry
        ImageView podSpodRectangle = new ImageView(new Image("file:imagesStart/samouczek_fabula/podSpod.png"));
        int initialX = 1500;
        int initialY = 900;
        podSpodRectangle.setLayoutX(initialX); //poza sceną
        podSpodRectangle.setLayoutY(initialY); //poza sceną
        podSpodRectangle.setFitWidth(221);
        podSpodRectangle.setFitHeight(75);
        rootSamouczek.getChildren().add(podSpodRectangle);

        ImageView jazdaAutemButton = new ImageView(new Image("file:imagesStart/samouczek_fabula/jazdaAutem.png"));
        jazdaAutemButton.setLayoutX(795);
        jazdaAutemButton.setLayoutY(250);
        jazdaAutemButton.setFitWidth(200);
        jazdaAutemButton.setFitHeight(53.33);
        jazdaAutemButton.setOnMouseClicked(event -> {
            Text textAutko = new Text("Aby poruszać się autkiem korzystaj ze strzałek na klawiaturze. Możesz się poruszać po całym ekranie samouczka :)");
            textAutko.setFont(font);
            textAutko.setWrappingWidth(300);
            textAutko.setLayoutX(750);
            textAutko.setLayoutY(350);
            textAutko.setTextAlignment(TextAlignment.CENTER);
            rootSamouczek.getChildren().add(textAutko);

            playerSamouczek = new Player(825, 470);
            playerSamouczek.setFitWidth(50);
            playerSamouczek.setFitHeight(24);
            rootSamouczek.getChildren().add(playerSamouczek);
        });

        ImageView startShow = new ImageView(new Image("file:imagesStart/samouczek_fabula/startFiolet.png"));
        startShow.setLayoutX(180);
        startShow.setLayoutY(250);
        startShow.setFitWidth(200);
        startShow.setFitHeight(53.33);
        Text text1 = new Text("Przycisk start jest dostępny w menu. Po jego naciśnięciu wrócisz na start.");

        startShow.setOnMouseEntered(event -> {
            rootSamouczek.getChildren().removeAll(textDymekSamouczek, dymekSamouczek);
            podSpodRectangle.setLayoutX(170);
            podSpodRectangle.setLayoutY(240);
            text1.setFont(font);
            text1.setLayoutX(420);
            text1.setLayoutY(262);
            text1.setWrappingWidth(310);
            rootSamouczek.getChildren().addAll(text1);

        });
        startShow.setOnMouseExited(event -> {
            rootSamouczek.getChildren().remove(text1);
            podSpodRectangle.setLayoutX(initialX);
            podSpodRectangle.setLayoutY(initialY);
        });

        ImageView samouczekShow = new ImageView(new Image("file:imagesStart/samouczek_fabula/samouczekFiolet.png"));
        samouczekShow.setLayoutX(180);
        samouczekShow.setLayoutY(325);
        samouczekShow.setFitWidth(200); //2/3
        samouczekShow.setFitHeight(53.33);
        Text text2 = new Text("Przycisk 'samouczek' jest dostępny w menu. Po jego naciśnięciu trafisz z powrotem do samouczka.");

        samouczekShow.setOnMouseEntered(event -> {
            rootSamouczek.getChildren().remove(textDymekSamouczek);
            podSpodRectangle.setLayoutX(170);
            podSpodRectangle.setLayoutY(315);
            text2.setFont(font);
            text2.setLayoutX(420);
            text2.setLayoutY(345);
            text2.setWrappingWidth(350);
            rootSamouczek.getChildren().add(text2);
        });
        samouczekShow.setOnMouseExited(event -> {
            rootSamouczek.getChildren().remove(text2);
            podSpodRectangle.setLayoutX(initialX);
            podSpodRectangle.setLayoutY(initialY);
        });

        ImageView wyjdzZGryShow = new ImageView(new Image("file:imagesStart/samouczek_fabula/wyjdzZGryFiolet.png"));
        wyjdzZGryShow.setLayoutX(180);
        wyjdzZGryShow.setLayoutY(400);
        wyjdzZGryShow.setFitWidth(200); //2/3
        wyjdzZGryShow.setFitHeight(53.33);
        Text text3 = new Text("Przycisk 'wyjdź z gry' również jest w menu. Po jego naciśnięciu możesz wyjść z gry.");

        wyjdzZGryShow.setOnMouseEntered(event -> {
            rootSamouczek.getChildren().remove(textDymekSamouczek);
            podSpodRectangle.setLayoutX(170);
            podSpodRectangle.setLayoutY(390);
            text3.setFont(font);
            text3.setLayoutX(420);
            text3.setLayoutY(415);
            text3.setWrappingWidth(300);
            rootSamouczek.getChildren().add(text3);

        });
        wyjdzZGryShow.setOnMouseExited(event -> {
            podSpodRectangle.setLayoutX(initialX);
            podSpodRectangle.setLayoutY(initialY);
            rootSamouczek.getChildren().remove(text3);
        });


        ImageView podSpodRectangleMniejszy = new ImageView(new Image("file:imagesStart/samouczek_fabula/podSpod.png"));
        podSpodRectangleMniejszy.setLayoutX(initialX); //poza sceną
        podSpodRectangleMniejszy.setLayoutY(initialY); //poza sceną
        podSpodRectangleMniejszy.setFitWidth(121);
        podSpodRectangleMniejszy.setFitHeight(71);
        rootSamouczek.getChildren().add(podSpodRectangleMniejszy);

        ImageView xShow = new ImageView(new Image("file:imagesStart/samouczek_fabula/Xfiolet.png"));
        xShow.setLayoutX(125);
        xShow.setLayoutY(550);
        Text text4 = new Text("Ten przycisk pozwala ci wyjść z menu :)");
        xShow.setOnMouseEntered(event -> {
            rootSamouczek.getChildren().remove(textDymekSamouczek);
            podSpodRectangleMniejszy.setLayoutX(115);
            podSpodRectangleMniejszy.setLayoutY(540);
            text4.setFont(font);
            text4.setLayoutX(245);
            text4.setLayoutY(578);
            text4.setWrappingWidth(200);
            rootSamouczek.getChildren().add(text4);

        });
        xShow.setOnMouseExited(event -> {
            podSpodRectangleMniejszy.setLayoutX(initialX);
            podSpodRectangleMniejszy.setLayoutY(initialY);
            rootSamouczek.getChildren().remove(text4);
        });

        ImageView cofnijShow = new ImageView(new Image("file:imagesStart/samouczek_fabula/cofnijFiolet.png"));
        cofnijShow.setLayoutX(125);
        cofnijShow.setLayoutY(625);
        Text text5 = new Text("Ten przycisk pozwala Ci się cofnąć do poprzedniej sceny.");
        cofnijShow.setOnMouseEntered(event -> {
            rootSamouczek.getChildren().remove(textDymekSamouczek);
            podSpodRectangleMniejszy.setLayoutX(115);
            podSpodRectangleMniejszy.setLayoutY(615);
            text5.setFont(font);
            text5.setWrappingWidth(300);
            text5.setLayoutX(245);
            text5.setLayoutY(653);
            rootSamouczek.getChildren().add(text5);

        });
        cofnijShow.setOnMouseExited(event -> {
            podSpodRectangleMniejszy.setLayoutX(initialX);
            podSpodRectangleMniejszy.setLayoutY(initialY);
            rootSamouczek.getChildren().remove(text5);
        });

        rootSamouczek.getChildren().addAll(startShow, samouczekShow, wyjdzZGryShow, xShow, cofnijShow, jazdaAutemButton);
        //koniec tych buttonów

        rootSamouczek.getChildren().addAll(imageViewMenuSamouczek, imageViewPominSamouczek, babciaSamouczek);

//TODO (pod sam koniec): wytłumaczenie kiedy się odblokowują levele?
    }

    public static void fabulaScene() {
        activeSamouczekScene = false;
        activeFabulaScene = true;
        activeChooseThemeScene = false;

        rootFabula.getChildren().clear();
        ImageView background = new ImageView(new Image("file:imagesStart/samouczek_fabula/backgroundFabula.png"));
        background.setFitWidth(WIDTH);
        background.setFitHeight(HEIGHT);
        rootFabula.getChildren().add(background);

        setupFabulaUI();

        stage.setTitle("Fabuła");
        stage.setScene(fabulaScene);
    }

    public static void setupFabulaUI() {
        ImageView imageViewMenuFabula = new ImageView(new Image("file:imagesStart/menuFabula.png"));
        imageViewMenuFabula.setLayoutX(1088);
        imageViewMenuFabula.setLayoutY(10);
        imageViewMenuFabula.setFitWidth(100);
        imageViewMenuFabula.setFitHeight(50);
        imageViewMenuFabula.setOnMouseClicked(event -> menu());

        ImageView babcia = new ImageView(new Image("file:imagesStart/samouczek_fabula/babcia.png"));
        babcia.setFitHeight(220);
        babcia.setFitWidth(164);
        babcia.setLayoutX(448);
        babcia.setLayoutY(520);

        ImageView dymek = new ImageView(new Image("file:imagesStart/samouczek_fabula/dymek.png"));
        dymek.setLayoutX(626);
        dymek.setLayoutY(280);
        dymek.setFitHeight(290);
        dymek.setFitWidth(550);

        ImageView imageViewPominFabula = new ImageView(new Image("file:imagesStart/pominFabula.png"));
        imageViewPominFabula.setLayoutX(1088);
        imageViewPominFabula.setLayoutY(740);
        imageViewPominFabula.setFitHeight(50);
        imageViewPominFabula.setFitWidth(100);
        imageViewPominFabula.setOnMouseClicked(event -> chooseThemeScene());

        String[] dymekContent = { "Oh, witaj dziecko. Czy coś się stał-",
                "Ahh, to ty...",
                "Co się stało TERAZ,",
                "że wreszcie się zdecydowałeś",
                "odwiedzić swoją starą i 'nudną'",
                " staruszkę hm?",
                "...Więc o to chodzi hmm.",
                "Nigdy nie sądziłam, że usłyszysz",
                " o tej historii gdziekolwiek. ",
                "W końcu stało się to tak dawno temu..",
                "Chyba plotki nigdy nie umierają...",
                "Ale tak, kiedyś byłam wyścigówką.",
                " Bardzo utalentowaną nawet!",
                "...Co się tak patrzysz?",
                "Nie możesz uwierzyć, że babcia miała",
                " taką ciekawą karierę?",
                "Miałam naprawdę wiele osiągnięć i ",
                "nagród za moje wyścigi!",
                "...Oraz cóż, nigdy mnie nie zapytałeś",
                "czym się zajmowałam w przeszłości.",
                "Nawet miałam cudowne",
                " i piękne czerwone lamborghini.",
                "Ale cóż, teraz jestem na to za stara.",
                "Jednak czemu się o to teraz wypytujesz?",
                "Ty? Ty? Chcesz się nauczyć",
                "jak być wyścigowcem?",
                "Zawsze wiedziałam, że",
                " masz szalone marzenia",
                "w tej twojej głowie ale to...",
                "Hmmm...", "W porządku, w porządku.",
                "Nauczę Cię wszystkiego co musisz",
                "wiedzieć o wyścigach!",
                "Jednak lepiej rób notatki",
                "bo ta stara babcia nie będzie",
                "się powtarzać dwa razy.",
                "I nie martw się, ze mną jako mentor",
                "zostaniesz najlepszym",
                "wyścigowcem w całym świecie."};
        final Text textDymek = new Text("");
        Font font = Font.loadFont("file:Minecraftia-Regular.ttf",35);
        textDymek.setFont(font);
        textDymek.setLayoutX(678);
        textDymek.setLayoutY(373);
        textDymek.setWrappingWidth(475);

        animateTextUsingTimeline(dymekContent, textDymek, 4.0, 3.7);

        rootFabula.getChildren().addAll(imageViewMenuFabula, babcia, dymek, imageViewPominFabula, textDymek );
    }

    public static void animateTextUsingTimeline(String[] contentArray, Text textDymek, double timeBetweenStrings, double durationBetweenStrings) {
        Timeline timeline = new Timeline();

        for (int i = 0; i < contentArray.length; i++) {
            String content = contentArray[i];

            KeyFrame keyFrame = new KeyFrame(
                    Duration.seconds(i * timeBetweenStrings),  // seconds between each string
                    e -> animateText(content, textDymek, durationBetweenStrings)
            );
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.setCycleCount(1);
        timeline.play();
    }



    private static void animateText(String content, Text textDymek, double durationBetweenStrings) {
        final Animation typingAnimation = new Transition() {
            {
                setCycleDuration(Duration.seconds(durationBetweenStrings));
            }

            protected void interpolate(double frac) {
                final int length = content.length();
                final int n = Math.round(length * (float) frac);
                textDymek.setText(content.substring(0, n));
            }
        };

        typingAnimation.play();
    }

    public static void chooseThemeScene() {
        activeSamouczekScene = false;
        activeFabulaScene = false;
        activeChooseThemeScene = true;

        rootChooseThemeScene.getChildren().clear();
        ImageView background = new ImageView(new Image("file:Tokyo/backgroundTokyoLevel.png"));
        background.setFitWidth(WIDTH);
        background.setFitHeight(HEIGHT);
        rootChooseThemeScene.getChildren().add(background);

        chooseThemeSceneSetup();

        stage.setTitle("Choose Theme");
        stage.setScene(chooseThemeScene);
    }

    public static void chooseThemeSceneSetup() {
        //tokyo drift
        ImageView tokyoDriftTheme = new ImageView(new Image("file:imagesStart/menutokyo.png"));
        tokyoDriftTheme.setLayoutX(150);
        tokyoDriftTheme.setLayoutY(250);
        tokyoDriftTheme.setFitWidth(400);
        tokyoDriftTheme.setFitHeight(300);
        tokyoDriftTheme.setOnMouseClicked(event -> TokyoDriftTheme.generateTokyo());

        //magical theme --> under construction
        ImageView magicalTheme = new ImageView(new Image("file:imagesStart/menumagic.png"));
        magicalTheme.setLayoutX(650);
        magicalTheme.setLayoutY(250);
        magicalTheme.setFitHeight(300);
        magicalTheme.setFitWidth(400);

        ImageView wBudowie = new ImageView(new Image("file:imagesStart/wBudowie.png"));
        wBudowie.setLayoutX(650);
        wBudowie.setLayoutY(210);
        wBudowie.setOnMouseClicked(event -> {
            ImageView babciaMowi = new ImageView(new Image("file:imagesStart/samouczek_fabula/babcia.png"));
            babciaMowi.setLayoutX(560);
            babciaMowi.setLayoutY(570);
            babciaMowi.setFitHeight(198); //198 (3/5)
            babciaMowi.setFitWidth(147.6); //147.6

            ImageView dymek = new ImageView(new Image("file:imagesStart/samouczek_fabula/dymek.png"));
            dymek.setLayoutX(700);
            dymek.setLayoutY(570);
            dymek.setFitHeight(108);
            dymek.setFitWidth(356.4);

            String[] dymekContent = {"Nadal nad tym pracujemy!", "narazie zapraszam Cię", "do zagrania w ...", "nocny wyścig!!!"};
            final Text textDymek = new Text("");
            Font font = Font.loadFont("file:Minecraftia-Regular.ttf",15);
            textDymek.setFont(font);
            textDymek.setLayoutX(751);
            textDymek.setLayoutY(616);

            animateTextUsingTimeline(dymekContent, textDymek, 3.35, 3.45);

            rootChooseThemeScene.getChildren().addAll(babciaMowi, dymek, textDymek);
        });

        ImageView menuButton = new ImageView(new Image("file:imagesStart/menuButtonChooseTheme.png"));
        menuButton.setLayoutX(1088);
        menuButton.setLayoutY(10);
        menuButton.setOnMouseClicked(event -> menu());

        rootChooseThemeScene.getChildren().addAll(tokyoDriftTheme, magicalTheme, menuButton, wBudowie);
    }

};
