package com.jpushkarskaya.tasker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;


public class MainDisplayActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{

    public static final String TASK_ID = "task_id";
    public static final String POSITION_ID = "position_id";

    private TaskAdapter tasksAdapter;
    private ListView lvTasks;

    private boolean onCreateCalled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_display);

        lvTasks = (ListView) findViewById(R.id.lvTasks);
        List<Task> tasks = TaskHelper.readTasks(this, getFilesDir());
        tasksAdapter = new TaskAdapter(this, android.R.layout.simple_list_item_1, tasks);

        lvTasks.setAdapter(tasksAdapter);
        lvTasks.setOnItemClickListener(this);
        lvTasks.setOnItemLongClickListener(this);

        onCreateCalled = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!onCreateCalled) {
            tasksAdapter.swapItems(TaskHelper.readTasks(this, getFilesDir()));
        }
        onCreateCalled = false;
    }

    public void onAdd(View view) {
        Intent intent = new Intent(this, AddEditActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent viewIntent = new Intent(this, AddEditActivity.class);
        viewIntent.putExtra(TASK_ID, tasksAdapter.getTasks().get(i).toString());
        viewIntent.putExtra(POSITION_ID, i);
        startActivity(viewIntent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        List<Task> tasks = tasksAdapter.getTasks();
        tasks.remove(i);
        tasksAdapter.swapItems(tasks);
        TaskHelper.writeTasks(this, getFilesDir(), tasksAdapter.getTasks());
        return true;
    }
}