package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.model.Model;
import com.example.todo_app_javafx.model.Subtask;
import com.example.todo_app_javafx.model.Task;
import com.example.todo_app_javafx.view.ViewFactory;
import javafx.event.Event;
import javafx.event.EventDispatchChain;
import javafx.event.EventDispatcher;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
public class TaskCellController implements Initializable {
    @FXML
    private Label taskTitleLbl;
    @FXML
    private Button delete_btn;
    private String description;
    @FXML
    private Button addSubtaskBtn;
    @FXML
    private AnchorPane root;
    @FXML
    private Button editBtn;
    private final Task task;
    private final TreeView<Object> treeView;

    public TaskCellController(Task task, TreeView<Object> treeView) {
        this.task = task;
        this.treeView = treeView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editBtn.setOnAction(e -> ViewFactory.openTaskEditWindow(task, this));
        root.getChildren().get(0).addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {

            if (event.getClickCount() == 2) {

                //dodac aby otwieral sie poprawny task a nie wszyskie
//                treeView.getRoot().getChildren().forEach(child -> child.setExpanded(true));


                treeView.getRoot().getChildren().forEach(child -> {
                    if (child.getValue() == task) {
                        child.setExpanded(!child.isExpanded());

                    }
                });
            }
            ;
            TasksController.description.set(task.getDescription());
            event.consume();

        });


        taskTitleLbl.setText(task.getTitle());
        delete_btn.setOnAction(e -> {
            treeView.getRoot().getChildren().removeIf(treeItem -> treeItem.getValue().equals(task));
            Model.getInstance().getUser().getTasks().remove(task);
            Dao.delete(task);
        });

        addSubtaskBtn.setOnAction(e -> {
            ViewFactory.openNewSubTaskWindow(treeView, task);

        });
    }

}
