package com.example.todo.mocks;

import com.example.todo.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public class TaskMocks {

    public static Page<Task> createTasks() {

        List<Task> taskList = new ArrayList<>();
        Task task1 = new Task();
        task1.setId(1);
        task1.setName("name1");
        task1.setDescription("description1");

        Task task2 = new Task();
        task2.setId(2);
        task2.setName("name2");
        task2.setDescription("description2");

        taskList.add(task1);
        taskList.add(task2);
        Page<Task> pagedResponse = new PageImpl(taskList);
        return pagedResponse;
    }

}
