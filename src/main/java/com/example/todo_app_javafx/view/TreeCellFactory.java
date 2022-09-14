package com.example.todo_app_javafx.view;

import com.example.todo_app_javafx.Main;
import com.example.todo_app_javafx.controllers.cell.SubtaskCellController;
import com.example.todo_app_javafx.controllers.cell.TaskCellController;
import com.example.todo_app_javafx.model.Subtask;
import com.example.todo_app_javafx.model.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeCell;

public class TreeCellFactory extends TreeCell<Object> {
    @Override
    protected void updateItem(Object item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null) {
            setText(null);
            setGraphic(null);
            setContextMenu(null);
        } else {

            if (item instanceof Task task) {
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("TaskCell.fxml"));
                TaskCellController controller = new TaskCellController(task , getTreeView());
                loader.setController(controller);
                try {
                    setGraphic(loader.load());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else if(item instanceof Subtask subtask){
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("SubTaskCell.fxml"));
                SubtaskCellController controller = new SubtaskCellController(subtask, getTreeView());
                loader.setController(controller);
                try {
                    setGraphic(loader.load());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            setCursor(null);
            setCache(false);
            setEditable(false);
            setEffect(null);
//            setMouseTransparent(true);
            setOnMousePressed(null);
            setFocusTraversable(false);
            setText(null);

        }
    }
}
