package com.example.cityweatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_day).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFiverrWork("day");
            }
        });

        findViewById(R.id.btn_night).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startFiverrWork("night");
            }
        });

    }

    private void startFiverrWork(String theme) {
        Intent intent = new Intent(this, FiverrWorkActivity.class);
        intent.putExtra("Theme", theme);
        startActivity(intent);
    }
}
