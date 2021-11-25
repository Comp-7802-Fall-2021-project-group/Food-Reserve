package com.example.foodreserve.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodreserve.model.Recipe;
import com.example.foodreserve.model.Recipes;
import com.example.foodreserve.presenter.RecipesPresenter;

import foodreserve.R;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipeItemViewHolder> {

    final String TAG = "CardViewRecipeAdapter";
    RecipesPresenter presenter;
    Recipes rec;

    public RecipesAdapter(RecipesPresenter pre) {
        presenter = pre;
        rec = presenter.getRecipes();
    }

    @NonNull
    @Override
    public RecipeItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeItemViewHolder viewHolder, int i) {
        Recipe recipe = presenter.getRecipe(i);
        String label = recipe.getLabel();
        viewHolder.recipeTitle.setText(label);
    }

    @Override
    public int getItemCount() {
        return presenter.getRecipesCount();
    }

    public static class RecipeItemViewHolder extends RecyclerView.ViewHolder {
        TextView recipeTitle;

        public RecipeItemViewHolder(@NonNull View itemView) {
            super(itemView);

            recipeTitle = itemView.findViewById(R.id.recipeItemTitle);
        }
    }

    // Update the recipes list when something changes
    public void updateRecipes() {
        rec = presenter.getRecipes();
        for(int i = 0 ; i < rec.getCount(); i++) {
            notifyItemChanged(i);
        }
    }
}
