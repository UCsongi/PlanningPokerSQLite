package com.example.ppa.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ppa.Data.DAOs.TaskDAO;
import com.example.ppa.Data.Entities.Task;
import com.example.ppa.Data.PPADatabase;
import com.example.ppa.R;

import java.util.List;

public class RateTasksFragment extends Fragment {

    private static final String USER_ID = "userid";

    private int userId;
    private List<Task> tasks;

    private OnRateListener mListener;

    public RateTasksFragment() {
        // Required empty public constructor
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

        TaskDAO taskDAO = PPADatabase.getInstance(getContext()).taskDAO();

        tasks = taskDAO.getAll();
        //TODO i am not sure if we get back null or something else when nothing exists
        if (tasks == null){

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_rate_tasks, container, false);

        return view;
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
