package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
    }

    public void saveData(View v){
        Toast.makeText(SummaryActivity.this, "Data saved",Toast.LENGTH_SHORT).show();
        finish();
    }

    public void cancel(View v){
        finish();
    }
}