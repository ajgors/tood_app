package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.model.Model;
import com.example.todo_app_javafx.model.Task;
import com.example.todo_app_javafx.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lombok.AccessLevel;
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
    @FXML
    private CheckBox taskCheckbox;
    private String description;
    @FXML
    private Button addSubtaskBtn;
    @FXML
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)

    private Text title;
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
        editBtn.setOnMouseClicked(e -> ViewFactory.openTaskEditWindow(task, this));
        root.getChildren().get(0).addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {

            if (event.getClickCount() == 2) {

                treeView.getRoot().getChildren().forEach(child -> {
                    if (child.getValue() == task) {
                        child.setExpanded(!child.isExpanded());

                    }
                });
            }
            TasksController.description.set(task.getDescription());
            event.consume();

        });

        Tooltip edit = new Tooltip("edit");
        Tooltip addSubtask = new Tooltip("add subtask");
        Tooltip delete = new Tooltip("delete");
        edit.setShowDelay(Duration.millis(0));
        addSubtask.setShowDelay(Duration.millis(0));
        delete.setShowDelay(Duration.millis(0));
        editBtn.setTooltip(edit);
        delete_btn.setTooltip(delete);
        addSubtaskBtn.setTooltip(addSubtask);


        delete_btn.setOnAction(e -> {
            treeView.getRoot().getChildren().removeIf(treeItem -> treeItem.getValue().equals(task));
            Model.getInstance().getUser().getTasks().remove(task);
            Dao.delete(task);
        });

        addSubtaskBtn.setOnAction(e -> {
            ViewFactory.openNewSubTaskWindow(treeView, task);

        });
//        taskTitleLbl.setText(task.getTitle());

        switch (task.getPriority()){
            case "High" -> title.setStyle("-fx-fill: red");
            case "Medium" -> title.setStyle("-fx-fill: orange");
            case "Low" -> title.setStyle("-fx-fill: green");
        };

        title.setText(task.getTitle());

        if(task.getDone() == 1){
          title.setStrikethrough(true);
          taskCheckbox.setSelected(true);
        }
        taskCheckbox.setOnAction(e -> {
            if (taskCheckbox.isSelected()) {
                title.setStrikethrough(true);
                task.setDone(1);
                task.getSubtasks().forEach(subtask -> subtask.setDone(1));

            }else{
                title.setStrikethrough(false);
                task.setDone(0);
            }
                treeView.refresh();
            Dao.update(task);
        });
    }
    public void setTitle(String title) {
        this.title.setText(title);
    }

}
