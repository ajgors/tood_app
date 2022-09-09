package com.example.todo_app_javafx;

import com.example.todo_app_javafx.dao.Dao;
import com.example.todo_app_javafx.model.Task;
import com.example.todo_app_javafx.model.User;

public class DatabaseMain {
    public static void main(String[] args) {
        User user = new User("igor", "stadnicki", "ajgor", "123", "is@gmail.com");
        Task task1 = new Task("zrobic zakupy", user);
        Task task2 = new Task("zrobic pranie", user);
        Task task3 = new Task("dodac ficzery",user);
        Dao.save(user);

        Dao.saveAll(task1, task2, task3);
        user.getTasks().add(task1);
        user.getTasks().add(task2);
        user.getTasks().add(task3);
        Dao.save(user);
//        System.out.println(Dao.loadByLogin("ajgor"));

//        Dao.saveAll(task1,task2,task3);
//        Dao.delete(user);
    }

}
