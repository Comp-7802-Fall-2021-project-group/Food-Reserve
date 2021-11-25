package com.example.foodreserve.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodreserve.presenter.RecipesPresenter;

import foodreserve.R;

public class RecipesActivity extends AppCompatActivity {

    private RecipesPresenter presenter = null;
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
        if(message != null) {
            presenter.getRecipes(message, this);
        }

        // Set up recipes recyclerview * layout orientation
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = findViewById(R.id.recipesView);
        recyclerView.setLayoutManager(layoutManager);

        // Hook up the recyclerview adapter with the presenter
        // Adapter has to request data from the presenter to load into the view
        adapter = new RecipesAdapter(presenter);
        recyclerView.setAdapter(adapter);
        presenter.setRecipesAdapter(adapter);

        updateRecipesAdapter();
    }

    /*
     * PUBLIC METHOD FOR BUTTON ACTIONS
     */

    // External API call on button click
    // For future use if searching by keyword instead of photo
    public void getRecipesAction(String query) {
        presenter.getRecipes(query, this);
        updateRecipesAdapter();
    }

    // Closes the view and takes the user back to parent view
    public void cancelButton(View view) {
        finish();
    }

    private void updateRecipesAdapter() {
        adapter.updateRecipes();
        recyclerView.setAdapter(adapter);
    }
}