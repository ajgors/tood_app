package com.example.todo_app_javafx.dao;

import com.example.todo_app_javafx.model.Task;
import com.example.todo_app_javafx.model.User;

import java.util.List;

import static com.example.todo_app_javafx.Main.entityManager;

public class TaskDao extends Dao{

//    public static List<Task> loadAllUserTasks(Long userId) {
//
//        return entityManager
//                .createQuery("from Task WHERE user = :userId", Task.class)
//                .setParameter("userId", userId)
//                .getResultList();
//    }
}
