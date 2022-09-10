package com.example.todo_app_javafx.dao;

import com.example.todo_app_javafx.model.Task;
import jakarta.persistence.EntityTransaction;
import java.util.List;

import static com.example.todo_app_javafx.Main.entityManager;

public class TaskDao extends Dao {


    public static void deleteById(Long id) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.createQuery("delete from Task where id = :id")
                .setParameter("id", id)
                .executeUpdate();

        entityTransaction.commit();

    }

    public static List<Task> loadAllUserTasks(Long userId) {

        return entityManager
                .createQuery("from Task WHERE user = :userId", Task.class)
                .setParameter("userId", userId)
                .getResultList();
    }
    public static List<Task> loadAllSubTasks(Long taksId) {

        return entityManager
                .createQuery("from Subtask WHERE  = :taskId", Task.class)
                .setParameter("taskId", taksId)
                .getResultList();
    }
}
