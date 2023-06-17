package com.example.prueba2.ui.adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prueba2.MenuCliente;
import com.example.prueba2.Producto;
import com.example.prueba2.R;
import com.example.prueba2.ui.fragments.CarritoFragment;
import com.google.gson.Gson;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public static ArrayList<Producto> products = new ArrayList<>();
    private Context context;
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mSizes = new ArrayList<>();
    private ArrayList<Double> mPrices = new ArrayList<>();

    public RecyclerAdapter(Context context, ArrayList<String> mImageNames, ArrayList<String> mNames, ArrayList<String> mSizes, ArrayList<Double> mPrices) {
        this.mImageNames = mImageNames;
        this.mNames = mNames;
        this.mSizes = mSizes;
        this.mPrices = mPrices;
        this.context = context;
    }

    public RecyclerAdapter() {}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) { // data

        Glide.with(context)
                .asBitmap()
                .load(mImageNames.get(position))
                .into(holder.image);

        holder.name.setText(mNames.get(position));
        holder.size.setText(mSizes.get(position));
        holder.price.setText(String.valueOf(mPrices.get(position)));
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                products.add(new Producto(mNames.get(position), mPrices.get(position), mImageNames.get(position), mSizes.get(position)));
                saveProduct();
                Toast.makeText(context, mNames.get(position) + " agregado al carrito.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, size, price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_product);
            name = itemView.findViewById(R.id.txt_nombre);
            size = itemView.findViewById(R.id.txt_tam);
            price = itemView.findViewById(R.id.txt_precio);
        }
    }

    private void saveProduct() {
        SharedPreferences preferences = context.getSharedPreferences("carrito", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(products);
        editor.putString("productos", json);
        editor.apply();
    }
}
