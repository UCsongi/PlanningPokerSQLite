package com.example.ppa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ppa.Data.Entities.Task;
import com.example.ppa.Models.RatedTask;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.MyViewHolder> {

    private List<RatedTask> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView task, rating;

        public MyViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.task);
            rating = view.findViewById(R.id.rating);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SummaryAdapter(List<RatedTask> values) {
        mDataset = values;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_summary, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RatedTask data = mDataset.get(position);
        holder.task.setText(data.task.title);
        holder.rating.setText(data.rating);
    }

    @Override
    public int getItemCount(){
        return mDataset.size();
    }
}

