package com.wirecamp.assignment.wirecamp.utils;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.wirecamp.assignment.wirecamp.retrofit.RestClient;
import com.wirecamp.assignment.wirecamp.retrofit.SOSInterface;

/**
 * This class is the base application of the class.
 */

public class Utils {

    private Context mContext = null;

    //Base url of the app
    private static final String BASE_URL = "http://transport.opendata.ch";

    /**
     * This method is used to create SOSinterface
     *
     * @return client
     */
    public static SOSInterface getSOService() {
        return RestClient.getClient(BASE_URL).create(SOSInterface.class);
    }

    /**
     * Public constructor that takes mContext for later use
     */
    public Utils(Context con) {
        mContext = con;
    }

    /**
     * Encode user email to use it as a Firebase key (Firebase does not allow "." in the key name)
     * Encoded email is also used as "userEmail", list and item "owner" value
     */
    public static String encodeEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

    //This is a method to Check if the device internet connection is currently on
    public boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager

                = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo != null && activeNetworkInfo.isConnected();

    }
}
