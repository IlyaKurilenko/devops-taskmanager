package com.devops.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TaskControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldReturnTasksList() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/tasks", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).contains("Изучить Docker");
    }

    @Test
    void shouldAddNewTask() {
        Task newTask = new Task(4, "Тестовая задача", false);
        ResponseEntity<Task> response = restTemplate.postForEntity("/api/tasks", newTask, Task.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}