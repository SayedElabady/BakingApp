package sayed.com.bakeryapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

import sayed.com.bakeryapp.R;
import sayed.com.bakeryapp.listener.RecyclerViewItemClickListener;
import sayed.com.bakeryapp.main.MainContract;
import sayed.com.bakeryapp.model.Recipe;

/**
 * Created by Sayed on 10/17/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> implements RecyclerViewItemClickListener {
    List<Recipe> recipeList;
    MainContract.Presenter presenter;

    public RecipeAdapter(List<Recipe> recipeList, MainContract.Presenter presenter) {
        this.recipeList = recipeList;
        this.presenter = presenter;
    }

    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_item, parent, false);

        return new RecipeViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.textView.setText(recipeList.get(position).getRecipeName());


    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    @Override
    public void onItemClick(int itemPosition) {
        presenter.onRecipeCardClicked(recipeList.get(itemPosition));
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;
        RecyclerViewItemClickListener recyclerViewItemClickListener;

        public RecipeViewHolder(View itemView, RecyclerViewItemClickListener recyclerViewItemClickListener) {
            super(itemView);
            this.recyclerViewItemClickListener = recyclerViewItemClickListener;
            textView = itemView.findViewById(R.id.recipe_item_text_view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            recyclerViewItemClickListener.onItemClick(getAdapterPosition());
        }
    }
}
