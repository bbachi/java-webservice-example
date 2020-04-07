package com.example.todo.repository;

import com.example.todo.entity.Task;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Collection;
import java.util.List;
import java.util.Optional;


@RepositoryRestResource(path = "task", collectionResourceRel = "task")
public interface TasksRepository extends JpaRepository<Task, Integer>, JpaSpecificationExecutor<Task>, QuerydslPredicateExecutor<Task> {

    Page<Task> findByIdIn(@Param(value = "id") List<Integer> eventid, Pageable pageable);

    Page<Task> findByNameIn(
            @Param("name") Collection<String> names, Pageable pageable);

    @Query(name="Task.findByName",
            nativeQuery = true)
    List<Task> findByName(@Param("name") String name);

    Page<Task> findAll(Pageable pageable);

    @Query(name="Task.findById", nativeQuery = true)
    @RestResource(exported = false)
    Optional<Task> findById(@Param("id") long id);

}

