package com.example.todo_app_javafx.dao;

import com.example.todo_app_javafx.model.User;

import static com.example.todo_app_javafx.Main.entityManager;

public class UserDao extends Dao {
    public static User loadByLogin(String login){
        User user = null;
        try{
            user = entityManager.createQuery("FROM User WHERE login=:login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        }catch (Exception ignored){}
        return user;
    }

    public static User getUserByLoginAndPassword(String login, String password){
        User user = null;
        try{
            user = entityManager.createQuery("FROM User WHERE login= :login AND password= :password", User.class)
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
        }catch (Exception ignored){}
        return user;
    }

}
