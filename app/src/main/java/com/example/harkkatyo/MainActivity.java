package com.example.harkkatyo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;


public class MainActivity extends AppCompatActivity {

    private DataViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        viewModel = new ViewModelProvider(this).get(DataViewModel.class);
        viewModel.consumption_Clothing = 100;
    }

    protected void onResume(){
        super.onResume();
    }

    public void gotoConsumption(View v){
        Intent intent = new Intent(MainActivity.this,ConsumptionActivity.class);
        startActivityForResult(intent,0);
    }




    public void gotoHousing(View v){
        Intent intent = new Intent(MainActivity.this,HousingActivity.class);
        intent.putExtra("extraHousing_Area",viewModel.housing_Area);
        intent.putExtra("extraHousing_Resident",viewModel.housing_Residents);
        intent.putExtra("extraHousing_Type",viewModel.housing_Type);
        startActivityForResult(intent,0);
    }


    public void gotoVehicle(View v){
        Intent intent = new Intent(MainActivity.this,VehicleActivity.class);
        intent.putExtra("extraVehicle_Distance",viewModel.vehicle_Distance);
        intent.putExtra("extraVehicle_FuelType",viewModel.vehicle_FuelType);
        intent.putExtra("extraVehicle_Passengers",viewModel.vehicle_Passengers);
        intent.putExtra("extraVehicle_Size",viewModel.vehicle_Size);
        intent.putExtra("extraVehicle_Year",viewModel.vehicle_Year);
        startActivityForResult(intent,0);
    }

    public void createSummary(View v){
        Intent intent = new Intent(MainActivity.this,SummaryActivity.class);
        startActivity(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String fromActivity = data.getStringExtra("fromActivity");

        switch (fromActivity) {
            case "consumptionActivity":

                viewModel.consumption_Clothing = data.getIntExtra("clothing", 0);
                viewModel.consumption_Electronics = data.getIntExtra("electronics", 0);
                viewModel.consumption_Paper = data.getIntExtra("paper", 0);
                viewModel.consumption_Recreation = data.getIntExtra("recreation", 0);
                TextView consumptionText = findViewById(R.id.textView_Consumption);
                consumptionText.setText("Clothing: " + viewModel.consumption_Clothing +
                        "\nElectronics: " + viewModel.consumption_Electronics +
                        "\nPaper: " + viewModel.consumption_Paper +
                        "\nRecreation: " + viewModel.consumption_Recreation);
                break;
            case "housingActivity":
                TextView housingText = findViewById(R.id.textView_Housing);
                viewModel.housing_Area = data.getIntExtra("area", 0);
                viewModel.housing_Residents = data.getIntExtra("residents", 0);
                viewModel.housing_Type = data.getStringExtra("type");
                housingText.setText("Area: " + viewModel.housing_Area +
                        "\nResidents: " + viewModel.housing_Residents +
                        "\nHouse type: " + viewModel.housing_Type);
                break;
            case "vehicleActivity":
                TextView vehicleText = findViewById(R.id.textView_Vehicle);
                viewModel.vehicle_Distance = data.getIntExtra("distance", 0);
                viewModel.vehicle_Passengers = data.getIntExtra("passengers", 0);
                viewModel.vehicle_Year = data.getIntExtra("year", 0);
                viewModel.vehicle_FuelType = data.getStringExtra("fuel");
                viewModel.vehicle_Size = data.getStringExtra("size");
                vehicleText.setText("Distance: " + viewModel.vehicle_Distance +
                        "\nPassengers: " + viewModel.vehicle_Passengers +
                        "\nYear: " + viewModel.vehicle_Year +
                        "\nFuel type: " + viewModel.vehicle_FuelType +
                        "\nVehicle size: " + viewModel.vehicle_Size);
                break;
        }
    }
}