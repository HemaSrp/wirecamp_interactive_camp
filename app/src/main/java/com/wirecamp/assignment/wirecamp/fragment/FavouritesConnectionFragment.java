package com.wirecamp.assignment.wirecamp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
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
 * This method is used to display the favourites between the pick up and destination
 */
public class FavouritesConnectionFragment extends Fragment {

    // Fragment name
    private static String strFavouriteChooseFragment;

    //Recyclerview
    private RecyclerView recyclerView;

    //Database handler
    private DatabaseHandler db;

    //Connection list
    private List<DatabaseModel> connectionList;

    //Connection adapter
    private ConnectionAdapter mAdapter;

    //Linear layout manager
    private LinearLayoutManager mLayoutManager;

    //TextConnection
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
        connectionList=db.getAllFavourites();
        if(!connectionList.isEmpty()) {
            txtConnection.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            setRecyclerView(connectionList);
        }else{
            recyclerView.setVisibility(View.GONE);
            txtConnection.setVisibility(View.VISIBLE);
            txtConnection.setText("No favourites to display");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mAdapter!=null)
            mAdapter.notifyDataSetChanged();
    }

    /**
     * This method is used to create the instance of the fragment
     *
     * @param chooseFragment   fragment name
     * @return  fragment
     */
    public static Fragment newInstance(String chooseFragment) {
        FavouritesConnectionFragment fragment=new FavouritesConnectionFragment();
       strFavouriteChooseFragment=chooseFragment;
        return fragment;
    }


    /**
     * This method is used to display the favourite connection list
      * @param favourite favouriteList
     */
    private void setRecyclerView(List<DatabaseModel> favourite) {
        mAdapter=new ConnectionAdapter(favourite, db, strFavouriteChooseFragment);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SeperatorDecoration(getActivity(), ContextCompat.getColor(getActivity(), R.color.white), 10.0f));
        recyclerView.setAdapter(mAdapter);
    }
}
