package sayed.com.bakeryapp.details;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import sayed.com.bakeryapp.R;
import sayed.com.bakeryapp.model.Recipe;
import sayed.com.bakeryapp.model.Step;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View {
    DetailsContract.Presenter presenter;
    private static boolean isTwoPane = false;
    FragmentManager fragmentManager;
    Bundle recipeBundle;
    private final static String FRAGMENT_STACK = "FRAGMENT_STACK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        recipeBundle = getIntent().getExtras();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Recipe recipe = (Recipe) recipeBundle.get("recipe");
        if (recipe != null)
        getSupportActionBar().setTitle(recipe.getRecipeName());
        presenter = new DetailsPresenter();
        presenter.setView(this);
        SelectARecipeStepFragment selectARecipeStepFragment = new SelectARecipeStepFragment();
        selectARecipeStepFragment.setPresenter(presenter);
        selectARecipeStepFragment.setArguments(recipeBundle);
        fragmentManager = getSupportFragmentManager();
        if(savedInstanceState == null)
        fragmentManager
                .beginTransaction()
                .add(R.id.select_container, selectARecipeStepFragment)
                .commit();
        if (findViewById(R.id.view_step_container) != null) {
            isTwoPane = true;

        }

        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    finish();

            }
        });


    }

    @Override
    public void viewStep(Step step) {
        ViewStepFragment viewStepFragment = new ViewStepFragment();
        Bundle stepBundle = new Bundle();
        stepBundle.putSerializable("step", step);
        viewStepFragment.setArguments(stepBundle);
        if (!isTwoPane) {
            fragmentManager.beginTransaction()
                    .replace(R.id.select_container, viewStepFragment)
                    .commit();
        } else {

            fragmentManager
                    .beginTransaction()
                    .replace(R.id.view_step_container, viewStepFragment)
                    .commit();

        }
    }
}
