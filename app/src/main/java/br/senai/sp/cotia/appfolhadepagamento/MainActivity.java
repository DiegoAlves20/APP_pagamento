package br.senai.sp.cotia.appfolhadepagamento;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    private TextView valorSalBruto, valorPlano, valorVT, valorVR, valorVA, descINSS, descIRRF, porcDesc, Tdesconto , salaLiquido;
    private EditText salBruto, nDepen;
    private Spinner spnSP;
    private RadioGroup radioVA, radioVR, radioVT;
    private Button enviar, refazer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinnerPS = (Spinner) findViewById(R.id.sp_PlanoSaude);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planos, android.R.layout.simple_dropdown_item_1line);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPS.setAdapter(adapter);

        salBruto = findViewById(R.id.edit_salBruto);

        nDepen = findViewById(R.id.edit_ndepen);

        spnSP = findViewById(R.id.sp_PlanoSaude);

        radioVA = findViewById(R.id.rgedit_valeA);
        radioVR = findViewById(R.id.rgedit_valeR);
        radioVT = findViewById(R.id.rgedit_valeT);


        enviar = findViewById(R.id.bt_enviar);
        refazer = findViewById(R.id.bt_refazer);

        valorSalBruto = findViewById(R.id.vlr_salBruto);
        valorPlano = findViewById(R.id.vlr_valorPlano);

        valorVT = findViewById(R.id.vlr_ValorVT);
        valorVA = findViewById(R.id.vlr_ValorVA);
        valorVR = findViewById(R.id.vlr_ValorVR);

        descINSS = findViewById(R.id.vlr_descINSS);
        descIRRF = findViewById(R.id.vlr_descIRRF);
        Tdesconto = findViewById(R.id.vlr_totalDesc);

        porcDesc = findViewById(R.id.vlr_PorceDesc);

        salaLiquido = findViewById(R.id.vlr_salLiquido);


        enviar.setOnClickListener(view -> {

            double salarioB = Double.parseDouble(salBruto.getText().toString());
            double dependentes = Double.parseDouble(nDepen.getText().toString());
            double plnS = 0;




                switch (spinnerPS.getSelectedItemPosition()) {

                    case 0:
                        plnS = 0;
                        break;
                    case 1:
                        if (salarioB <= 3000.00) {
                            plnS = 60.00;
                        } else {
                            plnS = 80.00;
                        }
                        break;
                    case 2:
                        if (salarioB <= 3000.00) {
                            plnS = 80.00;
                        } else {
                            plnS = 110.00;
                        }
                        break;
                    case 3:
                        if (salarioB <= 3000.00) {
                            plnS = 95.00;
                        } else {
                            plnS = 135.00;
                        }
                        break;
                    case 4:
                        if (salarioB <= 3000.00) {
                            plnS = 130.00;
                        } else {
                            plnS = 180.00;
                        }
                        break;
                }
                valorSalBruto.setText(salarioB + "");
                valorPlano.setText(plnS + "");

                double valeTrans = 0;
                double valeRefei = 0;
                double valeAlime = 0;

                switch (radioVT.getCheckedRadioButtonId()) {
                    case R.id.rbVTs:
                        valeTrans = salarioB * 0.06;
                        break;
                    case R.id.rbVTn:
                        valeTrans = 0;
                        break;
                }
                valorVT.setText(valeTrans + "");

                switch (radioVR.getCheckedRadioButtonId()) {
                    case R.id.rbVRs:
                        if (salarioB <= 3000.00) {
                            valeRefei = 2.60 * 22;
                        } else if (salarioB <= 5000.00) {
                            valeRefei = 3.65 * 22;
                        } else {
                            valeRefei = 6.50 * 22;
                        }
                        break;
                    case R.id.rbVRn:
                        valeRefei = 0;
                        break;
                }
                valorVR.setText(valeRefei + "");
                switch (radioVA.getCheckedRadioButtonId()) {
                    case R.id.rbVAs:
                        if (salarioB <= 3000.00) {
                            valeAlime = 15.00;
                        } else if (salarioB <= 5000.00) {
                            valeAlime = 25.00;
                        } else {
                            valeAlime = 35.00;
                        }
                        break;
                    case R.id.rbVAn:
                        valeAlime = 0;
                        break;
                }
                valorVA.setText(valeAlime + "");

                double inss = 0;
                if (salarioB <= 1212.00) {
                    inss = (0.075 * salarioB) - 0.00;
                } else if (salarioB <= 2427.35) {
                    inss = (0.09 * salarioB) - 18.18;
                } else if (salarioB <= 3641.03) {
                    inss = (0.12 * salarioB) - 91.01;
                } else if (salarioB <= 7087.22) {
                    inss = (0.14 * salarioB) - 163.00;
                } else {
                    inss = 829.0;
                }
                descINSS.setText(inss + "");
                descINSS.setText(String.format("%.2f", inss));

                double baseCal = salarioB - inss - (189.59 * dependentes);
                double irrf = 0;
                if (baseCal <= 1903.98) {

                } else if (baseCal <= 2826.65) {
                    irrf = (0.075 * baseCal) - 142.80;

                } else if (baseCal <= 3751.05) {
                    irrf = (0.15 * baseCal) - 354.80;

                } else if (baseCal <= 4664.68) {
                    irrf = (0.225 * baseCal) - 636.13;

                } else {
                    irrf = (0.275 * baseCal) - 869.36;

                }
                descIRRF.setText(irrf + "");
                descIRRF.setText(String.format("%.2f", irrf));

                double totalDesc = plnS + valeTrans + valeAlime + valeRefei + inss + irrf;
                Tdesconto.setText(totalDesc + "");
                Tdesconto.setText(String.format("%.2f", totalDesc));

                double salaLiq = salarioB - plnS - valeTrans - valeAlime - valeRefei - inss - irrf;
                salaLiquido.setText(salaLiq + "");
                salaLiquido.setText(String.format("%.2f", salaLiq));

                double desconto = (1 - salaLiq / salarioB) * 100;
                porcDesc.setText(desconto + "");
                porcDesc.setText(String.format("%.2f", desconto));


        });


    }

}