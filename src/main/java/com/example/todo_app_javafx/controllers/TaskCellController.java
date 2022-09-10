package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.model.Model;
import com.example.todo_app_javafx.model.Subtask;
import com.example.todo_app_javafx.model.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import java.net.URL;
import java.util.ResourceBundle;

public class TaskCellController implements Initializable {
    @FXML
    private Label taskTitleLbl;
    @FXML
    private Button delete_btn;
    @FXML
    private Button addSubtaskBtn;
    private final Task task;
    private final TreeView<Object> treeView;

    public TaskCellController(Task task, TreeView<Object> treeView) {
        this.task = task;
        this.treeView = treeView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taskTitleLbl.setText(task.getTitle());
        delete_btn.setOnAction(e -> {
            treeView.getRoot().getChildren().removeIf(treeItem -> treeItem.getValue().equals(task));
            Model.getInstance().getUser().getTasks().remove(task);
            Dao.delete(task);
        });

        addSubtaskBtn.setOnAction(e -> {
            TreeItem<Object> treeItem = treeView.getRoot().getChildren().filtered(i -> i.getValue().equals(task)).stream().findFirst().get();
            Subtask newSubtask = new Subtask("", task);
            treeItem.getChildren().add(new TreeItem<>(newSubtask));
            task.getSubtasks().add(newSubtask);
            Dao.save(newSubtask);

        });
    }
}
