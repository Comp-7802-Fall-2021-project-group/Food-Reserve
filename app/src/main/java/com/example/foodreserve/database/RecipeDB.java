package com.example.foodreserve.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.foodreserve.model.Recipe;

@Database(entities = {Recipe.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class RecipeDB extends RoomDatabase {

    public abstract RecipeDao recipeDao();

/** Singleton Pattern */
//    private static final String DB_NAME = "recipe_db";
//
//    private static RecipeDB instance;
//
//    public static synchronized RecipeDB getInstance(Context context) {
//        if(instance != null) {
//            instance = Room.databaseBuilder(context.getApplicationContext(), RecipeDB.class, DB_NAME).fallbackToDestructiveMigration().build();
//        }
//        return instance;
//    }
}
