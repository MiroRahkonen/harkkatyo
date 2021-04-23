package com.example.harkkatyo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Consumption {
    private static Consumption consumption = new Consumption();

    private double clothingResult;
    private double commResult;
    private double electronicsResult;
    private double paperResult;
    private double consOtherResult;
    private double recreationResult;
    private double shoesResult;
    private double consTotalResult;
    private int clothing;
    private int communications;
    private int electronics;
    private int other;
    private int paper;
    private int recreation;
    private int shoes;
    private String urlString;

    private Consumption(){}

    public static Consumption getInstance(){ return consumption; }

    public Consumption consumptionResults(int clothing, int communications, int electronics, int other, int paper, int recreation, int shoes){
        this.clothing = clothing;
        this.communications = communications;
        this.electronics = electronics;
        this.other = other;
        this.paper = paper;
        this.recreation = recreation;
        this.shoes = shoes;
        try {
            urlString = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/ConsumptionCalculator?query.clothing=" + clothing + "&query.communications=" + communications + "&query.electronics=" + electronics + "&query.other=" + other + "&query.paper=" + paper + "&query.recreation=" + recreation + "&query.shoes=" + shoes;
            System.out.println(urlString);
            JSONObject object = JsonApi.newInstance().getJson(urlString);
            clothingResult = object.getDouble("Clothing");
            commResult = object.getDouble("Communications");
            electronicsResult = object.getDouble("Electronics");
            consOtherResult = object.getDouble("Other");
            paperResult = object.getDouble("Paper");
            recreationResult = object.getDouble("Recreation");
            shoesResult = object.getDouble("Shoes");
            consTotalResult = object.getDouble("Total");

        }catch (NumberFormatException | JSONException | IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return this;
    }

    public double getClothing(){ return clothingResult; }
    public double getCommunications(){ return commResult; }
    public double getElectronics(){ return electronicsResult; }
    public double getPaper(){ return paperResult; }
    public double getConsumptionOther(){ return consOtherResult; }
    public double getRecreation(){ return recreationResult; }
    public double getShoes(){ return shoesResult; }
    public double getConsumptionTotal(){ return consTotalResult; }
    public String getURL(){return urlString;}
}
