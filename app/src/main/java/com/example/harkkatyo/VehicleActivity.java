package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class VehicleActivity extends AppCompatActivity {

    String[] vehicleFuel = new String[]{"Gasoline","Diesel","Electricity"};
    String[] vehicleSize = new String[]{"Mini","Medium","Large"};
    Spinner spinner_VehicleSize;
    Spinner spinner_VehicleFuel;
    EditText editText_Distance;
    EditText editText_Passengers;
    EditText editText_Year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        spinner_VehicleFuel = findViewById(R.id.spinner_VehicleFuel);
        ArrayAdapter<String> adapter_VehicleFuel = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,vehicleFuel);
        adapter_VehicleFuel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_VehicleFuel.setAdapter(adapter_VehicleFuel);

        spinner_VehicleSize = findViewById(R.id.spinner_VehicleSize);
        ArrayAdapter<String> adapter_VehicleSize = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,vehicleSize);
        adapter_VehicleSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_VehicleSize.setAdapter(adapter_VehicleSize);
        editText_Distance = findViewById(R.id.editText_VehicleDistance);
        editText_Passengers = findViewById(R.id.editText_VehiclePassengers);
        editText_Year = findViewById(R.id.editText_VehicleYear);

    }

    public void saveChanges(View v){
        int distance,passengers,year;
        String fuelType = spinner_VehicleFuel.getSelectedItem().toString();
        String size = spinner_VehicleSize.getSelectedItem().toString();

        try{        /*Getting data from edittexts*/
            distance = Integer.parseInt(editText_Distance.getText().toString());
            passengers = Integer.parseInt(editText_Passengers.getText().toString());
            year = Integer.parseInt(editText_Year.getText().toString());
            Toast.makeText(VehicleActivity.this, "Saved",Toast.LENGTH_SHORT).show();
            finish();
        }
        catch (NumberFormatException e) {
            Toast.makeText(VehicleActivity.this, "Invalid input",Toast.LENGTH_SHORT).show();
            distance = 0;
            passengers = 0;
            year = 0;
        }
    }

    public void cancel(View v){
        finish();
    }
}