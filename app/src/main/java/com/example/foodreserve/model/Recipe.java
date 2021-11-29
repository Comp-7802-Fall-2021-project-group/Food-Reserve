package com.example.foodreserve.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "recipes")
public class Recipe {
    /*
     * PRIVATE MEMBERS
     */

    @PrimaryKey(autoGenerate = true)
    private int rid;

    @ColumnInfo
    private String label;

    @ColumnInfo
    private String imageLink;

    @ColumnInfo
    private String source;

    @ColumnInfo
    private String url;

    @ColumnInfo
    private int yield;

    @ColumnInfo
    private ArrayList<String> dietLabels;

    @ColumnInfo
    private ArrayList<String> cautions;

    @ColumnInfo
    private ArrayList<String> ingredientLines;

    /*
     * CUSTOM CONSTRUCTOR
     */

    public Recipe(String label, String imageLink, String source, String url, int yield,
                  ArrayList<String> dietLabels, ArrayList<String> cautions, ArrayList<String> ingredientLines) {
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
    public int getRid() { return rid; }

    public void setRid(int rid) { this.rid = rid; }

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

    public ArrayList<String> getDietLabels() {
        return dietLabels;
    }

    public void setDietLabels(ArrayList<String> dietLabels) {
        this.dietLabels = dietLabels;
    }

    public ArrayList<String> getCautions() {
        return cautions;
    }

    public void setCautions(ArrayList<String> cautions) {
        this.cautions = cautions;
    }

    public ArrayList<String> getIngredientLines() {
        return ingredientLines;
    }

    public void setIngredientLines(ArrayList<String> ingredientLines) {
        this.ingredientLines = ingredientLines;
    }

    public String toString() {
        return "Label of the recipe: " + this.getLabel() + System.lineSeparator()
                + "Image link: " + this.getImageLink() + System.lineSeparator()
                + "Source: " + this.getSource() + System.lineSeparator()
                + "Url: " + this.getUrl() + System.lineSeparator()
                + "Yield: " + this.getUrl() + System.lineSeparator()
                + "Length of diet label: " + this.getDietLabels().size() + System.lineSeparator()
                + "Length of ingredient lines: " + this.getIngredientLines().size() + System.lineSeparator()
                + "Length of cautions: " + this.getCautions().size();
    }
}
