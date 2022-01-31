package com.zavaly.activities;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.zavaly.R;
import com.zavaly.databinding.ActivityMainBinding;

public class MainActivity extends CrashHandleActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;
    private MainActivity activity;
    public AppCompatImageButton advanceSearchBTN;
    public AppCompatEditText advanceSearchET;
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        advanceSearchBTN = binding.advanceSearchBtn;
        advanceSearchET = binding.advanceSearchEt;

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_cart, R.id.navigation_account)
                .setOpenableLayout(binding.drawerLayout)
                .build();

        navController = Navigation.findNavController(activity, R.id.nav_host_fragment_activity_main);
        //NavigationUI.setupActionBarWithNavController(activity, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupWithNavController(binding.mainSidebar, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {

                if (destination.getId() == R.id.navigation_home) {

                    if (binding.mainSearchLayout.getVisibility() == View.GONE) {

                        binding.mainSearchLayout.setVisibility(View.VISIBLE);

                    }

                } else {

                    if (binding.mainSearchLayout.getVisibility() == View.VISIBLE) {

                        binding.mainSearchLayout.setVisibility(View.GONE);

                    }

                }

            }
        });

        binding.mainSidebar.setItemIconTintList(null);
        binding.mainSidebar.setNavigationItemSelectedListener(activity);

        /**
         * Side
         * Menu
         * Switch
         * **/
        binding.sideMenuSwitchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {

                    //binding.sideMenuSwitchBtn.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.icon_menu));
                    binding.drawerLayout.closeDrawer(GravityCompat.START);

                } else {

                    ObjectAnimator animator = ObjectAnimator.ofFloat(binding.sideMenuSwitchBtn, "rotation", 0f, 360f);
                    animator.setDuration(500);
                    animator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {

                            //binding.sideMenuSwitchBtn.setImageDrawable(ContextCompat.getDrawable(activity, R.drawable.icon_close));
                            binding.drawerLayout.openDrawer(GravityCompat.START);

                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                    animator.start();

                }

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {

            binding.drawerLayout.closeDrawer(GravityCompat.START);

        }

        if (item.getItemId() == R.id.side_orders) {

            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
            navController.navigate(R.id.navigation_orders);

        }

        return true;
    }

    @Override
    public void onBackPressed() {

        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
}