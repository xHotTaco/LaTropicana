package com.example.prueba2.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.prueba2.Producto;
import com.example.prueba2.databinding.FragmentHomeBinding;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.lang.reflect.Type;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private TextView txt_pedidos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        txt_pedidos = (TextView) binding.txtPedidosRes;

        SharedPreferences pedidos = getActivity().getSharedPreferences("pedido", getActivity().MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pedidos.getString("arr_productos", null);
        Type type = new com.google.gson.reflect.TypeToken<Producto[]>(){}.getType();
        Producto[] productos = gson.fromJson(json, type);
        if (productos == null) {
            txt_pedidos.setText("No hay productos en el carrito");
        }
        else {
            String resultado = "";
            for (int i = 0; i < productos.length; i++) {
                resultado += "Producto: " + productos[i].getNombre() + "\n";
                resultado += "Precio: " + productos[i].getPrecio() + "\n";
                resultado += "Detalles: " + productos[i].getDescripcion() + "\n";
                resultado += "--------------------------\n";
            }
            txt_pedidos.setText(resultado);
        }

        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}