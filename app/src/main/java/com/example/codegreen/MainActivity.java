package com.example.codegreen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.codegreen.ui.userprofile.Milestone;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.codegreen.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public static final String EXTRA_USERNAME = "com.example.codegreen.USERNAME";
    public static final String EXTRA_LOCATION = "com.example.codegreen.LOCATION";
    public static final String EXTRA_USERABOUT = "com.example.codegreen.USERABOUT";

    private User user;

    public AppBarConfiguration appBarConfiguration;

    private User makeGenericUser() {
        User user = new User();
        user.setUsername("Default username.");
        user.setLocation("Unset");
        user.setUserAbout("Describe yourself!");
// Some generic sounding milestones.
        user.addToMilestones(
                new Milestone(
                        "Code Green",
                        "You have downloaded and started using Code Green!",
                        R.drawable.ic_home_black_24dp,
                        0));
        user.addToMilestones(
                new Milestone(
                        "First Scanning",
                        "You have scanned your first item!",
                        R.drawable.ic_camera_black_24dp,
                        1));
        user.addToMilestones(
                new Milestone(
                        "Top Half",
                        "You made the top half of users in your area!",
                        R.drawable.ic_notifications_black_24dp,
                        2));

        user.addToMilestones(
                new Milestone(
                        "One Month of Green",
                        "You have used Code Green for one month!",
                        R.drawable.ic_dashboard_black_24dp,
                        3));

        user.addToMilestones(
                new Milestone(
                        "Getting to Know You",
                        "You have set up your User Profile!",
                        R.drawable.ic_gear_black_24dp,
                        4));
        user.setFavoriteMilestone(user.getMilestones().get(2));
        return user;
    }

    @Override
    protected void onStart() {
        super.onStart();
        int themeId = R.style.FontSizeNormal;
        if (user.isLargeText()) {
            themeId = R.style.FontSizeLarge;
        }
        setTheme(themeId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = makeGenericUser();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_leaderboard, R.id.navigation_footprint, R.id.navigation_overview, R.id.navigation_scan)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    // Based on Android Studio tutorials, allows use of back button (for things like User Profile Milestones Fragment).
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public User getUser() {
        return user;
    }
}