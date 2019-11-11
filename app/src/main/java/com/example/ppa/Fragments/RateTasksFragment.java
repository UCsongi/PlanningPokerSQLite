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

import com.example.ppa.Adapters.TaskRaterAdapter;
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
    private List<RatedTask> ratedTasks;

    private OnRateListener mListener;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

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

        ratedTasks = new ArrayList();
        tasks = PPADatabase.getInstance(getContext()).taskDAO().getAll();
        for (Task task :tasks){
            RatedTask ratedTask = new RatedTask();
            ratedTask.task = task;
            ratedTask.rating = PPADatabase.getInstance(getContext()).ratingDAO().getAllByUserIdAndTaskId(userId, task.id).rating;
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

        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onRate();
        }
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
