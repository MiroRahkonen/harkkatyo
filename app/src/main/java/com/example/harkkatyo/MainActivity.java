package com.example.harkkatyo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


public class MainActivity extends AppCompatActivity {

    private DataViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        viewModel = new ViewModelProvider(this).get(DataViewModel.class);
    }

    protected void onResume(){
        super.onResume();
    }

    public void gotoConsumption(View v){
        Intent intent = new Intent(MainActivity.this,ConsumptionActivity.class);
        intent.putExtra("viewModel_Clothing",viewModel.consumption_Clothing);
        intent.putExtra("viewModel_Electronics",viewModel.consumption_Electronics);
        intent.putExtra("viewModel_Paper",viewModel.consumption_Paper);
        intent.putExtra("viewModel_Recreation",viewModel.consumption_Recreation);
        startActivityForResult(intent,0);
    }




    public void gotoHousing(View v){
        Intent intent = new Intent(MainActivity.this,HousingActivity.class);
        intent.putExtra("viewModel_Area",viewModel.housing_Area);
        intent.putExtra("viewModel_Resident",viewModel.housing_Residents);
        intent.putExtra("viewModel_Type",viewModel.housing_Type);
        startActivityForResult(intent,0);
    }


    public void gotoVehicle(View v){
        Intent intent = new Intent(MainActivity.this,VehicleActivity.class);
        intent.putExtra("viewModel_Distance",viewModel.vehicle_Distance);
        intent.putExtra("viewModel_FuelType",viewModel.vehicle_FuelType);
        intent.putExtra("viewModel_Passengers",viewModel.vehicle_Passengers);
        intent.putExtra("viewModel_Size",viewModel.vehicle_Size);
        intent.putExtra("viewModel_Year",viewModel.vehicle_Year);
        startActivityForResult(intent,0);
    }

    @SuppressLint("SetTextI18n")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String fromActivity = data.getStringExtra("fromActivity");

        switch (fromActivity) {
            case ("consumptionActivity"):

                viewModel.consumption_Clothing = data.getIntExtra("clothing", 0);
                viewModel.consumption_Electronics = data.getIntExtra("electronics", 0);
                viewModel.consumption_Paper = data.getIntExtra("paper", 0);
                viewModel.consumption_Recreation = data.getIntExtra("recreation", 0);

                viewModel.consumption_URL = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/ConsumptionCalculator?" +
                        "query.clothing="+ viewModel.consumption_Clothing+
                        "&query.electronics="+ viewModel.consumption_Electronics+
                        "&query.paper="+viewModel.consumption_Paper+
                        "&query.recreation="+viewModel.consumption_Recreation;
                System.out.println(viewModel.consumption_URL);
                TextView consumptionText = findViewById(R.id.textView_ConsumptionData);
                consumptionText.setText("Clothing: " + viewModel.consumption_Clothing +
                        "\nElectronics: " + viewModel.consumption_Electronics +
                        "\nPaper: " + viewModel.consumption_Paper +
                        "\nRecreation: " + viewModel.consumption_Recreation);
                break;

            case ("housingActivity"):
                TextView housingText = findViewById(R.id.textView_HousingData);
                viewModel.housing_Area = data.getIntExtra("area", 0);
                viewModel.housing_Residents = data.getIntExtra("residents", 0);
                viewModel.housing_Type = data.getStringExtra("type");

                viewModel.housing_URL = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/HousingCalculator/InfrastructureEstimate?" +
                        "type="+viewModel.housing_Type+
                        "&area="+viewModel.housing_Area+
                        "&residents="+viewModel.housing_Residents;
                System.out.println(viewModel.housing_URL);
                housingText.setText("Area: " + viewModel.housing_Area +
                        "\nResidents: " + viewModel.housing_Residents +
                        "\nHouse type: " + viewModel.housing_Type);
                break;

            case ("vehicleActivity"):
                TextView vehicleText = findViewById(R.id.textView_VehicleData);
                viewModel.vehicle_Distance = data.getIntExtra("distance", 0);
                viewModel.vehicle_Passengers = data.getIntExtra("passengers", 0);
                viewModel.vehicle_Year = data.getIntExtra("year", 0);
                viewModel.vehicle_FuelType = data.getStringExtra("fuel");
                viewModel.vehicle_Size = data.getStringExtra("size");

                viewModel.vehicle_URL = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/TransportCalculator/CarEstimate?" +
                        "query.buildYear="+viewModel.vehicle_Year+
                        "&query.driveDistance="+viewModel.vehicle_Distance+
                        "&query.size="+viewModel.vehicle_Size+
                        "&query.fuel="+viewModel.vehicle_FuelType+
                        "&query.passengerCount="+viewModel.vehicle_Passengers;
                System.out.println(viewModel.vehicle_URL);
                vehicleText.setText("Distance: " + viewModel.vehicle_Distance +
                        "\nPassengers: " + viewModel.vehicle_Passengers +
                        "\nYear: " + viewModel.vehicle_Year +
                        "\nFuel type: " + viewModel.vehicle_FuelType +
                        "\nVehicle size: " + viewModel.vehicle_Size);
                break;
        }
    }

    public void createSummary(View v){
        Intent intent = new Intent(MainActivity.this,SummaryActivity.class);
        startActivity(intent);
    }
}