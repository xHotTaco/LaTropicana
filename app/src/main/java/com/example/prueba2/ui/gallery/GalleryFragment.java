package com.example.prueba2.ui.gallery;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.prueba2.Producto;
import com.example.prueba2.databinding.FragmentGalleryBinding;
import com.google.gson.Gson;

import java.util.Arrays;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private EditText nombre, precio, descripcion, imagen;
    private Spinner spinner;
    private String[] opt = {
            "beers", "wines", "food", "snaks" // Por comodidad lo dejamos asi...
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        spinner = binding.spinner;
        spinner.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, opt));
        nombre = binding.txtNombreProducto;
        precio = binding.txtPrecio;
        descripcion = binding.txtDescripcionProducto;
        imagen = binding.txtImagenProducto;

        TextView btn_reg = (TextView) binding.btnRegistrarProducto;
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar(v);
            }
        });

        //final TextView textView = binding.textGallery;
        //galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void registrar(View v) {
        String selected = spinner.getSelectedItem().toString();
        String name = nombre.getText().toString();
        double price = Double.parseDouble(precio.getText().toString());
        String description = descripcion.getText().toString();
        String image = imagen.getText().toString();
        SharedPreferences preferences = getActivity().getSharedPreferences("key_productos", getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = preferences.getString(selected, null);
        Producto[] productos = gson.fromJson(json, Producto[].class);
        // productos[0] = new Producto(name, price, image, description);
        Producto[] tmp = new Producto[productos.length + 1];
        for (int i = 0; i < productos.length; i++) {
            tmp[i] = productos[i];
        }
        productos = tmp;
        productos[productos.length-1] = new Producto(name, price, image, description);
        json = gson.toJson(productos);
        editor.putString(selected, json);
        editor.commit();

        Toast.makeText(getContext(), "Producto registrado", Toast.LENGTH_SHORT).show();

    }
}