package com.example.hw2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

public class ActivityB extends AppCompatActivity {
    String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        Intent intent = getIntent();
        message = intent.getStringExtra(FragmentA.EXTRA_MESSAGE);
        System.out.println("MESSAGEEEEEEEE "+message);

        Bundle bundle = new Bundle();
        bundle.putString("message", message);

        FragmentB fb = new FragmentB();
        fb.setArguments(bundle);
        System.out.println("MESSAGEEEEEEEE "+bundle.getString("message"));
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.fragment_b, fb).commit();

    }

}
