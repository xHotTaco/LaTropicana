package com.example.prueba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText uname, passwd;

    private String[] users = {
            "mesa1",
            "mesa2",
            "mesa3",
            "mesa4",
            "mesa5",
            "mesa6"
    };

    private String[] employees = {
            "admin",
            "victor",
            "david",
            "carlos",
            "diego",
            "test"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        uname = (EditText) findViewById(R.id.txt_user);
        passwd = (EditText) findViewById(R.id.txt_password);
    }

    public void login(View view) {
        String usr = uname.getText().toString();
        String pass = passwd.getText().toString();
        boolean success = false;
        for (int i=0; i<employees.length; ++i) {
            if (usr.equals(employees[i]) && pass.equals("123")) {
                Empleado empleado = new Empleado(usr, pass, "Empleado", true);
                saveUser(empleado);
                Intent empl_intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(empl_intent);
                finish();
                success = true;
                break;
            } else if (usr.equals(users[i]) && pass.equals("123")) {
                Cliente cliente = new Cliente(usr, pass, "Cliente", true);
                saveUser(cliente);
                Intent usr_intent = new Intent(LoginActivity.this, MainActivity.class);
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
}