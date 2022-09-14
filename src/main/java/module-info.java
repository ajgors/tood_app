module com.example.todo_app_javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;
    requires jakarta.persistence;
    requires lombok;
    requires org.hibernate.orm.core;
    requires de.jensd.fx.glyphs.fontawesome;



    opens com.example.todo_app_javafx to javafx.fxml;

    exports com.example.todo_app_javafx.controllers;
    opens com.example.todo_app_javafx.controllers to javafx.fxml;
    exports com.example.todo_app_javafx.dao;
    opens com.example.todo_app_javafx.model to org.hibernate.orm.core;
    opens com.example.todo_app_javafx.dao;
    exports com.example.todo_app_javafx.view;
    opens com.example.todo_app_javafx.view to javafx.fxml;
    exports com.example.todo_app_javafx.model;
    exports com.example.todo_app_javafx;
    exports com.example.todo_app_javafx.controllers.edit;
    opens com.example.todo_app_javafx.controllers.edit to javafx.fxml;
    exports com.example.todo_app_javafx.controllers.create;
    opens com.example.todo_app_javafx.controllers.create to javafx.fxml;
    exports com.example.todo_app_javafx.controllers.cell;
    opens com.example.todo_app_javafx.controllers.cell to javafx.fxml;
}