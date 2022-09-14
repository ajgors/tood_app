package com.example.todo_app_javafx.controllers.cell;

import com.example.todo_app_javafx.controllers.TasksController;
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
    private Button delete_btn;
    @FXML
    private CheckBox taskCheckbox;

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
    private String description;

    public TaskCellController(Task task, TreeView<Object> treeView) {
        this.task = task;
        this.treeView = treeView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        title.setText(task.getTitle());
        editBtn.setOnMouseClicked(e -> ViewFactory.openTaskEditWindow(task, this));
        addSubtaskBtn.setOnAction(e -> ViewFactory.openNewSubTaskWindow(treeView, task));
        taskCheckbox.setOnAction(e -> setDoneSystem());
        setTaskCellMouseEvent();
        setTooltips();
        setDeleteButtonMouseEvent();
        setTaskPriority();
        setStrikeThrough();
    }

    private void setDoneSystem() {
        if (taskCheckbox.isSelected()) {
            title.setStrikethrough(true);
            task.setDone(1);
            task.getSubtasks().forEach(subtask -> subtask.setDone(1));

        } else {
            title.setStrikethrough(false);
            task.setDone(0);
        }
        treeView.refresh();
        Dao.update(task);
    }

    private void setStrikeThrough() {
        if (task.getDone() == 1) {
            title.setStrikethrough(true);
            taskCheckbox.setSelected(true);
        }
    }

    private void setTaskPriority() {
        switch (task.getPriority()) {
            case "High" -> title.setStyle("-fx-fill: red");
            case "Medium" -> title.setStyle("-fx-fill: orange");
            case "Low" -> title.setStyle("-fx-fill: green");
        }
    }

    private void setDeleteButtonMouseEvent() {
        delete_btn.setOnAction(e -> {
            treeView.getRoot().getChildren().removeIf(treeItem -> treeItem.getValue().equals(task));
            Model.getInstance().getUser().getTasks().remove(task);
            Dao.delete(task);

        });
    }

    private void setTaskCellMouseEvent() {
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
    }

    private void setTooltips() {
        Tooltip edit = new Tooltip("edit");
        Tooltip addSubtask = new Tooltip("add subtask");
        Tooltip delete = new Tooltip("delete");
        edit.setShowDelay(Duration.millis(0));
        addSubtask.setShowDelay(Duration.millis(0));
        delete.setShowDelay(Duration.millis(0));
        editBtn.setTooltip(edit);
        delete_btn.setTooltip(delete);
        addSubtaskBtn.setTooltip(addSubtask);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

}
