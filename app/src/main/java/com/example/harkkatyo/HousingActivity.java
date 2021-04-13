package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class HousingActivity extends AppCompatActivity {

    String[] HouseTypes = new String[]{"Family","Flat","Row"};
    Spinner spinner_HouseType;
    EditText editText_Area;
    EditText editText_Residents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing);

        spinner_HouseType = findViewById(R.id.spinner_HouseType);
        ArrayAdapter<String> adapter_HouseType = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,HouseTypes);
        adapter_HouseType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_HouseType.setAdapter(adapter_HouseType);
        editText_Area = findViewById(R.id.editText_HousingArea);
        editText_Residents = findViewById(R.id.editText_HouseResidents);

    }

    public void saveChanges(View v){
        int area,residents;
        String vehicle_FuelType = spinner_HouseType.getSelectedItem().toString();

        try{        /*Getting data from edittexts*/
            area = Integer.parseInt(editText_Area.getText().toString());
            residents = Integer.parseInt(editText_Residents.getText().toString());
            Toast.makeText(HousingActivity.this, "Saved",Toast.LENGTH_SHORT).show();
            finish();
        }
        catch (NumberFormatException e) {
            Toast.makeText(HousingActivity.this, "Invalid input",Toast.LENGTH_SHORT).show();
        }
    }
    public void cancel(View v){
        finish();
    }
}