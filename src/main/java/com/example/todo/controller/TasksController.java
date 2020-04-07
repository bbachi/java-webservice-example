package com.example.todo.controller;

import com.example.todo.dto.TaskDTO;
import com.example.todo.entity.Task;
import com.example.todo.links.TaskLinks;
import com.example.todo.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Slf4j
@RepositoryRestController
@RequestMapping("/todo/")
@RequiredArgsConstructor
public class TasksController {

    private final PagedResourcesAssembler pagedResourcesAssembler;
    private final TaskService taskService;

    @GetMapping(path = TaskLinks.TASKS)
    public ResponseEntity<?> getTasks(TaskDTO taskDTO, Pageable pageable, PersistentEntityResourceAssembler resourceAssembler) {
        log.info("TasksController: " + taskDTO);
        Page<Task> events = taskService.getTasks(pageable);
        PagedResources<?> resource = pagedResourcesAssembler.toResource(events, resourceAssembler);
        return ResponseEntity.ok(resource);
    }

    @GetMapping(path = TaskLinks.TASK)
    public ResponseEntity<?> getTask(@PathVariable("id") int taskId, Pageable pageable, PersistentEntityResourceAssembler resourceAssembler) {
        try {
            log.info("TasksController::: " + taskId);
            Task task = taskService.getTask(taskId);
            Link selfLink = linkTo(methodOn(TasksController.class).getTask(taskId, pageable, resourceAssembler)).withSelfRel();
            Link allTasksLink = linkTo(TasksController.class).slash("/tasks").withRel("all tasks");

            Resource<Task> taskResource = new Resource<>(task);
            taskResource.add(selfLink, allTasksLink);
            return ResponseEntity.ok(taskResource);
        }catch (RuntimeException exc) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Resource Not Found", exc);
        }
    }

    @PostMapping(path = TaskLinks.CREATE_TASK)
    public ResponseEntity<?> createTask(@RequestBody TaskDTO taskDTO, Pageable pageable, PersistentEntityResourceAssembler resourceAssembler) {
        log.info("TasksController: " + taskDTO);
        Task events = taskService.saveTask(taskDTO);

        Link selfLink = linkTo(methodOn(TasksController.class).createTask(taskDTO, pageable, resourceAssembler)).withSelfRel();
        Link allTasksLink = linkTo(TasksController.class).slash("/tasks").withRel("all tasks");

        Resource<Task> taskResource = new Resource<>(events);
        taskResource.add(selfLink,  allTasksLink);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("MyResponseHeader", "MyValue");
        return new ResponseEntity<Resource<Task>>(taskResource, responseHeaders, HttpStatus.CREATED);
    }

}
