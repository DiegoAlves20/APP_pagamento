package br.senai.sp.cotia.appfolhadepagamento;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    private Spinner spinnerPlano;

    //public void onCreate{
//
//        spinnerPlano  = (Spinner) rootView.findViewById(R.id.sp_PlanoSaude);
//        String[] lsPeso = getResources().getStringArray(R.array.planos);
//
//        spinnerPlano.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.spinner_layout, lsPeso));



   // }

}