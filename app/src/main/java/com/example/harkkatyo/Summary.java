package com.example.harkkatyo;

public class Summary {
    private double vehiclesum;
    private double consumptionsum;
    private double housingsum;

    public Summary(double vehiclesum1,double consumption1,double housingsum1) {
        vehiclesum = vehiclesum1;
        consumptionsum = consumption1;
        housingsum = housingsum1;
    }


    public double getVehiclesum() {
        return vehiclesum;
    }


    public double getConsumptionsum() {
        return consumptionsum;
    }

    public double getHousingsum() {
        return housingsum;
    }
}
