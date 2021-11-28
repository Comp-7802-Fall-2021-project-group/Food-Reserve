package com.example.foodreserve.presenter;

import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;

import com.example.foodreserve.model.Recipe;
import com.example.foodreserve.model.Recipes;
import com.example.foodreserve.view.RecipesAdapter;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

import foodreserve.R;

public class RecipesPresenter {

    final String TAG = "RecipesPresenter";

    Recipes recipes;
    RecipesAdapter adapter;
    final String CHARSET = StandardCharsets.UTF_8.name();

    public RecipesPresenter() {
        recipes = new Recipes();
        adapter = null;
    }

    /*
     * PUBLIC BUTTON ACTION METHODS
     */

    public Recipe getRecipe(int index) {
        return recipes.getRecipe(index);
    }

    public Recipes getRecipes() {
        return recipes;
    }

    public Recipes getRecipes(String query, Context context) {
        try {
            getRecipesListFromAPI(query, context);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return recipes;
    }

    public int getRecipesCount() {
        return recipes.getCount();
    }

    public void setRecipesAdapter(RecipesAdapter adp) {
        adapter = adp;
    }

    /*
     * PRIVATE METHODS
     */

    // External API call to get a list of recipes, reads through response and populates model
    private void getRecipesListFromAPI(String query, Context context) throws IOException {
        GetRecipesTask task = new GetRecipesTask(context);
        task.execute(query);
    }

    // Sets up the URL parameters required to get a response from external API and encodes secrets
    private String createParams(String query, Context context ) throws UnsupportedEncodingException {
        String result = null;
        Resources res = context.getResources();

        String app_id = URLEncoder.encode(res.getString(R.string.app_id), CHARSET);
        String app_key= URLEncoder.encode(res.getString(R.string.app_key), CHARSET);

        result = String.format("%s?type=public&q=%s&app_id=%s&app_key=%s",
                Recipes.SEARCH_RECIPIES,
                query,
                app_id,
                app_key);

        return result;
    }

    private class GetRecipesTask extends AsyncTask<String, Void, JSONObject> {

        private Context context;

        public GetRecipesTask(Context con) {
            context = con;
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            JSONObject jsonObject = null;
            HttpsURLConnection connection = null;
            StringBuffer response = null;
            try {

                String link = createParams(strings[0], context);
                URL url = new URL(link);
                connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                Log.d("responsecode", "code: " + connection.getResponseCode());
                if(connection.getResponseCode() == 200) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String inputline;
                    response = new StringBuffer();

                    while((inputline = in.readLine()) != null ) {
                        response.append(inputline);
                    }
                    in.close();
                    jsonObject = new JSONObject(response.toString());
                }
            } catch (Exception e){
                Log.e("oops", e.getMessage());
                e.printStackTrace();
            } finally {
                connection.disconnect();
            }

            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            recipes.readResults(jsonObject);
            adapter.updateRecipes();
        }
    }
}
