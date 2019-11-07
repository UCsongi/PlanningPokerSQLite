package com.example.ppa.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.ppa.Fragments.LoginFragment;
import com.example.ppa.Fragments.RateTasksFragment;
import com.example.ppa.Fragments.SummaryFragment;
import com.example.ppa.R;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginListener, RateTasksFragment.OnRateListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        changeFragmentTo(new LoginFragment());
    }

    @Override
    public void onLogin(int userId) {
        changeFragmentTo(RateTasksFragment.newInstance(userId));
    }

    @Override
    public void onRate() {
        changeFragmentTo(new SummaryFragment());
    }

    private void changeFragmentTo(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
