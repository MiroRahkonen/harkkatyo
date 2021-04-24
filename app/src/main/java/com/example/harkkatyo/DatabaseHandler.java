package com.example.harkkatyo;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DatabaseHandler {
    FirebaseDatabase root;
    DatabaseReference ref;
    ArrayList<String> list = new ArrayList<>();

    public void baseWriteUser(String email, String password, String name) {
        Account d1 = new Account(email, password, name);
        root = FirebaseDatabase.getInstance("https://harkkatyo-e2aad-default-rtdb.europe-west1.firebasedatabase.app");
        //root.setLogLevel(Logger.Level.DEBUG);
        root.getReference("testi").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(d1);


    }

    public void baseReadUser() {
        root = FirebaseDatabase.getInstance("https://harkkatyo-e2aad-default-rtdb.europe-west1.firebasedatabase.app");
        ref = root.getReference("testi").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    //list.add(dataSnapshot.getValue().toString());
                    System.out.println(dataSnapshot.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Reading cancelled");

            }
        });
    }

    public void bseWriteSummary() {
        root = FirebaseDatabase.getInstance("https://harkkatyo-e2aad-default-rtdb.europe-west1.firebasedatabase.app");
        ref = root.getReference("summary").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.setValue("hohoi");

    }






}
