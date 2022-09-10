package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.dao.SubTaskDao;
import com.example.todo_app_javafx.model.Subtask;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.net.URL;
import java.util.ResourceBundle;

public class SubTaskCellController implements Initializable {
    @FXML
    private Label taskTitleLbl;
    @FXML
    private Button delete_btn;
    private final Subtask subtask;
    private final TreeView<Object> treeView;

    public SubTaskCellController(Subtask task, TreeView<Object> treeView) {
        this.subtask = task;
        this.treeView = treeView;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        taskTitleLbl.setText(subtask.getTitle());
        delete_btn.setOnAction(e -> {
            TreeItem<Object> treeItem = treeView.getRoot().getChildren().filtered(i -> i.getValue().equals(subtask.getTask())).stream().findFirst().get();
            treeItem.getChildren().removeIf(o-> o.getValue().equals(subtask));
            SubTaskDao.deleteById(subtask.getId());
        });

    }
}
