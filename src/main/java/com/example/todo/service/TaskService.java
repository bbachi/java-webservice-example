package com.example.todo.service;

import com.example.todo.dto.TaskDTO;
import com.example.todo.entity.Task;
import com.example.todo.repository.TasksRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class TaskService {

    private TasksRepository tasksRepository;

    public TaskService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public Page<Task> getTasks(Pageable pageable) {
        return tasksRepository.findAll(pageable);
    }

    public Task getTask(long taskId) {
        Optional<Task> task = tasksRepository.findById(taskId);
        return task.get();
    }

    public Task saveTask(TaskDTO taskDTO) {
        ModelMapper modelMapper = new ModelMapper();
        Task task = modelMapper.map(taskDTO, Task.class);
        return tasksRepository.save(task);
    }
}
