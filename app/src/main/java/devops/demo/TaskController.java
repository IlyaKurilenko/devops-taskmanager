package com.devops.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
    private final List<Task> tasks = new ArrayList<>();

    public TaskController() {
        tasks.add(new Task(1, "Изучить Docker", false));
        tasks.add(new Task(2, "Написать Jenkinsfile", false));
        tasks.add(new Task(3, "Настроить мониторинг", false));
    }

    @GetMapping("/tasks")
    public List<Task> getTasks() {
        logger.info("Запрошен список задач. Количество: {}", tasks.size());
        return tasks;
    }

    @PostMapping("/tasks")
    public Task addTask(@RequestBody Task newTask) {
        logger.info("Добавлена новая задача: {}", newTask.getTitle());
        tasks.add(newTask);
        return newTask;
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskController.class, args);
    }
}

class Task {
    private int id;
    private String title;
    private boolean completed;

    public Task() {}
    
    public Task(int id, String title, boolean completed) {
        this.id = id;
        this.title = title;
        this.completed = completed;
    }
    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}