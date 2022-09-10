package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.model.*;
import com.example.todo_app_javafx.view.TreeCellFactory;
import com.example.todo_app_javafx.view.ViewFactory;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TasksController implements Initializable {
    public TreeView<Object> treeView;
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

    private static TreeItem<Object> tasks = new TreeItem<>(null);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deleteAccountBtn.setOnAction(e -> ViewFactory.openDeleteAccountStage());
        logOutBtn.setOnAction(e -> logOut());


        Model.getInstance().getTasks().addListener((ListChangeListener<Task>) change -> {
            treeView.getRoot().getChildren().clear();
            showTasks();
        });
        addTaskBtn.setOnAction(e -> createNewTask());


        tasks.setExpanded(true);
        showTasks();
        treeView.setRoot(tasks);
        treeView.setShowRoot(false);
        treeView.setCellFactory(e -> new TreeCellFactory());

    }

    public static void showTasks() {
        for (Task task : Model.getInstance().getTasks()) {

            TreeItem<Object> taskItem = new TreeItem<>(task);
            for (Subtask subtask : task.getSubtask()) {
                taskItem.getChildren().add(new TreeItem<>(subtask));
            }
            tasks.getChildren().add(taskItem);
            taskItem.setExpanded(true);
        }
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
            Model.getInstance().getTasks().add(newTask);
            Model.getInstance().getUser().getTasks().add(newTask);
        }
        newTaskTitleFld.clear();
    }

}