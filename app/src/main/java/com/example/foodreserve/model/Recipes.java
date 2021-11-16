package com.example.foodreserve.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Recipes {

    public static final String SEARCH_RECIPIES = "https://api.edamam.com/api/recipes/v2";
    public static final String[] SEARCH_FIELDS = new String[] {"label", "image", "url", "ingredientLines", "ingredients"};
    static final String TAG = "RecipesModel";

    /*
     * PRIVATE MEMBERS
     */
    private int from;
    private int to;
    private int count;
    private JSONArray hits;
    private ArrayList<Recipe> recipes;

    /*
     * CONSTRUCTOR & ACCESS METHODS
     */

    public Recipes() {
        this.recipes = new ArrayList<>();
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /*
     * CUSTOM METHODS TO PARSE JSON RESULTS FROM API CALLS
     */

    // Read the the main JSON results
    public void readResults(JSONObject jsonObject) {
        try {
            this.to = jsonObject.getInt("to");
            this.from = jsonObject.getInt("from");
            this.hits = jsonObject.getJSONArray("hits");

        } catch (JSONException e) {
            Log.e(TAG, "unable to parse jsonobject " + e.getMessage());
        }

        readHits();
    }

    // Read through each "hit" (aka result) and creates a Recipe object,
    // each recipe object is then added to Recipes collection
    private void readHits() {
        for (int i=0; i < hits.length(); i++)
        {
            Recipe recipe;

            try {
                JSONObject oneHit = hits.getJSONObject(i);
                JSONObject oneRecipe = oneHit.getJSONObject("recipe");

                // Pulling items from the recipe object
                String label = oneRecipe.getString("label");
                String imageLink = oneRecipe.getString("image");
                String source = oneRecipe.getString("source");
                String url = oneRecipe.getString("url");
                int yield = oneRecipe.getInt("yield");
                JSONArray dietLabels = oneRecipe.getJSONArray("dietLabels");
                JSONArray cautions = oneRecipe.getJSONArray("cautions");
                JSONArray ingredientLines = oneRecipe.getJSONArray("ingredientLines");

                recipe = new Recipe(label, imageLink, source, url, yield, dietLabels, cautions, ingredientLines);

                recipes.add(recipe);
                Log.d(TAG, recipe.toString());

            } catch (JSONException e) {
                Log.e(TAG, "unable to parse individual recipe result" + e.getMessage());
            }
        }

        Log.d(TAG, "parsed through results and got " + recipes.size() + " recipes");
    }
}