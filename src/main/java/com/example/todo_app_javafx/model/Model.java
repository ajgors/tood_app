package com.example.todo_app_javafx.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Model {
    private User user;
    private static Model instance;
    private ObservableList<Task> tasks = FXCollections.observableArrayList();
    private ObservableList<List<Subtask>> subtasks = FXCollections.observableArrayList();


    public static synchronized Model getInstance(){
        if(instance == null){
            instance = new Model();
        }
        return instance;
    }
    private Model(){}
}
