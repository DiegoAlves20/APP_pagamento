package br.senai.sp.cotia.appfolhadepagamento;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinnerPS = (Spinner) findViewById(R.id.sp_PlanoSaude);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planos, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPS.setAdapter(adapter);
       
    }











}