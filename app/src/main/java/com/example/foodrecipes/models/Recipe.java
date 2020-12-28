package com.example.foodrecipes.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

public class Recipe implements Parcelable {
    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
    private String publisher;
    private String title;
    private String image_url;
    private String[] ingredients;
    private String recipe_id;
    private float social_rank;

    public Recipe() {
    }

    public Recipe(String publisher, String title, String image_url,
                  String[] ingredients, String recipe_id, float social_rank) {
        this.publisher = publisher;
        this.title = title;
        this.image_url = image_url;
        this.ingredients = ingredients;
        this.recipe_id = recipe_id;
        this.social_rank = social_rank;
    }

    protected Recipe(Parcel in) {
        publisher = in.readString();
        title = in.readString();
        image_url = in.readString();
        ingredients = in.createStringArray();
        recipe_id = in.readString();
        social_rank = in.readFloat();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }

    public float getSocial_rank() {
        return social_rank;
    }

    public void setSocial_rank(float social_rank) {
        this.social_rank = social_rank;
    }

    @Override
    public String toString() {
        return "recipe{" +
                "publisher='" + publisher + '\'' +
                ", title='" + title + '\'' +
                ", image_url='" + image_url + '\'' +
                ", ingredients=" + Arrays.toString(ingredients) +
                ", recipe_id='" + recipe_id + '\'' +
                ", social_rank=" + social_rank +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(publisher);
        dest.writeString(title);
        dest.writeString(image_url);
        dest.writeStringArray(ingredients);
        dest.writeString(recipe_id);
        dest.writeFloat(social_rank);
    }
}
