package com.wirecamp.assignment.wirecamp.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wirecamp.assignment.wirecamp.R;
import com.wirecamp.assignment.wirecamp.adapter.ConnectionAdapter;
import com.wirecamp.assignment.wirecamp.database.DatabaseHandler;
import com.wirecamp.assignment.wirecamp.model.DatabaseModel;
import com.wirecamp.assignment.wirecamp.utils.SeperatorDecoration;
import com.wirecamp.assignment.wirecamp.utils.Utils;

import java.util.List;

/**
 * Created by hema on 8/2/18.
 */

public class ConnectionFragment extends Fragment {

    private DatabaseHandler db;
    private List<DatabaseModel> connectionList;
    private LinearLayoutManager mLayoutManager;
    private RecyclerView recyclerView;
    private ConnectionAdapter mAdapter;
/*
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_connection, container);
    }*/

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
        db=new DatabaseHandler(getActivity());

        connectionList=db.getAllDetails();
        setRecyclerView(connectionList);
    }

    public static Fragment newInstance() {
        ConnectionFragment fragment=new ConnectionFragment();
        return fragment;
    }
    private void setRecyclerView(List<DatabaseModel> photos) {
        mAdapter=new ConnectionAdapter(connectionList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new SeperatorDecoration(getActivity(), ContextCompat.getColor(getActivity(), R.color.white), 10.0f));
        recyclerView.setAdapter(mAdapter);
    }

}
