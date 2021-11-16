package com.example.foodreserve.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodreserve.presenter.RecipesPresenter;

import foodreserve.R;

public class RecipesActivity extends AppCompatActivity {

    private RecipesPresenter presenter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        presenter = new RecipesPresenter();

        // Get the intent that started this activity with the query param
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");

        getRecipesAction(message);
    }

    /*
     * PUBLIC METHOD FOR BUTTON ACTIONS
     */
    public void getRecipesAction(String message) {
        presenter.getRecipes(message, this);
    }
}