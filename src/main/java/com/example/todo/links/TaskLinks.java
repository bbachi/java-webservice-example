package com.example.todo.links;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

@Component
public class TaskLinks {

    private final EntityLinks entityLinks;

    public static final String TASKS = "/tasks";
    public static final String TASK = "/task/{id}";
    public static final String CREATE_TASK = "/task";

    public TaskLinks(EntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    /*public Link getCancelLink(Event event) {
        return entityLinks.linkForSingleResource(event).slash(CANCEL_EVENT).withRel(CANCEL_REL);
    }*/
}
