package com.example.noods;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {

    private Context context;
    private List<Ingredient> ingredients;

    //Card clicking stuff
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }


    public IngredientsAdapter(Context context, List<Ingredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);
        ViewHolder vh = new ViewHolder(itemView, mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.ingredientName.setText(ingredients.get(position).getIngredientName());
        holder.heldAmount.setText(String.valueOf(ingredients.get(position).getHeldAmount()));
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView ingredientName;
        public TextView heldAmount;
        public ImageView editorBtn;
        public ImageView editorBtn2;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            ingredientName = (TextView) itemView.findViewById(R.id.ingredientname);
            heldAmount = (TextView) itemView.findViewById(R.id.held);
            editorBtn = (ImageView) itemView.findViewById(R.id.editor);
            //editorBtn.setOnClickListener(this);
            editorBtn2 = (ImageView) itemView.findViewById(R.id.editor2);
            //editorBtn.setOnClickListener(this);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if (listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
/*
        //click handler
        @Override
        public void onClick(View v) {
            final int position = getAdapterPosition();
            //showPopupMenu(v,position);
            Toast.makeText(context, ingredients.get(position).getIngredientName()+" has been added", Toast.LENGTH_SHORT).show();

            //update query
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
                    Map<String, String> parameters  = new HashMap<String, String>();
                    double ingredientId = 100+position;

                    parameters.put("ingredientid", String.valueOf(ingredientId));
                    return parameters;
                }
            };
            requestQueue.add(requestUpd);
        }
        */
    }
}