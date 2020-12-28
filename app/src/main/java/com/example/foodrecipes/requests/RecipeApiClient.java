package com.example.foodrecipes.requests;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodrecipes.models.Recipe;
import com.example.foodrecipes.requests.response.RecipeResponse;
import com.example.foodrecipes.requests.response.RecipeSearchResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeApiClient {

    private static final String TAG = "RecipeApiClient";
    private static RecipeApiClient instance;
    private MutableLiveData<List<Recipe>> mRecipes;
    private MutableLiveData<Recipe> mRecipe;
    private MutableLiveData<Boolean> mRecipeRequestTimedOut = new MutableLiveData<>();
    private boolean cancelRequest;

    public RecipeApiClient() {
        mRecipes = new MutableLiveData<>();
        mRecipe = new MutableLiveData<>();
        cancelRequest = false;
    }

    public static RecipeApiClient getInstance() {
        if (instance == null)
            instance = new RecipeApiClient();
        return instance;
    }

    public LiveData<List<Recipe>> getRecipes() {
        return mRecipes;
    }

    public LiveData<Recipe> getRecipe() {
        return mRecipe;
    }

    public LiveData<Boolean> isRecipeRequestTimedOut() {
        return mRecipeRequestTimedOut;
    }

    public void searchRecipeById(String recipeId) {
        mRecipeRequestTimedOut.setValue(false);
        getRecipe(recipeId).enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                Recipe recipe = response.body().getRecipe();
                mRecipe.postValue(recipe);
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                mRecipe.postValue(null);
                mRecipeRequestTimedOut.postValue(true);
            }
        });
    }


    public void searchRecipesApi(String query, int pageNumber) {
        if (cancelRequest)
            getRecipes(query, pageNumber).cancel();

        getRecipes(query, pageNumber).enqueue(new Callback<RecipeSearchResponse>() {
            @Override
            public void onResponse(Call<RecipeSearchResponse> call, Response<RecipeSearchResponse> response) {
                List<Recipe> list = new ArrayList<>(response.body().getRecipes());
                if (pageNumber == 1)
                    mRecipes.postValue(list);
                else {
                    List<Recipe> currentRecipes = mRecipes.getValue();
                    currentRecipes.addAll(list);
                    mRecipes.postValue(currentRecipes);
                }
            }

            @Override
            public void onFailure(Call<RecipeSearchResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                mRecipes.postValue(null);
            }
        });

    }

    private Call<RecipeSearchResponse> getRecipes(String query, int pageNumber) {
        return ServiceGenerator.getRecipeApi().searchRecipe(query, String.valueOf(pageNumber));
    }

    private Call<RecipeResponse> getRecipe(String recipeId) {
        return ServiceGenerator.getRecipeApi().getRecipe(recipeId);
    }

    public void cancelRequest() {
        cancelRequest = true;
    }
}
