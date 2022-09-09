package com.example.todo_app_javafx.model;

public class LoggedUser {
    private int id;
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
//    private static LoggedUser loggedUserInstance;
//
//    public synchronized static LoggedUser getInstance(){
//        if(loggedUserInstance == null){
//            loggedUserInstance = new LoggedUser();
//        }
//        return loggedUserInstance;
//    }


//
    public LoggedUser(int id, String name,String surname, String login, String password, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
    }


    private LoggedUser(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
    public String getLogin(){
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
