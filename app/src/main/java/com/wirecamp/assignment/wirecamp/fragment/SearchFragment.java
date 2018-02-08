package com.wirecamp.assignment.wirecamp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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
 * Created by hema on 8/2/18.
 */

public class SearchFragment extends DialogFragment {

    private SOSInterface mService;
    private DatabaseHandler db;
    private EditText edtSearchTrainTo;
    private EditText edtSearchTrainFrom;
    private Button btnSearch;
    private String txtFrom;
    private String txtTo;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mService = Utils.getSOService();
        db = new DatabaseHandler(getActivity());

        return inflater.inflate(R.layout.fragment_search, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtSearchTrainFrom = (EditText) view.findViewById(R.id.searchTrainFrom);
        edtSearchTrainTo = (EditText) view.findViewById(R.id.searchTrainTo);
        btnSearch=(Button)view.findViewById(R.id.search);
        edtSearchTrainFrom.requestFocus();
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
                    loadFlickrApi(txtFrom, txtTo);
                }
            }
        });

    }


        /**
         * This method  is used to get the request and response from the api and stored in database
         * @param txtFrom
         * @param txtTo
         */
        private void loadFlickrApi(String txtFrom, String txtTo) {
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
                            dbModel.setDepatureTime(connection.getFrom().getDepartureTimestamp());
                            dbModel.setToName(connection.getTo().getStation().getName());
                            dbModel.setToLatitude(connection.getTo().getStation().getCoordinate().getX());
                            dbModel.setToLongitude(connection.getTo().getStation().getCoordinate().getY());
                            dbModel.setArrivalTime(connection.getTo().getArrivalTimestamp());
                            dbModel.setFavourites("0");
                            dbModel.setRandomId(String.valueOf(UUID.randomUUID()));
                            db.insertPhoto(dbModel);

                        }
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

    public static SearchFragment newInstance(String s) {
        SearchFragment searchFragment=new SearchFragment();
        return searchFragment;
    }
}

