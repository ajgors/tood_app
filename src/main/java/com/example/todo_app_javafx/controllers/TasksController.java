package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.Main;
import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.model.*;
import com.example.todo_app_javafx.view.TaskCellFactory;
import com.example.todo_app_javafx.view.TreeCellFactory;
import com.example.todo_app_javafx.view.ViewFactory;
import javafx.collections.ListChangeListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class TasksController implements Initializable {
    public TreeView<Object> treeView;
    @FXML
    private ListView<Task> tasksListView;
    @FXML
    private TextField newTaskTitleFld;
    @FXML
    private Button addTaskBtn;
    @FXML
    private Button logOutBtn;
    @FXML
    private Button deleteAccountBtn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deleteAccountBtn.setOnAction(e -> ViewFactory.openDeleteAccountStage());
        logOutBtn.setOnAction(e -> logOut());
//        tasksListView.setCellFactory(e -> new TaskCellFactory());
//        tasksListView.getItems().addAll(Model.getInstance().getTasks());
//        Model.getInstance().getTasks().addListener((ListChangeListener<Task>) change -> {
//            tasksListView.getItems().clear();
//            tasksListView.getItems().addAll(Model.getInstance().getTasks());
//        });
//        addTaskBtn.setOnAction(e -> createNewTask());


        TreeItem<Object> tasks = new TreeItem<>(null);
        tasks.setExpanded(true);
        for(Task task: Model.getInstance().getTasks()){
            TreeItem<Object> taskItem = new TreeItem<>(task);

            for(Subtask subtask : task.getSubtask()){
                taskItem.getChildren().add(subtask);
            }
//            taskItem.getChildren().setAll((TreeItem<Object>) task.getSubtask());
            tasks.getChildren().add(taskItem);
            taskItem.setExpanded(true);
        }
        treeView.setRoot(tasks);
        treeView.setShowRoot(false);
//        treeView.setCellFactory(e -> new TreeCellFactory());

    }

    private void logOut() {
        tasksListView.getItems().clear();
        Model.getInstance().getTasks().clear();
        Model.getInstance().setUser(null);
        Stage stage = (Stage) deleteAccountBtn.getScene().getWindow();
        stage.close();
        ViewFactory.openLoginStage();
    }

    private void createNewTask() {
        if (newTaskTitleFld.getText().isEmpty()) {
            return;
        } else {
            Task newTask = new Task(newTaskTitleFld.getText(), Model.getInstance().getUser());
            Dao.save(newTask);
            Model.getInstance().getUser().getTasks().add(newTask);
            tasksListView.getItems().add(newTask);
        }
        newTaskTitleFld.clear();
    }

}
//private final class TextFieldTreeCellImpl extends TreeCell<String> {
//
//    private TextField textField;
//    private ContextMenu addMenu = new ContextMenu();
//
//    public TextFieldTreeCellImpl() {
//        MenuItem addMenuItem = new MenuItem("Add Employee");
//        addMenu.getItems().add(addMenuItem);
//        addMenuItem.setOnAction(new EventHandler() {
//            public void handle(Event t) {
//                TreeItem newEmployee =
//                        new TreeItem<String>("New Employee");
//                getTreeItem().getChildren().add(newEmployee);
//            }
//        });
//    }