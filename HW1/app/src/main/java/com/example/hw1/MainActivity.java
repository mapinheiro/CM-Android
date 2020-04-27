package com.example.hw1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT= "com.example.teclado.EXTRA_TEXT";
    TextView txt;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btncard, btnast;
    ImageButton delete, call;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = (TextView) findViewById(R.id.textview_number);
        btn1 = (Button) findViewById(R.id.one);
        btn2 = (Button) findViewById(R.id.two);
        btn3 = (Button) findViewById(R.id.three);
        btn4 = (Button) findViewById(R.id.four);
        btn5 = (Button) findViewById(R.id.five);
        btn6 = (Button) findViewById(R.id.six);
        btn7 = (Button) findViewById(R.id.seven);
        btn8 = (Button) findViewById(R.id.eight);
        btn9 = (Button) findViewById(R.id.nine);
        btn0 = (Button) findViewById(R.id.zero);
        btncard = (Button) findViewById(R.id.cardinal);
        btnast = (Button) findViewById(R.id.ast);
        delete = (ImageButton) findViewById(R.id.btn_del);
        call = (ImageButton) findViewById(R.id.btn_call);

        Intent intent = getIntent();
        String number = intent.getStringExtra(MainActivity.EXTRA_TEXT);
        txt.setText(number);

        btn1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openSpeedDial();
                return true;
            }
        });
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                txt.append("1");
            }
        });
        btn2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openSpeedDial();
                return true;
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                txt.append("2");
            }
        });
        btn3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openSpeedDial();
                return true;
            }
        });
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                txt.append("3");
            }
        });
        btn4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                openSpeedDial();
                return true;
            }
        });
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                txt.append("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                txt.append("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                txt.append("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                txt.append("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                txt.append("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                txt.append("9");
            }
        });
        btn0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                txt.append("0");
            }
        });
        btn0.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                txt.append("+");
                return true;
            }
        });
        btnast.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                txt.append("*");
            }
        });
        btncard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                txt.append("#");
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txtTmp = txt.getText().toString();
                if(txt.length()>0){
                    txt.setText(txtTmp.substring(0, txtTmp.length()-1));
                }
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone_number = txt.getText().toString();
                dialPhoneNumber(phone_number);
            }
        });

    }
    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void openSpeedDial(){
        String number = txt.getText().toString();

        Intent intent = new Intent(this, SpeedDial.class);
        intent.putExtra(EXTRA_TEXT, number);
        startActivity(intent);
    }
}
