package com.example.foodreserve.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import foodreserve.R;

public class Recipes {

    private final String SEARCH_RECIPIES = "https://api.edamam.com/api/recipes/v2";
    private final String[] SEARCH_FIELDS = new String[] {"label", "image", "url", "ingredientLines", "ingredients"};
    String CHARSET = StandardCharsets.UTF_8.name();

    public void getRecipesList(String query) throws IOException {
        String link = createParams(query);
        URL url = new URL(link);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputline;
            StringBuilder content = new StringBuilder();
            while((inputline = in.readLine()) != null ) {
                content.append(inputline);
            }
            in.close();
        } finally {
            connection.disconnect();
        }
    }

    private String createParams(String query ) throws UnsupportedEncodingException {
        String result = null;

        result = String.format("%s?type=public&q=%s&app_id=%s&app_key=%s",
                SEARCH_RECIPIES,
                URLEncoder.encode(query, CHARSET),
                URLEncoder.encode(String.valueOf(R.string.app_id), CHARSET),
                URLEncoder.encode(String.valueOf(R.string.app_key), CHARSET));

        return result;
    }
}