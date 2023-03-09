package com.example.proiect_scd_mobile.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiect_scd_mobile.R;
import com.example.proiect_scd_mobile.networking.ItemClickListener;

import java.util.ArrayList;

public class RecyclerViewAdapterNFT extends RecyclerView.Adapter<RecyclerViewAdapterNFT.RecyclerViewHolder> {

    private ArrayList<String> recyclerDataArrayList;
    private Context mcontext;
    private ItemClickListener clickListener;
    private SharedPreferences prefs;

    public RecyclerViewAdapterNFT(ArrayList<String> recyclerDataArrayList, Context mcontext) {
        this.recyclerDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate Layout;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.nft_item, parent, false);
        return new RecyclerViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.NftName.setText(recyclerDataArrayList.get(position));
        if (position == 0) {
            boolean colorOn = prefs.getBoolean("yachtNotif", false);
            Log.e("cretiu","colorOn:"+colorOn);
            if (colorOn) {
                holder.bg.setBackgroundColor(ContextCompat.getColor(mcontext,R.color.green));
            } else {
               holder.bg.setBackgroundColor(ContextCompat.getColor(mcontext,R.color.grey));
            }
        } else if (position == 1) {
            boolean  colorOn = prefs.getBoolean("kennelNotif", false);

            if (colorOn) {
                holder.bg.setBackgroundColor(ContextCompat.getColor(mcontext,R.color.green));
            } else {
                holder.bg.setBackgroundColor(ContextCompat.getColor(mcontext,R.color.grey));
            }
        } else if (position == 2) {

            boolean  colorOn = prefs.getBoolean("moonNotif", false);

            if (colorOn) {
                holder.bg.setBackgroundColor(ContextCompat.getColor(mcontext,R.color.green));
            } else {
                holder.bg.setBackgroundColor(ContextCompat.getColor(mcontext,R.color.grey));
            }
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return recyclerDataArrayList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView NftName;
        private RelativeLayout bg;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            prefs = mcontext.getSharedPreferences(
                    "com.example.proiect_scd_mobile", Context.MODE_PRIVATE);
            bg = itemView.findViewById(R.id.cardViewLayout);
            NftName = itemView.findViewById(R.id.nftItem_title);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition());
        }
    }
}
