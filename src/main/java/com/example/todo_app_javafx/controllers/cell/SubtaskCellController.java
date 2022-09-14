package com.example.todo_app_javafx.controllers.cell;

import com.example.todo_app_javafx.controllers.TasksController;
import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.model.Subtask;
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
public class SubtaskCellController implements Initializable {
    @FXML
    private AnchorPane root;
    @FXML
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Text title;

    @FXML
    private Button editBtn;
    @FXML
    private Button delete_btn;
    @FXML
    private CheckBox taskCheckbox;

    private final Subtask subtask;
    private final TreeView<Object> treeView;
    private String description;



    public SubtaskCellController(Subtask task, TreeView<Object> treeView) {
        this.subtask = task;
        this.treeView = treeView;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editBtn.setOnAction(e -> ViewFactory.openSubtaskEditWindow(subtask, this));
        taskCheckbox.setOnAction(e -> setDoneSystem());
        title.setText(subtask.getTitle());
        setDescriptionLblOnClick();
        setDeleteButtonMouseEvent();
        setTooltips();
        setSubtaskPriority();
        setStrikeThrough();



    }

    private void setDoneSystem() {
        if (taskCheckbox.isSelected()) {
            title.setStrikethrough(true);
            subtask.setDone(1);
        }else{
            title.setStrikethrough(false);
            subtask.setDone(0);
        }
        Dao.update(subtask);
    }

    private void setStrikeThrough() {
        if(subtask.getTask().getDone() == 1){
            title.setStrikethrough(true);
            taskCheckbox.setSelected(true);
        }
        if(subtask.getDone() == 1){
            title.setStrikethrough(true);
            taskCheckbox.setSelected(true);
        }
    }

    private void setSubtaskPriority() {
        switch (subtask.getPriority()){
            case "High" -> title.setStyle("-fx-fill: red");
            case "Medium" -> title.setStyle("-fx-fill: orange");
            default -> title.setStyle("-fx-fill: green");

        }
    }

    private void setTooltips() {
        Tooltip edit = new Tooltip("edit");
        Tooltip delete = new Tooltip("delete");
        edit.setShowDelay(Duration.millis(0));
        delete.setShowDelay(Duration.millis(0));
        editBtn.setTooltip(edit);
        delete_btn.setTooltip(delete);
    }

    private void setDescriptionLblOnClick() {
        root.getChildren().get(0).addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            TasksController.description.set(subtask.getDescription());
            event.consume();
        });
    }

    private void setDeleteButtonMouseEvent() {
        delete_btn.setOnAction(e -> {
            TreeItem<Object> treeItem = treeView.getRoot().getChildren().filtered(i -> i.getValue().equals(subtask.getTask())).stream().findFirst().get();
            treeItem.getChildren().removeIf(o-> o.getValue().equals(subtask));
            subtask.getTask().getSubtasks().remove(subtask);
            Dao.delete(subtask);
        });
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }
}
