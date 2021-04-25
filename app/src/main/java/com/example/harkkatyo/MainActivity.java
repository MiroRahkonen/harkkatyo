package com.example.harkkatyo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase root;
    DatabaseReference ref;

    TextView textView_ConsumptionSaved, textView_HousingSaved, textView_VehicleSaved;
    protected Consumption consumptionData;
    protected Housing housingData;
    protected Vehicle vehicleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        //Check if data exists from database and update textfields
        ReadHousing();
        ReadConsumption();
        ReadVehicle();
        DatabaseHandler handler = new DatabaseHandler();
        handler.baseReadVehicle();
        handler.baseReadConsumption();
        handler.baseReadHousing();

        consumptionData = Consumption.getInstance();
        housingData = Housing.getInstance();
        vehicleData = Vehicle.getInstance();
        textView_ConsumptionSaved = findViewById(R.id.textView_ConsumptionSaved);
        textView_HousingSaved = findViewById(R.id.textView_HousingSaved);
        textView_VehicleSaved = findViewById(R.id.textView_VehicleSaved);
    }

    protected void onResume() {
        super.onResume();
    }

    public void gotoConsumption(View v) {
        Intent intent = new Intent(MainActivity.this, ConsumptionActivity.class);
        startActivity(intent);
    }

    public void gotoHousing(View v) {
        Intent intent = new Intent(MainActivity.this, HousingActivity.class);
        startActivity(intent);
    }

    public void gotoVehicle(View v) {
        Intent intent = new Intent(MainActivity.this, VehicleActivity.class);
        startActivity(intent);
    }

    //Goes to summary
    public void createSummary(View v){
        Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
        startActivity(intent);
    }

    //Get existing housing data from database if it exists and update textview with data
    public void ReadHousing() {
        root = FirebaseDatabase.getInstance("https://harkkatyo-e2aad-default-rtdb.europe-west1.firebasedatabase.app");
        ref = root.getReference("housing").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    String stringResult = snapshot.child("result").getValue(Double.class).toString();
                    double result = Float.parseFloat(stringResult);
                    textView_HousingSaved.setText("Current emissions(CO2 kg/year): "+String.format("%.2f",result));
                    textView_HousingSaved.setTextColor(Color.BLACK);
                }
                else{
                    textView_HousingSaved.setText("No existing data");
                    textView_HousingSaved.setTextColor(Color.RED);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Reading cancelled");
            }
        });
    }
    //Get existing vehicle data from database if it exists and update textview with data
    public void ReadVehicle() {
        root = FirebaseDatabase.getInstance("https://harkkatyo-e2aad-default-rtdb.europe-west1.firebasedatabase.app");
        ref = root.getReference("vehicle").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    String stringResult = snapshot.child("result").getValue(Double.class).toString();
                    double result = Float.parseFloat(stringResult);
                    textView_VehicleSaved.setText("Current emissions(CO2 kg/year): "+String.format("%.2f",result));
                    textView_VehicleSaved.setTextColor(Color.BLACK);
                }
                else{
                    textView_VehicleSaved.setText("No existing data");
                    textView_VehicleSaved.setTextColor(Color.RED);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Reading cancelled");
            }
        });
    }
    //Get existing consumption data from database if it exists and update textview with data
    public void ReadConsumption() {
        root = FirebaseDatabase.getInstance("https://harkkatyo-e2aad-default-rtdb.europe-west1.firebasedatabase.app");
        ref = root.getReference("consumption").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String stringResult = snapshot.child("consumptionTotal").getValue(Double.class).toString();
                    double result = Float.parseFloat(stringResult);
                    textView_ConsumptionSaved.setText("Current emissions(CO2 kg/year): "+String.format("%.2f",result));
                    textView_ConsumptionSaved.setTextColor(Color.BLACK);
                }
                else{
                    textView_ConsumptionSaved.setText("No existing data");
                    textView_ConsumptionSaved.setTextColor(Color.RED);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Reading cancelled");
            }
        });
    }
}