package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SummaryActivity extends AppCompatActivity {

    Consumption consumptionData;
    Housing housingData;
    Vehicle vehicleData;
    TextView textView_ConsumptionResult,textView_HousingResult,textView_VehicleResult,textView_SummaryTotal;

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
        textView_SummaryTotal = findViewById(R.id.textView_SummaryTotal);
        setSummaries();
    }

    public void saveData(View v){
        Toast.makeText(SummaryActivity.this, "Data saved",Toast.LENGTH_SHORT).show();
        finish();
    }

    public void setSummaries(){
        double doubleTotal = consumptionData.getConsumptionTotal()+housingData.getResult()+vehicleData.getResult();

        textView_ConsumptionResult.setText("Consumption emissions(CO2 kg/year)" +
                "\nClothing: " + String.format("%.2f",consumptionData.getClothingResult()) +
                "\nElectronics: " + String.format("%.2f",consumptionData.getElectronicsResult()) +
                "\nPaper: " + String.format("%.2f",consumptionData.getPaperResult()) +
                "\nRecreation: " + String.format("%.2f",consumptionData.getRecreationResult()) +
                "\nTotal: " + String.format("%.2f",consumptionData.getConsumptionTotal()));
        textView_HousingResult.setText("Housing emissions(CO2 kg/year)\nTotal: " + String.format("%.2f",housingData.getResult()));
        textView_VehicleResult.setText("Vehicle emission(CO2 kg/year)\nTotal: " + String.format("%.2f",vehicleData.getResult()));
        textView_SummaryTotal.setText("Total emissions(CO2 kg/year)\nTotal: "+String.format("%.2f",doubleTotal));
    }

    public void cancel(View v){
        finish();
    }
}