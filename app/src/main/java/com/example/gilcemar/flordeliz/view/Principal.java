package com.example.gilcemar.flordeliz.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gilcemar.flordeliz.R;

public class Principal extends AppCompatActivity implements View.OnClickListener{

    private Button botaoCadCalc;
    private Button botaoCadCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        botaoCadCalc = findViewById(R.id.cadCalc);
        botaoCadCliente = findViewById(R.id.cadCliente);

        botaoCadCalc.setOnClickListener(this);
        botaoCadCliente.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        final int id = v.getId();
        switch (v.getId()){
            case R.id.cadCalc:{
                Intent it = new Intent(Principal.this, CadastroCalcado.class);
                startActivity(it);
                break;
            }
            case R.id.cadCliente:{
                Intent it = new Intent(Principal.this, CadastroCliente.class);
                startActivity(it);
                break;
            }
        }



    }
}
