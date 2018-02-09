package com.wirecamp.assignment.wirecamp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wirecamp.assignment.wirecamp.R;
import com.wirecamp.assignment.wirecamp.utils.SharedPrefManager;

/**
 * This class is used to display the splash screen of the app.
 */
public class SplashScreen extends AppCompatActivity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    //Sharedpreferncemanager
    private SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        sharedPrefManager = new SharedPrefManager(this);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your activity on first time launch
                if(!sharedPrefManager.getFirstTimeLaunch()) {
                    Intent i = new Intent(SplashScreen.this, LoginScreen.class);
                    startActivity(i);
                    // close this activity
                    finish();
                }else{
                    //On second time launch this screen will be created
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                    // close this activity
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
}
