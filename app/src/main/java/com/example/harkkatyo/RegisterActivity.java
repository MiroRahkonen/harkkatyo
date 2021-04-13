package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText editText_Email;
    EditText editText_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editText_Email = findViewById(R.id.editText_RegisterEmail);
        editText_Password = findViewById(R.id.editText_RegisterPassword);

    }

    public void cancel(View v){
        finish();
    }

    public void saveAccount(View v){
        String email,password;
        email = editText_Email.getText().toString();
        password = editText_Password.getText().toString();
        if(email.equals("")) {
            Toast.makeText(RegisterActivity.this, "Missing email",Toast.LENGTH_SHORT).show();
        }
        else if(password.equals("")) {
            Toast.makeText(RegisterActivity.this, "Missing password",Toast.LENGTH_SHORT).show();
        }
        else{
            /*Muuta funktiota*/

            Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(RegisterActivity.this, "Account saved",Toast.LENGTH_SHORT).show();
        }
    }

}