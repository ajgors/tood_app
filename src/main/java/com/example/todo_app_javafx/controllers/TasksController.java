package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.view.TaskCellFactory;
import com.example.todo_app_javafx.model.Database;
import com.example.todo_app_javafx.model.Task;
import com.example.todo_app_javafx.view.ViewFactory;
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
    public static ObservableList<Task> tasks = Database.getUserTasks();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deleteAccountBtn.setOnAction(e -> ViewFactory.openDeleteAccountStage());
        logOutBtn.setOnAction(e -> logOut());

        tasksListView.setCellFactory(e -> new TaskCellFactory());
        tasksListView.getItems().addAll(tasks);
        tasks.addListener((ListChangeListener<Task>) change -> {
            tasksListView.getItems().clear();
            tasksListView.getItems().addAll(tasks);
        });
        addTaskBtn.setOnAction(e -> createNewTask());
    }

    private void logOut() {
        Stage stage = (Stage) deleteAccountBtn.getScene().getWindow();
        stage.close();
        ViewFactory.openLoginStage();
    }

    private void createNewTask() {
        if (newTaskTitleFld.getText().isEmpty()) {
            return;
        } else {
            int id = Database.addNewTaskToDatabase(List.of(newTaskTitleFld.getText()));
            tasks.add(new Task(id, newTaskTitleFld.getText()));
        }
        newTaskTitleFld.clear();
    }

}