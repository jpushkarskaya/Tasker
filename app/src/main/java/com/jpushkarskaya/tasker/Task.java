package com.jpushkarskaya.tasker;

import java.util.Date;

/**
 * Created by epushkarskaya on 9/10/16.
 */
public class Task {

    private int id;
    private String title;
    private String details;
    private Date dueDate;
    private String category;
    private String status;

    public Task(String taskString){
        String[] components = taskString.split("-");
        int id = Integer.parseInt(components[0]);
        String title = components[1];
        String details = components[2];
        Date dueDate = new Date(Integer.parseInt(components[3]));
        String category = components[4];
        String status = components[5];
        this.id = id;
        this.title = title;
        this.details = details;
        this.dueDate = dueDate;
        this.category = category;
        this.status = status;
    }

    public Task(int id, String title, Date dueDate, String details, String category, String status) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.dueDate = dueDate;
        this.category = category;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(id);
        builder.append("-");
        builder.append(title);
        builder.append("-");
        builder.append(details);
        builder.append("-");
        builder.append(dueDate.getTime());
        builder.append("-");
        builder.append(category);
        builder.append("-");
        builder.append(status);
        return builder.toString();
    }

}
