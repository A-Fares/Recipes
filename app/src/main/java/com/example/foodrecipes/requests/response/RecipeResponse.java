package com.example.foodrecipes.requests.response;

import androidx.annotation.NonNull;

import com.example.foodrecipes.models.Recipe;

public class RecipeResponse {
    private Recipe recipe;

    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public String toString() {
        return "RecipeResponse{" +
                "recipe=" + recipe +
                '}';
    }
}
