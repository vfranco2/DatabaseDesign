package com.example.noods;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.support.design.widget.FloatingActionButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //PHP script urls
    //Yeah yeah it's public, would bother with getter if ip retrieval worked
    public static String ip = "192.168.1.182";
    String insertUrl = "http://"+ip+"/insertIngredient.php";
    String showUrl = "http://"+ip+"/showIngredients.php";
    String updateUrl = "http://"+ip+"/updateIngredient.php";

    //Instantiate UI elements, POST request
    EditText ingredientName, heldAmt;
    Button insert, show;
    RequestQueue requestQueue;

    private List<Ingredient> ingredients;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayout;
    private IngredientsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Match UI elements to XML
        ingredientName = (EditText) findViewById(R.id.editText);
        heldAmt = (EditText) findViewById(R.id.editText2);
        insert = (Button) findViewById(R.id.insert);
        show = (Button) findViewById(R.id.showstudents);

        //Call getIngredientsFromDB method (POST)
        getIngredientsFromDB(0);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        ingredients = new ArrayList<>();
        gridLayout = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(gridLayout);
        adapter = new IngredientsAdapter(this, ingredients);
        recyclerView.setAdapter(adapter);
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        //Navigate to recipes
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RecipeActivity.class));
            }
        });
        //Navigate to accounts
        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AccountActivity.class));
            }
        });

        //HANDLE INSERT REQUEST CLICKLISTENER
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, insertUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters  = new HashMap<String, String>();
                        parameters.put("ingredientName", ingredientName.getText().toString());
                        parameters.put("heldAmt", heldAmt.getText().toString());
                        return parameters;
                    }
                };
                requestQueue.add(request);
            }

        });

        //HANDLE INGREDIENT AMOUNTS REQUEST CLICKLISTENER
        adapter.setOnItemClickListener(new IngredientsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                final int pos = position+101;
                Toast.makeText(getApplicationContext(), "Added item at ID: " + pos, Toast.LENGTH_SHORT).show();
                StringRequest requestUpd = new StringRequest(Request.Method.POST, updateUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> parameters  = new HashMap<String, String>();

                        parameters.put("ingredientid", String.valueOf(pos));
                        return parameters;
                    }
                };
                requestQueue.add(requestUpd);
            }
        });
    }

    //HANDLE INGREDIENT REFRESHING REQUEST CLICKLISTENER
    private void getIngredientsFromDB(int id){
        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                System.out.println("ww");
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, showUrl, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        try {
                            JSONArray ingredients = response.getJSONArray("ingredients");
                            clear();
                            for (int i = 0; i < ingredients.length(); i++) {
                                JSONObject object = ingredients.getJSONObject(i);

                                Ingredient ingredient = new Ingredient(object.getString("ingredientName"), object.getDouble("heldAmt"));
                                MainActivity.this.ingredients.add(ingredient);
                            }
                            adapter.notifyDataSetChanged();

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
                requestQueue.add(jsonObjectRequest);
            }
        });
    }

    //Clears current recyclerview array to allow new data to appear at top
    public void clear() {
        final int size = ingredients.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                ingredients.remove(0);
            }
            adapter.notifyDataSetChanged();
        }
    }


}