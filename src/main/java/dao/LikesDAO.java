package dao;

import model.Likes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LikesDAO {
    private final List<Likes> store = new ArrayList<>();
    private static final String URL = "jdbc:postgresql://localhost:5432/Forum";

    private static final String Username = "postgres";
    private static final String Password = "postgres";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL,Username,Password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void getBD(){

        try {
            Statement statement = connection.createStatement();
            String SQL = "SELECT * FROM likes ";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                Likes likes = new Likes();
                likes.setId(resultSet.getInt("id"));
                likes.setUserId(resultSet.getInt("userId"));
                likes.setPostId(resultSet.getInt("postId"));

                store.add(likes);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void AddToDB(int id, int userId, int postId){
        try {
            String SQL = "INSERT INTO likes values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, userId);
            preparedStatement.setInt(3, postId);
            preparedStatement.execute();
            System.out.println("New like add!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public boolean HaveTo(int user){
        Likes likes = null;
        for (int i = 0; i < store.size(); i++) {
            likes = store.get(i);
            if (likes.getUserId() == user )
                return false;
        }
        return true;
    }
    public int sizeStore(){
        return store.size();
    }

}
