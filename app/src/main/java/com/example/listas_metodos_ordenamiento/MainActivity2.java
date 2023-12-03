package com.example.listas_metodos_ordenamiento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    Button button_B, button_Q;
    TextView text_metodo, textaaa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         button_B = findViewById(R.id.button_B);
         button_Q = findViewById(R.id.button_Q);
        text_metodo = findViewById(R.id.text_metodo);
        textaaa = findViewById(R.id.textView2);
        button_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                text_metodo.setText("Metodo Burbuja.");
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                intent.putExtra("result", text_metodo.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        button_Q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_metodo.setText("Metodo Quicksort.");
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                intent.putExtra("result", text_metodo.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });


    }
    void cambio(TextView text_metodo){
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        intent.putExtra("result", text_metodo.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}