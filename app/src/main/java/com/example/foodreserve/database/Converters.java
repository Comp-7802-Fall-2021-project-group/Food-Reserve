package com.example.foodreserve.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public static ArrayList<String> fromString(String s) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(s, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<String> l) {
        Gson gson = new Gson();
        String json = gson.toJson(l);
        return json;
    }
}
