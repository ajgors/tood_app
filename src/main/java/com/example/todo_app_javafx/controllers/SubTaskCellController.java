package com.example.todo_app_javafx.controllers;

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
public class SubTaskCellController implements Initializable {
    @FXML
    private Label taskTitleLbl;
    @FXML
    private Button delete_btn;
    private String description;
    @FXML
    private CheckBox taskCheckbox;
    @FXML
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Text title;
    @FXML
    private Button editBtn;
    private final Subtask subtask;
    @FXML
    private AnchorPane root;
    private final TreeView<Object> treeView;


    //mogł bym przekazac treeview do open edit task
    // i po edycji zrobic treeView.refresh()


    public SubTaskCellController(Subtask task, TreeView<Object> treeView) {
        this.subtask = task;
        this.treeView = treeView;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        treeView.setEventDispatcher((event, tail) -> null);

        root.getChildren().get(0).addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            TasksController.description.set(subtask.getDescription());
            event.consume();
        });
        delete_btn.setOnAction(e -> {
            TreeItem<Object> treeItem = treeView.getRoot().getChildren().filtered(i -> i.getValue().equals(subtask.getTask())).stream().findFirst().get();
            treeItem.getChildren().removeIf(o-> o.getValue().equals(subtask));
            subtask.getTask().getSubtasks().remove(subtask);
            Dao.delete(subtask);
        });
        editBtn.setOnAction(e -> ViewFactory.openSubtaskEditWindow(subtask, this));
        Tooltip edit = new Tooltip("edit");
        Tooltip delete = new Tooltip("delete");
        edit.setShowDelay(Duration.millis(0));
        delete.setShowDelay(Duration.millis(0));
        editBtn.setTooltip(edit);
        delete_btn.setTooltip(delete);

        switch (subtask.getPriority()){
            case "High" -> title.setStyle("-fx-fill: red");
            case "Medium" -> title.setStyle("-fx-fill: orange");
            case "Low" -> title.setStyle("-fx-fill: green");
        };

        title.setText(subtask.getTitle());

        if(subtask.getTask().getDone() == 1){
            title.setStrikethrough(true);
            taskCheckbox.setSelected(true);
        }
        if(subtask.getDone() == 1){
            title.setStrikethrough(true);
            taskCheckbox.setSelected(true);

        }
        taskCheckbox.setOnAction(e -> {
            if (taskCheckbox.isSelected()) {
                title.setStrikethrough(true);
                subtask.setDone(1);
            }else{
                title.setStrikethrough(false);
                subtask.setDone(0);
            }
            Dao.update(subtask);
        });
    }
    public void setTitle(String title) {
        this.title.setText(title);
    }
}
