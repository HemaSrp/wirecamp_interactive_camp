package com.wirecamp.assignment.wirecamp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wirecamp.assignment.wirecamp.R;
import com.wirecamp.assignment.wirecamp.database.DatabaseHandler;
import com.wirecamp.assignment.wirecamp.model.DatabaseModel;
import com.wirecamp.assignment.wirecamp.utils.Constants;

import java.util.List;

/**
 * This method is used to display the connection found between from and to.
 */

public class ConnectionAdapter extends RecyclerView.Adapter<ConnectionAdapter.MyViewHolder> {

    //Connection list from the db
    private final List<DatabaseModel> connectionList;

    //Database handler
    private final DatabaseHandler db;

    //Fragment name
    private final String strChooseFragment;

    /**
     * This constructor is used to init the adapter
     * @param connectionList     collection of data
     * @param db                 database handler
     * @param strChooseFragment  choose fragment
     */
    public ConnectionAdapter(List<DatabaseModel> connectionList, DatabaseHandler db, String strChooseFragment) {
        this.connectionList = connectionList;
        this.db=db;
        this.strChooseFragment=strChooseFragment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_connection_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final DatabaseModel favouriteList = connectionList.get(position);
        holder.fromName.setText(favouriteList.getFromName());
        holder.toName.setText(favouriteList.getToName());
        holder.arrivalTime.setText(favouriteList.getArrivalTime());
        holder.depatureTime.setText(favouriteList.getDepatureTime());
        checkFavourites(holder,favouriteList);
        holder.imgFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favouriteList.setFavourites("1");
                db.updateContact("1",favouriteList);
                checkFavourites(holder,favouriteList);

            }
        });

        holder.imgSelectFavourites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favouriteList.setFavourites("0");
                db.updateContact("0",favouriteList);
                checkFavourites(holder,favouriteList);
            }
        });

    }

    /**
     * This method is used to check the favourite condition.
     *
     * @param holder           Holder
     * @param favouriteList    List
     */
    private void checkFavourites(MyViewHolder holder, DatabaseModel favouriteList) {
        if(strChooseFragment.equals(Constants.CONNECTION_FRAGMENT)) {
            if (favouriteList.getFavourites().equals("0")) {
                holder.imgFavourites.setVisibility(View.VISIBLE);
                holder.imgSelectFavourites.setVisibility(View.GONE);
            } else if (favouriteList.getFavourites().equals("1")) {
                holder.imgSelectFavourites.setVisibility(View.VISIBLE);
                holder.imgFavourites.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return connectionList.size();
    }

    /**
     * This class is the view holder of the current class.
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public final TextView fromName;
        public final TextView toName;
        public final TextView arrivalTime;
        public final TextView depatureTime;
        public final ImageView imgFavourites;
        public final ImageView imgSelectFavourites;

        public MyViewHolder(View view) {
            super(view);
            fromName = view.findViewById(R.id.fromName);
            toName = view.findViewById(R.id.toName);
            arrivalTime = view.findViewById(R.id.strArrivalTime);
            depatureTime = view.findViewById(R.id.strDepatureTime);
            imgFavourites=view.findViewById(R.id.imgFavourites);
            imgSelectFavourites=view.findViewById(R.id.imgSelectFavourites);


        }
    }
}