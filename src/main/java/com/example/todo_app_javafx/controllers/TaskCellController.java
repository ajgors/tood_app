package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.model.Database;
import com.example.todo_app_javafx.model.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskCellController implements Initializable {
    public Label taskTitleLbl;
    public Button delete_btn;
    private Task task;

    public TaskCellController(Task task) {
        this.task = task;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taskTitleLbl.setText(task.getTitle());
        delete_btn.setOnAction(e -> {
            Database.removeTaskFromDB(task.getId());
            TasksController.tasks.removeIf(i -> i.getId() == task.getId());
        });
    }
}
