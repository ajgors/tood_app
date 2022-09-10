package com.example.todo_app_javafx.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@ToString

public class Task {
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

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany
    @JoinColumn(name = "task_id")
    private List<Subtask> subtask =
            new ArrayList<>();


    public Task(String title, User user) {
        this.title = title;
        this.user = user;
    }

    public Task() {

    }

    public Task(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", done=" + done +
                ", createDate=" + createDate +
                ", priority='" + priority + '\'' +
                ", user=" + user +
                '}';
    }
}
