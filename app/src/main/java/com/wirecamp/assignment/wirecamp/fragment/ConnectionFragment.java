package com.wirecamp.assignment.wirecamp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wirecamp.assignment.wirecamp.R;
import com.wirecamp.assignment.wirecamp.adapter.ConnectionAdapter;
import com.wirecamp.assignment.wirecamp.database.DatabaseHandler;
import com.wirecamp.assignment.wirecamp.model.DatabaseModel;

import java.util.List;

/**
 * This method is used to display the connection between the pick up and destination
 */

public class ConnectionFragment extends Fragment {

    //Current fragment name
    private static String strChooseFragment;

    //Database handler
    private DatabaseHandler db;

    //Connection list
    private List<DatabaseModel> connectionList;

    //Linearlayout manager
    private LinearLayoutManager mLayoutManager;

    //Recyclerview
    private RecyclerView recyclerView;

    //Connection adapter
    private ConnectionAdapter mAdapter;

    //No connection text
    private TextView txtConnection;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_connection, container, false);
        return result;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view);
        txtConnection=(TextView)view.findViewById(R.id.noConnection);
        db=new DatabaseHandler(getActivity());
        connectionList=db.getAllDetails();
        if(!connectionList.isEmpty()) {
            txtConnection.setVisibility(View.GONE);
            setRecyclerView(connectionList);
        }else{
            txtConnection.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            txtConnection.setText("No connection to display");
        }
    }

    /**
     * This method is used to create the instance of the fragment
     *
     * @param chooseFragment   fragment name
     * @return  fragment
     */
    public static Fragment newInstance(String chooseFragment) {
        ConnectionFragment fragment=new ConnectionFragment();
        strChooseFragment=chooseFragment;
        return fragment;
    }

    /**
     * THis method is used to display the connection list in recyclerview.
     * @param photos
     */
    private void setRecyclerView(List<DatabaseModel> photos) {
        mAdapter=new ConnectionAdapter(photos,db,strChooseFragment);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mAdapter!=null)
            mAdapter.notifyDataSetChanged();
    }
}
