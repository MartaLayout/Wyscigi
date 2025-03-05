import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.scene.text.Font;
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

    private static Text textFabula;

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

    public static void startScene(){
        //szare
        AnchorPane rootStart = new AnchorPane();

        ImageView imageViewStartScene = new ImageView(new Image("file:imagesStart/GRANDMA (STER).png"));
        imageViewStartScene.setFitHeight(800);
        imageViewStartScene.setFitWidth(1200);

        ImageView imageViewStartButton = new ImageView(new Image("file:imagesStart/start.png"));
        /*Rectangle startButton = new Rectangle(300, 120);*/
        imageViewStartButton.setLayoutX(440);
        imageViewStartButton.setLayoutY(250);
        imageViewStartButton.setFitWidth(300);
        imageViewStartButton.setFitHeight(120);
        imageViewStartButton.setOnMouseClicked(event -> {
            fabulaScene();
        });

        ImageView imageViewButtonSamouczek = new ImageView(new Image("file:imagesStart/samouczek.png"));
        imageViewButtonSamouczek.setLayoutX(440);
        imageViewButtonSamouczek.setLayoutY(410);
        imageViewButtonSamouczek.setFitWidth(300);
        imageViewButtonSamouczek.setFitHeight(50);
        imageViewButtonSamouczek.setOnMouseClicked(event -> {
            samouczekScene();
        });

        rootStart.getChildren().addAll(imageViewStartScene, imageViewButtonSamouczek, imageViewStartButton);

        Scene startScene = new Scene(rootStart, WIDTH, HEIGHT);
        stage.setTitle("Ekran główny");
        stage.setScene(startScene);
    }
    public static void menu(){
        //niebieskie do srodka
        AnchorPane rootMenu= new AnchorPane();

        ImageView imageViewButtonSamouczek1 = new ImageView(new Image("file:imagesStart/samouczekMenu.png"));
        imageViewButtonSamouczek1.setLayoutX(220);
        imageViewButtonSamouczek1.setLayoutY(250);
        imageViewButtonSamouczek1.setFitHeight(80);
        imageViewButtonSamouczek1.setFitWidth(300);

        ImageView imageViewStartSceneButton = new ImageView(new Image("file:imagesStart/powrotNaStart.png"));
        imageViewStartSceneButton.setLayoutX(220);
        imageViewStartSceneButton.setLayoutY(135);
        imageViewStartSceneButton.setFitHeight(80);
        imageViewStartSceneButton.setFitWidth(300);

        /*ImageView imageViewSkipButton = new ImageView(new Image("file:imagesStart/powrotNaStart.png"));
        imageViewSkipButton.setLayoutX(220);
        imageViewSkipButton.setLayoutY(80);
        imageViewSkipButton.setFitWidth(300);
        imageViewSkipButton.setFitHeight(80);*/
        //Zosia mówi że nie potrzebujemy więc się słucham :))


        ImageView imageViewWyjdzZMenu = new ImageView(new Image("file:imagesStart/menueXit.png"));
        imageViewWyjdzZMenu.setLayoutX(10);
        imageViewWyjdzZMenu.setLayoutY(10);
        imageViewWyjdzZMenu.setFitWidth(100);
        imageViewWyjdzZMenu.setFitHeight(50);

        ImageView imageViewWyjdzZGry = new ImageView(new Image("file:imagesStart/wyjdzZGry.png"));
        imageViewWyjdzZGry.setLayoutX(220);
        imageViewWyjdzZGry.setLayoutY(365);
        imageViewWyjdzZGry.setFitHeight(80);
        imageViewWyjdzZGry.setFitWidth(300);

        rootMenu.getChildren().addAll(imageViewButtonSamouczek1, imageViewStartSceneButton, imageViewWyjdzZMenu);

        Scene menu = new Scene(rootMenu, 750, 500);
        Stage stageMenu = new Stage();
        stageMenu.setX(370);
        stageMenu.setY(170);
        stageMenu.setTitle("MENU");
        stageMenu.setScene(menu);
        stageMenu.show();
        stageMenu.setAlwaysOnTop(true);

        imageViewStartSceneButton.setOnMouseClicked(event -> {
            startScene();
            stageMenu.close();
        });

        imageViewButtonSamouczek1.setOnMouseClicked(event -> {
            samouczekScene();
            stageMenu.close();
        });

        /*imageViewSkipButton.setOnMouseClicked(event -> {
            chooseThemeScene();
            stageMenu.close();
        });*/

        imageViewWyjdzZMenu.setOnMouseClicked(event -> {
        //W KONCU DZIAŁA
            if(activeSamouczekScene == true){
                samouczekScene();
                stageMenu.close();
                activeSamouczekScene = false;
            }
            if(activeFabulaScene == true){
                fabulaScene();
                stageMenu.close();
                activeFabulaScene = false;
            }
            if(activeChooseThemeScene == true){
                chooseThemeScene();
                stageMenu.close();
                activeChooseThemeScene = false;
            }
            if(TokyoDriftTheme.activeGenerateScene == true){
                TokyoDriftTheme.generateTokyo();
                stageMenu.close();
                TokyoDriftTheme.activeGenerateScene = false;
            }
            if(TokyoDriftTheme.activeLevel1Scene == true){
                TokyoDriftTheme.level1();
                stageMenu.close();
                TokyoDriftTheme.activeLevel1Scene = false;
            }

        });

        //TODO Alert !!!
        imageViewWyjdzZGry.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stageMenu.setAlwaysOnTop(false);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText("Czy na pewno chcesz wyjść z gry?");
                alert.setTitle("");

                Optional<ButtonType> result = alert.showAndWait();
                if(result.isPresent() && result.get() == ButtonType.OK){
                    stage.close();
                    stageMenu.close();
                }
                else{
                    alert.close();
                }
            }
        });
        rootMenu.getChildren().addAll(imageViewWyjdzZGry);

        /*imageViewWyjdzZGry.setOnMouseClicked(event -> {

            AnchorPane rootWyjdzZgry = new AnchorPane();
            Stage stageWyjdzZGry = new Stage();
            Scene sceneWyjdzZGry = new Scene(rootWyjdzZgry, 400, 200);

            stageWyjdzZGry.setX(550);
            stageWyjdzZGry.setY(400);
            stageWyjdzZGry.setTitle("Wyjdź z gry");
            stageWyjdzZGry.setAlwaysOnTop(true);

            Button cancelButton = new Button("Anuluj");
            cancelButton.setPrefSize(150, 60);
            cancelButton.setLayoutX(130);
            cancelButton.setLayoutY(100);
            cancelButton.setOnAction(event1 -> {
                stageWyjdzZGry.close();
                stageWyjdzZGryActive = false;
            });
            rootWyjdzZgry.getChildren().addAll(cancelButton);*/

    //nie działają te booleany, w sensie jakby ten na cancelButton zmiana booleana nie działa (ja wiem że on jest na false defaultowo)
            /*if(stageWyjdzZGryActive == true){
                Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(5), event1 -> {
                    for (int i = 0; i < 6; i++) {
                        //dlaczego ten tekst się nie wyświetla
                        Text text = new Text();
                        text.setText("Wyjście z gry za " + i + " sek");
                        text.setFont(new Font(20));
                        text.setX(130);
                        text.setY(90);
                        rootWyjdzZgry.getChildren().addAll(text);
                    }
                    stageWyjdzZGry.close();
                    stage.close();
                    stageMenu.close();
                }));
                timeline.play();
            }

            stageWyjdzZGry.setScene(sceneWyjdzZGry);
            stageWyjdzZGry.show();
        });*/
    }

    public static void samouczekScene(){
        //fiolet
        AnchorPane rootSamouczek = new AnchorPane();

        ImageView samouczekImage = new ImageView(new Image("file:imagesStart/babciaSamouczek1.PNG")); //tu coś musimy wymyśleć jak będzie wyglądała plansza samouczka

        ImageView imageViewPominSamouczek = new ImageView(new Image("file:imagesStart/pomin.png"));
        imageViewPominSamouczek.setLayoutX(1088);
        imageViewPominSamouczek.setLayoutY(740);
        imageViewPominSamouczek.setFitHeight(50);
        imageViewPominSamouczek.setFitWidth(100);
        imageViewPominSamouczek.setOnMouseClicked(event -> {
            fabulaScene();
        });


        Timeline timelineSamouczek = new Timeline(new KeyFrame(Duration.millis(1000), event -> {

        }));

        ImageView imageViewMenuSamouczek = new ImageView(new Image("file:imagesStart/menuSamouczek.png"));
        imageViewMenuSamouczek.setLayoutX(1088);
        imageViewMenuSamouczek.setLayoutY(10);
        imageViewMenuSamouczek.setFitWidth(100);
        imageViewMenuSamouczek.setFitHeight(50);
        imageViewMenuSamouczek.setOnMouseClicked(event -> {
            activeSamouczekScene = true;
            menu();
        });



        activeChooseThemeScene = false;
        activeFabulaScene = false;
        activeSamouczekScene = false;

        rootSamouczek.getChildren().addAll(samouczekImage, imageViewMenuSamouczek, imageViewPominSamouczek);

        Scene samouczekScene = new Scene(rootSamouczek, WIDTH, HEIGHT);
        stage.setTitle("Samouczek");
        stage.setScene(samouczekScene);

        samouczekScene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.SPACE){
                Projectile projectile = new Projectile(Math.random()*100, Math.random()*100, 6.0);
                projectiles.add(projectile);
                rootSamouczek.getChildren().add(projectile);
            }
        });
    }

    public static void fabulaScene(){
        //zielone

        AnchorPane rootFabula = new AnchorPane();

        ImageView fabulaImage = new ImageView(new Image("file:")); //to będzie cały obrazek babci z dymkiem na miejsce na tekst
//        Text text1 = new Text("tekst1");


        ImageView imageViewPominFabula = new ImageView(new Image("file:imagesStart/pominFabula.png"));
        imageViewPominFabula.setLayoutX(1088);
        imageViewPominFabula.setLayoutY(740);
        imageViewPominFabula.setFitHeight(50);
        imageViewPominFabula.setFitWidth(100);
        imageViewPominFabula.setOnMouseClicked(event -> {
            chooseThemeScene();
        });


        ImageView imageViewMenuFabula = new ImageView(new Image("file:imagesStart/menuFabula.png"));
        imageViewMenuFabula.setLayoutX(1088);
        imageViewMenuFabula.setLayoutY(10);
        imageViewMenuFabula.setFitWidth(100);
        imageViewMenuFabula.setFitHeight(50);
        imageViewMenuFabula.setOnMouseClicked(event -> {
            activeFabulaScene = true;
            menu();
        });
        activeChooseThemeScene = false;
        activeFabulaScene = false;
        activeSamouczekScene = false;

        //TODO button "dalej" w timeline?
        Timeline timelineFabula = new Timeline(new KeyFrame(Duration.seconds(100), event -> {
            //różne zdj
            for (int i = 0; i < 6; i++) {
                textFabula = new Text("" + i);
                rootFabula.getChildren().addAll(textFabula);
                //rootFabula.getChildren().remove(textFabula);
            }
            textFabula.setX(500);
            textFabula.setY(300);
            textFabula.setFont(Font.font(32));
        }));
        timelineFabula.setCycleCount(1);
        timelineFabula.play();
        timelineFabula.setOnFinished(event -> {
            rootFabula.getChildren().remove(textFabula);
        });

        rootFabula.getChildren().addAll(fabulaImage, imageViewMenuFabula, imageViewPominFabula);
        Scene fabulaScene = new Scene(rootFabula, WIDTH, HEIGHT);
        stage.setTitle("Fabuła");
        stage.setScene(fabulaScene);

    }

    public static void chooseThemeScene() {
        AnchorPane rootTheme = new AnchorPane();

        //tokyo drift
        ImageView tokyoDriftTheme = new ImageView(new Image("file:imagesStart/menutokyo.png")); //file needed
        tokyoDriftTheme.setLayoutX(150);
        tokyoDriftTheme.setLayoutY(250);
        tokyoDriftTheme.setFitWidth(400);
        tokyoDriftTheme.setFitHeight(300);
        tokyoDriftTheme.setOnMouseClicked(event -> {
            TokyoDriftTheme.generateTokyo();
        });

        //magical theme
        ImageView magicalTheme = new ImageView(new Image("file:imagesStart/menumagic.png"));
        magicalTheme.setLayoutX(650);
        magicalTheme.setLayoutY(250);
        magicalTheme.setFitHeight(300);
        magicalTheme.setFitWidth(400);
        magicalTheme.setOnMouseClicked(event -> {
            MagicalTheme.generateMagical();
        });

        Button menu3 = new Button("MENU");
        menu3.setLayoutX(1088);
        menu3.setLayoutY(10);
        menu3.setPrefSize(100, 50);
        menu3.setOnAction(event -> {
            activeChooseThemeScene = true;
            menu();
        });
        activeChooseThemeScene = false;
        activeFabulaScene = false;
        activeSamouczekScene = false;

        rootTheme.getChildren().addAll(tokyoDriftTheme, magicalTheme, menu3);
        Scene chooseThemeScene = new Scene(rootTheme, WIDTH, HEIGHT);
        stage.setTitle("Choose theme");
        stage.setScene(chooseThemeScene);
    }

//    public void trackScene (){
//        AnchorPane rootTrack = new AnchorPane();
//
//        ImageView tokyoDriftTheme = new ImageView(new Image("file:imagesStart\\menutokyo.png")); //file needed
//        tokyoDriftTheme.setLayoutX(0);
//        tokyoDriftTheme.setLayoutY(0);
//
//
//    }
}