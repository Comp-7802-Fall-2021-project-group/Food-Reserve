package com.example.foodreserve.model;

import org.json.JSONArray;

public class Recipe {
    /*
     * PRIVATE MEMBERS
     */
    private String label;
    private String imageLink;
    private String source;
    private String url;
    private int yield;
    private JSONArray dietLabels;
    private JSONArray cautions;
    private JSONArray ingredientLines;

    /*
     * CUSTOM CONSTRUCTOR
     */

    public Recipe(String label, String imageLink, String source, String url, int yield,
                  JSONArray dietLabels, JSONArray cautions, JSONArray ingredientLines) {
        this.label = label;
        this.imageLink = imageLink;
        this.source = source;
        this.url = url;
        this.yield = yield;
        this.dietLabels = dietLabels;
        this.cautions = cautions;
        this.ingredientLines = ingredientLines;
    }

    /*
     * ACCESS METHODS
     */
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getYield() {
        return yield;
    }

    public void setYield(int yield) {
        this.yield = yield;
    }

    public JSONArray getDietLabels() {
        return dietLabels;
    }

    public void setDietLabels(JSONArray dietLabels) {
        this.dietLabels = dietLabels;
    }

    public JSONArray getCautions() {
        return cautions;
    }

    public void setCautions(JSONArray cautions) {
        this.cautions = cautions;
    }

    public JSONArray getIngredientLines() {
        return ingredientLines;
    }

    public void setIngredientLines(JSONArray ingredientLines) {
        this.ingredientLines = ingredientLines;
    }
}
