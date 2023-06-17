package com.example.prueba2.ui.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prueba2.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private Context context;
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mDescription = new ArrayList<>();
    private ArrayList<Double> mPrices = new ArrayList<>();

    public CartAdapter(Context context, ArrayList<String> mImageNames, ArrayList<String> mNames, ArrayList<String> mDescription, ArrayList<Double> mPrices) {
        this.context = context;
        this.mImageNames = mImageNames;
        this.mNames = mNames;
        this.mDescription = mDescription;
        this.mPrices = mPrices;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.products_list_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Glide.with(context)
                .asBitmap()
                .load(mImageNames.get(position))
                .into(holder.image);

        holder.name.setText(mNames.get(position));
        holder.des.setText(mDescription.get(position));
        holder.price.setText(String.valueOf(mPrices.get(position)));

    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, des, price;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_product_cart);
            name = itemView.findViewById(R.id.txt_nombre_cart);
            des = itemView.findViewById(R.id.txt_des_cart);
            price = itemView.findViewById(R.id.txt_precio_cart);
        }
    }
}
