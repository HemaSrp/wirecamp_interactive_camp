package com.wirecamp.assignment.wirecamp.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
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

import com.bumptech.glide.Glide;
import com.wirecamp.assignment.wirecamp.R;
import com.wirecamp.assignment.wirecamp.fragment.ConnectionFragment;
import com.wirecamp.assignment.wirecamp.fragment.FavouritesConnectionFragment;
import com.wirecamp.assignment.wirecamp.fragment.SearchFragment;
import com.wirecamp.assignment.wirecamp.utils.Constants;
import com.wirecamp.assignment.wirecamp.utils.SharedPrefManager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,SearchFragment.RegenerateArrayButtonClick {


    //This class used to store with key and value
    private SharedPrefManager sharedPrefManager;

    //Context of the class
    private final Context mContext = this;

    //Floating action button
    private FloatingActionButton fab;

    //Fragment name
    private String chooseFragment=Constants.CONNECTION_FRAGMENT;

    //Toolbar
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * This method is used to initialize the views
     */
    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        sharedPrefManager = new SharedPrefManager(mContext);
        NavigationView navView=(NavigationView)findViewById(R.id.nav_view);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        navView.setNavigationItemSelectedListener(this);
        View headerView=navView.getHeaderView(0);
        ImageView imgProfilePic=(ImageView)headerView.findViewById(R.id.profileImage);
        TextView txtName=(TextView)headerView.findViewById(R.id.userName);
        TextView txtEmailId=(TextView)headerView.findViewById(R.id.emailId);
        txtName.setText(sharedPrefManager.getName());
        txtEmailId.setText(sharedPrefManager.getUserEmail());
        String imgUrl=sharedPrefManager.getPhoto();
        Glide.with(this)
                .load(Uri.parse(imgUrl))
                .into(imgProfilePic);
        showSearchFragment();
        changeFragment(0);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSearchFragment();
            }
        });
    }

    /**
     * This method is used to display the dialog fragment when the view is crated
     */
    private void showSearchFragment() {
        FragmentManager fm = getSupportFragmentManager();
        SearchFragment editNameDialogFragment = SearchFragment.newInstance();
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

            changeFragment(0);
        } else if (id == R.id.nav_connection_favourites) {
            changeFragment(1);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    /**
     * To load fragments for sample
     * @param position menu index
     */
    private void changeFragment(int position) {
        Fragment newFragment = null;
        if (position == 0) {
            this.getSupportActionBar().setTitle("Connections");
            fab.setVisibility(View.VISIBLE);

            chooseFragment= Constants.CONNECTION_FRAGMENT;
            newFragment = ConnectionFragment.newInstance(chooseFragment);
        } else  {
            getSupportActionBar().setTitle("Favourites");
            chooseFragment= Constants.FAVOURITES_CONNECTION_FRAGMENT;
            fab.setVisibility(View.GONE);
            newFragment = FavouritesConnectionFragment.newInstance(chooseFragment);
        }
        getSupportFragmentManager().beginTransaction().setAllowOptimization(false).detach(newFragment).attach(newFragment).replace(
                R.id.fragmentContainer, newFragment)
                .commitAllowingStateLoss();
    }

    @Override
    public void onClickButton() {
        changeFragment(0);
    }
}

