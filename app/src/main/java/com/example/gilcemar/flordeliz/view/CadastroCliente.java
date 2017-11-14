package com.example.gilcemar.flordeliz.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.gilcemar.flordeliz.R;

public class CadastroCliente extends AppCompatActivity implements View.OnClickListener{
    private Button botaoVoltarCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        botaoVoltarCliente = findViewById(R.id.botaoVoltarCliente);
        botaoVoltarCliente.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
