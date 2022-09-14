package com.example.todo_app_javafx.controllers.edit;

import com.example.todo_app_javafx.controllers.cell.SubtaskCellController;
import com.example.todo_app_javafx.controllers.cell.TaskCellController;
import com.example.todo_app_javafx.model.Subtask;
import com.example.todo_app_javafx.model.Task;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    public TextField titleFld;
    public TextArea description;
    public Button applyBtn;
    public ToggleGroup priority;



    //task i taskcell controller
    private Task task;
    private TaskCellController taskCellController;
    private Subtask subtask;
    private SubtaskCellController subTaskCellController;

    public EditController(Task task, TaskCellController taskCellController) {
        this.task = task;
        this.taskCellController = taskCellController;
    }
    public EditController(Subtask subtask, SubtaskCellController subTaskCellController){
        this.subTaskCellController = subTaskCellController;
        this.subtask = subtask;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
