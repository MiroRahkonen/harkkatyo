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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.opencsv.CSVWriter;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText editText_EmailAddress2, editText_Password, editTextTextPersonName;
    Button button_confirm, button_return;
    TextView textView2, textView3;
    Boolean check1, check2, check3;
    private FirebaseAuth mAuth;
    FirebaseDatabase root;
    //DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();



        button_confirm = (Button) findViewById(R.id.button_LoginButton);
        button_return = (Button) findViewById(R.id.button_RegisterReturn);
        editText_EmailAddress2 = (EditText) findViewById(R.id.editText_EmailAddress2);
        editText_Password = (EditText) findViewById(R.id.editText_RegisterPassword);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        editTextTextPersonName = (EditText) findViewById(R.id.editTextTextPersonName);

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
                check3 = checkName();
                if (check1 && check2 && check3 == true) { //Check if email and password are good
                    //Create new account
                    saveAccount();

                }
                else {
                    Toast.makeText(RegisterActivity.this, "Insufficient information",Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
    //Check if user has given valid email address
    private Boolean checkEmail() {
        String test = editText_EmailAddress2.getText().toString();

        if (test.isEmpty()) {
            editText_EmailAddress2.setError("You have to give email");
            return false;
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(test).matches()) {
            editText_EmailAddress2.setError("You have to give valid email");
            return false;
        }
        else {
            return true;
        }
    }
    //Check name
    private Boolean checkName() {
        String test = editTextTextPersonName.getText().toString();
        if (test.isEmpty()) {
            editText_Password.setError("You have to give password");
            return false;
        }
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
        else if (test.length() < 8) {
            editText_Password.setError("Password length 8 characters min");
            return false;
        }
        else {
            return true;
        }

    }


    //Creates new Account
    public void saveAccount(){
        String name = editTextTextPersonName.getText().toString();
        String email1 = editText_EmailAddress2.getText().toString();
        String password1 = editText_Password.getText().toString();
        //Create Firebase account
        createUser(email1, password1, name);
        //createDatabase(email1, password1, name);
        DatabaseHandler handler = new DatabaseHandler();
        //handler.writeBase();
        System.out.println("Täällä tulostetaan***************************************************");
        finish();



    }

    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }*/

    //Creates new account in Firebase database and check if succesful
    public void createUser(String email, String password, String name) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Account d1 = new Account(email, password, name);
                            //Create new user in firebase and link it to account

                            System.out.println("Toimii************************************************************************************************************************************************************************");
                            root = FirebaseDatabase.getInstance("https://harkkatyo-e2aad-default-rtdb.europe-west1.firebasedatabase.app");
                            root.setLogLevel(Logger.Level.DEBUG);
                            root.getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(d1);
                            //Store info in database
                            /*root = FirebaseDatabase.getInstance();
                            ref = root.getReference("testi");
                            ref.child(name).setValue("moi");*/
                            Toast.makeText(RegisterActivity.this, "New Account Created", Toast.LENGTH_LONG).show();



                        }






                        else {
                            Toast.makeText(RegisterActivity.this, "Account creation failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    /*public void createDatabase(String email, String password, String name) {
        //Store info in database
        root = FirebaseDatabase.getInstance();
        ref = root.getReference("testi");
        ref.setValue("kukkuluuruu");

    }*/


}