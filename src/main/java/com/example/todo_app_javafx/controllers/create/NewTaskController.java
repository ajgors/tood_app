package com.example.todo_app_javafx.controllers.create;

import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.model.Model;
import com.example.todo_app_javafx.model.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class NewTaskController implements Initializable {


    @FXML
    private TextField titleFld;
    @FXML
    private TextArea description;
    @FXML
    private ToggleGroup priority;
    @FXML
    private Button applyBtn;
    private final TreeView<Object> treeView;

    public NewTaskController(TreeView<Object> treeView) {
        this.treeView = treeView;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        applyBtn.setOnAction(e -> createNewTask());
    }

    private void createNewTask() {
        if (titleFld.getText().length() > 60) {
            System.out.println("title to long");
            return;
        }
        if (titleFld.getText().isEmpty()) {
            return;
        } else {
            String selectedPriority = ((ToggleButton) priority.getSelectedToggle()).getText();
            Task newTask = new Task(titleFld.getText(), description.getText(), selectedPriority, Model.getInstance().getUser());
            Dao.save(newTask);
            Model.getInstance().getUser().getTasks().add(newTask);
            treeView.getRoot().getChildren().add(new TreeItem<>(newTask));
        }
        Stage stage = (Stage) applyBtn.getScene().getWindow();
        stage.close();
    }
}
