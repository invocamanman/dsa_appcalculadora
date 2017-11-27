package com.example.jesus.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Jesus on 27/11/2017.
 */

public class TractamentOperacio extends AppCompatActivity {

    TextView txview;
    Button modificar;
    Button borrar;
    int index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tractamentoperacio);

        Bundle intentdata = getIntent().getExtras();
        final String txt = intentdata.getString("OP");
        index = intentdata.getInt("index");
        txview = (TextView)findViewById(R.id.Operacio);
        txview.setText(txt);

        modificar= (Button) findViewById(R.id.Modificar);
        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intres = getIntent();
                intres.putExtra("modb", 1);
                intres.putExtra("index", index);
                intres.putExtra("oper", txt);
                setResult(RESULT_OK, intres);
                finish();

            }
        });
        borrar= (Button) findViewById(R.id.Borrar);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intres = getIntent();
                intres.putExtra("modb", 0);
                intres.putExtra("index", index);
                intres.putExtra("oper", txt);
                setResult(RESULT_OK, intres);
                finish();

            }
        });


    }
}
