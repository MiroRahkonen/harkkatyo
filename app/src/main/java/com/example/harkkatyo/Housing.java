package com.example.harkkatyo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Housing {
    private static Housing housing = null;

    private double housingResult;
    private int area;
    private int residents;
    private String type;

    private Housing(){
        housingResult = 0;
        area = 0;
        residents = 0;
        type = "flat";
    }

    public static Housing getInstance(){
        if (housing == null){
            housing = new Housing();
        }
        return housing;}

    public void housingResults(int area, int residents, String type){
        this.area = area;
        this.residents = residents;
        this.type = type;
        try {
            String urlString = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/HousingCalculator/InfrastructureEstimate?type=" + type + "&area=" + area + "&residents=" + residents;
            System.out.println(urlString);
            InputStream input = JsonApi.newInstance().getInput(urlString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            housingResult = Double.parseDouble(reader.readLine());

        }catch (NumberFormatException | IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public double getResult() { return housingResult; }
    public int getArea() { return area; }
    public int getResidents() { return residents; }
    public String getType() { return type; }
}
