package com.example.foodreserve.presenter;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import com.example.foodreserve.model.Recipes;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import foodreserve.R;

public class RecipesPresenter {

    final Recipes recipes;
    final String CHARSET = StandardCharsets.UTF_8.name();

    public RecipesPresenter() {
        recipes = new Recipes();
    }

    /*
     * PUBLIC BUTTON ACTION METHODS
     */
    public void getRecipes(String query, Context context) {
        try {
            getRecipesList(query, context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * PRIVATE METHODS
     */

    // External API call to get a list of recipes, reads through response and populates model
    private void getRecipesList(String query, Context context) throws IOException {

        new Thread(() -> {

            HttpsURLConnection connection = null;
            try {

                String link = createParams(query, context);
                URL url = new URL(link);
                connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                Log.d("responsecode", "code: " + connection.getResponseCode());
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputline;

                while((inputline = in.readLine()) != null ) {
                    JSONObject jsonObject = new JSONObject(inputline);
                    recipes.readResults(jsonObject);
                }

                in.close();
            } catch (Exception e){
                Log.e("oops", e.getMessage());
                e.printStackTrace();
            } finally {
                connection.disconnect();
            }
        }).start();
    }

    // Sets up the URL parameters required to get a response from external API and encodes secrets
    private String createParams(String query, Context context ) throws UnsupportedEncodingException {
        String result = null;
        Resources res = context.getResources();

        String app_id = URLEncoder.encode(res.getString(R.string.app_id), CHARSET);
        String app_key= URLEncoder.encode(res.getString(R.string.app_key), CHARSET);

        result = String.format("%s?type=public&q=%s&app_id=%s&app_key=%s",
                Recipes.SEARCH_RECIPIES,
                query,
                app_id,
                app_key);

        return result;
    }
}
