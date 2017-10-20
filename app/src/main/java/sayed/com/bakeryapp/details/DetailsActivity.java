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
    SelectARecipeStepFragment selectARecipeStepFragment;
    private final static String SELECT_TAG = "SELCET_TAG";

    private final static String VIEW_TAG = "VIEW_TAG";
    boolean isResumed = false;
    ViewStepFragment viewStepFragment;
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


        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            presenter = new DetailsPresenter();
            presenter.setView(this);
            recipeBundle.putSerializable("presenter" , presenter);

            selectARecipeStepFragment = new SelectARecipeStepFragment();
            selectARecipeStepFragment.setArguments(recipeBundle);
            fragmentManager
                    .beginTransaction()
                    .add(R.id.select_container, selectARecipeStepFragment , SELECT_TAG)
                    .commit();
        }else {
            selectARecipeStepFragment = (SelectARecipeStepFragment) fragmentManager.findFragmentByTag(SELECT_TAG);
        }
        if (findViewById(R.id.view_step_container) != null) {
            isTwoPane = true;
            Bundle stepBundle = new Bundle();
            stepBundle.putSerializable("step", recipe.getStepList().get(0));
            if(savedInstanceState == null) {
                viewStepFragment = new ViewStepFragment();


                viewStepFragment.setArguments(stepBundle);
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.view_step_container, viewStepFragment , VIEW_TAG)
                        .commit();
            }else
                viewStepFragment = (ViewStepFragment) fragmentManager.findFragmentByTag(VIEW_TAG);

        }
        myToolbar.setNavigationOnClickListener(v -> finish());


    }

    @Override
    public void viewStep(Step step) {
        Bundle stepBundle = new Bundle();
        stepBundle.putSerializable("step", step);
        viewStepFragment.setArguments(stepBundle);
        if (!isTwoPane) {
            fragmentManager.beginTransaction()
                    .replace(R.id.select_container, viewStepFragment)
                    .commit();
        } else {
            try {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.view_step_container, viewStepFragment)
                        .commit();
            }catch (Exception e){
                throw e;
            }
        }
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("WORKAROUND_FOR_BUG_19917_KEY", "WORKAROUND_FOR_BUG_19917_VALUE");
    }
}
