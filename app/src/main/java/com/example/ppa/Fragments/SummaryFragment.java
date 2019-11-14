package com.example.ppa.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ppa.Data.DAOs.RatingDAO;
import com.example.ppa.Data.Entities.Rating;
import com.example.ppa.Data.Entities.Task;
import com.example.ppa.Data.PPADatabase;
import com.example.ppa.Models.RatedTask;
import com.example.ppa.R;
import com.example.ppa.Adapters.SummaryAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SummaryFragment extends Fragment {

    private RecyclerView recyclerView;
    private SummaryAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<RatedTask> values = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        RatingDAO ratingDAO = PPADatabase.getInstance(getContext()).ratingDAO();
        List<Task> TaskList  = PPADatabase.getInstance(getContext()).taskDAO().getAll();

        TaskList.forEach((task) -> {
            List<Rating> ratings = ratingDAO.getAllByTaskId(task.id);
            int ratingForTask = ratings.stream().mapToInt(rating -> rating.rating).sum();
            RatedTask ratedTask = new RatedTask();
            ratedTask.task = task;
            ratedTask.rating = ratingForTask;
            values.add(ratedTask);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_rate_tasks, container, false);

        recyclerView = view.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        mAdapter = new SummaryAdapter(values);
        recyclerView.setAdapter(mAdapter);

        return view;
    }
}