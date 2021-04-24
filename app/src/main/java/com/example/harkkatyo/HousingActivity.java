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

public class HousingActivity extends AppCompatActivity {

    int area,residents;
    String type;
    String[] HouseTypes = new String[]{"Flat","Row","Family"};
    Spinner spinner_HouseType;
    EditText editText_Area;
    EditText editText_Residents;
    Housing housingData;

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
        housingData = Housing.getInstance();
        setValuesToText();
    }

    public void saveChanges(View v){

        try{        /*Getting data from edittexts to viewmodel*/
            Intent returnIntent = new Intent();

            area = Integer.parseInt(editText_Area.getText().toString());
            residents = Integer.parseInt(editText_Residents.getText().toString());

            type = spinner_HouseType.getSelectedItem().toString();
            switch(type) {
                case ("Flat"):
                    type = "flat";
                    break;
                case ("Row"):
                    type = "row";
                    break;
                case ("Family"):
                    type = "family";
                    break;
            }
            if(testInput()){
                housingData.housingResults(area,residents,type);
                DatabaseHandler handler = new DatabaseHandler();
                handler.baseWriteHousing();
                returnIntent.putExtra("fromActivity","housingActivity");
                Toast.makeText(HousingActivity.this, "Housing data saved",Toast.LENGTH_SHORT).show();
                setResult(1,returnIntent);
                finish();
            }
        }
        catch (NumberFormatException e) {       /*Shows error if input isn't valid*/
            Toast.makeText(HousingActivity.this, "Invalid input",Toast.LENGTH_SHORT).show();
        }
    }

    public void setValuesToText(){
        TextView area = findViewById(R.id.textView_HousingArea);
        area.setText("Current: "+ housingData.getArea());
        TextView residents = findViewById(R.id.textView_HousingResidents);
        residents.setText("Current: "+ housingData.getResidents());
        switch(housingData.getType()){
            case("flat"):
                spinner_HouseType.setSelection(0);
                break;
            case("row"):
                spinner_HouseType.setSelection(1);
                break;
            case("family"):
                spinner_HouseType.setSelection(2);
                break;
        }
    }

    public Boolean testInput(){
        if(area < 1){
            Toast.makeText(HousingActivity.this, "Area range is (1 - ...)",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(residents < 1){
            Toast.makeText(HousingActivity.this, "Resident range is (1 - ...)",Toast.LENGTH_SHORT).show();
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