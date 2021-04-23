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

    private Consumption(){
        clothing = 0;
        communications = 0;
        electronics = 0;
        other = 0;
        paper = 0;
        recreation = 0;
        shoes = 0;
    }

    public static Consumption getInstance(){ return consumption; }

    public void consumptionResults(int clothing, int communications, int electronics, int other, int paper, int recreation, int shoes){
        this.clothing = clothing;
        this.communications = communications;
        this.electronics = electronics;
        this.other = other;
        this.paper = paper;
        this.recreation = recreation;
        this.shoes = shoes;
        try {
            String urlString = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/ConsumptionCalculator?query.clothing=" + clothing + "&query.communications=" + communications + "&query.electronics=" + electronics + "&query.other=" + other + "&query.paper=" + paper + "&query.recreation=" + recreation + "&query.shoes=" + shoes;
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
}
