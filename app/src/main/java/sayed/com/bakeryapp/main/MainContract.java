package sayed.com.bakeryapp.main;

import java.util.List;

import sayed.com.bakeryapp.model.Recipe;

/**
 * Created by Sayed on 10/17/2017.
 */

public interface MainContract {

    interface View {
        void updateRecipesList(List<Recipe> recipeList);

        void navigateToSelectAStep(Recipe recipe);

    }

    interface Presenter {
        void onRecipeCardClicked(Recipe recipe);

        void setView(View view);

        void fetchRecipes();
    }
}
