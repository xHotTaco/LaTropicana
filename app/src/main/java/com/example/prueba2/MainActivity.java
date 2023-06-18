package com.example.prueba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Producto[] beers, wines, food, snaks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        beers = new Producto[6];
        wines = new Producto[4];
        food = new Producto[3];
        snaks = new Producto[1];
        createDefaultProducts();
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

    private void createDefaultProducts() {
        SharedPreferences preferences = getSharedPreferences(
                "key_productos", MODE_PRIVATE
        );
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = preferences.getString("beers", null);
        Producto[] productos = gson.fromJson(json, Producto[].class);
        if (productos == null) {
            beers[0] = new Producto("Corona extra", 24.53, "https://hebmx.vtexassets.com/arquivos/ids/604983-800-800?v=638218521196070000&width=800&height=800&aspect=true", "355ml");
            beers[1] = new Producto("Modelo especial", 28.33,  "https://lapencavinos.com/wp-content/uploads/2019/10/modelo-especial.png", "355ml");
            beers[2] = new Producto("Heineken", 24.53,  "https://www.heineken.com/media-us/01pfxdqq/heineken-original-bottle.png?quality=85", "355ml");
            beers[3] = new Producto("Victoria", 24.53,  "https://lapencavinos.com/wp-content/uploads/2019/10/victoria-355-ml.png", "355ml");
            beers[4] = new Producto("Barrilito", 24.53,  "https://lapencavinos.com/wp-content/uploads/2019/10/barrilito.png", "355ml");
            beers[5] = new Producto("Pacifico clara", 24.53,  "https://www.trajinerasxochimilco.com.mx/wp-content/uploads/2020/10/pacifico.png", "355ml");
            wines[0] = new Producto("Black & white", 499.99, "https://www.lanaval.com.mx/107740-product_default/whisky-black-and-white-700-ml.jpg", "700ml");
            wines[1] = new Producto("Jack Daniels", 519,  "https://www.vinasa.mx/wp-content/uploads/2021/09/Jack-daniels-700ml.png" , "700ml");
            wines[2] = new Producto("Buchanans", 1300,  "https://www.lanaval.com.mx/100970-product_default/whisky-buchanan-s-deluxe-12-anos-1-litro.jpg", "700ml");
            wines[3] = new Producto("El tequileno", 950, "https://static.wixstatic.com/media/1bbfe3_e60a4d56e76342b98955ff7090d06fa8~mv2.png/v1/fill/w_1080,h_1080,al_c/1bbfe3_e60a4d56e76342b98955ff7090d06fa8~mv2.png", "750ml");
            food[0] = new Producto("Hamburguesa", 59, "https://static.vecteezy.com/system/resources/previews/021/952/562/original/tasty-hamburger-on-transparent-background-png.png", "Clasica");
            food[1] = new Producto("Pizza", 21, "https://images.fineartamerica.com/images/artworkimages/medium/1/pizza-slice-diane-diederich-transparent.png", "Peperoni - 1 pieza");
            food[2] = new Producto("Hot dog", 30, "https://i.pinimg.com/736x/35/54/fe/3554fe6af3b952a2199246d9b2eab77e--hot-dogs-simple.jpg", "Clasico - 1 pieza");
            snaks[0] = new Producto("Papas a la francesa", 49, "https://images.ctfassets.net/l5fkpck1mwg3/5Ih3U85mGJXFzfQrfe9yP4/334041e07865621f88f687c6a5291463/Appetizers_French_Fries.png", "Grandes");
            String jsonBeers = gson.toJson(beers);
            String jsonWines = gson.toJson(wines);
            String jsonFood = gson.toJson(food);
            String jsonSnaks = gson.toJson(snaks);
            editor.putString("beers", jsonBeers);
            editor.putString("wines", jsonWines);
            editor.putString("food", jsonFood);
            editor.putString("snaks", jsonSnaks);
            editor.commit();
        }
    }

}
