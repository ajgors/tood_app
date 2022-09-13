package com.example.todo_app_javafx.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

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
    private String description;
    private int done;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;
    private String priority;

    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;

    public Subtask(String title,String description, String priority, Task task) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.task = task;
    }
    public Subtask(){}
}