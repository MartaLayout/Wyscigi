public interface Obstacle {

//    boolean collide(Car car);
//    boolean collide(Opponent opponent);
    void movement();
    void delete();
    boolean collision(Car car);
//    void updateStateOfObstacle();
}
