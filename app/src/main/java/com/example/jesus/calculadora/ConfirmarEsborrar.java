package com.example.jesus.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Jesus on 27/11/2017.
 */

public class ConfirmarEsborrar extends AppCompatActivity {

    Button no;
    Button si;
    ArrayList<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_esborrar);



        no= (Button) findViewById(R.id.No);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intres = getIntent();
                intres.putExtra("valor2", 0);
                setResult(RESULT_OK, intres);
                finish();

            }
        });
        si= (Button) findViewById(R.id.Si);
        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intres = getIntent();
                intres.putExtra("valor2", 1);
                setResult(RESULT_OK, intres);
                finish();

            }
        });


    }

}
