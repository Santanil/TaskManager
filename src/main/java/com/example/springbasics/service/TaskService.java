package com.example.springbasics.service;

import com.example.springbasics.entities.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TaskService {
    private final List<Task> taskList;
    private AtomicInteger taskId=new AtomicInteger(1);

    public static class TaskNotFoundException extends IllegalThreadStateException{
        public TaskNotFoundException(Integer id){
            super("Task with "+ id +" Not Found");
        }
    }

    public TaskService() {
        taskList = new ArrayList<>();
        taskList.add(new Task(taskId.getAndIncrement(), "Task1", "Desc 1", "11-03-2023"));
        taskList.add(new Task(taskId.getAndIncrement(), "Task2", "Desc 2", "12-03-2023"));
    }

    public List<Task> getTasks(){
        return taskList;
    }

    public Task createTask(String title,String desc,String dueDate){
        //TODO: Ensure date is not before today
        var newTask=new Task(taskId.incrementAndGet(), title,desc,dueDate);
        taskList.add(newTask);
        return newTask;
    }

    public Task getTask(Integer id){
        for(int i=0;i<taskList.size();i++){
            if(taskList.get(i).getTaskId().equals(id)){
                return taskList.get(i);
            }
        }
        return null;
    }

    public Task updateTask(Integer id,String title,String desc,String date){
        Task curTask=null;
        for(int i=0;i<taskList.size();i++){
            curTask=taskList.get(i);
            if(curTask.getTaskId().equals(id)) {
                if (title != null) curTask.setTitle(title);
                if (desc != null) curTask.setDesc(desc);
                if (date != null) curTask.setDate(date);
                break;
            }
        }
        if(curTask==null)
            throw new TaskNotFoundException(id);
        return curTask;
    }

    public Task deleteTask(Integer id){
        var task=getTask(id);
        taskList.remove(task);
        return task;
    }
}
