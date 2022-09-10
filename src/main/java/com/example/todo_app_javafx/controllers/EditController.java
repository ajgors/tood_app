package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.model.Subtask;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditController implements Initializable {
    public TextField titleFld;
    public TextArea description;
    public Button applyBtn;
    private Subtask subtask;
    private SubTaskCellController subTaskCellController;

    public EditController(Subtask subtask, SubTaskCellController subTaskCellController){
        this.subTaskCellController = subTaskCellController;
        this.subtask = subtask;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleFld.setText(subTaskCellController.getTaskTitleLbl().getText());
        description.setText(subTaskCellController.getDescription());
        applyBtn.setOnAction(e-> update());

    }
    private void update() {
        subTaskCellController.getTaskTitleLbl().setText(titleFld.getText());
        subTaskCellController.setDescription(description.getText());
        subtask.setTitle(titleFld.getText());
        subtask.setDescription(description.getText());
        Dao.update(subtask);
        Stage stage = (Stage) applyBtn.getScene().getWindow();
        stage.close();
    }
}
