package sayed.com.bakeryapp.main;

import android.util.Log;
import android.widget.Toast;

import sayed.com.bakeryapp.model.Recipe;
import sayed.com.bakeryapp.network.ApiController;

/**
 * Created by Sayed on 10/17/2017.
 */

public class MainPresenter implements MainContract.Presenter {

    MainContract.View view;


    @Override
    public void onRecipeCardClicked(Recipe recipe) {
        view.navigateToSelectAStep(recipe);
    }

    @Override
    public void setView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void fetchRecipes() {
        ApiController
                .getRecipes()
                .subscribe(
                        reviews -> view.updateRecipesList(reviews),
                        throwable -> {
                            Log.v("MainPresenter" , throwable.getMessage());
                            throwable.printStackTrace();
                        }
                );
    }
}
