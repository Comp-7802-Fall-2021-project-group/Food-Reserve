package com.example.foodreserve;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.foodreserve.database.RecipeDB;
import com.example.foodreserve.database.RecipeDao;
import com.example.foodreserve.model.Recipe;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    private RecipeDao recipeDao;
    private RecipeDB db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, RecipeDB.class).build();
        recipeDao = db.recipeDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeAndReadOneRecipe() throws Exception {
        ArrayList<String> ingredientLines = new ArrayList<>();
        ArrayList<String> dietLabels = new ArrayList<>();
        ArrayList<String> cautions = new ArrayList<>();

        ingredientLines.add("ingredient line 1");
        dietLabels.add("dietLabels1");
        cautions.add("cautions1");
        Recipe recipe = new Recipe("apple", "imageLink1", "source1", "url1", 123, dietLabels, cautions, ingredientLines);
        recipeDao.insertRecipe(recipe);
        List<Recipe> testRecipe = recipeDao.getAllRecipes();
        System.out.println(testRecipe.get(0).getLabel() + " " );
        assertEquals(testRecipe.get(0).getLabel(), recipe.getLabel());
    }

    @Test
    public void writeAndReadThreeRecipes() throws Exception {
        ArrayList<String> ingredientLines = new ArrayList<>();
        ArrayList<String> dietLabels = new ArrayList<>();
        ArrayList<String> cautions = new ArrayList<>();

        ingredientLines.add("ingredient line 1");
        ingredientLines.add("ingredient line 2");
        ingredientLines.add("ingredient line 3");

        dietLabels.add("dietLabels1");
        dietLabels.add("dietLabels2");
        dietLabels.add("dietLabels3");

        cautions.add("cautions1");
        cautions.add("cautions2");
        cautions.add("cautions3");

        Recipe recipe1 = new Recipe("r1", "image1", "s1", "url1", 111, dietLabels, cautions, ingredientLines);
        Recipe recipe2 = new Recipe("r2", "image2", "s2", "url2", 222, dietLabels, cautions, ingredientLines);
        Recipe recipe3 = new Recipe("r3", "image3", "s3", "url3", 333, dietLabels, cautions, ingredientLines);

        recipeDao.insertRecipe(recipe1);
        recipeDao.insertRecipe(recipe2);
        recipeDao.insertRecipe(recipe3);

        List<Recipe> testRecipes = recipeDao.getAllRecipes();

        testRecipes.stream().forEach(e -> System.out.println(e.toString()));

        assertEquals(testRecipes.size(), 3);


        assertEquals(testRecipes.get(0).getLabel(), "r1");
        assertEquals(testRecipes.get(0).getYield(), 111);
        assertEquals(testRecipes.get((0)).getIngredientLines().size(), 3);

        assertEquals(testRecipes.get(1).getImageLink(), "image2");
        assertEquals(testRecipes.get(1).getYield(), 222);
        assertEquals(testRecipes.get((1)).getCautions().size(), 3);

        assertEquals(testRecipes.get(2).getSource(), "s3");
        assertEquals(testRecipes.get(2).getYield(), 333);
        assertEquals(testRecipes.get((2)).getDietLabels().size(), 3);
    }


}
