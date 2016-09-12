package com.jpushkarskaya.tasker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by epushkarskaya on 9/10/16.
 */
public class TaskAdapter extends ArrayAdapter<Task> {

    private List<Task> tasks;

    public TaskAdapter(Context context, int resource, List<Task> tasks) {
        super(context, resource, tasks);
        this.tasks = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.single_task, null);
        }

        final Task task = tasks.get(position);
        ((TextView) view.findViewById(R.id.tvTitle)).setText(task.getTitle());
        ((TextView) view.findViewById(R.id.tvDue)).setText(task.getDueDate());
        ((TextView) view.findViewById(R.id.tvCategory)).setText(task.getCategory());
        ((TextView) view.findViewById(R.id.tvStatus)).setText(task.getStatus());
        return view;
    }

    @Override
    public int getCount(){
        return tasks.size();
    }

    public void swapItems(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public List<Task> getTasks(){
        return tasks;
    }
}
