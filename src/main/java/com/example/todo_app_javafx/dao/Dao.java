package com.example.todo_app_javafx.dao;

import jakarta.persistence.EntityTransaction;

import java.util.function.Consumer;

import static com.example.todo_app_javafx.Main.entityManager;

public class Dao {
    protected Dao() {
    }

    public static <T> void inTransaction(Consumer<T> consumer, T entity) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        consumer.accept(entity);
        entityTransaction.commit();
    }

    public static void save(Object entity) {
        inTransaction(entityManager::persist, entity);
    }


    public static void update(Object entity) {
        inTransaction(entityManager::merge, entity);
    }

    public static void delete(Object entity) {
        if (entityManager.contains(entity)) {
            inTransaction(entityManager::remove, entity);

        }

    }



}
