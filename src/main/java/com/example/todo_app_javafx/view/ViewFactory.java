package com.example.todo_app_javafx.view;

import com.example.todo_app_javafx.Main;
import com.example.todo_app_javafx.controllers.cell.SubtaskCellController;
import com.example.todo_app_javafx.controllers.cell.TaskCellController;
import com.example.todo_app_javafx.controllers.create.NewSubtaskController;
import com.example.todo_app_javafx.controllers.create.NewTaskController;
import com.example.todo_app_javafx.controllers.edit.EditSubtaskController;
import com.example.todo_app_javafx.controllers.edit.EditTaskController;
import com.example.todo_app_javafx.model.Subtask;
import com.example.todo_app_javafx.model.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {


    private static final String errorMessage = "error while opening new window";

    private ViewFactory() {
    }

    public static void getTasksWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Tasks.fxml"));
        createStage(fxmlLoader);
    }


    public static void openLoginStage() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        createStage(fxmlLoader);
    }

    public static void openDeleteAccountStage() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("DeleteAccount.fxml"));
        createStage(fxmlLoader);
    }

    private static void createStage(FXMLLoader fxmlLoader) {
        Stage stage = new Stage();
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stage.setScene(scene);
        stage.show();
    }

    public static AnchorPane getRegisterView() {
        try {
            return new FXMLLoader(Main.class.getResource("Register.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static AnchorPane getLoginView() {
        try {
            return new FXMLLoader(Main.class.getResource("Login.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void openSubtaskEditWindow(Subtask subtask, SubtaskCellController subTaskCellController) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Edit.fxml"));
        try {
            EditSubtaskController editController = new EditSubtaskController(subtask, subTaskCellController);
            fxmlLoader.setController(editController);
            setStageForSmallStages(fxmlLoader);
        } catch (IOException e) {
            throw new RuntimeException(errorMessage);
        }
    }


    public static void openTaskEditWindow(Task task, TaskCellController taskCellController) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Edit.fxml"));
        try {
            EditTaskController editController = new EditTaskController(task, taskCellController);
            fxmlLoader.setController(editController);
            setStageForSmallStages(fxmlLoader);
        } catch (IOException e) {
            throw new RuntimeException(errorMessage);
        }
    }

    private static void setStageForSmallStages(FXMLLoader fxmlLoader) throws IOException {
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.show();
    }

    public static void openNewTaskWindow(TreeView<Object> treeView) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NewTask.fxml"));
        try {
            NewTaskController newTaskController = new NewTaskController(treeView);
            fxmlLoader.setController(newTaskController);
            setStageForSmallStages(fxmlLoader);
        } catch (IOException e) {
            throw new RuntimeException(errorMessage);
        }
    }

    public static void openNewSubTaskWindow(TreeView<Object> treeView, Task task) {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NewTask.fxml"));
        try {
            NewSubtaskController controller = new NewSubtaskController(treeView, task);
            fxmlLoader.setController(controller);
            setStageForSmallStages(fxmlLoader);
        } catch (IOException e) {
            throw new RuntimeException(errorMessage);
        }
    }
}
