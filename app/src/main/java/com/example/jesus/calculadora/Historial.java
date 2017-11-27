package com.example.jesus.calculadora;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Jesus on 27/11/2017.
 */

public class Historial  extends AppCompatActivity {

    TextView txview3;
    Button boton;
    Button cerrar;
    Button esborrar;
    String resultat;
    ArrayList<String> lista;
    private ArrayAdapter<String> adaptador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historial);



        Bundle intentdata = getIntent().getExtras();
        lista = intentdata.getStringArrayList("valor1");
        //lista=(ArrayList<String>)getIntent().getSerializableExtra("valor1");

        adaptador= new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, lista);
        ListView lisv = (ListView) findViewById(R.id.Lista1);
        lisv.setAdapter(adaptador);
        adaptador.notifyDataSetChanged();


        lisv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String operacio = (String)adapterView.getItemAtPosition(i);
                Intent tract = new Intent(view.getContext(),TractamentOperacio.class);
                tract.putExtra("OP",operacio);
                tract.putExtra("index",i);
                startActivityForResult(tract,3);
            }
        });

        cerrar = (Button) findViewById(R.id.Cerrar);
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intres = getIntent();
                intres.putExtra("valor12", lista);
                String aux="";
                intres.putExtra("modif", aux);
                setResult(RESULT_OK, intres);
                finish();

            }
        });

        esborrar = (Button) findViewById(R.id.Esborrar);
        esborrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent inb1= new Intent(view.getContext(), ConfirmarEsborrar.class);
                startActivityForResult(inb1, 2);


            }
        });





    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 2)&&(resultCode== Activity.RESULT_OK)){

            Bundle resultsAct = data.getExtras();
            int result = resultsAct.getInt("valor2");
            if(result==1)
            {
                lista.clear();
                Intent intres = getIntent();
                String aux="";
                intres.putExtra("modif", aux);
                intres.putExtra("valor12", lista);
                setResult(RESULT_OK, intres);
                finish();

            }

        }
        if ((requestCode == 3)&&(resultCode== Activity.RESULT_OK)) {

            Bundle resultsAct = data.getExtras();
            int result = resultsAct.getInt("modb");
            int index = resultsAct.getInt("index");
            String oper = resultsAct.getString("oper");
            if(result==0)
            {
                lista.remove(index);
                ListView lisv2 = (ListView) findViewById(R.id.Lista1);
                adaptador.notifyDataSetChanged();


            }
            else{

                Intent intres = getIntent();
                //ListView lisv2 = (ListView) findViewById(R.id.Lista1);
                //String aux = (String)lisv2.itgetItemAtPosition(index);
                intres.putExtra("valor12", lista);
                intres.putExtra("modif", oper);
                setResult(RESULT_OK, intres);
                finish();
            }

        }
    }
}
