package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SpeedDial extends AppCompatActivity {
    public static final String EXTRA_TEXT= "com.example.teclado.EXTRA_TEXT";
    EditText nome, num;
    Button cancel, save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_dial);

        nome = (EditText) findViewById(R.id.txt_nome);
        num = (EditText) findViewById(R.id.txt_number);
        cancel = (Button) findViewById(R.id.btn_cancel);
        save = (Button) findViewById(R.id.btn_guardar);

        Intent intent = getIntent();
        String number = intent.getStringExtra(MainActivity.EXTRA_TEXT);

        num.setText(number);

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String number = num.getText().toString();
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                intent.putExtra(EXTRA_TEXT, number);
                startActivity(intent);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
