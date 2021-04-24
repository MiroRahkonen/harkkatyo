package com.example.harkkatyo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;



public class MainActivity extends AppCompatActivity {
    FirebaseDatabase root;
    DatabaseReference ref;

    TextView textView_ConsumptionSaved, textView_HousingSaved, textView_VehicleSaved;
    private Boolean consumptionSaved = false, housingSaved = false, vehicleSaved = false;
    protected Consumption consumptionData;
    protected Housing housingData;
    protected Vehicle vehicleData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        ReadHousing();
        ReadConsumption();
        ReadVehicle();
        //DatabaseHandler handler = new DatabaseHandler();
        //handler.baseReadHousing();


        consumptionData = Consumption.getInstance();
        housingData = Housing.getInstance();
        vehicleData = Vehicle.getInstance();
        textView_ConsumptionSaved = findViewById(R.id.textView_ConsumptionSaved);
        textView_HousingSaved = findViewById(R.id.textView_HousingSaved);
        textView_VehicleSaved = findViewById(R.id.textView_VehicleSaved);

        //textView_HousingSaved.setText(housing1);

        textView_ConsumptionSaved.setTextColor(Color.RED);
        textView_HousingSaved.setTextColor(Color.RED);
        textView_VehicleSaved.setTextColor(Color.RED);

    }

    protected void onResume() {
        super.onResume();
    }

    public void gotoConsumption(View v) {
        Intent intent = new Intent(MainActivity.this, ConsumptionActivity.class);
        startActivityForResult(intent, 0);
    }

    public void gotoHousing(View v) {
        Intent intent = new Intent(MainActivity.this, HousingActivity.class);
        startActivityForResult(intent, 0);
    }


    public void gotoVehicle(View v) {
        Intent intent = new Intent(MainActivity.this, VehicleActivity.class);
        startActivityForResult(intent, 0);
    }

    //Saving data to viewmodel from result
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String fromActivity = data.getStringExtra("fromActivity");

        //Saving to different variables based on the activity
        switch (fromActivity) {
            case ("consumptionActivity"):
                //textView_ConsumptionSaved.setText("Data saved");
                //textView_ConsumptionSaved.setTextColor(Color.BLACK);
                consumptionSaved = true;
                break;

            case ("housingActivity"):
                //textView_HousingSaved.setText("Data saved");
                //textView_HousingSaved.setTextColor(Color.BLACK);
                housingSaved = true;
                break;

            case ("vehicleActivity"):
                //textView_VehicleSaved.setText("Data saved");
                //textView_VehicleSaved.setTextColor(Color.BLACK);
                vehicleSaved = true;
                break;
        }
    }

    public void createSummary(View v){
        //Testing if all data has been saved

        if (consumptionSaved && housingSaved && vehicleSaved) {
            Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(MainActivity.this, "Input all information first", Toast.LENGTH_SHORT).show();
        }
    }
    public void ReadHousing() {
        root = FirebaseDatabase.getInstance("https://harkkatyo-e2aad-default-rtdb.europe-west1.firebasedatabase.app");
        ref = root.getReference("housing").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String result = snapshot.child("result").getValue(Double.class).toString();
                    System.out.println(result);
                    textView_HousingSaved.setText(result);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Reading cancelled");

            }

        });
    }
    public void ReadVehicle() {
        root = FirebaseDatabase.getInstance("https://harkkatyo-e2aad-default-rtdb.europe-west1.firebasedatabase.app");
        ref = root.getReference("vehicle").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String result = snapshot.child("result").getValue(Double.class).toString();
                    System.out.println(result);
                    textView_VehicleSaved.setText(result);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Reading cancelled");

            }

        });
    }
    public void ReadConsumption() {
        root = FirebaseDatabase.getInstance("https://harkkatyo-e2aad-default-rtdb.europe-west1.firebasedatabase.app");
        ref = root.getReference("consumption").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String result = snapshot.child("consumptionTotal").getValue(Double.class).toString();
                    System.out.println(result);
                    textView_ConsumptionSaved.setText(result);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("Reading cancelled");

            }

        });
    }
}