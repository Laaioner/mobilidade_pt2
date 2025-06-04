package com.example.listagempersonalizada;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView listaFrutas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        String[] frutas = {
                "Maçã", "Banana", "Laranja", "Abacaxi", "Uva",
                "Morango", "Manga", "Melancia", "Kiwi", "Pêssego"
        };

        listaFrutas = findViewById(R.id.listaFrutas);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                frutas
        );

        listaFrutas.setAdapter(adapter);
    }
}
