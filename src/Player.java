//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//
//public class Player extends Car {
//
//    static Image image = new Image("file:imageStart//car.png");
//    static ImageView imageView  = new ImageView(image);;
//
//
//
//    public Player(double x, double y) {
//
//        super(x, y, imageView);
//
//    }
//
//    public static ImageView getImageView() {
//        return imageView;
//    }
//}


import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends Car{

    static Image image = new Image("file:Tokyo/car.png");

    public Player(double x,double y) {
        super(x, y, image);
    }}


