package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption);
        editText_Clothing = findViewById(R.id.editText_ConsumptionClothing);
        editText_Electronics = findViewById(R.id.editText_ConsumptionElectronics);
        editText_Paper = findViewById(R.id.editText_ConsumptionPaper);
        editText_Recreation = findViewById(R.id.editText_ConsumptionRecreation);

    }

    public void saveChanges(View v){
        int clothing,electronics,paper,recreation;

        try{        /*Getting data from edittexts*/
            clothing = Integer.parseInt(editText_Clothing.getText().toString());
            electronics = Integer.parseInt(editText_Electronics.getText().toString());
            paper = Integer.parseInt(editText_Paper.getText().toString());
            recreation = Integer.parseInt(editText_Recreation.getText().toString());
            Toast.makeText(ConsumptionActivity.this, "Saved",Toast.LENGTH_SHORT).show();
            finish();
        }
        catch (NumberFormatException e) {
            Toast.makeText(ConsumptionActivity.this, "Invalid input",Toast.LENGTH_SHORT).show();
        }
    }
    public void cancel(View v){
        finish();
    }
}