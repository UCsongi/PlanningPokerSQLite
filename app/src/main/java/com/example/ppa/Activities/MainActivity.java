package com.example.ppa.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.net.Uri;
import android.os.Bundle;

import com.example.ppa.Fragments.LoginFragment;
import com.example.ppa.Fragments.RateTasksFragment;
import com.example.ppa.Fragments.SectionsStatePagerAdapter;
import com.example.ppa.Fragments.SummaryFragment;
import com.example.ppa.R;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginListener {

    //private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    //private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*mSectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);

        setupViewPager(mViewPager);*/
        changeFragmentTo(new LoginFragment());
    }

    @Override
    public void onLogin(int userId) {
        changeFragmentTo(RateTasksFragment.newInstance(userId));
    }

    private void changeFragmentTo(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /*private void setupViewPager(ViewPager viewPager){
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SummaryFragment(), "SummaryFragment");
        adapter.addFragment(new LoginFragment(), "LoginFragment");
        adapter.addFragment(new RateTasksFragment(), "RateTasksFragment");
        viewPager.setAdapter(adapter);
    }*/
}
