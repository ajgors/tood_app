package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.model.*;
import com.example.todo_app_javafx.view.TreeCellFactory;
import com.example.todo_app_javafx.view.ViewFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class TasksController implements Initializable {

    @FXML
    private Label descriptionLbl;
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

    public static StringProperty description = new SimpleStringProperty();

    private static final TreeItem<Object> tasks = new TreeItem<>(null);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addTaskBtn.setOnAction(e-> ViewFactory.openNewTaskWindow(treeView));
        deleteAccountBtn.setOnAction(e -> ViewFactory.openDeleteAccountStage());
        logOutBtn.setOnAction(e -> logOut());
        showTasks();
        treeView.setRoot(tasks);
        treeView.setShowRoot(false);
        treeView.setCellFactory(e -> new TreeCellFactory());
        description.addListener((observable, oldValue, newValue) -> {
            descriptionLbl.setText(newValue);
        });
        treeView.setOnMouseClicked(null);
    }

    public static void showTasks() {
        for (Task task : Model.getInstance().getUser().getTasks()) {
            TreeItem<Object> taskItem = new TreeItem<>(task);
            for (Subtask subtask : task.getSubtasks()) {
                taskItem.getChildren().add(new TreeItem<>(subtask));
            }
            tasks.getChildren().add(taskItem);
        }
    }


    private void logOut() {
        treeView.getRoot().getChildren().clear();
        Model.getInstance().setUser(null);
        Stage stage = (Stage) deleteAccountBtn.getScene().getWindow();
        stage.close();
        ViewFactory.openLoginStage();
    }

//    private void createNewTask() {
//        if (newTaskTitleFld.getText().isEmpty()) {
//            return;
//        } else {
//            Task newTask = new Task(newTaskTitleFld.getText(), Model.getInstance().getUser());
//            Dao.save(newTask);
//            Model.getInstance().getUser().getTasks().add(newTask);
//        }
//        newTaskTitleFld.clear();
//    }

}