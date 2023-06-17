package com.example.prueba2.ui.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.prueba2.Producto;
import com.example.prueba2.R;
import com.example.prueba2.ui.adaptadores.RecyclerAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MenuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MenuFragment newInstance(String param1, String param2) {
        MenuFragment fragment = new MenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private Producto[] beers = new Producto[10];
    private Producto[] wines = new Producto[10];
    private Producto[] foods = new Producto[10];

    private ArrayList<String> mImageNamesBeer = new ArrayList<>();
    private ArrayList<String> mNamesBeer = new ArrayList<>();
    private ArrayList<String> mSizesBeer = new ArrayList<>();
    private ArrayList<Double> mPricesBeer = new ArrayList<>();

    private ArrayList<String> mImageNamesWine = new ArrayList<>();
    private ArrayList<String> mNamesWine = new ArrayList<>();
    private ArrayList<String> mSizesWine = new ArrayList<>();
    private ArrayList<Double> mPricesWine = new ArrayList<>();

    private ArrayList<String> mImageNamesFood = new ArrayList<>();
    private ArrayList<String> mNamesFood = new ArrayList<>();
    private ArrayList<String> mSizesFood = new ArrayList<>();
    private ArrayList<Double> mPricesFood = new ArrayList<>();

    private ArrayList<String> mImageNamesSnaks = new ArrayList<>();
    private ArrayList<String> mNamesSnaks = new ArrayList<>();
    private ArrayList<String> mSizesSnaks = new ArrayList<>();
    private ArrayList<Double> mPricesSnaks = new ArrayList<>();

    private View viewRoot;

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
        viewRoot = inflater.inflate(R.layout.fragment_menu, container, false);
        createBeerView();
        createWineView();
        createFoodView();
        createSnaksView();

        return viewRoot;
    }

    private void createBeerView() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("key_productos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = sharedPreferences.getString("beers", null);
        Producto[] productos = gson.fromJson(json, Producto[].class);
        if (productos != null) {
            for (int i = 0; i < productos.length; i++) {
                mImageNamesBeer.add(productos[i].getImagen());
                mNamesBeer.add(productos[i].getNombre());
                mSizesBeer.add(productos[i].getDescripcion());
                mPricesBeer.add(productos[i].getPrecio());
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(viewRoot.getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = viewRoot.findViewById(R.id.recycler_view_beers);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerAdapter adapter = new RecyclerAdapter(viewRoot.getContext(), mImageNamesBeer, mNamesBeer, mSizesBeer, mPricesBeer);
        recyclerView.setAdapter(adapter);
    }

    private void createWineView() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("key_productos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = sharedPreferences.getString("wines", null);
        Producto[] productos = gson.fromJson(json, Producto[].class);
        if (productos != null) {
            for (int i = 0; i < productos.length; i++) {
                mImageNamesWine.add(productos[i].getImagen());
                mNamesWine.add(productos[i].getNombre());
                mSizesWine.add(productos[i].getDescripcion());
                mPricesWine.add(productos[i].getPrecio());
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(viewRoot.getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewWine = viewRoot.findViewById(R.id.recycler_view_wine);
        recyclerViewWine.setLayoutManager(layoutManager);
        RecyclerAdapter adapterWine = new RecyclerAdapter(viewRoot.getContext(), mImageNamesWine, mNamesWine, mSizesWine, mPricesWine);
        recyclerViewWine.setAdapter(adapterWine);
    }

    private void createFoodView() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("key_productos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = sharedPreferences.getString("food", null);
        Producto[] productos = gson.fromJson(json, Producto[].class);
        if (productos != null) {
            for (int i = 0; i < productos.length; i++) {
                mImageNamesFood.add(productos[i].getImagen());
                mNamesFood.add(productos[i].getNombre());
                mSizesFood.add(productos[i].getDescripcion());
                mPricesFood.add(productos[i].getPrecio());
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(viewRoot.getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewFood = viewRoot.findViewById(R.id.recycler_view_food);
        recyclerViewFood.setLayoutManager(layoutManager);
        RecyclerAdapter adapterFood = new RecyclerAdapter(viewRoot.getContext(), mImageNamesFood, mNamesFood, mSizesFood, mPricesFood);
        recyclerViewFood.setAdapter(adapterFood);
    }

    private void createSnaksView() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("key_productos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = sharedPreferences.getString("snaks", null);
        Producto[] productos = gson.fromJson(json, Producto[].class);
        if (productos != null) {
            for (int i = 0; i < productos.length; i++) {
                mImageNamesSnaks.add(productos[i].getImagen());
                mNamesSnaks.add(productos[i].getNombre());
                mSizesSnaks.add(productos[i].getDescripcion());
                mPricesSnaks.add(productos[i].getPrecio());
            }
        }

        Type type = new TypeToken<Producto[]>() {}.getType();
        LinearLayoutManager layoutManager = new LinearLayoutManager(viewRoot.getContext(), LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerViewFood = viewRoot.findViewById(R.id.recycler_view_snaks);
        recyclerViewFood.setLayoutManager(layoutManager);
        RecyclerAdapter adapterSnaks = new RecyclerAdapter(viewRoot.getContext(), mImageNamesSnaks, mNamesSnaks, mSizesSnaks, mPricesSnaks);
        recyclerViewFood.setAdapter(adapterSnaks);
    }
}