package com.example.todo.dto;

import lombok.Data;

@Data
public class TaskDTO {

    private long id;
    private String name;
    private String description;
    private String createdDate;
}
