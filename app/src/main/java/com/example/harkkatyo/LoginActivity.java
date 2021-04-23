package com.example.harkkatyo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginActivity extends AppCompatActivity {
    EditText editText_EmailAddress, editText_Password;
    Button button_confirm, button_register;
    TextView textView2, textView3, textView4;
    Boolean check1, check2;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editText_EmailAddress = (EditText) findViewById(R.id.editText_EmailAddress2);
        editText_Password = findViewById(R.id.editText_LoginPassword);
        button_confirm = (Button) findViewById(R.id.button_LoginButton);
        button_register = findViewById(R.id.button_LoginRegisterButton);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);



        //Confirm
        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editText_EmailAddress.getText().toString();
                String password = editText_Password.getText().toString();
                check1 = checkEmail1();
                check2 = checkPassword1();
                if (check1 && check2 == true) {
                    //loginUser(email, password);
                    goToMain();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Insufficient information",Toast.LENGTH_LONG).show();
                }
            }
        });

        //Register
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRegister();
            }
        });
        /*editText_Email = findViewById(R.id.editText_LoginEmail);
        editText_Password = findViewById(R.id.editText_LoginPassword);
        button_Register = findViewById(R.id.button_LoginRegisterButton);*/
    }




    /*public void gotoMain(View v){
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


            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(LoginActivity.this, "Login successful",Toast.LENGTH_SHORT).show();
        }
    }*/



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

    //Login user in firebase database
    /*private void loginUser(String email1, String password1) {
        mAuth.signInWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Login Succesful",Toast.LENGTH_LONG).show();
                    goToMain();
                }
                else {
                    Toast.makeText(LoginActivity.this, "Login Unsuccesful",Toast.LENGTH_LONG).show();
                }
            }
        });

    }*/

    private Boolean checkEmail1() {
        String test = editText_EmailAddress.getText().toString();

        if (test.isEmpty()) {
            editText_EmailAddress.setError("You have to give email");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(test).matches()) {
            editText_EmailAddress.setError("You have to give valid email");
            return false;
        }
        else {
            return true;
        }
    }
    private Boolean checkPassword1() {
        String test = editText_Password.getText().toString();
        if (test.isEmpty()) {
            editText_Password.setError("You have to give password");
            return false;
        }
        else {
            return true;
        }
    }







}