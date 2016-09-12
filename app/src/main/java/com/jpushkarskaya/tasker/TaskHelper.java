package com.jpushkarskaya.tasker;

import android.content.Context;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by epushkarskaya on 9/10/16.
 */
public class TaskHelper {

    private static final String file_name = "tasks_db.txt";

    public static void writeTasks(Context context, File filesDir, List<Task> tasks) {
        File tasksFile = new File(filesDir, file_name);

        try {
            ArrayList<String> taskStrings = new ArrayList<>();
            for (Task task : tasks){
                taskStrings.add(task.toString());
            }
            FileUtils.writeLines(tasksFile, taskStrings);
        } catch (Exception e) {
            Toast.makeText(context, "Could not export data!", Toast.LENGTH_SHORT).show();
        }

    }

    public static ArrayList<Task> readTasks(Context context, File filesDir) {
        ArrayList<Task> tasks = new ArrayList<>();
        File tasksFile = new File(filesDir, file_name);

        try {
            ArrayList<String> taskStrings = new ArrayList<>(FileUtils.readLines(tasksFile));
            for (String taskString : taskStrings) {
                if (taskString != null)
                    tasks.add(new Task(taskString));
            }
        } catch (Exception ex){
            Toast.makeText(context, "Could not read!", Toast.LENGTH_SHORT).show();
        }

        return tasks;
    }

}
