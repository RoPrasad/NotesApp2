package com.wolken.notesapp.Models;

public class Task {

    private String name;
    private String desc;
    private  String date;
    private String task_id;

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Task(String name, String desc, String date, String task_id) {
        this.name = name;
        this.desc = desc;
        this.date = date;
        this.task_id = task_id;
    }
}

