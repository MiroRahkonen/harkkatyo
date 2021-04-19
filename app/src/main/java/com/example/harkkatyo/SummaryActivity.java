package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SummaryActivity extends AppCompatActivity {

    protected String consumption_URL,housing_URL,vehicle_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Bundle extras = getIntent().getExtras();
        consumption_URL = extras.getString("consumption_URL");
        housing_URL = extras.getString("housing_URL");
        vehicle_URL = extras.getString("vehicle_URL");
        System.out.println(consumption_URL);
        System.out.println(housing_URL);
        System.out.println(vehicle_URL);
    }

    public void saveData(View v){
        Toast.makeText(SummaryActivity.this, "Data saved",Toast.LENGTH_SHORT).show();
        finish();
    }

    public void cancel(View v){
        finish();
    }
}