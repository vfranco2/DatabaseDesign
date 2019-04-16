package com.example.noods;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.noods.MainActivity.ip;

//----------------------------------------------------
//Noods by Vlad Franco, Jacob Couveau, and David Michon
//----------------------------------------------------

public class RecipeActivity extends AppCompatActivity {

    //UI/request things
    Button showRec;
    RequestQueue requestQueue;
    String showUrl = "http://"+ip+"/refreshRecipes.php";

    private List<Recipe> recipes;
    private RecyclerView recyclerViewRecipe;
    private GridLayoutManager gridLayoutRecipe;
    private RecipesAdapter adapterRecipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        recyclerViewRecipe = (RecyclerView) findViewById(R.id.recyclerviewrecipes);
        recipes = new ArrayList<>();
        gridLayoutRecipe = new GridLayoutManager(this, 1);
        recyclerViewRecipe.setLayoutManager(gridLayoutRecipe);
        adapterRecipe = new RecipesAdapter(this, recipes);
        recyclerViewRecipe.setAdapter(adapterRecipe);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        showRec = (Button) findViewById(R.id.showrecipes);

        //button listener, recipe refresh
        showRec.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                JsonObjectRequest jsonObjectRequestRecipe = new JsonObjectRequest(Request.Method.POST, showUrl, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        try {
                            JSONArray recipes = response.getJSONArray("recipes");
                            for (int i = 0; i < recipes.length(); i++) {
                                JSONObject objectrecipe = recipes.getJSONObject(i);

                                Recipe recipe = new Recipe(objectrecipe.getString("recipeName"), objectrecipe.getString("recipeLink"));
                                RecipeActivity.this.recipes.add(recipe);

                            }
                            adapterRecipe.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.append(error.getMessage());

                    }
                });
                requestQueue.add(jsonObjectRequestRecipe);
            }
        });
    }
}