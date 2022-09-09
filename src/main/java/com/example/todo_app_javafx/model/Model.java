package com.example.todo_app_javafx.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {

    private User user;
    private static Model instance;
    private ObservableList<Task> tasks = FXCollections.observableArrayList();

    public synchronized static Model getInstance(){
        if(instance == null){
            instance = new Model();
        }
        return instance;
    }
    private Model(){}

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public ObservableList<Task> getTasks() {
        return tasks;
    }
}
