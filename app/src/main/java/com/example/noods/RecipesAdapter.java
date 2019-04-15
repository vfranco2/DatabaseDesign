package com.example.noods;

import android.content.Context;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private Context contextRec;
    private List<Recipe> recipes;

    public RecipesAdapter(Context contextRec, List<Recipe> recipes) {
        this.contextRec = contextRec;
        this.recipes = recipes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parentRec, int viewType) {
        View itemViewRec = LayoutInflater.from(parentRec.getContext()).inflate(R.layout.cardrecipe,parentRec,false);
        return new ViewHolder(itemViewRec);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holderRec, int position) {
        holderRec.recipeName.setText(recipes.get(position).getRecipeName());
        holderRec.recipeLink.setText(recipes.get(position).getRecipeLink());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public  class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView recipeName;
        public TextView recipeLink;
        public ViewHolder(View itemView) {
            super(itemView);
            recipeName = (TextView) itemView.findViewById(R.id.recipename);
            recipeLink = (TextView) itemView.findViewById(R.id.recipelink);
            recipeName.setOnClickListener(this);
        }

        //click handler
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Toast.makeText(contextRec, recipes.get(position).getRecipeName()+" has been selected", Toast.LENGTH_SHORT).show();
            Uri webpage = Uri.parse(recipes.get(position).getRecipeLink());
            launchWeb(webpage);
        }
    }
    public void launchWeb(Uri holderRec){
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        final CustomTabsIntent intent = new CustomTabsIntent.Builder().build();
        intent.launchUrl(contextRec, holderRec);
        builder.setToolbarColor(ContextCompat.getColor(contextRec, R.color.colorPrimary));
    }
}