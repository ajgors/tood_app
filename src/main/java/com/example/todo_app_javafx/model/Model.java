package com.example.todo_app_javafx.model;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class Model {
    private User user;
    private static Model instance;

    public static synchronized Model getInstance(){
        if(instance == null){
            instance = new Model();
        }
        return instance;
    }
    private Model(){}
}
