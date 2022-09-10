package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.model.Model;
import com.example.todo_app_javafx.model.Subtask;
import com.example.todo_app_javafx.model.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewSubTaskController implements Initializable {

    public AnchorPane root;
    public TextField titleFld;
    public TextArea description;
    public ToggleGroup priority;
    public Button applyBtn;
    @FXML
     private Label mainLabel;
    private TreeView<Object> treeView;
    private Task task;

    public NewSubTaskController(TreeView<Object> treeView, Task task){
        this.task = task;
        this.treeView = treeView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applyBtn.setOnAction(e-> createNewTask());
        mainLabel.setText("Create new Subtask");
    }
    private void createNewTask(){
        if (titleFld.getText().isEmpty()) {
            return;
        } else {
//            String selectedPriority = ((ToggleButton)priority.getSelectedToggle()).getText();
//            Task newTask = new Task(titleFld.getText(), description.getText(), selectedPriority, Model.getInstance().getUser());
//            Dao.save(newTask);
//            Model.getInstance().getUser().getTasks().add(newTask);
//            treeView.getRoot().getChildren().clear();
//            TasksController.showTasks();

            TreeItem<Object> treeItem = treeView.getRoot().getChildren().filtered(i -> i.getValue().equals(task)).stream().findFirst().get();
            Subtask newSubtask = new Subtask("", task);
            treeItem.getChildren().add(new TreeItem<>(newSubtask));
            task.getSubtasks().add(newSubtask);
            Dao.save(newSubtask);
        }
        Stage stage =(Stage) applyBtn.getScene().getWindow();
        stage.close();
    }
}
