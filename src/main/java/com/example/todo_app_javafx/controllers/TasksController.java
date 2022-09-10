package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.model.*;
import com.example.todo_app_javafx.view.TreeCellFactory;
import com.example.todo_app_javafx.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TasksController implements Initializable {
    @FXML
    private TreeView<Object> treeView;
    @FXML
    private TextField newTaskTitleFld;
    @FXML
    private Button addTaskBtn;
    @FXML
    private Button logOutBtn;
    @FXML
    private Button deleteAccountBtn;

    private static final TreeItem<Object> tasks = new TreeItem<>(null);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deleteAccountBtn.setOnAction(e -> ViewFactory.openDeleteAccountStage());
        logOutBtn.setOnAction(e -> logOut());
        addTaskBtn.setOnAction(e -> {
            treeView.getRoot().getChildren().clear();
            createNewTask();
            showTasks();
        });

        tasks.setExpanded(true);
        showTasks();
        treeView.setRoot(tasks);
        treeView.setShowRoot(false);
        treeView.setCellFactory(e -> new TreeCellFactory());

    }

    public static void showTasks() {
        for (Task task : Model.getInstance().getUser().getTasks()) {
            TreeItem<Object> taskItem = new TreeItem<>(task);
            for (Subtask subtask : task.getSubtasks()) {
                taskItem.getChildren().add(new TreeItem<>(subtask));
            }
            tasks.getChildren().add(taskItem);
            taskItem.setExpanded(true);
        }
    }

    private void logOut() {
        treeView.getRoot().getChildren().clear();
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
            Model.getInstance().getUser().getTasks().add(newTask);
        }
        newTaskTitleFld.clear();
    }

}