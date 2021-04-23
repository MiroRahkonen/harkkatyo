package com.example.harkkatyo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Consumption {
    private static Consumption consumption = null;

    private double clothingResult;
    private double commResult;
    private double electronicsResult;
    private double paperResult;
    private double consOtherResult;
    private double recreationResult;
    private double shoesResult;
    private double consTotalResult;
    private int clothing = 0;
    private int communications = 0;
    private int electronics = 0;
    private int other = 0;
    private int paper = 0;
    private int recreation = 0;
    private int shoes = 0;
    private String urlString;

    private Consumption(){}

    public static Consumption getInstance(){
        if (consumption == null){
            consumption = new Consumption();
        }
        return consumption; }

    public void consumptionResults(int clothing, int communications, int electronics, int other, int paper, int recreation, int shoes){
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
    }

    public int getClothing(){ return clothing; }
    public int getCommunications(){ return communications; }
    public int getElectronics(){ return electronics; }
    public int getPaper(){ return paper; }
    public int getConsumptionOther(){ return other; }
    public int getRecreation(){ return recreation; }
    public int getShoes(){ return shoes; }
    public double getClothingResult(){ return clothingResult; }
    public double getCommunicationsResult(){ return commResult; }
    public double getElectronicsResult(){ return electronicsResult; }
    public double getPaperResult(){ return paperResult; }
    public double getConsumptionOtherResult(){ return consOtherResult; }
    public double getRecreationResult(){ return recreationResult; }
    public double getShoesResult(){ return shoesResult; }
    public double getConsumptionTotal(){ return consTotalResult; }
    public String getURL(){return urlString;}
}
