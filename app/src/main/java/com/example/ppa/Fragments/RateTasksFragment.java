package com.example.ppa.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.ppa.Adapters.TaskRaterAdapter;
import com.example.ppa.Data.DAOs.RatingDAO;
import com.example.ppa.Data.DAOs.TaskDAO;
import com.example.ppa.Data.Entities.Rating;
import com.example.ppa.Data.Entities.Task;
import com.example.ppa.Data.PPADatabase;
import com.example.ppa.Models.RatedTask;
import com.example.ppa.R;

import java.util.ArrayList;
import java.util.List;

public class RateTasksFragment extends Fragment {

    private static final String USER_ID = "userid";
    private int userId;

    private List<Task> tasks;
    private ArrayList<RatedTask> ratedTasks;

    private OnRateListener mListener;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private TaskDAO taskDAO;
    private RatingDAO ratingDAO;

    public RateTasksFragment() {
    }

    public static RateTasksFragment newInstance(int userId) {
        RateTasksFragment fragment = new RateTasksFragment();
        Bundle args = new Bundle();
        args.putInt(USER_ID, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getInt(USER_ID);
        }

        taskDAO = PPADatabase.getInstance(getContext()).taskDAO();
        ratingDAO = PPADatabase.getInstance(getContext()).ratingDAO();

        ratedTasks = new ArrayList();
        tasks = taskDAO.getAll();
        for (Task task :tasks){
            RatedTask ratedTask = new RatedTask();
            ratedTask.task = task;

            Rating rating = ratingDAO.getByUserIdAndTaskId(userId, task.id);
            if (rating != null){
                ratedTask.rating = rating.rating;
            } else {
                ratedTask.rating = 0;
            }

            ratedTasks.add(ratedTask);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_rate_tasks, container, false);

        recyclerView = rootView.findViewById(R.id.task_rating_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new TaskRaterAdapter(ratedTasks);
        recyclerView.setAdapter(mAdapter);

        Button saveButton = rootView.findViewById(R.id.savebutton);
        saveButton.setOnClickListener(v -> {
            for (RatedTask ratedTask : ratedTasks)
            {
                Rating rating = ratingDAO.getByUserIdAndTaskId(userId, ratedTask.task.id);
                if (rating == null) {
                    ratingDAO.insertAll(new Rating(userId, ratedTask.task.id, ratedTask.rating));
                } else {
                    rating.rating = ratedTask.rating;
                    ratingDAO.updateAll(rating);
                }
            }

            if (mListener != null) {
                mListener.onRate();
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRateListener) {
            mListener = (OnRateListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnRateListener {
        void onRate();
    }
}
