package com.example.ppa.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.ppa.Fragments.FragmentLogin;
import com.example.ppa.Fragments.RateTasksFragment;
import com.example.ppa.Fragments.SectionsStatePagerAdapter;
import com.example.ppa.Fragments.SummaryFragment;
import com.example.ppa.R;

public class MainActivity extends AppCompatActivity {

    private SectionsStatePagerAdapter mSectionsStatePagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSectionsStatePagerAdapter = new SectionsStatePagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);

        setupViewPager(mViewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsStatePagerAdapter adapter = new SectionsStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new SummaryFragment(), "SummaryFragment");
        adapter.addFragment(new FragmentLogin(), "LoginFragment");
        adapter.addFragment(new RateTasksFragment(), "RateTasksFragment");
        viewPager.setAdapter(adapter);
    }
}
