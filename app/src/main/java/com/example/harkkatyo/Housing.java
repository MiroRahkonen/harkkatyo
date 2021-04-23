package com.example.harkkatyo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class Housing {




    public Housing housingResults(int area, int residents, String type, int year, int floors){
        try {
            String urlString = "";
            System.out.println(urlString);
            JSONObject object = JsonApi.newInstance().getJson(urlString);

        }catch (NumberFormatException | JSONException | IOException | NullPointerException e) {
            e.printStackTrace();
        }
        return this;
    }
}
