package sayed.com.bakeryapp.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Sayed on 10/17/2017.
 */

public class Ingredient implements Serializable {
    @SerializedName("quantity")
    float quantity;
    @SerializedName("measure")
    String measure;

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(String ingredientType) {
        this.ingredientType = ingredientType;
    }

    @SerializedName("ingredient")
    String ingredientType;

}
