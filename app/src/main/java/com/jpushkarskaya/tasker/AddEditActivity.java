package com.jpushkarskaya.tasker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class AddEditActivity extends AppCompatActivity {

    EditText etTitle;
    DatePicker datePicker;
    Spinner spinnerCategory;
    Spinner spinnerStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        initViews();
        String taskString = getIntent().getStringExtra(MainDisplayActivity.TASK_ID);
        if (taskString != null){
            populateViews(taskString);
        }
    }

    private void initViews() {
        etTitle = (EditText) findViewById(R.id.etTitle);
        datePicker = (DatePicker) findViewById(R.id.date_picker);
        spinnerCategory = (Spinner) findViewById(R.id.spinnerCategory);
        spinnerStatus = (Spinner) findViewById(R.id.spinnerStatus);
    }

    private void populateViews(String taskString) {
        Task task = new Task(taskString);
        etTitle.setText(task.getTitle());
        datePicker.updateDate(task.getDueYear(), task.getDueMonth(), task.getDueDay());
        spinnerCategory.setSelection(task.getCategoryId());
        spinnerStatus.setSelection(task.getStatusId());
    }

    public void onSave(View view) {
        if (TextUtils.isEmpty(etTitle.getText())) {
            Toast.makeText(this, "Enter a task!", Toast.LENGTH_SHORT).show();
        } else {
            String title = etTitle.getText().toString();
            int day = datePicker.getDayOfMonth();
            int month = datePicker.getMonth();
            int year = datePicker.getYear();
            int category = spinnerCategory.getSelectedItemPosition();
            int status = spinnerStatus.getSelectedItemPosition();

            Task task = new Task(title, day, month, year, category, status);
            ArrayList<Task> oldTasks = TaskHelper.readTasks(getFilesDir());

            int position = getIntent().getIntExtra(MainDisplayActivity.POSITION_ID, -1);

            if (position != -1){
                oldTasks.remove(position);
                oldTasks.add(position, task);
            } else {
                oldTasks.add(task);
            }

            TaskHelper.writeTasks(getFilesDir(), oldTasks);

            Intent returnIntent = new Intent();
            setResult(RESULT_OK, returnIntent);
            finish();
        }
    }

    public void onExit(View view) {
        Toast.makeText(this, "Your changes have not been saved.", Toast.LENGTH_SHORT).show();
        AddEditActivity.this.finish();
    }
}