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

    String[] HouseTypes = new String[]{"Flat","Row","Family"};
    Spinner spinner_HouseType;
    EditText editText_Area;
    EditText editText_Residents;
    private DataViewModel viewModel;

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
        getIntentValues();
        setValuesToText();
    }

    public void saveChanges(View v){

        try{        /*Getting data from edittexts to viewmodel*/
            Intent returnIntent = new Intent();

            viewModel.housing_Area = Integer.parseInt(editText_Area.getText().toString());
            viewModel.housing_Residents = Integer.parseInt(editText_Residents.getText().toString());
            //viewModel.housing_Type = spinner_HouseType.getSelectedItem().toString();

            String housingType = spinner_HouseType.getSelectedItem().toString();
            switch(housingType) {
                case ("Flat"):
                    viewModel.housing_Type = "flat";
                    break;
                case ("Row"):
                    viewModel.housing_Type = "row";
                    break;
                case ("Family"):
                    viewModel.housing_Type = "family";
                    break;
            }

            if(testInput()){
                returnIntent.putExtra("fromActivity","housingActivity");
                returnIntent.putExtra("area",Integer.parseInt(editText_Area.getText().toString()));
                returnIntent.putExtra("residents",Integer.parseInt(editText_Residents.getText().toString()));
                returnIntent.putExtra("type",viewModel.housing_Type);
                Toast.makeText(HousingActivity.this, "Saved",Toast.LENGTH_SHORT).show();
                setResult(1,returnIntent);
                finish();
            }
        }
        catch (NumberFormatException e) {       /*Shows error if input isn't valid*/
            Toast.makeText(HousingActivity.this, "Invalid input",Toast.LENGTH_SHORT).show();
        }
    }

    public void getIntentValues(){
        Bundle extras = getIntent().getExtras();
        viewModel.housing_Area = extras.getInt("viewModel_Area");
        viewModel.housing_Residents = extras.getInt("viewModel_Resident");
        viewModel.housing_Type = extras.getString("viewModel_Type");
    }

    public void setValuesToText(){
        TextView area = findViewById(R.id.textView_HousingArea);
        area.setText("Current: "+ viewModel.housing_Area);
        TextView residents = findViewById(R.id.textView_HousingResidents);
        residents.setText("Current: "+ viewModel.housing_Residents);
        System.out.println(viewModel.housing_Type);
        switch(viewModel.housing_Type){
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
        if(viewModel.housing_Area < 1){
            Toast.makeText(HousingActivity.this, "Area range is (1 - ...)",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(viewModel.housing_Residents < 1){
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