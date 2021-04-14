package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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
    private DataViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        viewModel = new ViewModelProvider(this).get(DataViewModel.class);
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

        try{        /*Getting data from edittexts to viewmodel*/
            viewModel.vehicle_FuelType = spinner_VehicleFuel.getSelectedItem().toString();
            viewModel.vehicle_Size = spinner_VehicleSize.getSelectedItem().toString();
            viewModel.vehicle_Distance = Integer.parseInt(editText_Distance.getText().toString());
            viewModel.vehicle_Passengers = Integer.parseInt(editText_Passengers.getText().toString());
            viewModel.vehicle_Year = Integer.parseInt(editText_Year.getText().toString());
            Toast.makeText(VehicleActivity.this, "Saved",Toast.LENGTH_SHORT).show();
            finish();
        }
        catch (NumberFormatException e) {       /*Shows error if input isn't valid*/
            Toast.makeText(VehicleActivity.this, "Invalid input",Toast.LENGTH_SHORT).show();
        }
    }

    public void cancel(View v){
        finish();
    }
}