package com.example.foodrecipes.requests.response;

import com.example.foodrecipes.models.Recipe;

import java.util.List;

public class RecipeSearchResponse {
    private int count;
    private List<Recipe> recipes;

    public int getCount() {
        return count;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    @Override
    public String toString() {
        return "RecipeSearchResponse{" +
                "count=" + count +
                ", recipes=" + recipes +
                '}';
    }
}
