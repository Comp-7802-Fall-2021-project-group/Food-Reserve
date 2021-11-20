package com.example.foodreserve.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodreserve.model.Recipes;
import com.example.foodreserve.presenter.RecipesPresenter;

import foodreserve.R;

public class RecipesActivity extends AppCompatActivity {

    private RecipesPresenter presenter = null;
    private Recipes recipes;
    private RecyclerView recyclerView;
    private RecipesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        // Get the intent that started this activity with the query param
        Intent intent = getIntent();
        String message = intent.getStringExtra("message");

        presenter = new RecipesPresenter();
        recipes = presenter.getRecipes(message, this);

        // Set up recipes recyclerview * layout orientation
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = findViewById(R.id.recipesView);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new RecipesAdapter(recipes);
        recyclerView.setAdapter(adapter);

        updateRecipesAdapter(message);
    }

    /*
     * PUBLIC METHOD FOR BUTTON ACTIONS
     */

    // External API call on button click
    // For future use if searching by keyword instead of photo
    public void getRecipesAction(String query) {
        recipes = presenter.getRecipes(query, this);
        updateRecipesAdapter(query);
    }

    // Closes the view and takes the user back to parent view
    public void cancelButton(View view) {
        finish();
    }

    private void updateRecipesAdapter(String query) {
        recipes = presenter.getRecipes(query, this);
        recyclerView.setAdapter(adapter);
    }
}