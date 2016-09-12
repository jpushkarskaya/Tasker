package com.jpushkarskaya.tasker;

/**
 * Created by epushkarskaya on 9/10/16.
 */
public class Task {

    private String title;
    private int dueDay;
    private int dueMonth;
    private int dueYear;
    private int category;
    private int status;

    public Task(String taskString){
        String[] components = taskString.split("-");
        String title = components[0];
        int dueDay = Integer.parseInt(components[1]);
        int dueMonth = Integer.parseInt(components[2]);
        int dueYear = Integer.parseInt(components[3]);
        int category = Integer.parseInt(components[4]);
        int status = Integer.parseInt(components[5]);

        this.title = title;
        this.dueDay = dueDay;
        this.dueMonth = dueMonth;
        this.dueYear = dueYear;
        this.category = category;
        this.status = status;
    }

    public Task(String title, int dueDay, int dueMonth, int dueYear, int category, int status) {
        this.title = title;
        this.dueDay = dueDay;
        this.dueMonth = dueMonth;
        this.dueYear = dueYear;
        this.category = category;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public int getDueDay() {
        return dueDay;
    }

    public int getDueMonth() {
        return dueMonth;
    }

    public int getDueYear() {
        return dueYear;
    }

    public int getCategoryId() {
        return category;
    }

    public int getStatusId() {
        return status;
    }

    public String getCategory() {
        if (category == 0){
            return "HOME";
        } else if (category == 1){
            return "WORK";
        } else if (category == 2){
            return "FUN";
        } else if (category == 3){
            return "EXERCISE";
        } else {
            return "OTHER";
        }
    }

    public String getStatus() {
        if (status == 0){
            return "TODO";
        } else if (status == 1){
            return "DOING";
        } else {
            return "DONE";
        }
    }

    public String getDueDate() {
        StringBuilder builder = new StringBuilder();
        builder.append(dueMonth);
        builder.append("/");
        builder.append(dueDay);
        builder.append("/");
        builder.append(dueYear);
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(title);
        builder.append("-");
        builder.append(dueDay);
        builder.append("-");
        builder.append(dueMonth);
        builder.append("-");
        builder.append(dueYear);
        builder.append("-");
        builder.append(category);
        builder.append("-");
        builder.append(status);
        return builder.toString();
    }
}