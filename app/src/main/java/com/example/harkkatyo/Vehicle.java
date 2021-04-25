package com.example.harkkatyo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Vehicle {

    private static Vehicle vehicle = null;

    private double vehicleResult;
    private int distance;
    private int passengers;
    private int year;
    private String fuel;
    private String size;

    private Vehicle(){
        vehicleResult = 0;
        distance = 0;
        passengers = 0;
        year = 0;
        fuel = "gasoline";
        size = "mini";
    }

    public static Vehicle getInstance(){
        if (vehicle == null){
            vehicle = new Vehicle();
        }
        return vehicle; }

    //Saves parameters to the object and gets calculation results from API to the object this allows the parameters and results to be accessed from every activity
    public void vehicleResults(int distance, int passengers, int year, String fuel, String size){
        this.distance = distance;
        this.passengers = passengers;
        this.year = year;
        this.fuel = fuel;
        this.size = size;
        try {
            String urlString = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/TransportCalculator/CarEstimate?query.buildYear=" + year + "&query.driveDistance=" + distance + "&query.size="+ size +"&query.fuel=" + fuel + "&query.passengerCount="+ passengers;
            System.out.println(urlString);
            InputStream input = JsonApi.newInstance().getInput(urlString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            vehicleResult = Double.parseDouble(reader.readLine());
        }catch (NumberFormatException | NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }
    public double getResult(){return vehicleResult;}
    public int getDistance(){return distance;}
    public int getYear(){return year;}
    public int getPassengers(){return passengers;}
    public String getFuel(){return fuel;}
    public String getSize(){return size;}
}
