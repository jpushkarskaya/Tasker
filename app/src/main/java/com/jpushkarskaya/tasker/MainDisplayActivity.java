package com.jpushkarskaya.tasker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainDisplayActivity extends AppCompatActivity {

    public static final String TASK_ID = "task_id";
    public static final String POSITION_ID = "position_id";

    private TaskAdapter tasksAdapter;
    private ListView lvTasks;

    private boolean debugging = true;
    private boolean createCalled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_display);

        lvTasks = (ListView) findViewById(R.id.lvTasks);
        attachAdapter();
        setUpClickListeners();

        createCalled = true;
    }

    private void attachAdapter() {
        List<Task> tasks;
        if (debugging){
            tasks = populateTestTasks();
        } else {
            tasks = TaskHelper.readTasks(this, getFilesDir());
        }
        tasksAdapter = new TaskAdapter(this, android.R.layout.simple_list_item_1, tasks);
        lvTasks.setAdapter(tasksAdapter);
    }

    private List<Task> populateTestTasks() {
        List<Task> testTasks = new ArrayList<>();
        testTasks.add(new Task("Walk dog", 10, 3, 2016, 1, 1));
        testTasks.add(new Task("Walk walk", 10, 4, 2016, 2, 1));
        testTasks.add(new Task("Walk self", 10, 5, 2017, 1, 0));
        return testTasks;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!createCalled) {
            tasksAdapter.swapItems(TaskHelper.readTasks(this, getFilesDir()));
        }
        createCalled = false;
    }

    public void onAdd(View view) {
        Intent intent = new Intent(this, AddEditActivity.class);
        startActivity(intent);
    }

    private void setUpClickListeners() {
        lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent viewIntent = new Intent(MainDisplayActivity.this, AddEditActivity.class);
                viewIntent.putExtra(TASK_ID, tasksAdapter.getTasks().get(i).toString());
                viewIntent.putExtra(POSITION_ID, i);
                startActivity(viewIntent);
            }
        });

        lvTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                List<Task> tasks = tasksAdapter.getTasks();
                tasks.remove(i);
                tasksAdapter.swapItems(tasks);
                TaskHelper.writeTasks(MainDisplayActivity.this, getFilesDir(), tasksAdapter.getTasks());
                return true;
            }
        });
    }
}