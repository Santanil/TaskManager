package com.example.springbasics.controller;

import com.example.springbasics.dtos.ErrorResponse;
import com.example.springbasics.entities.Task;
import com.example.springbasics.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class TaskController{
    private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService=taskService;
    }

    //to get all the tasks that are currently available
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok(taskService.getTasks());
    }

    //to create a new task
    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task){
        var newTask=taskService.createTask(task.getTitle(),task.getDesc(),task.getDate());
        return ResponseEntity.created(URI.create("/tasks/"+newTask.getTaskId())).body(newTask);
    }

    //to get a task details by taskId
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id){
        return ResponseEntity.ok(taskService.getTask(id));
    }

    //update a task by id
    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTaskById(@RequestBody Task task,@PathVariable Integer id){
        return ResponseEntity.accepted().body(taskService.updateTask(id,task.getTitle(),task.getDesc(),task.getDate()));
    }

    //delete a task
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Task> deleteTaskById(@PathVariable Integer id){
        return ResponseEntity.accepted().body(taskService.deleteTask(id));
    }

    //Whenever TaskNotFound exception will be thrown, following method will be executed
    //ErrorResponse present in ctos pack
    @ExceptionHandler(TaskService.TaskNotFoundException.class)
    ResponseEntity<ErrorResponse> handleErrors(TaskService.TaskNotFoundException e){
        return new ResponseEntity<>(new ErrorResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
