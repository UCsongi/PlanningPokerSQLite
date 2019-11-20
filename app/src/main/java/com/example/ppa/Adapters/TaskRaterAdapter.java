package com.example.ppa.Adapters;

import android.text.Editable;
import android.text.TextWatcher;
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
    private List<RatedTask> mDataset;

    public static class TaskRaterViewHolder extends RecyclerView.ViewHolder {
        public TextView titleView;
        public TextView descriptionView;
        public EditText ratingText;
        public RatingChangedListener ratingChangedListener;

        public TaskRaterViewHolder(View v, RatingChangedListener ratingChangedListener) {
            super(v);

            titleView = v.findViewById(R.id.titleView);
            descriptionView = v.findViewById(R.id.descriptionView);
            ratingText = v.findViewById(R.id.ratingText);
            this.ratingChangedListener = ratingChangedListener;
            ratingText.addTextChangedListener(ratingChangedListener);
        }
    }

    private class RatingChangedListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            // no op
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            try {
                mDataset.get(position).rating = Integer.parseInt(charSequence.toString());
            } catch (NumberFormatException e){
                //TODO
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // no op
        }
    }

    public TaskRaterAdapter(List<RatedTask> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public TaskRaterAdapter.TaskRaterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_rate_task, parent, false);
        TaskRaterViewHolder vh = new TaskRaterViewHolder(v, new RatingChangedListener());
        return vh;
    }

    @Override
    public void onBindViewHolder(TaskRaterViewHolder holder, int position) {

        holder.ratingChangedListener.updatePosition(position);

        holder.titleView.setText(mDataset.get(position).task.title);
        holder.descriptionView.setText(mDataset.get(position).task.description);
        holder.ratingText.setText(Integer.toString(mDataset.get(position).rating));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}

