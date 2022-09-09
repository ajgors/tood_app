package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.dao.TaskDao;
import com.example.todo_app_javafx.model.Model;
import com.example.todo_app_javafx.model.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TaskCellController implements Initializable {
    @FXML
    private Label taskTitleLbl;
    @FXML
    private Button delete_btn;
    private final Task task;

    public TaskCellController(Task task) {
        this.task = task;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taskTitleLbl.setText(task.getTitle());
        delete_btn.setOnAction(e -> {
            TaskDao.deleteById(task.getId());
            Model.getInstance().getTasks().removeIf(i -> Objects.equals(i.getId(), task.getId()));
            Model.getInstance().getUser().getTasks().removeIf(i -> Objects.equals(i.getId(), task.getId()));
        });
    }
}
