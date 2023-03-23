package com.example.project.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.networking.User;

import java.util.ArrayList;

public class RecyclerViewAdapterRestaurant extends RecyclerView.Adapter<RecyclerViewAdapterRestaurant.RecyclerViewHolder> {

    private ArrayList<User> recyclerDataArrayList;
    private Context mcontext;

    // creating a constructor class.
    public RecyclerViewAdapterRestaurant(ArrayList<User> recyclerDataArrayList, Context mcontext) {
        this.recyclerDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate Layout;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurants_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        User user = recyclerDataArrayList.get(position);
        holder.restaurantName.setText(user.getName());
       // Picasso.get().load(modal.getImage()).into(holder.restaurantImage);

    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return recyclerDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private TextView restaurantName;
        private ImageView restaurantImage;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.
            restaurantName = itemView.findViewById(R.id.restaurantItem_title);
          //  restaurantImage = itemView.findViewById(R.id.restaurantItemImageview);
        }
    }
}

