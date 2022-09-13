package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.model.Subtask;
import com.example.todo_app_javafx.model.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditTaskController implements Initializable {
        public TextField titleFld;
        public TextArea description;
        public Button applyBtn;
        private Task task;
        private TaskCellController taskCellController;
    public ToggleGroup priority;


    public EditTaskController(Task task, TaskCellController taskCellController){
            this.task = task;
            this.taskCellController = taskCellController;
        }
        @Override
        public void initialize(URL location, ResourceBundle resources) {
            titleFld.setText(task.getTitle());
            description.setText(task.getDescription());


            priority.getToggles().forEach(toggle -> {
                if(((ToggleButton)toggle).getText().equals(task.getPriority())){
                    toggle.setSelected(true);
                }
            });
            applyBtn.setOnAction(e-> update());

        }
        private void update() {
            String selectedPriority = ((ToggleButton)priority.getSelectedToggle()).getText();

            taskCellController.setTitle(titleFld.getText());
            taskCellController.setDescription(description.getText());
            task.setPriority(selectedPriority);
            task.setTitle(titleFld.getText());
            task.setDescription(description.getText());
            Dao.update(task);
            Stage stage = (Stage) applyBtn.getScene().getWindow();
            stage.close();
            taskCellController.getTreeView().refresh();
        }

}
