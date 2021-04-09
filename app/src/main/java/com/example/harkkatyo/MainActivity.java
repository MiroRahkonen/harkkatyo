package com.example.harkkatyo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);



    }

    public void gotoHousing(View v){
        Intent intent = new Intent(MainActivity.this,HousingActivity.class);
        startActivity(intent);
    }

    public void gotoVehicle(View v){
        Intent intent = new Intent(MainActivity.this,VehicleActivity.class);
        startActivity(intent);

    }

    public void gotoConsumption(View v){
        Intent intent = new Intent(MainActivity.this,ConsumptionActivity.class);
        startActivity(intent);
    }

    public void createSummary(View v){
        Intent intent = new Intent(MainActivity.this,SummaryActivity.class);
        startActivity(intent);
    }
}