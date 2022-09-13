package com.example.todo_app_javafx;

import com.example.todo_app_javafx.view.ViewFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static final EntityManager entityManager = Persistence.createEntityManagerFactory("todo_app").createEntityManager();

    @Override
    public void start(Stage stage) {
        ViewFactory.openLoginStage();
    }

    public static void main(String[] args) {

        launch();
    }
}