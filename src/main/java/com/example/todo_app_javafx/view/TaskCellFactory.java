package com.example.todo_app_javafx.view;

import com.example.todo_app_javafx.Main;
import com.example.todo_app_javafx.controllers.TaskCellController;
import com.example.todo_app_javafx.model.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;


public class TaskCellFactory extends ListCell<Task> {
    @Override
    protected void updateItem(Task task, boolean empty) {
        super.updateItem(task, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("TaskCell.fxml"));
            TaskCellController controller = new TaskCellController(task);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e) {
                e.printStackTrace();
            }
//            Button button = new Button("X");
//            Text text = new Text(task.getTitle());
//            Text id = new Text(String.valueOf(task.getId()));
//            id.setVisible(false);
//            HBox hbox = new HBox(0,id,text);
//            AnchorPane root = new AnchorPane(hbox,button);
//            AnchorPane.setLeftAnchor(hbox, 0.0);
//
//            AnchorPane.setRightAnchor(button, 10.0);
//            button.setOnAction(e -> {
//                Database.removeTaskFromDB(task.getId());
//                TasksController.tasks.removeIf(i-> i.getId() == task.getId());
//            });

        }
    }


}
