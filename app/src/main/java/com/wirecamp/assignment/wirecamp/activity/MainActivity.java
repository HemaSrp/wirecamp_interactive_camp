package com.wirecamp.assignment.wirecamp.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.wirecamp.assignment.wirecamp.R;
import com.wirecamp.assignment.wirecamp.fragment.ConnectionFragment;
import com.wirecamp.assignment.wirecamp.fragment.FavouritesConnectionFragment;
import com.wirecamp.assignment.wirecamp.fragment.SearchFragment;
import com.wirecamp.assignment.wirecamp.utils.SharedPrefManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private SharedPrefManager sharedPrefManager;
    private final Context mContext = this;
    private TextView mTextMessage;
    private int[] bottomBarColors;
    private BottomNavigationView bottomNavigation;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        bottomBarColors = new int[]{
                R.color.colorPrimary,
                R.color.colorPrimaryDark,
        };


        showSearchFragment();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        bottomNavigation=(BottomNavigationView)findViewById(R.id.bottom_navigation);
         navigationView = (NavigationView) findViewById(R.id.nav_view);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_connections:
                        changeBottomBarColor(bottomNavigation, 0);
                        changeFragment(0);
                        break;
                    case R.id.nav_connection_favourites:
                        changeBottomBarColor(bottomNavigation, 1);
                        changeFragment(1);
                        break;
                }
                return true;
            }
        });

    }

    private void initViews() {
        sharedPrefManager = new SharedPrefManager(mContext);
         bottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        NavigationView navView=(NavigationView)findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
        View headerView=navView.getHeaderView(0);
        ImageView imgProfilePic=(ImageView)headerView.findViewById(R.id.profileImage);
        TextView txtName=(TextView)headerView.findViewById(R.id.userName);
        TextView txtEmailId=(TextView)headerView.findViewById(R.id.emailId);
        txtName.setText(sharedPrefManager.getName());
        txtEmailId.setText(sharedPrefManager.getUserEmail());
        mTextMessage = (TextView) findViewById(R.id.message);
    }

    private void showSearchFragment() {
        FragmentManager fm = getSupportFragmentManager();
        SearchFragment editNameDialogFragment = SearchFragment.newInstance("SearchFragment");
        editNameDialogFragment.show(fm, "fragment_search");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle bottomNavigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_connections) {
            changeBottomBarColor(bottomNavigation, 0);
            changeFragment(0);
        } else if (id == R.id.nav_connection_favourites) {
            changeBottomBarColor(bottomNavigation, 1);
            changeFragment(1);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /**
     * To change bottom bar color on the basis of index
     * @param bottomNavigationView bottom bar object
     * @param index menu index
     */
    private void changeBottomBarColor(BottomNavigationView bottomNavigationView, int index) {
        if (bottomBarColors != null) {
            int colorCode = 0;

            if (index == 0) {
                colorCode = bottomBarColors[index];
            } else {
                colorCode = ContextCompat.getColor(MainActivity.this, bottomBarColors[index]);
            }

            DrawableCompat.setTint(ContextCompat.getDrawable(MainActivity.this,
                    R.color.colorPrimary),
                    colorCode);

            bottomNavigation.setItemBackgroundResource( R.color.colorPrimary);

            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                // If you want to change status bar color
                //getWindow().setStatusBarColor(ContextCompat.getColor(BottomBarDemo.this, colorCode));

                // If you want to change bottom device bottomNavigation key background color
                getWindow().setNavigationBarColor(colorCode);
            }
        }
    }

    /**
     * To load fragments for sample
     * @param position menu index
     */
    private void changeFragment(int position) {

        Fragment newFragment = null;

        if (position == 0) {
            newFragment = ConnectionFragment.newInstance();
        } else  {
            newFragment = FavouritesConnectionFragment.newInstance();
        }

        getSupportFragmentManager().beginTransaction().replace(
                R.id.fragmentContainer, newFragment)
                .commit();
    }
}

