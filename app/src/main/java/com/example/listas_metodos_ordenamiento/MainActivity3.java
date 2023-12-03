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
        //La conexión entre el .java y xml de los botones y TextView
        EditText plain_Valor =findViewById(R.id.plain_Valor);
        TextView textView = findViewById(R.id.textView3);
        TextView outputTextView = findViewById(R.id.outputTextView);

        //Se crea dos String y un intent, con estos tres se guardara lo que se envio de MainActivity
        String condicion1, condicion2;
        Intent condicion = getIntent();
        //Con el Bundle se obtiene ambos datos y lo almacena.
        Bundle b = condicion.getExtras();

            condicion1 = b.getString("ListaCondicion");
            condicion2 = b.getString("ListaCondicion2");
            textView.setText(condicion1);

        Button button_A = findViewById(R.id.button_A);
        Button button_I = findViewById(R.id.botton_I);
        Button button_ord = findViewById(R.id.botton_ord);
        //Con esta funcion permite agregar el dato convirtiendo de EditText a String y por ultimo a int
        button_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int valor =Integer.parseInt(plain_Valor.getText().toString());
                agregarNodo(valor);
                plain_Valor.setText("");
                actualizarSalida();
            }
        });

        /*Con este boton se compara La condicion1 que es el tipo de lista que el usuario eligio
        y si lo permite el tipo de lista imprimira los datos al revés*/
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

        //Con este boton se compara condicion2 con los 2 metodos (burbuja o quicksort)
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

    //Con esta función modificia el outputTextView e imprime todos los datos guardados
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

    //Cumple la misma función que el anterior con la diferencia de que lo imprime de derecha a izquierda
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
    //con este metodo se llama a la clase Nodo y permite la creación y almacenamiento de los datos
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

    //Permite el ordenamiento de la lista por el metodo burbuja
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

    //Permite el ordenamiento de la lista por el metodo quicksort
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

    //Sirve como complemento de la funcion quicksort
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
