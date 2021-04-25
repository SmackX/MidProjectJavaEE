package dao;

import model.Article;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final List<User> store = new ArrayList<>();
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
            String SQL = "SELECT * FROM person";
            ResultSet resultSet = statement.executeQuery(SQL);

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(StringToRole(resultSet.getString("role")));
                store.add(user);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public User getById(int id) {

        User result = new User();
        result.setId(-1);

        for (User user : store) {
            if (user.getId() == id) {
                result = user;
            }
        }

        return result;
    }

    public User getUserByLoginPassword(final String login, final String password) {

        User result = new User();
        result.setId(-1);

        for (User user : store) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user;
            }
        }

        return result;
    }

        public boolean add(final User user) {

        for (User u : store) {
            if (u.getLogin().equals(user.getLogin()) && u.getPassword().equals(user.getPassword())) {
                return false;
            }
        }

        return store.add(user);
    }
    public User.Role StringToRole(String roleDB){
        User.Role role;
        if (roleDB.equals("admin")) {
            role = User.Role.ADMIN;
        }else if (roleDB.equals("user"))
            role = User.Role.USER;
        else {
            role = User.Role.UNKNOWN;
        }
        return role;
    }
    public String RoleToString(User.Role role){
        String stringRole = "";
        if (role.equals(User.Role.ADMIN)) {
            stringRole = "admin";
        }else if (role.equals(User.Role.USER))
            stringRole = "user";
        else {
            stringRole = "Unknown";
        }
        return stringRole;
    }
    public User.Role getRoleByLoginPassword(final String login, final String password) {
        User.Role result = User.Role.UNKNOWN;

        for (User user : store) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = user.getRole();
            }
        }

        return result;
    }

    public boolean userIsExist(final String login, final String password) {

        boolean result = false;

        for (User user : store) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = true;
                break;
            }
        }

        return result;
    }

    public void AddToDB(int id, String login, String password, User.Role role){
        try {
            String SQL = "INSERT INTO person values (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, RoleToString(role));
            preparedStatement.execute();
            System.out.println("New account add!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void DeleteFromDB(int id){
        try {
            Statement statement = connection.createStatement();
            String SQL = "DELETE FROM person WHERE id ="+id;
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.execute();
            System.out.println("Well done! Person delete!");
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public boolean isUser(int id){
        boolean auth = false;
        User user;
        for (int i = 0; i < store.size(); i++) {
            user = store.get(i);
            if (user.getId() == id){
                auth = true;
                break;
            }
        }
        return auth;
    }
    public String getName(int id){
        User user = null;
        user = store.get(id);

        return user.getLogin();
    }
    public int getId(String name){
        User user = null;
        for (int i = 0; i < store.size(); i++) {
            user = store.get(i);
            if (user.getLogin().equals(name)){
                break;
            }
        }
        return user.getId();
    }
}
