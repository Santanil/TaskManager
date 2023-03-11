package com.example.springbasics.entities;

import java.util.Date;

public class Task {
    Integer taskId;
    String title;
    String desc;
    String date;

    public Task(Integer taskId, String title, String desc, String date) {
        this.taskId=taskId;
        this.title=title;
        this.desc=desc;
        this.date=date;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
