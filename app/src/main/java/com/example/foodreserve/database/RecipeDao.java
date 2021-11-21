package com.example.foodreserve.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodreserve.model.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipes")
    List<Recipe> getAllRecipes();

    @Query("SELECT * FROM recipes WHERE rid = :rid")
    Recipe getRecipeById(int rid);

    @Query("SELECT * FROM recipes WHERE label = :label")
    Recipe getRecipeByLabel(String label);

    @Insert
    void insertRecipe(Recipe recipe);

    @Update
    void updateRecipe(Recipe newRecipe);

    @Delete
    void deleteRecipe(Recipe recipe);
}
