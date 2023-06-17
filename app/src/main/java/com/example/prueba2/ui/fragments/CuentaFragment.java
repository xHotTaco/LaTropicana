package com.example.prueba2.ui.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prueba2.Producto;
import com.example.prueba2.R;
import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CuentaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CuentaFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CuentaFragment() {}

    public static CuentaFragment newInstance(String param1, String param2) {
        CuentaFragment fragment = new CuentaFragment();
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

    private TextView txt_total, txt_results;
    private Button btn_pagar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vistaCuenta = inflater.inflate(R.layout.fragment_cuenta, container, false);

        txt_total = (TextView) vistaCuenta.findViewById(R.id.txt_cuenta_titulo);
        txt_results = (TextView) vistaCuenta.findViewById(R.id.txt_resultado_cuenta);
        btn_pagar = (Button) vistaCuenta.findViewById(R.id.btn_pagar_cuenta);

        verPedidos();

        btn_pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarPago(v);
            }
        });

        return vistaCuenta;
    }

    private void verPedidos() {
        SharedPreferences preferences = getActivity().getSharedPreferences("cuenta", getActivity().MODE_PRIVATE);
        float total = preferences.getFloat("cuenta", 0);
        txt_total.setText("Total a pagar: " + " $: " + String.valueOf(total));

        SharedPreferences pedidos = getActivity().getSharedPreferences("pedido", getActivity().MODE_PRIVATE);
        Gson gson = new Gson();
        String json = pedidos.getString("arr_productos", null);
        Type type = new com.google.gson.reflect.TypeToken<Producto[]>(){}.getType();
        Producto[] productos = gson.fromJson(json, type);
        if (productos == null) {
            txt_results.setText("No hay productos en el carrito");
        }
        else {
            String resultado = "";
            for (int i = 0; i < productos.length; i++) {
                resultado += "Producto: " + productos[i].getNombre() + "\n";
                resultado += "Precio: " + productos[i].getPrecio() + "\n";
                resultado += "Detalles: " + productos[i].getDescripcion() + "\n";
                resultado += "--------------------------\n";
            }
            txt_results.setText(resultado);
        }
    }

    private void realizarPago(View view) {
        SharedPreferences cuenta = getActivity().getSharedPreferences("cuenta", getActivity().MODE_PRIVATE);
        SharedPreferences pedidos = getActivity().getSharedPreferences("pedido", getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = cuenta.edit();
        editor.clear();
        editor.commit();
        editor = pedidos.edit();
        editor.clear();
        editor.commit();
        Toast.makeText(getActivity(), "Tu cuenta esta en camino", Toast.LENGTH_SHORT).show();
    }
}