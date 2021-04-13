package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
<<<<<<< HEAD
import android.widget.TextView;

=======
import android.widget.Toast;
>>>>>>> 31bb2254076e7156f6149f237dca792c5c2c77cc

public class LoginActivity extends AppCompatActivity {
    EditText editText_EmailAddress, editText_Password;
    Button button_confirm, button_register;
    TextView textView2, textView3, textView4;

    EditText editText_Email;
    EditText editText_Password;
    Button button_Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
<<<<<<< HEAD

        editText_EmailAddress = (EditText) findViewById(R.id.editText_EmailAddress2);
        editText_Password = (EditText) findViewById(R.id.editText_Password);
        button_confirm = (Button) findViewById(R.id.button_confirm);
        button_register = (Button) findViewById(R.id.button_register);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);



        //Confirm
        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if credencials are right.... coming soon
                goToMain();
            }
        });

        //Register
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();
            }
        });
=======
        editText_Email = findViewById(R.id.editText_LoginEmail);
        editText_Password = findViewById(R.id.editText_LoginPassword);
        button_Register = findViewById(R.id.button_LoginRegisterButton);
>>>>>>> 31bb2254076e7156f6149f237dca792c5c2c77cc
    }



    //Makes this opening Activity
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


    //Move to RegisterActivity
    private void goToRegister() {
        Intent switchActivityIntent = new Intent (this, RegisterActivity.class);
        startActivity(switchActivityIntent);
    }
    //Move to MainActivity
    private void goToMain() {
        Intent switchActivityIntent = new Intent (this, MainActivity.class);
        startActivity(switchActivityIntent);
    }





}