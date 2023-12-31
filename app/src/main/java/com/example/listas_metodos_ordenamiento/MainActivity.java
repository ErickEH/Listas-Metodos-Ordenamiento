package com.example.listas_metodos_ordenamiento;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
//Hernandez Garcia Erick Emmanuel I3A
public class MainActivity extends AppCompatActivity {
    Button button_S, button_D, button_C, button_M, button_X;
    private ActivityResultLauncher<Intent> launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       //La conexión entre el .java y xml de los botones y TextView
       button_S = findViewById(R.id.button_S);
        button_D = findViewById(R.id.button_D);
        button_C = findViewById(R.id.button_C);
        button_M= findViewById(R.id.button_M);
        button_X=findViewById(R.id.button_X);
        TextView Text_vacio = findViewById(R.id.text_decision);
        TextView text_decision2 =findViewById(R.id.text_decision2);
        //esta mini función permite registrar los datos que se recuperaran del MainActivity2
        launcher= registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
            if(result.getResultCode() == RESULT_OK){
                Intent data = result.getData();
                text_decision2.setText(data.getStringExtra("result"));
            }
                }
                );
        //Los tres botones debajo pondran a Text_vacio con un una Lista dependiendo de la pulsacion
        button_S.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Text_vacio.setText("Lista simple.");
            }
        });

        button_D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Text_vacio.setText("Lista Doblemente Enlazada.");
            }
        });

        button_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Text_vacio.setText("Lista Circular.");
            }
        });

        //Con este boton se lanzara a la MainActivity2 en espera de un resultado
        button_M.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                launcher.launch(intent);
            }
        });

        /*Con este boton lo recopilado es decir, la lista que eligio el usuario y el metodo de ordenamiento
        * se le pasara al siguiente MainActivity3 para permitir La creacion y ordenamiento puesto por el usuario
        * */
        button_X.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                Bundle extras = new Bundle();
                String TextCondicion = Text_vacio.getText().toString();
                String TextCondicion2 = text_decision2.getText().toString();
                extras.putString("ListaCondicion", TextCondicion);
                extras.putString("ListaCondicion2", TextCondicion2);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }
}