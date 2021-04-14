package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ConsumptionActivity extends AppCompatActivity {

    EditText editText_Clothing;
    EditText editText_Electronics;
    EditText editText_Paper;
    EditText editText_Recreation;
    private DataViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption);

        viewModel = new ViewModelProvider(this).get(DataViewModel.class);
        editText_Clothing = findViewById(R.id.editText_ConsumptionClothing);
        editText_Electronics = findViewById(R.id.editText_ConsumptionElectronics);
        editText_Paper = findViewById(R.id.editText_ConsumptionPaper);
        editText_Recreation = findViewById(R.id.editText_ConsumptionRecreation);

        //Viikon 11 tehtävästä viewmodelin haku
        /*if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                viewModel.allowInput= null;
            } else {
                viewModel.allowInput= extras.getBoolean("allowInput");
            }
        } else {
            viewModel.allowInput= (Boolean) savedInstanceState.getSerializable("allowInput");
        }*/
    }

    public void saveChanges(View v){

        try{        /*Getting data from edittexts to viewmodel*/
            viewModel.consumption_Clothing = Integer.parseInt(editText_Clothing.getText().toString());
            System.out.println(viewModel.consumption_Clothing);
            viewModel.consumption_Electronics = Integer.parseInt(editText_Electronics.getText().toString());
            viewModel.consumption_Paper = Integer.parseInt(editText_Paper.getText().toString());
            viewModel.consumption_Recreation = Integer.parseInt(editText_Recreation.getText().toString());
            Toast.makeText(ConsumptionActivity.this, "Saved",Toast.LENGTH_SHORT).show();

            /*intent.putExtra("extraConsumption_Clothing",viewModel.consumption_Clothing);
            intent.putExtra("extraConsumption_Electronics",viewModel.consumption_Electronics);
            intent.putExtra("extraConsumption_Paper",viewModel.consumption_Paper);
            intent.putExtra("extraConsumption_Recreation",viewModel.consumption_Recreation);*/
            finish();
        }
        catch (NumberFormatException e) {       /*Shows error if input isn't valid*/
            Toast.makeText(ConsumptionActivity.this, "Invalid input",Toast.LENGTH_SHORT).show();
        }
    }
    public void cancel(View v){
        finish();
    }
}