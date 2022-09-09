package com.example.todo_app_javafx.controllers;

import com.example.todo_app_javafx.model.Database;
import com.example.todo_app_javafx.view.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private TextField emailFld;
    @FXML
    private TextField nameFld;
    @FXML
    private TextField surnameFld;
    @FXML
    private TextField loginFld;
    @FXML
    private PasswordField passwordFld;
    @FXML
    private Button registerBtn;
    @FXML
    private AnchorPane root;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        registerBtn.setOnAction(e -> userCreation());
    }

    private void userCreation(){
        if (Database.checkIfUserExists(loginFld.getText())) {
            loginExistsAlert();
        } else {
            Database.createUser(nameFld.getText(), loginFld.getText(),
                    surnameFld.getText(), passwordFld.getText(), emailFld.getText());
            root.getChildren().setAll(ViewFactory.getLoginView());
        }
    }

    private void loginExistsAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("This login exists");
        alert.show();
    }
}
