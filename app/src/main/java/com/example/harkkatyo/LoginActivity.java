package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editText_Email;
    EditText editText_Password;
    Button button_Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_Email = findViewById(R.id.editText_LoginEmail);
        editText_Password = findViewById(R.id.editText_LoginPassword);
        button_Register = findViewById(R.id.button_LoginRegisterButton);
    }


    public void gotoMain(View v){
        String email,password;
        email = editText_Email.getText().toString();
        password = editText_Password.getText().toString();
        if(email.equals("")) {
            Toast.makeText(LoginActivity.this, "Missing email",Toast.LENGTH_SHORT).show();
        }
        else if(password.equals("")) {
            Toast.makeText(LoginActivity.this, "Missing password",Toast.LENGTH_SHORT).show();
        }
        else{
            /*Muuta funktiota*/

            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(LoginActivity.this, "Login successful",Toast.LENGTH_SHORT).show();
        }
    }
    public void gotoRegister(View v){
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
}