package com.example.harkkatyo;



import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class JsonApi {
    private static JsonApi jsonApi = new JsonApi();

    private JsonApi(){
    }

    public static JsonApi newInstance() { return jsonApi; }


    protected InputStream getInput(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setReadTimeout(10000);
        connection.setConnectTimeout(10000);
        connection.setRequestMethod("GET");
        return new BufferedInputStream(connection.getInputStream());
    }

    protected JSONObject getJson(String urlString) throws IOException, JSONException {
        String response = null;
        InputStream input = getInput(urlString);
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder builder = new StringBuilder();
        String temp = null;
        while ((temp = reader.readLine()) != null) {
            builder.append(temp);
        }
        response = builder.toString();
        input.close();
        if (response != null) {
            return new JSONObject(response);
        }
        return new JSONObject();
    }

}
