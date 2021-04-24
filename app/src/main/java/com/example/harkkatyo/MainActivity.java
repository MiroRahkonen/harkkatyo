package com.example.harkkatyo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {

    TextView textView_ConsumptionSaved, textView_HousingSaved, textView_VehicleSaved;
    private Boolean consumptionSaved = false, housingSaved = false, vehicleSaved = false;
    protected Consumption consumptionData;
    protected Housing housingData;
    protected Vehicle vehicleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        consumptionData = Consumption.getInstance();
        housingData = Housing.getInstance();
        vehicleData = Vehicle.getInstance();
        textView_ConsumptionSaved = findViewById(R.id.textView_ConsumptionSaved);
        textView_HousingSaved = findViewById(R.id.textView_HousingSaved);
        textView_VehicleSaved = findViewById(R.id.textView_VehicleSaved);
    }

    protected void onResume() {
        super.onResume();
    }

    public void gotoConsumption(View v) {
        Intent intent = new Intent(MainActivity.this, ConsumptionActivity.class);
        startActivityForResult(intent, 0);
    }

    public void gotoHousing(View v) {
        Intent intent = new Intent(MainActivity.this, HousingActivity.class);
        startActivityForResult(intent, 0);
    }


    public void gotoVehicle(View v) {
        Intent intent = new Intent(MainActivity.this, VehicleActivity.class);
        startActivityForResult(intent, 0);
    }

    //Saving data to viewmodel from result
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String fromActivity = data.getStringExtra("fromActivity");

        //Saving to different variables based on the activity
        switch (fromActivity) {
            case ("consumptionActivity"):
                System.out.println(consumptionData.getURL());
                textView_ConsumptionSaved.setText("Data saved");
                consumptionSaved = true;
                break;

            case ("housingActivity"):
                textView_HousingSaved.setText("Data saved");
                housingSaved = true;
                break;

            case ("vehicleActivity"):
                textView_VehicleSaved.setText("Data saved");
                vehicleSaved = true;
                break;
        }
    }

    public void createSummary(View v) throws InterruptedException {
        //Testing if all data has been saved
        Boolean save = true;
        if (consumptionSaved == false) {
            textView_ConsumptionSaved.setTextColor(Color.RED);
            save = false;
        }
        if (housingSaved == false) {
            textView_HousingSaved.setTextColor(Color.RED);
            save = false;
        }
        if (vehicleSaved == false) {
            textView_VehicleSaved.setTextColor(Color.RED);
            save = false;
        }
        if (save == true) {
            Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(MainActivity.this, "Information missing", Toast.LENGTH_SHORT).show();
            TimeUnit.SECONDS.sleep(1);
            textView_ConsumptionSaved.setTextColor(Color.BLACK);
            textView_HousingSaved.setTextColor(Color.BLACK);
            textView_VehicleSaved.setTextColor(Color.BLACK);
        }
    }
}