package com.example.springbasics.controller;

import com.example.springbasics.entities.Task;
import com.example.springbasics.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController{
    private TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService=taskService;
    }

    //to get all the tasks that are currently available
    @GetMapping("/tasks")
    public List<Task> getAllTasks(){
        return taskService.getTasks();
    }

    //to create a new task
    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task.getTitle(),task.getDesc(),task.getDate().toString());
    }

    //to get a task details by taskId
    @GetMapping("/tasks/{id}")
    public Task getTaskById(@PathVariable Integer id){
        return taskService.getTask(id);
    }

    //update a task by id
    @PatchMapping("/tasks/{id}")
    public Task updateTaskById(@RequestBody Task task,@PathVariable Integer id){
        return taskService.updateTask(id,task.getTitle(),task.getDesc(),task.getDate().toString());
    }

    //delete a task
    @DeleteMapping("/tasks/{id}")
    public Task deleteTaskById(@PathVariable Integer id){
        return taskService.deleteTask(id);
    }
}
