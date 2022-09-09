package com.example.todo_app_javafx.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.List;

public class Database {
    private Database(){}

    public Connection connection;
    public static LoggedUser loggedUser;

    public static Connection connectToDB() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/todo", "root", "Grupa03!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean loginUser(String login, String password, String email){
        Connection connection = Database.connectToDB();
        String sqlSelectUser = "SELECT id,name,login,surname,password,email FROM user WHERE login = ? OR email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectUser, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, login);
            preparedStatement.setString(2, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            String hashedPassword;
            if (resultSet != null && resultSet.next()) {
                loggedUser = new LoggedUser(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("email"));
                hashedPassword = resultSet.getString("password");
                return BCrypt.checkpw(password, hashedPassword);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static void createUser(String name,String login, String surname, String password, String email) {
        String encryptedPassowrd = encrypt(password);
        Connection connection = Database.connectToDB();
        String user = "INSERT INTO user (name,login,surname,password,email) VALUES (?,?,?,?,?)";
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(user)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, surname);
            preparedStatement.setString(4, encryptedPassowrd);
            preparedStatement.setString(5, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static String encrypt(String password) {
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, salt);
        return hashedPassword;
    }
    public static boolean checkIfUserExists(String username) {
        Connection connection = Database.connectToDB();
        String sql = "SELECT * FROM user WHERE login = ?";
        try (
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);
        ) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet != null && resultSet.next();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int addNewTaskToDatabase(List<String> data) {
        Connection connection = Database.connectToDB();
        String task = "INSERT INTO task (title, user_id) VALUES (?,?)";
        try (
                PreparedStatement preparedStatement =
                        connection.prepareStatement(task, Statement.RETURN_GENERATED_KEYS);

        ) {
            preparedStatement.setString(1, data.get(0));
            preparedStatement.setInt(2, Database.loggedUser.getId());
            preparedStatement.executeUpdate();

            ResultSet resultSetInsert = preparedStatement.getGeneratedKeys();

            if(resultSetInsert != null && resultSetInsert.next()) {
                return resultSetInsert.getInt(1);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public static ObservableList<Task> getUserTasks() {
        Connection connection = Database.connectToDB();
        String sqlSelectTaks = "SELECT * FROM task WHERE user_id ='" + Database.loggedUser.getId() + "'";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlSelectTaks);
            ObservableList<Task> tasks =FXCollections.observableArrayList();
            while (resultSet.next()) {
                Task newTask = new Task(resultSet.getInt("id"), resultSet.getString("title"));
                tasks.add(newTask);
            }
            return tasks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void removeTaskFromDB(int id) {
        Connection connection = Database.connectToDB();
        String sqlSelectUser = "DELETE FROM task WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlSelectUser)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkIfPassowrdIsCorrect(String password) {
        Connection connection = Database.connectToDB();
        String sql = "SELECT password FROM user WHERE login = ?";
        try (
                PreparedStatement preparedStatement =
                        connection.prepareStatement(sql);
        ) {

            preparedStatement.setString(1, Database.loggedUser.getLogin());
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet != null && resultSet.next()) {
                return BCrypt.checkpw(password, resultSet.getString(1));
            } else {
                return false;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteCurrentLoggedUser() {
        Connection connection = Database.connectToDB();
        try {
            String user = "DELETE FROM user WHERE login = ?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(user, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, Database.loggedUser.getLogin());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
