package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
            System.out.println("Saved");
        }
        catch (NumberFormatException e) {
            System.out.println("Wrong input type or field empty");
            clothing = 0;
            electronics = 0;
            paper = 0;
            recreation = 0;
        }

        finish();
    }
}