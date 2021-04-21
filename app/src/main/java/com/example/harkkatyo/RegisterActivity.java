package com.example.harkkatyo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVWriter;

import java.io.IOException;
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


        button_confirm = (Button) findViewById(R.id.button_LoginButton);
        button_return = (Button) findViewById(R.id.button_RegisterReturn);
        editText_EmailAddress2 = (EditText) findViewById(R.id.editText_EmailAddress2);
        editText_Password = (EditText) findViewById(R.id.editText_RegisterPassword);
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
                    //Create new account
                    saveAccount();

                }
                else {
                    Toast.makeText(RegisterActivity.this, "Insufficient information",Toast.LENGTH_SHORT).show();
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

    /*public void cancel(View v){
        finish();
    }
    */

    //Creates new Account
    public void saveAccount(){
        String email1 = editText_EmailAddress2.getText().toString();
        String password1 = editText_Password.getText().toString();
        Account d1 = new Account(email1, password1);
        /*CSVwriter writercsv = CSVwriter.getInstance();
        try {
            writercsv.writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Toast.makeText(RegisterActivity.this, "New Account Created",Toast.LENGTH_SHORT).show();
        //Switch to main Activity
        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(intent);

    }

}