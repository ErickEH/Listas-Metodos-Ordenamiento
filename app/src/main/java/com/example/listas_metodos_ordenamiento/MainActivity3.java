package com.example.listas_metodos_ordenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//Hernandez Garcia Erick Emmanuel I3A
public class MainActivity3 extends AppCompatActivity {

    private Nodo inicio =null;
    private Nodo ultimo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        EditText plain_Valor =findViewById(R.id.plain_Valor);
        TextView textView = findViewById(R.id.textView3);
        TextView outputTextView = findViewById(R.id.outputTextView);
        String condicion1, condicion2;
        String simple = "Lista simple.";
        Intent condicion = getIntent();
        Bundle b = condicion.getExtras();

            condicion1 = b.getString("ListaCondicion");
            condicion2 = b.getString("ListaCondicion2");
            textView.setText(condicion1);

        Button button_A = findViewById(R.id.button_A);
        Button button_I = findViewById(R.id.botton_I);
        Button button_ord = findViewById(R.id.botton_ord);
        button_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valor =Integer.parseInt(plain_Valor.getText().toString());
                agregarNodo(valor);
                plain_Valor.setText("");
                actualizarSalida();
            }
        });
        button_I.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView.getText().toString().equals("Lista simple.")){
                    Toast.makeText(MainActivity3.this, "No se puede Invertir pues es una lista simple", Toast.LENGTH_SHORT).show();
                }else if(textView.getText().toString().equals("Lista Doblemente Enlazada.")){
                    outputTextView.setText("");
                    actualizarSalidaalreves();
                }else if(textView.getText().toString().equals("Lista Circular.")){
                    outputTextView.setText("");
                    actualizarSalidaalreves();

                }
            }
        });
        button_ord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(condicion2.equals("Metodo Burbuja.")) {
                    inicio = burbuja(inicio);
                    actualizarSalida();
                }else if(condicion2.equals("Metodo Quicksort.")){
                    Nodo fin = ultimo;
                    inicio = quicksort(inicio, fin);
                }
            }
        });
    }
    private void actualizarSalida() {
        StringBuilder resultado = new StringBuilder();
        Nodo imp = inicio;
        while (imp != null) {
            resultado.append(imp.dato).append("\n");
            imp = imp.siguiente;
            if(imp == inicio){
                break;
            }
        }
        TextView outputTextView = findViewById(R.id.outputTextView);
        outputTextView.setText(resultado.toString());
    }
    private void actualizarSalidaalreves() {
        StringBuilder resultado = new StringBuilder();
        Nodo imp = ultimo;
        while (imp != null) {
            resultado.append(imp.dato).append("\n");
            imp = imp.anterior;
            if(imp == ultimo){
                break;
            }
        }
        TextView outputTextView = findViewById(R.id.outputTextView);
        outputTextView.setText(resultado.toString());
    }
    public void agregarNodo(int valor){
        Nodo nuevoNodo = new Nodo(valor);
        if(inicio == null){
            inicio = nuevoNodo;
            ultimo = nuevoNodo;
        }else{
            ultimo.siguiente=nuevoNodo;
            nuevoNodo.anterior=ultimo;
            ultimo=nuevoNodo;
        }

    }
    public Nodo burbuja(Nodo inicio){
        boolean intercambio;
        do{
            intercambio=false;
            Nodo actual = inicio;
            Nodo previo = null;
            while(actual != null && actual.siguiente != null){
                if(actual.dato > actual.siguiente.dato){
                    if(previo != null){
                        previo.siguiente=actual.siguiente;
                        actual.siguiente=actual.siguiente.siguiente;
                        previo.siguiente.siguiente=actual;
                        intercambio=true;
                    }else{
                        inicio=actual.siguiente;
                        actual.siguiente=inicio.siguiente;
                        inicio.siguiente=actual;
                        intercambio=true;
                    }
                }
                previo=actual;
                actual=actual.siguiente;
            }
        }while (intercambio);
        return inicio;
    }
    public Nodo quicksort(Nodo inicio, Nodo fin) {
        if (inicio == null || inicio == fin) {
            return inicio;
        }

        Nodo nuevoFin = null;
        Nodo nuevoInicio = null;
        Nodo pivote = partir(inicio, fin, nuevoFin, nuevoInicio);

        if (nuevoInicio != pivote) {
            Nodo temp = nuevoInicio;
            while (temp.siguiente != pivote) {
                temp = temp.siguiente;
            }
            temp.siguiente = null;

            nuevoInicio = quicksort(nuevoInicio, temp);
            temp = nuevoInicio;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = pivote;
        }

        pivote.siguiente = quicksort(pivote.siguiente, nuevoInicio);

        return nuevoInicio;
    }
    public Nodo partir(Nodo inicio, Nodo fin, Nodo nuevoFin, Nodo nuevoInicio) {
        Nodo pivote = fin;
        Nodo previo = null;
        Nodo current = inicio;
        Nodo tail = pivote;

        while (current != pivote) {
            if (current.dato < pivote.dato) {
                if (nuevoInicio == null) {
                    nuevoInicio = current;
                }
                previo = current;
                current = current.siguiente;
            } else {
                if (previo != null) {
                    previo.siguiente = current.siguiente;
                }
                Nodo temp = current.siguiente;
                current.siguiente = null;
                tail.siguiente = current;
                tail = current;
                current = temp;
            }
        }

        if (nuevoInicio == null) {
            nuevoInicio = pivote;
        }

        nuevoFin = tail;

        return pivote;
    }

}
