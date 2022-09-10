package com.example.todo_app_javafx.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "subtask")
public class Subtask  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public Subtask(String title, Task task) {
        this.title = title;
        this.task = task;
    }
    public Subtask(){}
}