import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    static Scene samouczekScene = new Scene(rootSamouczek, WIDTH, HEIGHT);
    static Scene fabulaScene = new Scene(rootFabula, WIDTH, HEIGHT);
    static Scene chooseThemeScene = new Scene(rootChooseThemeScene, WIDTH, HEIGHT);

    static Rectangle rectangleSamouczek = new Rectangle(100, 100);
    static Player playerSamouczek;
    static Rectangle torMalutkiSamouczek = new Rectangle(700, 200, 350, 200);
    static ImageView torSamouczekImageView = new ImageView(new Image("file:imagesStart/samouczek_fabula/torMaly.png"));

    static List<Projectile> projectiles = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        startScene();
        stage.show();
    }

    public static void startScene() {
        // Create the start screen
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
        // Create the menu scene
        AnchorPane rootMenu = new AnchorPane();

        ImageView backgroundMenu = new ImageView(new Image("file:Tokyo/backgroundGenerate.png")); //TODO?
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

        ImageView wyjdzZMenu = new ImageView(new Image("file:imagesStart/menu/menueXit.png"));
        wyjdzZMenu.setLayoutX(30);
        wyjdzZMenu.setLayoutY(30);
        wyjdzZMenu.setFitWidth(100);
        wyjdzZMenu.setFitHeight(50);

        ImageView wyjdzZGry = new ImageView(new Image("file:imagesStart/menu/wyjdzZGry.png"));
        wyjdzZGry.setLayoutX(220);
        wyjdzZGry.setLayoutY(365);
        wyjdzZGry.setFitHeight(80);
        wyjdzZGry.setFitWidth(300);

        rootMenu.getChildren().addAll(samouczekButtonMenu, powrotNaStartButton, wyjdzZMenu, wyjdzZGry);

        Scene menu = new Scene(rootMenu, 750, 500);
        Stage stageMenu = new Stage();
        stageMenu.setX(370);
        stageMenu.setY(170);
        stageMenu.setTitle("MENU");
        stageMenu.setScene(menu);
        stageMenu.show();
        stageMenu.setAlwaysOnTop(true);

        // Only visible if in game or specific scenes
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
            TokyoDriftTheme.level1();
            stageMenu.close();
        } else if (TokyoDriftTheme.activeLevel2Scene) {
            TokyoDriftTheme.level2();
            stageMenu.close();
        } else if (TokyoDriftTheme.activeLevel3Scene) {
            TokyoDriftTheme.level3();
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

        ImageView babciaSamouczek = new ImageView(new Image("file:imagesStart/samouczek_fabula/babcia.png"));
        babciaSamouczek.setLayoutX(50);
        babciaSamouczek.setLayoutY(22);
        babciaSamouczek.setFitHeight(165);
        babciaSamouczek.setFitWidth(123);

        ImageView dymekSamouczek = new ImageView(new Image("file:imagesStart/samouczek_fabula/dymek.png"));
        dymekSamouczek.setLayoutX(170);
        dymekSamouczek.setLayoutY(26);
        dymekSamouczek.setFitWidth(297);
        dymekSamouczek.setFitHeight(90);

        playerSamouczek = new Player(710, 210);
        playerSamouczek.setFitWidth(50);
        playerSamouczek.setFitHeight(24);

        torMalutkiSamouczek.setStroke(Color.BLACK);
        torMalutkiSamouczek.setStrokeWidth(10);
        torMalutkiSamouczek.setFill(Color.TRANSPARENT);
        torSamouczekImageView.setFitWidth(torMalutkiSamouczek.getWidth());
        torSamouczekImageView.setFitHeight(torMalutkiSamouczek.getHeight());
        torSamouczekImageView.setLayoutX(700);
        torSamouczekImageView.setLayoutY(200);

        String[] dymekContentSamouczek = {"Hej, to jest do samouczka", "damy tu taki tekst", "na zasadzie, że", "młody graczu, poznaj", "zasady itd"}; //todo tekst

        final Text textDymekSamouczek = new Text("");
        Font font = Font.loadFont("file:Minecraftia-Regular.ttf",15);
        textDymekSamouczek.setFont(font);
        textDymekSamouczek.setLayoutX(192);
        textDymekSamouczek.setLayoutY(70);

        animateTextUsingTimeline(dymekContentSamouczek, textDymekSamouczek, 3.4);

        rootSamouczek.getChildren().addAll(imageViewMenuSamouczek, imageViewPominSamouczek, rectangleSamouczek, dymekSamouczek, babciaSamouczek, textDymekSamouczek, torMalutkiSamouczek, torSamouczekImageView, playerSamouczek);
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
        dymek.setLayoutX(600);
        dymek.setLayoutY(280);

        ImageView imageViewPominFabula = new ImageView(new Image("file:imagesStart/pominFabula.png"));
        imageViewPominFabula.setLayoutX(1088);
        imageViewPominFabula.setLayoutY(740);
        imageViewPominFabula.setFitHeight(50);
        imageViewPominFabula.setFitWidth(100);
        imageViewPominFabula.setOnMouseClicked(event -> chooseThemeScene());

        //TODO button "dalej" w timeline?
        String[] dymekContent = {"ok I can't believe it works", "testing again", "the babcia :)))", "I'm really happy", "with how that works"}; //todo tekst
        final Text textDymek = new Text("");
        Font font = Font.loadFont("file:Minecraftia-Regular.ttf",35);
        textDymek.setFont(font);
        textDymek.setLayoutX(651);
        textDymek.setLayoutY(370);

        animateTextUsingTimeline(dymekContent, textDymek, 3.5);

        rootFabula.getChildren().addAll(imageViewMenuFabula, babcia, dymek, imageViewPominFabula, textDymek );
    }

    public static void animateTextUsingTimeline(String[] contentArray, Text textDymek, double timeBetweenStrings) {
        Timeline timeline = new Timeline();

        for (int i = 0; i < contentArray.length; i++) {
            final int index = i;
            String content = contentArray[i];

            KeyFrame keyFrame = new KeyFrame(
                    Duration.seconds(i * timeBetweenStrings),  // seconds between each string
                    e -> animateText(content, textDymek)
            );
            timeline.getKeyFrames().add(keyFrame);
        }

        timeline.setCycleCount(1);
        timeline.play();
    }

    private static void animateText(String content, Text textDymek) {
        final Animation typingAnimation = new Transition() {
            {
                setCycleDuration(Duration.seconds(3.25)); // Duration of typing effect for each string
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

        rootChooseThemeScene.getChildren().clear(); // Clear previous elements
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

        //magical theme --> we are not doing that probably
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

            animateTextUsingTimeline(dymekContent, textDymek, 3.35);

            rootChooseThemeScene.getChildren().addAll(babciaMowi, dymek, textDymek);
        });

        Button menu3 = new Button("MENU");
        menu3.setLayoutX(1088);
        menu3.setLayoutY(10);
        menu3.setPrefSize(100, 50);
        menu3.setOnAction(event -> menu());

        rootChooseThemeScene.getChildren().addAll(tokyoDriftTheme, magicalTheme, menu3, wBudowie);
    }
}