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
                    intent = new Intent(MainActivity.this, MenuCliente.class);
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

}
