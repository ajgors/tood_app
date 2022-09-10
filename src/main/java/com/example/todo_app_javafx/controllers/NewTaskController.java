package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.model.Model;
import com.example.todo_app_javafx.model.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewTaskController implements Initializable {
    public AnchorPane root;
    public TextField titleFld;
    public TextArea description;
    public ToggleGroup priority;
    public Button applyBtn;
    private TreeView<Object> treeView;

    public NewTaskController(TreeView<Object> treeView){
        this.treeView = treeView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applyBtn.setOnAction(e-> createNewTask());
    }
    private void createNewTask(){
        if (titleFld.getText().isEmpty()) {
            return;
        } else {
            Task newTask = new Task(titleFld.getText(), Model.getInstance().getUser());
            Dao.save(newTask);
            Model.getInstance().getUser().getTasks().add(newTask);
            treeView.getRoot().getChildren().clear();
            TasksController.showTasks();
        }
        Stage stage =(Stage) applyBtn.getScene().getWindow();
        stage.close();
    }
}
