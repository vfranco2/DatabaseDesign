package com.example.noods;

public class Ingredient {

    private String ingredientName;
    private double heldAmount;

    public Ingredient(String ingredientName, double heldAmount) {
        this.ingredientName = ingredientName;
        this.heldAmount = heldAmount;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public double getHeldAmount() {
        return heldAmount;
    }

    public void setHeldAmount(Double heldAmount) {
        this.heldAmount = heldAmount;
    }
}