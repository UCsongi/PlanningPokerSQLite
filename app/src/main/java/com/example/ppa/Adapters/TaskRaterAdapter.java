package com.example.ppa.Adapters;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.ppa.Models.RatedTask;
import com.example.ppa.R;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class TaskRaterAdapter extends RecyclerView.Adapter<TaskRaterAdapter.TaskRaterViewHolder> {
    private ArrayList<RatedTask> mDataset;

    public static class TaskRaterViewHolder extends RecyclerView.ViewHolder {
        public TextView titleView;
        public TextView descriptionView;
        public EditText ratingText;

        public TaskRaterViewHolder(View v) {
            super(v);

            titleView = v.findViewById(R.id.titleView);
            descriptionView = v.findViewById(R.id.descriptionView);
            ratingText = v.findViewById(R.id.ratingText);
        }
    }

    public TaskRaterAdapter(ArrayList<RatedTask> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public TaskRaterAdapter.TaskRaterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rate_task, parent, false);
        TaskRaterViewHolder vh = new TaskRaterViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TaskRaterViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //TODO test if it has rating or not
        holder.titleView.setText(mDataset.get(position).task.title);
        holder.descriptionView.setText(mDataset.get(position).task.description);
        holder.ratingText.setText(mDataset.get(position).rating);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

