package com.wirecamp.assignment.wirecamp.fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wirecamp.assignment.wirecamp.R;
import com.wirecamp.assignment.wirecamp.database.DatabaseHandler;
import com.wirecamp.assignment.wirecamp.model.ConnectionsItem;
import com.wirecamp.assignment.wirecamp.model.DatabaseModel;
import com.wirecamp.assignment.wirecamp.retrofit.SOSInterface;
import com.wirecamp.assignment.wirecamp.utils.Utils;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * This class is used to give the user input for the pickup and destination
 */
public class SearchFragment extends DialogFragment {

    //Interface
    private SOSInterface mService;

    //Database handler
    private DatabaseHandler db;

    //Destination user input
    private EditText edtSearchTrainTo;

    //Pickup user input
    private EditText edtSearchTrainFrom;

    //Button search
    private Button btnSearch;

    //String pickup point
    private String txtFrom;

    //String destination point
    private String txtTo;

    //Progress bar
    private ProgressBar mprogressBar;

    //Callback on button click
    private RegenerateArrayButtonClick mCallback;

    //Textview loading
    private TextView txtLoading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mService = Utils.getSOService();
        db = new DatabaseHandler(getActivity());
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);
        return inflater.inflate(R.layout.fragment_search, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtSearchTrainFrom = (EditText) view.findViewById(R.id.searchTrainFrom);
        edtSearchTrainTo = (EditText) view.findViewById(R.id.searchTrainTo);
        btnSearch=(Button)view.findViewById(R.id.search);
        txtLoading=(TextView)view.findViewById(R.id.txtLoading);
        mprogressBar = (ProgressBar) view.findViewById(R.id.circular_progress_bar);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtFrom="Lausanne";
                txtTo="Genève";
                if(txtFrom.isEmpty()&&txtFrom==null){
                    Toast.makeText(getActivity(),"Please enter the from location",Toast.LENGTH_LONG).show();
                }else if(txtTo.isEmpty()&&txtTo==null){
                    Toast.makeText(getActivity(),"Please enter the to location",Toast.LENGTH_LONG).show();
                }else {
                    getDialog().setCancelable(false);
                    db.deleteRecord();
                    txtLoading.setVisibility(View.VISIBLE);
                    mprogressBar.setVisibility(View.VISIBLE);
                    ObjectAnimator anim = ObjectAnimator.ofInt(mprogressBar, "progress", 0, 100);
                    anim.setDuration(15000);
                    anim.setInterpolator(new DecelerateInterpolator());
                    anim.start();
                    if (Utils.isNetworkAvailable(getActivity())) {
                        loadCollectionApi(txtFrom, txtTo);
                    }else{
                        Toast.makeText(getActivity(), "Oops! no internet connection!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }


        /**
         * This method  is used to get the request and response from the api and stored in database
         * @param txtFrom
         * @param txtTo
         */
        private void loadCollectionApi(String txtFrom, String txtTo) {
            mService.getPhotos(txtFrom,txtTo).enqueue(new Callback<com.wirecamp.assignment.wirecamp.model.Response>() {
                @Override
                public void onResponse(Call<com.wirecamp.assignment.wirecamp.model.Response> call, Response<com.wirecamp.assignment.wirecamp.model.Response> response) {
                    List<ConnectionsItem> connections = response.body().getConnections();
                    if (!connections.isEmpty()) {
                        DatabaseModel dbModel;
                        for (int i = 0; i < connections.size(); i++){
                            dbModel=new DatabaseModel();
                            ConnectionsItem connection = connections.get(i);
                            dbModel.setFromName(connection.getFrom().getStation().getName());
                            dbModel.setFromLatitude(connection.getFrom().getStation().getCoordinate().getX());
                            dbModel.setToLongitude(connection.getFrom().getStation().getCoordinate().getY());
                            dbModel.setDepatureTime(connection.getFrom().getDeparture());
                            dbModel.setToName(connection.getTo().getStation().getName());
                            dbModel.setToLatitude(connection.getTo().getStation().getCoordinate().getX());
                            dbModel.setToLongitude(connection.getTo().getStation().getCoordinate().getY());
                            dbModel.setArrivalTime(connection.getTo().getArrival());
                            dbModel.setFavourites("0");
                            dbModel.setRandomId(String.valueOf(UUID.randomUUID()));
                            db.insertCollections(dbModel);
                            dismiss();
                        }
                        mprogressBar.setVisibility(View.GONE);
                        mCallback.onClickButton();
                        txtLoading.setVisibility(View.GONE);


                    } else {
                        Toast.makeText(getActivity(), "Error loading from API", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<com.wirecamp.assignment.wirecamp.model.Response> call, Throwable t) {
                    Toast.makeText(getActivity(), "Error loading from API", Toast.LENGTH_LONG).show();
                }
            });
        }

    /**
     * This method is used to create the fragment
     *
     * @return searchFragment fragment
     */
    public static SearchFragment newInstance() {
        SearchFragment searchFragment=new SearchFragment();
        return searchFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (RegenerateArrayButtonClick) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement RegenerateArrayButtonClick");
        }
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

    /**
     * To interact with the activity
     */
    public interface RegenerateArrayButtonClick {
        void onClickButton();
    }

}

