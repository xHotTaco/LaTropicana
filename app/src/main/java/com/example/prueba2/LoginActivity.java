package com.example.prueba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prueba2.ui.slideshow.SlideshowFragment;

public class LoginActivity extends AppCompatActivity {
    private EditText uname, passwd;

    private Empleado[] employees = new Empleado[5];
    private Cliente[] mesas = new Cliente[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        uname = (EditText) findViewById(R.id.txt_user);
        passwd = (EditText) findViewById(R.id.txt_password);
        employees[0] = new Empleado("admin", "123", "Empleado", true);
        employees[1] = new Empleado("victor", "123", "Empleado", true);
        employees[2] = new Empleado("carlos", "123", "Empleado", true);
        employees[3] = new Empleado("diego", "123", "Empleado", true);
        employees[4] = SlideshowFragment.empleado; // TODO: solo un emplado por ahora
        for (int i=0; i<8; ++i) {
            mesas[i] = new Cliente("mesa" + i, "mesa", "Cliente", true);
        }
    }

    public void login(View view) {
        String usr = uname.getText().toString();
        String pass = passwd.getText().toString();
        boolean success = false;
        for (int i=0; i<employees.length; ++i) {
            if (usr.equals(employees[i].getUsername()) && pass.equals(employees[i].getPassword())) {
                saveUser(employees[i]);
                Intent empl_intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(empl_intent);
                finish();
                success = true;
                break;
            } else if (usr.equals(mesas[i].getUsername()) && pass.equals(mesas[i].getPassword())) {
                // Cliente cliente = new Cliente(usr, pass, "Cliente", true);
                saveUser(mesas[i]);
                Intent usr_intent = new Intent(LoginActivity.this, MenuCliente.class);
                startActivity(usr_intent);
                finish();
                success = true;
                break;
            }
        }
        if (!success) {
            Toast.makeText(this, "Datos incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveUser(Usuario usr) {
        SharedPreferences preferences;
        if (usr.getTipo().equals("Empleado")) {
            preferences = getSharedPreferences("employee.dat", MODE_PRIVATE);
        } else {
            preferences = getSharedPreferences("user.dat", MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username", usr.getUsername());
        editor.putString("password", usr.getPassword());
        editor.putString("tipo", usr.getTipo());
        editor.putBoolean("exists", usr.isExists());
        editor.apply();
    }

    public void irRegistro(View view){
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

}