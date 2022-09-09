package com.example.todo_app_javafx;

import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.model.Task;
import com.example.todo_app_javafx.view.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        ViewFactory.openLoginStage();
    }

    public static void main(String[] args) {

        launch();
    }
}