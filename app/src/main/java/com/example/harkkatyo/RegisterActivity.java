package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLOutput;

public class RegisterActivity extends AppCompatActivity {

    EditText editText_EmailAddress2, editText_Password;
    Button button_confirm, button_return;
    TextView textView2, textView3;
    Boolean check1, check2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        button_confirm = (Button) findViewById(R.id.button_confirm);
        button_return = (Button) findViewById(R.id.button_return);
        editText_EmailAddress2 = (EditText) findViewById(R.id.editText_EmailAddress2);
        editText_Password = (EditText) findViewById(R.id.editText_Password);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);

        //Return back to LoginActivity
        button_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Confirming new account
        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check1 = checkEmail();
                check2 = checkPassword();
                if (check1 && check2 == true) { //Check if email and password are good
                    System.out.println("Creating new account");
                    //Create new account
                    String email1 = editText_EmailAddress2.getText().toString();
                    String password1 = editText_Password.getText().toString();
                    Account d1 = new Account(email1, password1);
                    System.out.println("New account created");
                    //Mby success window or smth here...
                    finish();
                }
            }
        });




    }
    //Check if user has given email address
    private Boolean checkEmail() {
        String test = editText_EmailAddress2.getText().toString();

        if (test.isEmpty()) {
            editText_EmailAddress2.setError("You have to give email");
            return false;
        }
        //else if ()
        else {
            return true;
        }
    }
    //Check if user has given password and if it meets the requirements
    private Boolean checkPassword() {
        String test = editText_Password.getText().toString();
        if (test.isEmpty()) {
            editText_Password.setError("You have to give password");
            return false;
        }
        else if (test.length() <= 4) {
            editText_Password.setError("Password length 4 characters min");
            return false;
        }
        else {
            return true;
        }
    }

}