package com.example.todo_app_javafx.view;

import com.example.todo_app_javafx.controllers.TasksController;
import com.example.todo_app_javafx.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    private static ViewFactory ViewFactory;

    private ViewFactory(){}

    public static void getTasksWindow() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Tasks.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TasksController tasksController = new TasksController();
        fxmlLoader.setController(tasksController);
        stage.setScene(scene);
        stage.show();
    }


    public static void openLoginStage() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setScene(scene);
        stage.show();
    }

    public static void openDeleteAccountStage() {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("DeleteAccount.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        TasksController tasksController = new TasksController();
        fxmlLoader.setController(tasksController);
        stage.setScene(scene);
        stage.show();
    }
    public static AnchorPane getRegisterView(){
        try {
            return new FXMLLoader(Main.class.getResource("Register.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static AnchorPane getLoginView(){
        try {
            return new FXMLLoader(Main.class.getResource("Login.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
