package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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
    DataViewModel viewModel;
    private Consumption consumptionData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption);

        consumptionData = consumptionData.getInstance();
        viewModel = new ViewModelProvider(this).get(DataViewModel.class);
        editText_Clothing = findViewById(R.id.editText_ConsumptionClothing);
        editText_Electronics = findViewById(R.id.editText_ConsumptionElectronics);
        editText_Paper = findViewById(R.id.editText_ConsumptionPaper);
        editText_Recreation = findViewById(R.id.editText_ConsumptionRecreation);
        getIntentValues();
        setValuesToText();
    }

    public void saveChanges(View v){

        try{        /*Getting data from edittexts to viewmodel*/
            Intent returnIntent = new Intent();
            viewModel.consumption_Clothing = Integer.parseInt(editText_Clothing.getText().toString());
            viewModel.consumption_Electronics = Integer.parseInt(editText_Electronics.getText().toString());
            viewModel.consumption_Paper = Integer.parseInt(editText_Paper.getText().toString());
            viewModel.consumption_Recreation = Integer.parseInt(editText_Recreation.getText().toString());

            if(testInput()){

                returnIntent.putExtra("fromActivity","consumptionActivity");
                returnIntent.putExtra("clothing",viewModel.consumption_Clothing);
                returnIntent.putExtra("electronics",viewModel.consumption_Electronics);
                returnIntent.putExtra("paper",viewModel.consumption_Paper);
                returnIntent.putExtra("recreation",viewModel.consumption_Recreation);
                Toast.makeText(ConsumptionActivity.this, "Saved",Toast.LENGTH_SHORT).show();
                setResult(1,returnIntent);
                finish();
            }
        }
        catch (NumberFormatException e) {       /*Shows error if input isn't valid*/
            Toast.makeText(ConsumptionActivity.this, "Invalid input",Toast.LENGTH_SHORT).show();
        }
    }

    public void getIntentValues(){
        Bundle extras = getIntent().getExtras();
        viewModel.consumption_Clothing = extras.getInt("viewModel_Clothing");
        viewModel.consumption_Electronics = extras.getInt("viewModel_Electronics");
        viewModel.consumption_Paper = extras.getInt("viewModel_Paper");
        viewModel.consumption_Recreation = extras.getInt("viewModel_Recreation");
    }

    public void setValuesToText(){
        TextView clothing = findViewById(R.id.textView_ConsumptionClothing);
        clothing.setText("Current: "+ viewModel.consumption_Clothing);
        TextView electronics = findViewById(R.id.textView_ConsumptionElectronics);
        electronics.setText("Current: "+ viewModel.consumption_Electronics);
        TextView paper = findViewById(R.id.textView_ConsumptionPaper);
        paper.setText("Current: "+ viewModel.consumption_Paper);
        TextView recreation = findViewById(R.id.textView_ConsumptionRecreation);
        recreation.setText("Current: "+ viewModel.consumption_Recreation);
    }

    public Boolean testInput(){
        if((viewModel.consumption_Clothing > 1000) || (viewModel.consumption_Clothing < 0)){
            Toast.makeText(ConsumptionActivity.this, "Clothing range is (0 - 1000)",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if((viewModel.consumption_Electronics > 1000) || (viewModel.consumption_Electronics < 0)){
            Toast.makeText(ConsumptionActivity.this, "Electronics range is (0 - 1000)",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if((viewModel.consumption_Paper > 1000) || (viewModel.consumption_Paper < 0)){
            Toast.makeText(ConsumptionActivity.this, "Paper range is (0 - 1000)",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if((viewModel.consumption_Recreation > 5000) || (viewModel.consumption_Recreation < 0)){
            Toast.makeText(ConsumptionActivity.this, "Recreation range is (0 - 5000)",Toast.LENGTH_SHORT).show();
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