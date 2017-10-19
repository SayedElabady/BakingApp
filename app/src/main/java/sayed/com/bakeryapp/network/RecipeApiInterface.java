package sayed.com.bakeryapp.network;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sayed.com.bakeryapp.model.Recipe;

/**
 * Created by Sayed on 10/17/2017.
 */

public interface RecipeApiInterface {


    @GET("baking.json")
    Observable<List<Recipe>> getRecipes();

}
