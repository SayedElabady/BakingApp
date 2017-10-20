package sayed.com.bakeryapp.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import sayed.com.bakeryapp.R;
import sayed.com.bakeryapp.adapter.RecipeAdapter;
import sayed.com.bakeryapp.details.DetailsActivity;
import sayed.com.bakeryapp.idlingresource.SimpleIdlingResource;
import sayed.com.bakeryapp.model.Recipe;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    List<Recipe> recipeList;
    @BindView(R.id.recipes_recycler_view)
    RecyclerView recipesRecyclerView;
    MainContract.Presenter presenter;
    RecipeAdapter recipeAdapter;

    private SimpleIdlingResource simpleIdlingResource;
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResouce(){
        if(simpleIdlingResource == null)
            simpleIdlingResource = new SimpleIdlingResource();
        return simpleIdlingResource;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recipeList = new ArrayList<>();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        presenter = new MainPresenter();
        presenter.setView(this);
        getIdlingResouce();

        recipesRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recipeAdapter = new RecipeAdapter(recipeList , presenter);
        recipesRecyclerView.setAdapter(recipeAdapter);
        presenter.fetchRecipes();
        if(simpleIdlingResource != null){
            simpleIdlingResource.setIdleState(false);
        }

    }

    @Override
    public void updateRecipesList(List<Recipe> recipeList) {
        this.recipeList.clear();
        this.recipeList.addAll(recipeList);
        recipeAdapter.notifyDataSetChanged();
        if(simpleIdlingResource != null){
            simpleIdlingResource.setIdleState(true);
        }
    }

    @Override
    public void navigateToSelectAStep(Recipe recipe) {
        Bundle recipeBundle = new Bundle();
        Intent intent = new Intent(MainActivity.this , DetailsActivity.class);
        recipeBundle.putSerializable("recipe" , recipe);
        intent.putExtras(recipeBundle);
        startActivity(intent);
    }
}
