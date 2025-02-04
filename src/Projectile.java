import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Projectile extends Circle {

    private final static double step = 5;

    public Projectile(double centerX, double centerY, double radius) {
        super(centerX, centerY, radius, Color.RED);
        Main.projectiles.add(this);
        //add to root? How?


    }

    public void move(double angle){

        //movement in one frame (calculated so that step is exactly how much step is in diagonal aka 5 atm)

        double moveX = step* Math.cos(angle);
        double moveY = step* Math.sin(angle);


        this.setCenterX(this.getCenterX()+moveX);
        this.setCenterY(this.getCenterY()+moveY);




    }

    public double calculateAngleBetweenPlayerAndClosestOpponent(double xOfOpponent, double yOfOpponent, double PlayerX, double PlayerY ){
        double tanOfAngle = (yOfOpponent- PlayerY) / (xOfOpponent -PlayerX);
        return Math.atan(tanOfAngle);


    }





//    public Opponent getClosestOpponent(){
//
//    }
}
