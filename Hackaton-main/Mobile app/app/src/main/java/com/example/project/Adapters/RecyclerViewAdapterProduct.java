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
import com.example.project.networking.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapterProduct extends RecyclerView.Adapter<RecyclerViewAdapterProduct.RecyclerViewHolder> {

    private ArrayList<Product> recyclerDataArrayList;
    private Context mcontext;

    // creating a constructor class.
    public RecyclerViewAdapterProduct(ArrayList<Product> recyclerDataArrayList, Context mcontext) {
        this.recyclerDataArrayList = recyclerDataArrayList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflate Layout;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        Product modal = recyclerDataArrayList.get(position);
        holder.productName.setText(modal.getProductName().toString());
        holder.productCategory.setText(modal.getProductCategory().toString());
        holder.productAvailable.setText(modal.getProductAvailable().toString());
        holder.productDescription.setText(modal.getProductDetails().toString());
        holder.productQuantity.setText(modal.getProductQuantity().toString());
      //  Picasso.get().load(modal.getImage()).into(holder.productImage);

    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return recyclerDataArrayList.size();
    }

    // View Holder Class to handle Recycler View.
    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        // creating variables for our views.
        private TextView productName, productCategory, productAvailable, productDescription, productQuantity;
        private ImageView productImage;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.
            productName = itemView.findViewById(R.id.product_name);
            productCategory = itemView.findViewById(R.id.product_category);
            productAvailable = itemView.findViewById(R.id.product_available);
            productDescription = itemView.findViewById(R.id.product_description);
            productQuantity = itemView.findViewById(R.id.product_quantity);
            productImage = itemView.findViewById(R.id.product_imageView);
        }
    }
}

