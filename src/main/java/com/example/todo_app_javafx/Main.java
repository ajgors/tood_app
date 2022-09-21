package com.example.todo_app_javafx;

import com.example.todo_app_javafx.model.ConnectToDb;
import com.example.todo_app_javafx.view.ViewFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void init() throws Exception {
        super.init();
        ConnectToDb connectToDb = new ConnectToDb();
        Thread thread = new Thread(connectToDb);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void start(Stage stage) {

        ViewFactory.openLoginStage();
    }

    public static void main(String[] args) {

        launch();
    }
}