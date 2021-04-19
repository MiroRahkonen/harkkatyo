package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
        getIntentValues();
        setValuesToText();
    }

    public void saveChanges(View v){

        try{        /*Getting data from edittexts to viewmodel*/
            Intent returnIntent = new Intent();
            returnIntent.putExtra("fromActivity","vehicleActivity");

            viewModel.vehicle_Distance = Integer.parseInt(editText_Distance.getText().toString());
            viewModel.vehicle_Passengers = Integer.parseInt(editText_Passengers.getText().toString());
            viewModel.vehicle_Year = Integer.parseInt(editText_Year.getText().toString());

            String fuelType = spinner_VehicleFuel.getSelectedItem().toString();
            switch(fuelType){
                case("Gasoline"):
                    viewModel.vehicle_FuelType = "gasoline";
                    break;
                case("Diesel"):
                    viewModel.vehicle_FuelType = "diesel";
                    break;
                case("Electricity"):
                    viewModel.vehicle_FuelType = "electricity";
                    break;
            }

            String vehicleSize = spinner_VehicleSize.getSelectedItem().toString();
            switch(vehicleSize){
                case("Mini"):
                    viewModel.vehicle_Size = "mini";
                    break;
                case("Medium"):
                    viewModel.vehicle_Size = "mediumFamily";
                    break;
                case("Large"):
                    viewModel.vehicle_Size = "large";
                    break;
            }
            //If input is correct and thus testinput returns true, saving data
            if(testInput()){
                returnIntent.putExtra("distance",Integer.parseInt(editText_Distance.getText().toString()));
                returnIntent.putExtra("passengers",Integer.parseInt(editText_Passengers.getText().toString()));
                returnIntent.putExtra("year",Integer.parseInt(editText_Year.getText().toString()));
                returnIntent.putExtra("fuel",viewModel.vehicle_FuelType);
                returnIntent.putExtra("size",viewModel.vehicle_Size);
                Toast.makeText(VehicleActivity.this, "Saved",Toast.LENGTH_SHORT).show();
                setResult(1,returnIntent);
                finish();
            }
        }
        catch (NumberFormatException e) {       /*Shows error if input isn't valid*/
            Toast.makeText(VehicleActivity.this, "Invalid input",Toast.LENGTH_SHORT).show();
        }
    }
            //Getting intent extras to viewmodel
    public void getIntentValues(){
        Bundle extras = getIntent().getExtras();
        viewModel.vehicle_Distance = extras.getInt("viewModel_Distance");
        viewModel.vehicle_Passengers = extras.getInt("viewModel_Passengers");
        viewModel.vehicle_Year = extras.getInt("viewModel_Year");
        viewModel.vehicle_FuelType = extras.getString("viewModel_FuelType");
        viewModel.vehicle_Size = extras.getString("viewModel_Size");
    }

    public void setValuesToText(){
        TextView distance = findViewById(R.id.textView_VehicleDistance);
        distance.setText("Current: "+ viewModel.vehicle_Distance);
        TextView passengers = findViewById(R.id.textView_VehiclePassengers);
        passengers.setText("Current: "+ viewModel.vehicle_Passengers);
        TextView year = findViewById(R.id.textView_VehicleYear);
        year.setText("Current: "+ viewModel.vehicle_Year);

        //Setting saved options to spinners
        switch(viewModel.vehicle_FuelType){
            case("gasoline"):
                spinner_VehicleFuel.setSelection(0);
                break;
            case("diesel"):
                spinner_VehicleFuel.setSelection(1);
                break;
            case("electricity"):
                spinner_VehicleFuel.setSelection(2);
                break;
        }
        switch(viewModel.vehicle_Size){
            case("mini"):
                spinner_VehicleSize.setSelection(0);
                break;
            case("mediumFamily"):
                spinner_VehicleSize.setSelection(1);
                break;
            case("large"):
                spinner_VehicleSize.setSelection(2);
                break;
        }
    }

    //Used to test if input is in their set ranges
    public Boolean testInput(){
        if((viewModel.vehicle_Year > 2030) || (viewModel.vehicle_Year < 1800)){
            Toast.makeText(VehicleActivity.this, "Year range is (1800 - 2030)",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if((viewModel.vehicle_Passengers > 10) || (viewModel.vehicle_Passengers < 1)){
            Toast.makeText(VehicleActivity.this, "Passenger range is (0 - 10)",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if((viewModel.vehicle_Distance > 120000) || (viewModel.vehicle_Distance < 0)){
            Toast.makeText(VehicleActivity.this, "Distance range is (0 - 120 000)",Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    public void cancel(View v){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("fromActivity","null");
        setResult(0,returnIntent);
        finish();
    }
}