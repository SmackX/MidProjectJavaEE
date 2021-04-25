package dao;

import model.Article;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostDAO {
    private final List<Article> store = new ArrayList<>();
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
            String SQL = "SELECT * FROM article ";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                Article article = new Article();
                article.setId(resultSet.getInt("articleID"));
                article.setUserId(resultSet.getInt("personID"));
                article.setText(resultSet.getString("text"));
                article.setRating(resultSet.getInt("rating"));
                store.add(article);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void AddToDB(int id, int userId, String text, int rating){
        try {
            String SQL = "INSERT INTO article values (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, userId);
            preparedStatement.setString(3, text);
            preparedStatement.setInt(4, rating);
            preparedStatement.execute();
            System.out.println("New post add!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void UpdateText(int id, String text){
        try {
            Statement statement = connection.createStatement();
            String SQL = "Update article Set text =" + text + " WHERE articleid" + id;
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.execute();
            System.out.println("Well done! text Update!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void DeleteFromDB(int id){
        try {
            String SQL = "DELETE FROM article WHERE id ="+id;
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.execute();
            System.out.println("Well done! Post delete!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean isAuthor(int id){
        boolean auth = false;
        Article article;
        for (int i = 0; i < store.size(); i++) {
            article = store.get(i);
            if (article.getUserId() == id){
                auth = true;
                break;
            }
        }
        return auth;
    }

    public Article getData(int id){
        Article article = null;
        for (int i = 0; i < store.size(); i++) {
            article = store.get(i);
            if (article.getUserId() == id){
                break;
            }
        }
        return article;
    }
    public void AddLike(int id){
        int count = store.get(id).getRating();
        count++;
        store.get(id).setRating(count);
        try {
            Statement statement = connection.createStatement();
            String SQL = "Update article Set rating =" + count + " WHERE articleid" + id;
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.execute();
            System.out.println("Well done! text Update!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public int sizeStore(){
        return store.size();
    }


}
