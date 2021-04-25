package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConsumptionActivity extends AppCompatActivity {

    EditText editText_Clothing;
    EditText editText_Electronics;
    EditText editText_Paper;
    EditText editText_Recreation;
    protected Consumption consumptionData;
    int clothing,electronics,paper,recreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption);

        consumptionData = Consumption.getInstance();
        editText_Clothing = findViewById(R.id.editText_ConsumptionClothing);
        editText_Electronics = findViewById(R.id.editText_ConsumptionElectronics);
        editText_Paper = findViewById(R.id.editText_ConsumptionPaper);
        editText_Recreation = findViewById(R.id.editText_ConsumptionRecreation);
        setValuesToText();
    }

    public void saveChanges(View v){

        try{        /*Getting data from edittexts*/
            clothing = Integer.parseInt(editText_Clothing.getText().toString());
            electronics = Integer.parseInt(editText_Electronics.getText().toString());
            paper = Integer.parseInt(editText_Paper.getText().toString());
            recreation = Integer.parseInt(editText_Recreation.getText().toString());

            //If input is valid, saving data and returning to main window
            if(testInput()){
                consumptionData.consumptionResults(clothing,0,electronics,0,paper,recreation,0);
                DatabaseHandler handler = new DatabaseHandler();
                handler.baseWriteConsumption();
                Toast.makeText(ConsumptionActivity.this, "Consumption data saved",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        catch (NumberFormatException e) {       /*Shows error if input isn't an integer*/
            Toast.makeText(ConsumptionActivity.this, "Invalid input",Toast.LENGTH_SHORT).show();
        }
    }

    //Setting existing data to text fields
    public void setValuesToText(){
        TextView clothing = findViewById(R.id.textView_ConsumptionClothing);
        clothing.setText("Current: "+ consumptionData.getClothing());
        TextView electronics = findViewById(R.id.textView_ConsumptionElectronics);
        electronics.setText("Current: "+ consumptionData.getElectronics());
        TextView paper = findViewById(R.id.textView_ConsumptionPaper);
        paper.setText("Current: "+ consumptionData.getPaper());
        TextView recreation = findViewById(R.id.textView_ConsumptionRecreation);
        recreation.setText("Current: "+ consumptionData.getRecreation());
    }

    //Tests if the input is within the correct range and returns true if all input is correct
    public Boolean testInput(){
        if((clothing > 1000) || (clothing < 0)){
            Toast.makeText(ConsumptionActivity.this, "Clothing range is (0 - 1000)",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if((electronics > 1000) || (electronics < 0)){
            Toast.makeText(ConsumptionActivity.this, "Electronics range is (0 - 1000)",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if((paper > 1000) || (paper < 0)){
            Toast.makeText(ConsumptionActivity.this, "Paper range is (0 - 1000)",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if((recreation > 5000) || (recreation < 0)){
            Toast.makeText(ConsumptionActivity.this, "Recreation range is (0 - 5000)",Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }

    //Data is not saved and returning to main window
    public void cancel(View v){finish();
    }

}