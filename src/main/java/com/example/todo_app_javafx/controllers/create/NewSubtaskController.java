package com.example.todo_app_javafx.controllers.create;

import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.model.Subtask;
import com.example.todo_app_javafx.model.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewSubtaskController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private TextField titleFld;
    @FXML
    private TextArea description;
    @FXML
    private ToggleGroup priority;
    @FXML
    private Button applyBtn;
    @FXML
    private Label mainLabel;

    private TreeView<Object> treeView;
    private Task task;

    public NewSubtaskController(TreeView<Object> treeView, Task task) {
        this.task = task;
        this.treeView = treeView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applyBtn.setOnAction(e -> createNewSubtask());
        mainLabel.setText("Create new Subtask");
    }

    private void createNewSubtask() {
        if (titleFld.getText().isEmpty()) {
            return;
        } else {
            String selectedPriority = ((ToggleButton) priority.getSelectedToggle()).getText();
            TreeItem<Object> treeItem = treeView.getRoot().getChildren().filtered(i -> i.getValue().equals(task)).stream().findFirst().get();
            Subtask newSubtask = new Subtask(titleFld.getText(), description.getText(), selectedPriority, task);
            treeItem.getChildren().add(new TreeItem<>(newSubtask));
            task.getSubtasks().add(newSubtask);
            task.setDone(0);
            Dao.save(newSubtask);
        }
        Stage stage = (Stage) applyBtn.getScene().getWindow();
        stage.close();
    }
}
