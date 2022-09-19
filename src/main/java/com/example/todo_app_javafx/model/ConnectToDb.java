package com.example.todo_app_javafx.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConnectToDb implements Runnable {
    public static EntityManager entityManager = null;

    @Override
    public void run() {
        entityManager = Persistence.createEntityManagerFactory("todo_app").createEntityManager();

    }
}
