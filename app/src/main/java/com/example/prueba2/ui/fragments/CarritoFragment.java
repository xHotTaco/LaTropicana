package com.example.prueba2.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.prueba2.Producto;
import com.example.prueba2.R;
import com.example.prueba2.ui.adaptadores.RecyclerAdapter;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

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

    private TextView conter;
    TextView results;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewRoot = inflater.inflate(R.layout.fragment_carrito, container, false);
        conter = viewRoot.findViewById(R.id.txt_carrito_contador);
        results = viewRoot.findViewById(R.id.id_txt_cart);

        SharedPreferences preferences = getActivity().getSharedPreferences("carrito", getActivity().MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("productos", null);
        Type type = new com.google.gson.reflect.TypeToken<ArrayList<Producto>>(){}.getType();
        RecyclerAdapter.products = gson.fromJson(json, type);
        if (RecyclerAdapter.products == null) {
            RecyclerAdapter.products = new ArrayList<>();
        }
        for (Producto producto : RecyclerAdapter.products) {
            results.append(
                    "Nombre: " + producto.getNombre() + "\n" +
                    "Descripción: " + producto.getDescripcion() + "\n" +
                    "Precio: " + producto.getPrecio() + "\n\n");
        }
        conter.setText(String.valueOf(RecyclerAdapter.products.size()));

        Button limpiar = viewRoot.findViewById(R.id.btn_limpiar_pedido);
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiarCarro(v);
            }
        });

        return viewRoot;
    }

    public void limpiarCarro(View view) {
        SharedPreferences preferences = getActivity().getSharedPreferences("carrito", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        conter.setText("0");
        results.setText("");
    }
}