package com.example.prueba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText uname, passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        uname = (EditText) findViewById(R.id.txt_user);
        passwd = (EditText) findViewById(R.id.txt_password);
    }

    public void login(View view) {
        String input = String.valueOf(uname.getText());
        if (input.equals("mesa")) {
            Intent intent = new Intent(this, registroActivity.class);
            startActivity(intent);
        }
        else if ((input.equals("admin") || input.equals("empleado")) && passwd.getText().toString().equals("123")) {
            Intent intent = new Intent(this, MenuLateralActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    public void irRegistro(View view){
        Intent intent = new Intent(this, registroActivity.class);
        startActivity(intent);
    }

}
