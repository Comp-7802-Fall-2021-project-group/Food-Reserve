package com.example.foodreserve.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodreserve.model.Recipe;
import com.example.foodreserve.model.Recipes;

import foodreserve.R;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeItemViewHolder> {

    final String TAG = "CardViewRecipeAdapter";

    private Recipes rec;

    public RecipesAdapter(Recipes recipes) {
        rec = recipes;
    }

    @NonNull
    @Override
    public RecipeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeItemViewHolder viewHolder, int i) {
        Recipe recipe = rec.getRecipe(i);
        String label = recipe.getLabel();
        viewHolder.recipeTitle.setText(label);
    }

    @Override
    public int getItemCount() {
        return rec.getCount();
    }

    public static class RecipeItemViewHolder extends RecyclerView.ViewHolder {
        TextView recipeTitle;

        public RecipeItemViewHolder(@NonNull View itemView) {
            super(itemView);

            recipeTitle = itemView.findViewById(R.id.recipeItemTitle);
        }
    }

    // Update the recipes list when something changes
    public void updateRecipes(Recipes recipes) {
        rec = recipes;
    }
}
