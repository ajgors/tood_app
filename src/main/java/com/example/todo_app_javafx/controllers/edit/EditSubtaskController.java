package com.example.todo_app_javafx.controllers.edit;

import com.example.todo_app_javafx.controllers.cell.SubtaskCellController;
import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.model.Subtask;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditSubtaskController implements Initializable {
    @FXML
    public TextField titleFld;
    @FXML
    public TextArea description;
    @FXML
    public Button applyBtn;
    @FXML
    public ToggleGroup priority;
    private final Subtask subtask;
    private final SubtaskCellController subTaskCellController;


    public EditSubtaskController(Subtask subtask, SubtaskCellController subTaskCellController){
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
        titleFld.setText(subtask.getTitle());
        description.setText(subtask.getDescription());
        applyBtn.setOnAction(e-> update());

    }
    private void update() {
        String selectedPriority = ((ToggleButton)priority.getSelectedToggle()).getText();

        subTaskCellController.setTitle(titleFld.getText());
        subTaskCellController.setDescription(description.getText());
        subtask.setTitle(titleFld.getText());
        subtask.setPriority(selectedPriority);
        subtask.setDescription(description.getText());
        Dao.update(subtask);
        Stage stage = (Stage) applyBtn.getScene().getWindow();
        stage.close();
        subTaskCellController.getTreeView().refresh();

    }
}
