package com.example.prueba2.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.res.TypedArrayUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba2.Producto;
import com.example.prueba2.R;
import com.example.prueba2.ui.adaptadores.CartAdapter;
import com.example.prueba2.ui.adaptadores.RecyclerAdapter;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

import kotlin.internal.ProgressionUtilKt;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarritoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarritoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView conter;
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Double> mPrices = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<String> mDescriptions = new ArrayList<>();

    private View viewRoot;

    public CarritoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarritoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CarritoFragment newInstance(String param1, String param2) {
        CarritoFragment fragment = new CarritoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewRoot = inflater.inflate(R.layout.fragment_carrito, container, false);
        conter = viewRoot.findViewById(R.id.txt_carrito_contador);

        getProducts();
        showProducts();
        Button limpiar = viewRoot.findViewById(R.id.btn_limpiar_pedido);
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCarro(v);
            }
        });
        Button pedido = viewRoot.findViewById(R.id.btn_realizar_pedido);
        pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarPedido(v);
            }
        });

        return viewRoot;
    }

    public void realizarPedido(View view) {
        // Mandar la cuenta total
        SharedPreferences preferences = getActivity().getSharedPreferences("cuenta", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        float total = preferences.getFloat("cuenta", 0);
        for (Producto producto : RecyclerAdapter.products) {
            total += producto.getPrecio();
        }
        editor.putFloat("cuenta", total);
        editor.commit();

        // Mandar pedido a meseros y a la cuenta
        SharedPreferences preferencesPedido = getActivity().getSharedPreferences("pedido", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editorPedido = preferencesPedido.edit();
        Gson gson = new Gson();
        String json = preferencesPedido.getString("arr_productos", null);
        Type type = new com.google.gson.reflect.TypeToken<Producto[]>(){}.getType();
        Producto[] productos = gson.fromJson(json, type);
        if (productos != null) { // TODO: Ver si se puede evitar ya que consume memoria innecesaria
            for (Producto producto : productos) {
                mNames.add(producto.getNombre());
                mPrices.add(producto.getPrecio());
                mImages.add(producto.getImagen());
                mDescriptions.add(producto.getDescripcion());
            }
        }
        Producto[] pedirProductos = new Producto[mNames.size()];
        for (int i = 0; i < mNames.size(); i++) {
            pedirProductos[i] = new Producto(mNames.get(i), mPrices.get(i), mImages.get(i), mDescriptions.get(i));
        }
        String jsonSend = gson.toJson(pedirProductos);
        editorPedido.putString("arr_productos", jsonSend);
        editorPedido.apply();

        limpiarCarro(view);
        Toast.makeText(viewRoot.getContext(), "Pedido realizado", Toast.LENGTH_SHORT).show();
    }

    public void limpiarCarro(View view) {
        SharedPreferences preferences = getActivity().getSharedPreferences("carrito", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        RecyclerAdapter.products.clear();
        mImages.clear();
        mNames.clear();
        mDescriptions.clear();
        mPrices.clear();
        getProducts();
        showProducts();
        conter.setText("0");
    }

    private void getProducts() {
        SharedPreferences preferences = getActivity().getSharedPreferences("carrito", getActivity().MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("productos", null);
        Type type = new com.google.gson.reflect.TypeToken<ArrayList<Producto>>(){}.getType();
        RecyclerAdapter.products = gson.fromJson(json, type);
        if (RecyclerAdapter.products == null) {
            RecyclerAdapter.products = new ArrayList<>();
        }
    }

    private void showProducts() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(viewRoot.getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerViewFood = viewRoot.findViewById(R.id.reclyer_view_cart);
        recyclerViewFood.setLayoutManager(layoutManager);
        for (Producto producto : RecyclerAdapter.products) {
            mImages.add(producto.getImagen());
            mNames.add(producto.getNombre());
            mDescriptions.add(producto.getDescripcion());
            mPrices.add(producto.getPrecio());
        }
        CartAdapter adapterSnaks = new CartAdapter(viewRoot.getContext(), mImages, mNames, mDescriptions, mPrices);
        recyclerViewFood.setAdapter(adapterSnaks);
        conter.setText(String.valueOf(RecyclerAdapter.products.size()));
    }

}