package com.example.prueba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Intent intent;
                if (employeeRegistry()) {
                    intent = new Intent(MainActivity.this, MenuLateralActivity.class);
                } else if (userRegistry()) {
                    intent = new Intent(MainActivity.this, LoginActivity.class); // TODO: change later
                } else {
                    intent = new Intent(MainActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 3000);
    }

    private boolean employeeRegistry() {
        SharedPreferences preferences = getSharedPreferences(
                "employee.dat", MODE_PRIVATE
        );
        return preferences.getBoolean("exists", false);
    }

    private boolean userRegistry() {
        SharedPreferences preferences = getSharedPreferences(
                "user.dat", MODE_PRIVATE
        );
        return preferences.getBoolean("exists", false);
    }

    /*
    public void login(View view) {
        String input = String.valueOf(uname.getText());
        if (input.equals("mesa")) {
            Intent intent = new Intent(this, RegistroActivity.class);
            startActivity(intent);
        }
        else if ((input.equals("admin") || input.equals("empleado")) && passwd.getText().toString().equals("123")) {
            Intent intent = new Intent(this, MenuLateralActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
     */

    public void irRegistro(View view){
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }

}
