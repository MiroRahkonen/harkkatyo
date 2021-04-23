package com.example.harkkatyo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;


public class MainActivity extends AppCompatActivity {

    private DataViewModel viewModel;
    TextView textView_ConsumptionSaved, textView_HousingSaved, textView_VehicleSaved;
    private Boolean consumptionSaved = false, housingSaved = false, vehicleSaved = false;
    Consumption consumptionData;
    Housing housingData;
    Vehicle vehicleData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        consumptionData = consumptionData.getInstance();
        housingData = housingData.getInstance();
        vehicleData = vehicleData.getInstance();
        //viewModel = new ViewModelProvider(this).get(DataViewModel.class);
        textView_ConsumptionSaved = findViewById(R.id.textView_ConsumptionSaved);
        textView_HousingSaved = findViewById(R.id.textView_HousingSaved);
        textView_VehicleSaved = findViewById(R.id.textView_VehicleSaved);
    }

    protected void onResume(){
        super.onResume();
    }

    public void gotoConsumption(View v){
        Intent intent = new Intent(MainActivity.this,ConsumptionActivity.class);
        intent.putExtra("viewModel_Clothing",consumptionData.getClothing());
        intent.putExtra("viewModel_Electronics",consumptionData.getElectronics());
        intent.putExtra("viewModel_Paper",consumptionData.getPaper());
        intent.putExtra("viewModel_Recreation",consumptionData.getRecreation());
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
        intent.putExtra("viewModel_Distance",vehicleData.getDistance());
        intent.putExtra("viewModel_FuelType",vehicleData.getFuel());
        intent.putExtra("viewModel_Passengers",vehicleData.getPassengers());
        intent.putExtra("viewModel_Size",vehicleData.getSize());
        intent.putExtra("viewModel_Year",vehicleData.getYear());
        startActivityForResult(intent,0);
    }

    @SuppressLint("SetTextI18n")
    //Saving data to viewmodel from result
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String fromActivity = data.getStringExtra("fromActivity");

        //Saving to different variables based on the activity
        switch (fromActivity) {
            case ("consumptionActivity"):
                int consumptionClothing = data.getIntExtra("clothing", 0);
                int consumptionElectronics = data.getIntExtra("electronics", 0);
                int consumptionPaper = data.getIntExtra("paper", 0);
                int consumptionRecreation = data.getIntExtra("recreation", 0);
                consumptionData = consumptionData.consumptionResults(consumptionClothing,0,consumptionElectronics,0,consumptionPaper,consumptionRecreation,0);

                /*viewModel.consumption_URL = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/ConsumptionCalculator?" +
                        "query.clothing="+ viewModel.consumption_Clothing+
                        "&query.electronics="+ viewModel.consumption_Electronics+
                        "&query.paper="+viewModel.consumption_Paper+
                        "&query.recreation="+viewModel.consumption_Recreation;*/
                System.out.println(consumptionData.getURL());
                textView_ConsumptionSaved.setText("Data saved");
                consumptionSaved = true;
                /*consumptionText.setText("Clothing: " + viewModel.consumption_Clothing +
                        "\nElectronics: " + viewModel.consumption_Electronics +
                        "\nPaper: " + viewModel.consumption_Paper +
                        "\nRecreation: " + viewModel.consumption_Recreation);*/
                break;

            case ("housingActivity"):
                int housingArea = data.getIntExtra("area", 0);
                int housingResidents = data.getIntExtra("residents", 0);
                String housingType = data.getStringExtra("type");
                housingData = housingData.housingResults(housingArea,housingResidents,housingType);

                viewModel.housing_URL = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/HousingCalculator/InfrastructureEstimate?" +
                        "type="+viewModel.housing_Type+
                        "&area="+viewModel.housing_Area+
                        "&residents="+viewModel.housing_Residents;
                System.out.println(viewModel.housing_URL);
                textView_HousingSaved.setText("Data saved");
                housingSaved = true;
                /*housingText.setText("Area: " + viewModel.housing_Area +
                        "\nResidents: " + viewModel.housing_Residents +
                        "\nHouse type: " + viewModel.housing_Type);*/
                break;

            case ("vehicleActivity"):
                int vehicleDistance = data.getIntExtra("distance", 0);
                int vehiclePassengers = data.getIntExtra("passengers", 0);
                int vehicleYear = data.getIntExtra("year", 0);
                String vehicleFuel = data.getStringExtra("fuel");
                String vehicleSize = data.getStringExtra("size");
                vehicleData.getVehicleResult(vehicleDistance,vehiclePassengers, vehicleYear, vehicleFuel, vehicleSize);
                textView_VehicleSaved.setText("Data saved");
                vehicleSaved = true;
                /*viewModel.vehicle_URL = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/TransportCalculator/CarEstimate?" +
                        "query.buildYear="+viewModel.vehicle_Year+
                        "&query.driveDistance="+viewModel.vehicle_Distance+
                        "&query.size="+viewModel.vehicle_Size+
                        "&query.fuel="+viewModel.vehicle_FuelType+
                        "&query.passengerCount="+viewModel.vehicle_Passengers;*/

                /*vehicleText.setText("Distance: " + viewModel.vehicle_Distance +
                        "\nPassengers: " + viewModel.vehicle_Passengers +
                        "\nYear: " + viewModel.vehicle_Year +
                        "\nFuel type: " + viewModel.vehicle_FuelType +
                        "\nVehicle size: " + viewModel.vehicle_Size);*/
                break;
        }
    }

    public void createSummary(View v){
        //Testing if all data has been saved
        if(consumptionSaved == false){
            Toast.makeText(MainActivity.this, "Consumption information missing",Toast.LENGTH_SHORT).show();
        }
        else if (housingSaved == false){
            Toast.makeText(MainActivity.this, "Housing information missing",Toast.LENGTH_SHORT).show();
        }
        else if(vehicleSaved == false){
            Toast.makeText(MainActivity.this, "Vehicle information missing",Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent(MainActivity.this,SummaryActivity.class);
            intent.putExtra("consumption_URL",viewModel.consumption_URL);
            intent.putExtra("housing_URL",viewModel.housing_URL);
            intent.putExtra("vehicle_URL",viewModel.vehicle_URL);
            startActivity(intent);
        }
    }
}