package com.example.jesus.calculadora;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewDebug;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    Button igual;
    Button historial;
    Button clear;
    int operacion = 1;//suma per defecte
    int op1;
    int op2;
    TextView resultat;
    ArrayList<String> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = new ArrayList<String>();


        //spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.operaciones_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //Igual
        igual = (Button) findViewById(R.id.Igual);
        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                    EditText txtent= (EditText) findViewById(R.id.Op1);
                    EditText txtent2= (EditText) findViewById(R.id.Op2);
                    String aux =txtent.getText().toString();
                    String aux2 =txtent2.getText().toString();
                    if ((aux.isEmpty())||(aux2.isEmpty())){
                        Toast.makeText(MainActivity.super.getApplicationContext(),"Cal indicar els dos valors numérics",Toast.LENGTH_LONG).show();
                        return;
                    }
                try {
                    op1= Integer.parseInt(aux);
                    op2= Integer.parseInt(aux2);
                } catch (NumberFormatException e) {
                    Toast.makeText(MainActivity.super.getApplicationContext(),"Els valors ha de ser valors numèrics enters",Toast.LENGTH_LONG).show();
                    return;

                }

                resultat= (TextView) findViewById(R.id.Resultat);
                int result;
                switch (operacion) {
                    case 1:
                        result =op1+op2;
                        resultat.setText(Integer.toString(result));
                        lista.add(op1+" + "+ op2 + " = "+ result);
                        break;
                    case 2:
                        result =op1-op2;
                        resultat.setText(Integer.toString(result));
                        lista.add(op1+" - "+ op2 + " = "+ result);
                        break;
                    case 3:
                        result =op1*op2;
                        resultat.setText(Integer.toString(result));
                        lista.add(op1+" * "+ op2 + " = "+ result);
                        break;
                    case 4:
                        if (op2 ==0)
                        {
                            Toast.makeText(MainActivity.super.getApplicationContext(),"no es pot dividir entre 0",Toast.LENGTH_LONG).show();
                        }
                        else {
                            result = op1 / op2;
                            resultat.setText(Integer.toString(result));
                            lista.add(op1+" / "+ op2 + " = "+ result);
                        }
                        break;

                }
            }
        });
        //clear button
        clear = (Button) findViewById(R.id.Clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText txtent= (EditText) findViewById(R.id.Op1);
                EditText txtent2= (EditText) findViewById(R.id.Op2);
                TextView txtent3= (TextView) findViewById(R.id.Resultat);
                txtent.setText("0");
                txtent2.setText("0");
                txtent3.setText("0");
            }
        });

        historial = (Button) findViewById(R.id.Historial);
        historial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent inb1= new Intent(MainActivity.this, Historial.class);
                inb1.putExtra("valor1", lista);
                startActivityForResult(inb1, 100);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 100)&&(resultCode== Activity.RESULT_OK)){

            Bundle resultsAct = data.getExtras();
            lista = resultsAct.getStringArrayList("valor12");
            String aux = resultsAct.getString("modif");
            if(!aux.isEmpty())
            {
                String[] aux2 = aux.split(" ");
                EditText txtent= (EditText) findViewById(R.id.Op1);
                EditText txtent2= (EditText) findViewById(R.id.Op2);
                TextView txtent3= (TextView) findViewById(R.id.Resultat);
                txtent.setText(aux2[0]);
                txtent2.setText(aux2[2]);
                txtent3.setText(aux2[4]);

                Spinner spinner = findViewById(R.id.spinner);
                int num = 0 ;
                switch (aux2[1]){
                    case "+":
                        num =0;
                        break;
                    case "-":
                        num =1;
                        break;
                    case "*":
                        num =2;
                        break;
                    case "/":
                        num =3;
                        break;
                }
                spinner.setSelection(num);





            }
        }

    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        operacion = i+1;//1=suma, 2 = resta, 3= multiplicacio, 4 = divisio

    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }







}
