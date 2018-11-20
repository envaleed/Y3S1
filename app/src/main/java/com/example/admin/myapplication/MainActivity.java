package com.example.admin.myapplication;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private FragmentHome homeFragment;
    private FragmentNotification notificationFragment;
    private FragmentStudy studyFragment;
    private FragmentAssignment assignmentFragment;
    private FragmentBudget budgetFragment;
    private FragementPersonal personalFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        homeFragment = new FragmentHome();
        notificationFragment = new FragmentNotification();
        studyFragment = new FragmentStudy();
        assignmentFragment = new FragmentAssignment();
        budgetFragment = new FragmentBudget();
        personalFragment = new FragementPersonal();

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.nav_hame:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimary);
                        setFragment(homeFragment);
                        return true;

                    case R.id.nav_study:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(studyFragment);
                        return true;

                    case R.id.nav_assignment:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(assignmentFragment);
                        return true;

                    case R.id.nav_budget:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(budgetFragment);
                        return true;

                    case R.id.nav_personal:
                        mMainNav.setItemBackgroundResource(R.color.colorPrimaryDark);
                        setFragment(personalFragment);
                        return true;

                        default:
                            return false;
                }
            }

            private void setFragment(Fragment fragment) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame, fragment);
                fragmentTransaction.commit();
            }


        });
    }


}
