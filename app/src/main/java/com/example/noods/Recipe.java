package com.example.noods;

public class Recipe {

    private String recipeName;
    private String recipeLink;
//
    public Recipe(String recipeName, String recipeLink) {
        this.recipeName = recipeName;
        this.recipeLink = recipeLink;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public String getRecipeLink() {
        return recipeLink;
    }

    public void setRecipeLink(String recipeLink) {
        this.recipeLink = recipeLink;
    }
}