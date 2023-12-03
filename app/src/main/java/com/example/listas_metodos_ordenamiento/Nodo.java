package com.example.listas_metodos_ordenamiento;

//Hernandez Garcia Erick Emmanuel I3A
//Clase que permite darle la estructura al nodo.
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
