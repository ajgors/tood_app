package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.dao.TaskDao;
import com.example.todo_app_javafx.model.Model;
import com.example.todo_app_javafx.model.Subtask;
import com.example.todo_app_javafx.model.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class SubTaskCellController implements Initializable {
    @FXML
    private Label taskTitleLbl;
    @FXML
    private Button delete_btn;
    private final Subtask subtask;

    public SubTaskCellController(Subtask task) {
        this.subtask = task;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taskTitleLbl.setText(subtask.getTitle());
        delete_btn.setOnAction(e -> {
        });

    }
}
