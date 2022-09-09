package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.dao.UserDao;
import com.example.todo_app_javafx.model.*;
import com.example.todo_app_javafx.view.TaskCellFactory;
import com.example.todo_app_javafx.view.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TasksController implements Initializable {
    @FXML
    private ListView<Task> tasksListView;
    @FXML
    private TextField newTaskTitleFld;
    @FXML
    private Button addTaskBtn;
    @FXML
    private Button logOutBtn;
    @FXML
    private Button deleteAccountBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deleteAccountBtn.setOnAction(e -> ViewFactory.openDeleteAccountStage());
        logOutBtn.setOnAction(e -> logOut());
        tasksListView.setCellFactory(e -> new TaskCellFactory());
        tasksListView.getItems().addAll(Model.getInstance().getTasks());
        Model.getInstance().getTasks().addListener((ListChangeListener<Task>) change -> {
            tasksListView.getItems().clear();
            tasksListView.getItems().addAll(Model.getInstance().getTasks());
        });
        addTaskBtn.setOnAction(e -> createNewTask());
    }

    private void logOut() {
        tasksListView.getItems().clear();
        Model.getInstance().getTasks().clear();
        Model.getInstance().setUser(null);
        Stage stage = (Stage) deleteAccountBtn.getScene().getWindow();
        stage.close();
        ViewFactory.openLoginStage();
    }

    private void createNewTask() {
        if (newTaskTitleFld.getText().isEmpty()) {
            return;
        } else {
            Task newTask = new Task(newTaskTitleFld.getText(), Model.getInstance().getUser());
            Dao.save(newTask);
            tasksListView.getItems().add(newTask);
        }
        newTaskTitleFld.clear();
    }

}