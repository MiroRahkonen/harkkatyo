package com.example.harkkatyo;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseHandler {

    public void writeBase() {
        FirebaseDatabase databased = FirebaseDatabase.getInstance();
        DatabaseReference ref;
        ref = databased.getReference("testi");

        ref.setValue("First data");

    }






}
