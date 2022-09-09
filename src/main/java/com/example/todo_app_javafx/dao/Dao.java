package com.example.todo_app_javafx.dao;

import com.example.todo_app_javafx.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static com.example.todo_app_javafx.Main.entityManager;

public class Dao {

    public static <T> void inTransaction(Consumer<T> consumer, T entity) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        ///
        consumer.accept(entity);
        ///
        entityTransaction.commit();
    }

    public static void save(Object entity) {
        inTransaction(entityManager::persist, entity);
    }

    public static void saveAll(Object... entities) {
        for (Object entity :entities) {
            inTransaction(entityManager::persist, entity);
        }
    }

    public static void update(Object entity) {
        inTransaction(entityManager::merge, entity);
    }

    public static void delete(Object entity) {
        inTransaction(entityManager::remove, entity);
    }

//    PersonDao -> Person  loadById(1)
//    Dao -> Person.class  loadBy

    public static <T> T loadById(Class<T> clazz, Long id) {
        return entityManager.find(clazz, id);
    }

    public static <T> List<T> loadAll(Class<T> clazz) {

        return entityManager.
                createQuery("from " + clazz.getSimpleName(),
                        clazz).getResultList();
    }


}
