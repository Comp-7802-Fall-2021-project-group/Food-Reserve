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
        return recipes.size();
    }

    public Recipe getRecipe(int index) {
        return recipes.get(index);
    }

    public ArrayList<Recipe> getRecipes() { return recipes; }

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

                JSONArray dietLabelsArr = oneRecipe.getJSONArray("dietLabels");
                ArrayList<String> dietLabels = toList(dietLabelsArr);

                JSONArray cautionsArr = oneRecipe.getJSONArray("cautions");
                ArrayList<String> cautions = toList(cautionsArr);

                JSONArray ingredientLinesArr = oneRecipe.getJSONArray("ingredientLines");
                ArrayList<String> ingredientLines = toList(ingredientLinesArr);

                recipe = new Recipe(label, imageLink, source, url, yield, dietLabels, cautions, ingredientLines);

                recipes.add(recipe);
                Log.d(TAG, label);

            } catch (JSONException e) {
                Log.e(TAG, "unable to parse individual recipe result" + e.getMessage());
            }

        }
    }

    private ArrayList<String> toList(JSONArray ja) throws RuntimeException{
        ArrayList<String> l = new ArrayList<>();

        try{
            for(int i = 0; i < ja.length(); ++i) {
                l.add(ja.getString(i));
            }
            return l;
        } catch (JSONException e){
            throw new RuntimeException("Error in processing array of strings", e);
        }

    }

}