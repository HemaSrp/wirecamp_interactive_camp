package com.wirecamp.assignment.wirecamp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wirecamp.assignment.wirecamp.R;
import com.wirecamp.assignment.wirecamp.model.DatabaseModel;

import java.util.List;

/**
 * Created by hema on 8/2/18.
 */

public class ConnectionAdapter extends RecyclerView.Adapter<ConnectionAdapter.MyViewHolder> {

    private final List<DatabaseModel> moviesList;


    public ConnectionAdapter(List<DatabaseModel> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_connection_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public final ImageView mPhoto;

        public MyViewHolder(View view) {
            super(view);
            mPhoto = view.findViewById(R.id.title);

        }
    }
}