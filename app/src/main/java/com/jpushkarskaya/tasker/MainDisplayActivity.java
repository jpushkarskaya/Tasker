package com.jpushkarskaya.tasker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainDisplayActivity extends AppCompatActivity {

    private final String TAG = "MainDisplayActivity";
    private final String file_name = "tasks.txt";

    private ArrayList<String> tasks;
    private ArrayAdapter<String> tasksAdapter;
    private ListView lvTasks;
    private EditText etNewTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_display);

        etNewTask = (EditText) findViewById(R.id.etNewTask);
        lvTasks = (ListView) findViewById(R.id.lvTasks);

        readItems();
        attachAdapter();
        setUpListViewListener();
    }

    private void attachAdapter(){
        tasksAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_checked, tasks);
        lvTasks.setAdapter(tasksAdapter);
    }

    private void readItems(){
        File filesDir = getFilesDir();
        File tasksFile = new File(filesDir, file_name);
        try {
            tasks = new ArrayList<>(FileUtils.readLines(tasksFile));
        } catch (IOException ex){
            tasks = new ArrayList<>();
        }
    }

    private void setUpListViewListener() {
        lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CheckedTextView textView = (CheckedTextView) view;
                textView.setChecked(!textView.isChecked());
            }
        });

        lvTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                tasks.remove(i);
                tasksAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });
    }

    private void writeItems(){
        File filesDir = getFilesDir();
        File tasksFile = new File(filesDir, file_name);
        try {
            FileUtils.writeLines(tasksFile, tasks);
        } catch (IOException e) {
            String errorMsg = "Couldn't save tasks :[";
            Log.d(TAG, errorMsg);
            Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    }

    public void onAddTask(View view) {
        tasks.add(etNewTask.getText().toString());
        etNewTask.setText("");
        tasksAdapter.notifyDataSetChanged();
        writeItems();
    }


}
