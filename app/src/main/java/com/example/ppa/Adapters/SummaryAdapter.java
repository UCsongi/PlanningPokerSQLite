package com.example.ppa.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ppa.Models.RatedTask;
import com.example.ppa.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.MyViewHolder> {

    private List<RatedTask> mDataset;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView task, rating;

        MyViewHolder(View view) {
            super(view);
            task = view.findViewById(R.id.task);
            rating = view.findViewById(R.id.rating);
        }
    }

    public SummaryAdapter(List<RatedTask> values) {
        mDataset = values;
        for (RatedTask ratedTask : mDataset){
            Log.d("summaryAdapter", Integer.toString(ratedTask.rating));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_summary, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RatedTask data = mDataset.get(position);

        holder.task.setText(data.task.title);
        holder.rating.setText(Integer.toString(data.rating));
    }

    @Override
    public int getItemCount(){
        return mDataset.size();
    }
}

