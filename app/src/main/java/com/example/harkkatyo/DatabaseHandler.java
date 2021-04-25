package com.example.harkkatyo;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
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

    //Store class data in firebase database
    public void baseWriteUser(String email, String password, String name) {
        Account d1 = new Account(email, password, name);
        root = FirebaseDatabase.getInstance("https://harkkatyo-e2aad-default-rtdb.europe-west1.firebasedatabase.app");
        //root.setLogLevel(Logger.Level.DEBUG);
        root.getReference("testi").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(d1);
    }

    //Store class data in firebase database
    public void baseWriteSummary(double vehiclesum, double consumptionsum, double housingsum) {
        Summary summary = new Summary(vehiclesum, consumptionsum, housingsum);
        root = FirebaseDatabase.getInstance("https://harkkatyo-e2aad-default-rtdb.europe-west1.firebasedatabase.app");
        ref = root.getReference("summary").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.setValue(summary);

    }

    //Read user specific housing data from database if data exists and update class instance data from it
    public void baseReadHousing() {
        Housing house = Housing.getInstance();
        root = FirebaseDatabase.getInstance("https://harkkatyo-e2aad-default-rtdb.europe-west1.firebasedatabase.app");
        ref = root.getReference("housing").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        //ref = root.getReference();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String type = snapshot.child("type").getValue(String.class);
                    int area = snapshot.child("area").getValue(int.class);
                    int residents = snapshot.child("residents").getValue(int.class);
                    double result = snapshot.child("result").getValue(Double.class);
                    house.housingResults(area, residents, type);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Reading cancelled");
            }
        });

    }
    //Read user specific vehicle data from database if data exists and update class instance data from it
    public void baseReadVehicle() {
        Vehicle vehicle = Vehicle.getInstance();
        root = FirebaseDatabase.getInstance("https://harkkatyo-e2aad-default-rtdb.europe-west1.firebasedatabase.app");
        ref = root.getReference("vehicle").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    int distance = snapshot.child("distance").getValue(int.class);
                    String fuel = snapshot.child("fuel").getValue(String.class);
                    int passengers = snapshot.child("passengers").getValue(int.class);
                    double result = snapshot.child("result").getValue(Double.class);
                    String size = snapshot.child("size").getValue(String.class);
                    int year = snapshot.child("year").getValue(int.class);
                    vehicle.vehicleResults(distance, passengers, year, fuel, size);

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Reading cancelled");
            }
        });

    }
    //Read user specific consumption data from database if data exists and update class instance data from it
    public void baseReadConsumption() {
        Consumption consumption = Consumption.getInstance();
        root = FirebaseDatabase.getInstance("https://harkkatyo-e2aad-default-rtdb.europe-west1.firebasedatabase.app");
        ref = root.getReference("consumption").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    int clothing = snapshot.child("clothing").getValue(int.class);
                    int electronics = snapshot.child("electronics").getValue(int.class);
                    int paper = snapshot.child("paper").getValue(int.class);
                    int recreation = snapshot.child("recreation").getValue(int.class);
                    String url = snapshot.child("url").getValue(String.class);

                    consumption.consumptionResults(clothing, 0, electronics, 0, paper, recreation, 0);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Reading cancelled");
            }
        });
    }

    //Store class data in firebase database
    public void baseWriteHousing() {
        Housing housing = Housing.getInstance();
        root = FirebaseDatabase.getInstance("https://harkkatyo-e2aad-default-rtdb.europe-west1.firebasedatabase.app");
        ref = root.getReference("housing").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.setValue(housing);

    }

    //Store class data in firebase database
    public void baseWriteVehicle() {
        Vehicle vehicle = Vehicle.getInstance();
        root = FirebaseDatabase.getInstance("https://harkkatyo-e2aad-default-rtdb.europe-west1.firebasedatabase.app");
        ref = root.getReference("vehicle").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.setValue(vehicle);

    }

    //Store class data in firebase database
    public void baseWriteConsumption() {
        Consumption consumption = Consumption.getInstance();
        root = FirebaseDatabase.getInstance("https://harkkatyo-e2aad-default-rtdb.europe-west1.firebasedatabase.app");
        ref = root.getReference("consumption").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.setValue(consumption);

    }







}
