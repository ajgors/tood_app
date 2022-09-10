package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.model.Model;
import com.example.todo_app_javafx.model.Subtask;
import com.example.todo_app_javafx.model.Task;
import com.example.todo_app_javafx.view.ViewFactory;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskCellController implements Initializable {
    @FXML
    private Label taskTitleLbl;
    @FXML
    private Button delete_btn;
    @FXML
    private Button addSubtaskBtn;
    @FXML
    private AnchorPane root;
    private final Task task;
    private final TreeView<Object> treeView;

    public TaskCellController(Task task, TreeView<Object> treeView) {
        this.task = task;
        this.treeView = treeView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            TasksController.description.set(task.getDescription());
        });
        taskTitleLbl.setText(task.getTitle());
        delete_btn.setOnAction(e -> {
            treeView.getRoot().getChildren().removeIf(treeItem -> treeItem.getValue().equals(task));
            Model.getInstance().getUser().getTasks().remove(task);
            Dao.delete(task);
        });

        addSubtaskBtn.setOnAction(e -> {
            ViewFactory.openNewSubTaskWindow(treeView,task);

        });
    }
}
