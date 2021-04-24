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

    int distance, passengers, year;
    String fuel, size;

    String[] vehicleFuel = new String[]{"Gasoline","Diesel","Electricity"};
    String[] vehicleSize = new String[]{"Mini","Medium","Large"};
    Spinner spinner_VehicleSize;
    Spinner spinner_VehicleFuel;
    EditText editText_Distance;
    EditText editText_Passengers;
    EditText editText_Year;
    Vehicle vehicleData;

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
        vehicleData = Vehicle.getInstance();
        setValuesToText();
    }

    public void saveChanges(View v){

        try{        /*Getting data from edittexts to viewmodel*/
            Intent returnIntent = new Intent();
            returnIntent.putExtra("fromActivity","vehicleActivity");

            distance = Integer.parseInt(editText_Distance.getText().toString());
            passengers = Integer.parseInt(editText_Passengers.getText().toString());
            year = Integer.parseInt(editText_Year.getText().toString());

            String fuelType = spinner_VehicleFuel.getSelectedItem().toString();
            switch(fuelType){
                case("Gasoline"):
                    fuel = "gasoline";
                    break;
                case("Diesel"):
                    fuel = "diesel";
                    break;
                case("Electricity"):
                    fuel = "electricity";
                    break;
            }

            String vehicleSize = spinner_VehicleSize.getSelectedItem().toString();
            switch(vehicleSize){
                case("Mini"):
                    size = "mini";
                    break;
                case("Medium"):
                    size = "mediumFamily";
                    break;
                case("Large"):
                    size = "large";
                    break;
            }
            //If input is correct and thus testinput returns true, saving data
            if(testInput()){
                vehicleData.vehicleResults(distance,passengers,year,fuel,size);
                DatabaseHandler handler = new DatabaseHandler();
                handler.baseWriteHousing();
                returnIntent.putExtra("fromActivity","vehicleActivity");
                Toast.makeText(VehicleActivity.this, "Saved",Toast.LENGTH_SHORT).show();
                setResult(0,returnIntent);
                finish();
            }
        }
        catch (NumberFormatException e) {       /*Shows error if input isn't valid*/
            Toast.makeText(VehicleActivity.this, "Invalid input",Toast.LENGTH_SHORT).show();
        }
    }

    public void setValuesToText(){
        TextView textView_Distance = findViewById(R.id.textView_VehicleDistance);
        textView_Distance.setText("Current: "+ vehicleData.getDistance());
        TextView textView_Passengers = findViewById(R.id.textView_VehiclePassengers);
        textView_Passengers.setText("Current: "+ vehicleData.getPassengers());
        TextView textView_Year = findViewById(R.id.textView_VehicleYear);
        textView_Year.setText("Current: "+ vehicleData.getYear());

        //Setting saved options to spinners
        switch(vehicleData.getFuel()){
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
        switch(vehicleData.getSize()){
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
        if((year > 2030) || (year < 1800)){
            Toast.makeText(VehicleActivity.this, "Year range is (1800 - 2030)",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if((passengers > 10) || (passengers < 1)){
            Toast.makeText(VehicleActivity.this, "Passenger range is (0 - 10)",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if((distance > 120000) || (distance < 0)){
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