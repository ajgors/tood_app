package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.model.Database;
import com.example.todo_app_javafx.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.net.URL;
import java.util.ResourceBundle;

public class DeleteAccountController implements Initializable {

    @FXML
    private PasswordField passwordFld;
    @FXML
    private PasswordField repeatedPasswordFld;
    @FXML
    private Button deleteAccountBtn;
    @FXML
    private Label loginLbl;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loginLbl.setText("Account login: " + Database.loggedUser.getLogin());
        deleteAccountBtn.setOnAction(e -> removeAccount());
    }

    private void closeDeleteAccountWindow(){
        Stage stage = (Stage) passwordFld.getScene().getWindow();
        stage.close();
    }

    private void closeTasksWindow(){
        Stage taskStage = (Stage) Window.getWindows().get(0);
        taskStage.close();
        ViewFactory.openLoginStage();
    }

    private void removeAccount(){
        if (passwordFld.getText().equals(repeatedPasswordFld.getText())
                && Database.checkIfPassowrdIsCorrect(passwordFld.getText())) {

            Database.deleteCurrentLoggedUser();
            closeDeleteAccountWindow();
            closeTasksWindow();
        }
    }



}
