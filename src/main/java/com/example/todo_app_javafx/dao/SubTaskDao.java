package com.example.todo_app_javafx.dao;

import jakarta.persistence.EntityTransaction;

import static com.example.todo_app_javafx.Main.entityManager;

public class SubTaskDao {

    public static void deleteById(Long id) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.createQuery("delete from Subtask where id = :id").setParameter("id", id).executeUpdate();
        entityTransaction.commit();

    }
}
