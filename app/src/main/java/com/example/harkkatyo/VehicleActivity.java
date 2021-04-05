package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class VehicleActivity extends AppCompatActivity {

    String[] vehiclefuel = new String[]{"Gasoline","Diesel","Electricity"};
    String[] vehiclesize = new String[]{"Mini","Medium","Large"};
    Spinner spinner_vehiclesize;
    Spinner spinner_vehiclefuel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        spinner_vehiclefuel = findViewById(R.id.spinner_vehiclefuel);
        ArrayAdapter<String> adapter_vehiclefuel = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,vehiclefuel);
        adapter_vehiclefuel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_vehiclefuel.setAdapter(adapter_vehiclefuel);

        spinner_vehiclesize = findViewById(R.id.spinner_vehiclesize);
        ArrayAdapter<String> adapter_vehiclesize = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,vehiclesize);
        adapter_vehiclesize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_vehiclesize.setAdapter(adapter_vehiclesize);
    }

    public void saveChanges(View v){
        finish();
    }
}