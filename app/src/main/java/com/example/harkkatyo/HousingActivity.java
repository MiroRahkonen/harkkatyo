package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class HousingActivity extends AppCompatActivity {

    String[] housetypes = new String[]{"Family","Flat","Row"};
    Spinner spinner_housetype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housing);

        spinner_housetype = findViewById(R.id.spinner_housetypes);
        ArrayAdapter<String> adapter_housetype = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,housetypes);
        adapter_housetype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_housetype.setAdapter(adapter_housetype);
    }

    public void saveChanges(View v){
        finish();
    }

}