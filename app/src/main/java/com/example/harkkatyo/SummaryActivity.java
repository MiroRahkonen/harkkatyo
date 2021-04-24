package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

public class SummaryActivity extends AppCompatActivity {

    Consumption consumptionData;
    Housing housingData;
    Vehicle vehicleData;
    TextView textView_ConsumptionResult,textView_HousingResult,textView_VehicleResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        consumptionData = Consumption.getInstance();
        housingData = Housing.getInstance();
        vehicleData = Vehicle.getInstance();
        textView_ConsumptionResult = findViewById(R.id.textView_SummaryConsumption);
        textView_HousingResult = findViewById(R.id.textView_SummaryHousing);
        textView_VehicleResult = findViewById(R.id.textView_SummaryVehicle);
        setSummaries();
    }

    public void saveData(View v){
        Toast.makeText(SummaryActivity.this, "Data saved",Toast.LENGTH_SHORT).show();
        finish();
    }

    public void setSummaries(){
        String consumptionTotal = String.format("%.2f",consumptionData.getConsumptionTotal());
        String housingTotal = String.format("%.2f",housingData.getResult());
        String vehicleTotal = String.format("%.2f",vehicleData.getResult());


        textView_ConsumptionResult.setText("Consumption CO2-emissions in total: " + consumptionTotal);
        textView_HousingResult.setText("Housing CO2-emissions in total: " + housingTotal);
        textView_VehicleResult.setText("Vehicle CO2-emission in total: " + vehicleTotal);
    }

    public void cancel(View v){
        finish();
    }
}