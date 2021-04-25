package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

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

        try{        /*Getting data from edittexts*/
            distance = Integer.parseInt(editText_Distance.getText().toString());
            passengers = Integer.parseInt(editText_Passengers.getText().toString());
            year = Integer.parseInt(editText_Year.getText().toString());

            //Getting items from spinners
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

            //If input is correct, saving data and returning to main window
            if(testInput()){
                vehicleData.vehicleResults(distance,passengers,year,fuel,size);
                DatabaseHandler handler = new DatabaseHandler();
                handler.baseWriteVehicle();
                Toast.makeText(VehicleActivity.this, "Vehicle data saved",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        catch (NumberFormatException e) {       /*Shows error if input isn't an integer*/
            Toast.makeText(VehicleActivity.this, "Invalid input",Toast.LENGTH_SHORT).show();
        }
    }
    //Setting existing data to text fields
    public void setValuesToText(){
        TextView textView_Distance = findViewById(R.id.textView_VehicleDistance);
        textView_Distance.setText("Current: "+ vehicleData.getDistance());
        TextView textView_Passengers = findViewById(R.id.textView_VehiclePassengers);
        textView_Passengers.setText("Current: "+ vehicleData.getPassengers());
        TextView textView_Year = findViewById(R.id.textView_VehicleYear);
        textView_Year.setText("Current: "+ vehicleData.getYear());

        //Setting existing options to spinners
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

    /*Tests if the input is within the
    correct range and returns true
    if all input is correct*/
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
    //Doesn't save data and returns to main window
    public void cancel(View v){finish();
    }
}