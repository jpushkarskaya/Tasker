package com.jpushkarskaya.tasker;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by epushkarskaya on 9/10/16.
 */
public class TaskHelper {

    private static final String file_name = "tasks_db.txt";

    public static void writeTasks(File filesDir, List<Task> tasks) {
        File tasksFile = new File(filesDir, file_name);

        try {
            ArrayList<String> taskStrings = new ArrayList<>();
            for (Task task : tasks){
                taskStrings.add(task.toString());
            }
            FileUtils.writeLines(tasksFile, taskStrings);
        } catch (Exception e) {
            // is ok
        }

    }

    public static ArrayList<Task> readTasks(File filesDir) {
        ArrayList<Task> tasks = new ArrayList<>();
        File tasksFile = new File(filesDir, file_name);

        try {
            ArrayList<String> taskStrings = new ArrayList<>(FileUtils.readLines(tasksFile));
            for (String taskString : taskStrings) {
                if (taskString != null)
                    tasks.add(new Task(taskString));
            }
        } catch (Exception ex){
            // is ok
        }

        return tasks;
    }

}
