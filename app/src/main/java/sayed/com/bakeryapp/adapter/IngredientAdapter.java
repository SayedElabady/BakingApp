package sayed.com.bakeryapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sayed.com.bakeryapp.R;
import sayed.com.bakeryapp.main.MainContract;
import sayed.com.bakeryapp.model.Ingredient;

/**
 * Created by Sayed on 10/18/2017.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {
    List<Ingredient> ingredients;
    public IngredientAdapter(List<Ingredient> list){
        ingredients= list;
    }
    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingredient_item , parent , false);
        return new IngredientViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        holder.textView.setText(ingredient.getIngredientType() + "\n" +
                "Quantity: " + ingredient.getQuantity() + "\n" +
                "Measure: " + ingredient.getMeasure() + "\n");
    }

    @Override
    public int getItemCount() {
        return ingredients != null ? ingredients.size() : 0;
    }

    public class IngredientViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public IngredientViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.ingredient_item_view);
        }
    }
}
