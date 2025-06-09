package com.example.layouts;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityLinearLayout extends AppCompatActivity {

    EditText editNome, editEmail;
    RadioButton radioCurso,radioGraduacao, radioPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_linear_layout);

        editNome = findViewById(R.id.editNome);
        editEmail = findViewById(R.id.editEmail);
        radioCurso = findViewById(R.id.radioCurso);
        radioGraduacao= findViewById(R.id.radioGraduacao);
        radioPos = findViewById(R.id.radioPos);

    }
        public void abrirToast(View view){

        String nomeDigitado = editNome.getText().toString();
        String emailDigitado = editNome.getText().toString();
        String curso;

            if (radioCurso.isChecked()) {
                curso = "Curso Técnico";
            } else if (radioGraduacao.isChecked()) {
                curso = "Graduação";
            } else if (radioPos.isChecked()) {
                curso = "Pós";
            } else {
                curso = "Não selecionado";
            }

            String mensagem = "Nome: " + nomeDigitado  + " Email: " + emailDigitado  + " Curso: " + curso;

            Toast.makeText(getApplicationContext(), mensagem, Toast.LENGTH_LONG).show();

        /*
        Toast toast = new Toast(getApplicationContext() );
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView("ação");
        toast.show();
        */

        /*
            Toast.makeText(
                    getApplicationContext(),
                    "Ação realizada com sucesso",
                    Toast.LENGTH_LONG
            ).show();

         */
    }

}