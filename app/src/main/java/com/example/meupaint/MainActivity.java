package com.example.meupaint;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class MainActivity extends AppCompatActivity {
    SimplePaint simplePaint;
    ImageView ivColorPicker,ivLinha, ivCirculo, ivRetan ;
    Button bntLimpar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
         simplePaint = findViewById(R.id.simplePaintView);
         ivColorPicker =  findViewById(R.id.ivColorPicker);
         ivLinha = findViewById(R.id.ivLinha);
         ivCirculo = findViewById(R.id.ivCirculo);
         ivRetan = findViewById(R.id.ivRetan);
         bntLimpar = findViewById(R.id.bntLimpar);

    }

    public void colorPickerSelectColor(){
        new ColorPickerDialog.Builder(this)
                .setTitle("ColorPicker Dialog")
                .setPreferenceName("MyColorPickerDialog")
                .setPositiveButton(getString(R.string.confirm),
                        new ColorEnvelopeListener() {
                            @Override
                            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                                setColor(envelope);
                            }
                        })
                .setNegativeButton(getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                .attachAlphaSlideBar(true) // the default value is true.
                .attachBrightnessSlideBar(true)  // the default value is true.
                .setBottomSpace(12) // set a bottom space between the last slidebar and buttons.
                .show();
    }

    private void setColor(ColorEnvelope envelope) {
        simplePaint.setColor(Color.valueOf(envelope.getColor()));
        ivColorPicker.setColorFilter(Color.valueOf(envelope.getColor()).toArgb());
    }

    public void circulo(View view){
        simplePaint.setMode(SimplePaint.Mode.CIRCLE);
    }

    public void quadrado (View view){
        simplePaint.setMode(SimplePaint.Mode.RECTANGLE);
    }

    public void livre(View view){
        simplePaint.setMode(SimplePaint.Mode.FREE_DRAW);
    }

    public void apagar(View view){
        simplePaint.limparTela();
    }

    public  void selecionarCor(View view){
        colorPickerSelectColor();
    }
}