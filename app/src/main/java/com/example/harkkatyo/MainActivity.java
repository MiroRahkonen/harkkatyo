package com.example.harkkatyo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;


public class MainActivity extends AppCompatActivity {

    private DataViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        viewModel = ViewModelProviders.of(this).get(DataViewModel.class);
    }

    protected void onResume(){
        super.onResume();
    }

    public void gotoConsumption(View v){
        Intent intent = new Intent(MainActivity.this,ConsumptionActivity.class);
        intent.putExtra("extraConsumption_Clothing",viewModel.consumption_Clothing);
        intent.putExtra("extraConsumption_Electronics",viewModel.consumption_Electronics);
        intent.putExtra("extraConsumption_Paper",viewModel.consumption_Paper);
        intent.putExtra("extraConsumption_Recreation",viewModel.consumption_Recreation);
        startActivity(intent);
    }
    public void gotoHousing(View v){
        Intent intent = new Intent(MainActivity.this,HousingActivity.class);
        intent.putExtra("extraHousing_Area",viewModel.housing_Area);
        intent.putExtra("extraHousing_Resident",viewModel.housing_Residents);
        intent.putExtra("extraHousing_Type",viewModel.housing_Type);
        startActivity(intent);
    }

    public void gotoVehicle(View v){
        Intent intent = new Intent(MainActivity.this,VehicleActivity.class);
        intent.putExtra("extraVehicle_Distance",viewModel.vehicle_Distance);
        intent.putExtra("extraVehicle_FuelType",viewModel.vehicle_FuelType);
        intent.putExtra("extraVehicle_Passengers",viewModel.vehicle_Passengers);
        intent.putExtra("extraVehicle_Size",viewModel.vehicle_Size);
        intent.putExtra("extraVehicle_Year",viewModel.vehicle_Year);
        startActivity(intent);
    }

    public void createSummary(View v){
        Intent intent = new Intent(MainActivity.this,SummaryActivity.class);
        startActivity(intent);
    }

    public void print(View v){
        System.out.println(viewModel.consumption_Clothing);
    }
}