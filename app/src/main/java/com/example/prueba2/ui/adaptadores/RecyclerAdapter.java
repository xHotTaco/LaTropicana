package com.example.prueba2.ui.adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prueba2.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    /*
    private ArrayList<String> mImageNames = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mSizes = new ArrayList<>();
    private ArrayList<String> mPrices = new ArrayList<>();
     */
    private Context context;
    private String mImageNames;
    private String mNames;
    private String mSizes;
    private String mPrices;

    public RecyclerAdapter(Context context, String mImageNames, String mNames, String mSizes, String mPrices) {
        this.mImageNames = mImageNames;
        this.mNames = mNames;
        this.mSizes = mSizes;
        this.mPrices = mPrices;
        this.context = context;
    }

    /*
    public RecyclerAdapter(Context context, ArrayList<String> mImageNames, ArrayList<String> mNames, ArrayList<String> mSizes, ArrayList<String> mPrices) {
        this.mImageNames = mImageNames;
        this.mNames = mNames;
        this.mSizes = mSizes;
        this.mPrices = mPrices;
        this.context = context;
    }

     */

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) { // data

        Glide.with(context)
                .asBitmap()
                .load(mImageNames)
                .into(holder.image);

        holder.name.setText(mNames);
        holder.size.setText(mSizes);
        holder.price.setText(mPrices);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, mNames + " agregado al carrito.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mNames.length();
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
}
