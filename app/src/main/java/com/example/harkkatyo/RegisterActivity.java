package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    EditText editText_Email;
    EditText editText_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editText_Email = findViewById(R.id.editText_RegisterEmail);
        editText_Password = findViewById(R.id.editText_LoginPassword);

    }

    public void cancel(View v){
        finish();
    }

    public void saveAccount(View v){

    }

}