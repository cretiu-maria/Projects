package com.example.project.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;

import java.util.List;

public class RecyclerViewAdapterHomePartners extends RecyclerView.Adapter<RecyclerViewAdapterHomePartners.RecyclerViewHolder> {

    private List<Integer> imageList;

    public RecyclerViewAdapterHomePartners(List<Integer> imageList) {
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterHomePartners.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_view_item_partners, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterHomePartners.RecyclerViewHolder holder, int position) {
        holder.imageView.setImageResource(imageList.get(position));

    }


    @Override
    public int getItemCount() {
        return imageList.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
