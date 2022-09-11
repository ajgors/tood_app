package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.model.Subtask;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditSubtaskController implements Initializable {
    public TextField titleFld;
    public TextArea description;
    public Button applyBtn;
    private Subtask subtask;
    private SubTaskCellController subTaskCellController;
    public ToggleGroup priority;


    public EditSubtaskController(Subtask subtask, SubTaskCellController subTaskCellController){
        this.subTaskCellController = subTaskCellController;
        this.subtask = subtask;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        priority.getToggles().forEach(toggle -> {
            if(((ToggleButton)toggle).getText().equals(subtask.getPriority())){
                toggle.setSelected(true);
            }
        });
        titleFld.setText(subTaskCellController.getTaskTitleLbl().getText());
        description.setText(subtask.getDescription());
        applyBtn.setOnAction(e-> update());

    }
    private void update() {
        String selectedPriority = ((ToggleButton)priority.getSelectedToggle()).getText();

        subTaskCellController.getTaskTitleLbl().setText(titleFld.getText());
        subTaskCellController.setDescription(description.getText());
        subtask.setTitle(titleFld.getText());
        subtask.setPriority(selectedPriority);
        subtask.setDescription(description.getText());
        Dao.update(subtask);
        Stage stage = (Stage) applyBtn.getScene().getWindow();
        stage.close();
    }
}
