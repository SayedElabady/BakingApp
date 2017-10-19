package sayed.com.bakeryapp.details;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import sayed.com.bakeryapp.R;
import sayed.com.bakeryapp.adapter.IngredientAdapter;
import sayed.com.bakeryapp.adapter.StepAdapter;
import sayed.com.bakeryapp.listener.RecyclerViewItemClickListener;
import sayed.com.bakeryapp.model.Ingredient;
import sayed.com.bakeryapp.model.Recipe;
import sayed.com.bakeryapp.widget.BakingService;

/**
 * Created by Sayed on 10/18/2017.
 */

public class SelectARecipeStepFragment extends Fragment {
    Recipe recipe;
    @BindView(R.id.ingredients_recycler_view)
    RecyclerView ingredientsRecyclerView;
    @BindView(R.id.steps_recycler_view)
    RecyclerView stepsRecyclerView;
    Unbinder unbinder;
    DetailsContract.Presenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.select_recipe_step_fragment, container, false);
        ButterKnife.bind(this, rootView);
        Bundle recipeBundle = getArguments();
        if (savedInstanceState != null)
            recipe = (Recipe) savedInstanceState.get("recipe");
        else if(recipeBundle != null)
            recipe = (Recipe) recipeBundle.get("recipe");
        else recipe = new Recipe();

        IngredientAdapter ingredientAdapter = new IngredientAdapter(recipe.getIngredientList());
        ingredientsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ingredientsRecyclerView.setAdapter(ingredientAdapter);

        StepAdapter stepAdapter = new StepAdapter(recipe.getStepList(), presenter);
        stepsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        stepsRecyclerView.setAdapter(stepAdapter);
        ArrayList<String> ingredientsList = getStringFromIngredients(recipe.getIngredientList());
        BakingService.startUpdateService(getContext(), recipe);
        return rootView;
    }

    public void setPresenter(DetailsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private ArrayList<String> getStringFromIngredients(List<Ingredient> ingredients) {
        ArrayList<String> stringIngredientList = new ArrayList<>();
        if(ingredients != null)
        for (Ingredient ingredient : ingredients) {
            stringIngredientList.add(ingredient.getIngredientType() + "\n" +
                    "Quantity: " + ingredient.getQuantity() + "\n" +
                    "Measure: " + ingredient.getMeasure() + "\n");
        }
        return stringIngredientList;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //  unbinder.unbind();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("recipe", recipe);

    }
}
