import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBController {
    private static final String CONN = "jdbc:sqlite:wyniki.db";
    //jdbc - java data base controller



//    public static void add(Candy candy){
//        try {
//            Connection connection = DriverManager.getConnection(CONN);
//
//            String sql = "INSERT INTO candies(candies, weight) VALUES(?, ?);";
//            //? = placeholder indexes from 1(liczone od 1 nie od 0)
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, candy.getName());
//            preparedStatement.setInt(2, candy.getWeight());
//
//            preparedStatement.executeUpdate();
//
//            connection.close();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//    public static List<Candy> get(){
//        List<Candy> candies = new ArrayList<>();
//
//        try {
//            Connection connection = DriverManager.getConnection(CONN);
//
//            String sql = "SELECT * FROM candies;";
//
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while(resultSet.next()){
//                String name = resultSet.getString("candies"); //names
//                int weight = resultSet.getInt("weight");
//                Candy candy = new Candy(name,weight);
//                candies.add(candy);
//            }
//            connection.close();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        return candies;
//    }
}
