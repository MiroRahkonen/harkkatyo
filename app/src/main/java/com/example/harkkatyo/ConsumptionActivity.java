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
    }

    public void saveChanges(View v){

        try{        /*Getting data from edittexts to viewmodel*/
            Intent returnIntent = new Intent();
            returnIntent.putExtra("fromActivity","consumptionActivity");
            returnIntent.putExtra("clothing",Integer.parseInt(editText_Clothing.getText().toString()));
            returnIntent.putExtra("electronics",Integer.parseInt(editText_Electronics.getText().toString()));
            returnIntent.putExtra("paper",Integer.parseInt(editText_Paper.getText().toString()));
            returnIntent.putExtra("recreation",Integer.parseInt(editText_Recreation.getText().toString()));
            Toast.makeText(ConsumptionActivity.this, "Saved",Toast.LENGTH_SHORT).show();
            setResult(1,returnIntent);
            finish();

        }
        catch (NumberFormatException e) {       /*Shows error if input isn't valid*/
            Toast.makeText(ConsumptionActivity.this, "Invalid input",Toast.LENGTH_SHORT).show();
        }
    }

    public void cancel(View v){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("fromActivity","null");
        setResult(0,returnIntent);
        finish();
    }

}