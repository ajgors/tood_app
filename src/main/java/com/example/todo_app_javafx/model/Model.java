package com.example.todo_app_javafx.model;

public class Model {

    private Long LogedUserId;
    private static Model instance;

    public synchronized static Model getInstance(){
        if(instance == null){
            instance = new Model();
        }
        return instance;
    }
    private Model(){}

    public Long getLogedUserId() {
        return LogedUserId;
    }

    public void setLogedUserId(Long logedUserId) {
        LogedUserId = logedUserId;
    }
}
