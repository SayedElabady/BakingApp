package sayed.com.bakeryapp.network;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import sayed.com.bakeryapp.model.Recipe;

/**
 * Created by Sayed on 10/17/2017.
 */

public class ApiController {

    public static Observable<List<Recipe>> getRecipes() {
        return ApiClient.getClient()
                .create(RecipeApiInterface.class)
                .getRecipes()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());

    }

}
