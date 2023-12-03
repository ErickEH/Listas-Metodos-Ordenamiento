package com.example.listas_metodos_ordenamiento;

import android.widget.TextView;

public class Nodo {
    public int dato;
    public Nodo siguiente;
    public Nodo anterior;

    public Nodo(int valor){
        dato = valor;
        siguiente = null;
        anterior = null;
    }
}
