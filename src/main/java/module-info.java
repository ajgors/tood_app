module com.example.todo_app_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;
    requires jakarta.persistence;


    opens com.example.todo_app_javafx to javafx.fxml;
    exports com.example.todo_app_javafx;
    exports com.example.todo_app_javafx.controllers;
    opens com.example.todo_app_javafx.controllers to javafx.fxml;
//    exports com.example.todo_app_javafx.Model;
//    opens com.example.todo_app_javafx.Model to javafx.fxml;
    exports com.example.todo_app_javafx.view;
    opens com.example.todo_app_javafx.view to javafx.fxml;
    exports com.example.todo_app_javafx.model;
    opens com.example.todo_app_javafx.model to javafx.fxml;
}