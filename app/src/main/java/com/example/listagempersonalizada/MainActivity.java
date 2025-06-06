package com.example.listagempersonalizada;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
    Button buttonInsere;
    EditText editText;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // ASSOCIAR VIEW A VARIAVEIS LOCAIS
        buttonInsere = findViewById(R.id.buttonInsere);
        editText = findViewById(R.id.edText);
        listView = findViewById(R.id.listView);

        db = openOrCreateDatabase("minhanotinhas", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS notas (id INTEGER PRIMARY KEY AUTOINCREMENT, txt TEXT)");

        // Handler tratamento de evento
        buttonInsere.setOnClickListener(v -> {
            insereNota(editText.getText().toString().trim());
        });

        String exemplo = "To com fome";
        ContentValues cv = new ContentValues();
        cv.put("txt", exemplo);
        db.insert("notas", null, cv);

        listView.setOnItemLongClickListener((adapterView, view, i, l) -> {
            db.delete("notas", "id=?", new String[]{Integer.toString(i + 1)});
            Toast.makeText(getApplicationContext(), "Nota deletada", Toast.LENGTH_SHORT).show();
            carregaNota();
            return true;
        });

        carregaNota();
    }

    public String insereNota(String txt) {
        ContentValues cv = new ContentValues();
        cv.put("txt", txt);
        db.insert("notas", null, cv);
        carregaNota();
        return "Nota Inserida";
    }

    public void carregaNota() {
        Cursor cursor = db.rawQuery("SELECT * FROM notas", null);
        cursor.moveToFirst(); // inicia o cursor
        ArrayList<String> notas = new ArrayList<>();

        while (!cursor.isAfterLast()) {
            int column = cursor.getColumnIndex("txt");
            notas.add(cursor.getString(column));
            cursor.moveToNext();
        }

        cursor.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                notas
        );

        listView.setAdapter(adapter);
    }
}