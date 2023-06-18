package com.example.prueba2.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.prueba2.Empleado;
import com.example.prueba2.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private EditText nombre, edad, rfc;
    private RadioButton mesero, cocinero, bartender;

    public static Empleado empleado;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        nombre = (EditText) binding.txtNombreEmpleado;
        edad = (EditText) binding.txtEdadEmpleado;
        rfc = (EditText) binding.txtRFC;
        mesero = (RadioButton) binding.radioMesero;
        cocinero = (RadioButton) binding.radioCocinero;
        bartender = (RadioButton) binding.radioBartender;

        TextView btn_reg = (TextView) binding.btnRegistrarEmpleado;
        btn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar(v);
            }
        });

        //final TextView textView = binding.textSlideshow;
        //slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void registrar(View view) {
        String name = nombre.getText().toString();
        int age = Integer.parseInt(edad.getText().toString());
        String rfc = this.rfc.getText().toString();
        String puesto = "";
        if (name.isEmpty() || age == 0 || rfc.isEmpty()) {
            return;
        }
        if (mesero.isChecked()) {
            puesto = "Mesero";
        } else if (cocinero.isChecked()) {
            puesto = "Cocinero";
        } else if (bartender.isChecked()) {
            puesto = "Bartender";
        } else {
            return;
        }
        empleado = new Empleado(name, name + String.valueOf(age), "Empleado", true, name, age, rfc, puesto);
    }
}