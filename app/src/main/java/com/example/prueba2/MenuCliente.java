package com.example.prueba2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.prueba2.databinding.ActivityMenuClienteBinding;
import com.example.prueba2.ui.fragments.CarritoFragment;
import com.example.prueba2.ui.fragments.CuentaFragment;
import com.example.prueba2.ui.fragments.MenuFragment;

public class MenuCliente extends AppCompatActivity {

    ActivityMenuClienteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuClienteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        viewFragment(new MenuFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu:
                    viewFragment(new MenuFragment());
                    break;
                case R.id.cuenta:
                    viewFragment(new CuentaFragment());
                    break;
                case R.id.carrito:
                    viewFragment(new CarritoFragment());
                    break;
                case R.id.salir_cliente:
                    break;
            }
            return true;
        });
    }

    private void viewFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    public void logout_cliente(MenuItem item) {
        SharedPreferences preferences = getSharedPreferences("user.dat", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        Intent client_exit = new Intent(MenuCliente.this, LoginActivity.class);
        client_exit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(client_exit);
        finish();
    }
}