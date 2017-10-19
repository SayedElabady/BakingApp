package sayed.com.bakeryapp.details;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import sayed.com.bakeryapp.R;
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

        presenter = new DetailsPresenter();
        presenter.setView(this);
        SelectARecipeStepFragment selectARecipeStepFragment = new SelectARecipeStepFragment();
        selectARecipeStepFragment.setPresenter(presenter);
        selectARecipeStepFragment.setArguments(recipeBundle);
        fragmentManager = getSupportFragmentManager();
        fragmentManager
                .beginTransaction()
                .add(R.id.select_container, selectARecipeStepFragment).addToBackStack(FRAGMENT_STACK)
                .commit();
        if (findViewById(R.id.view_step_container) != null) {
            isTwoPane = true;

        }


    }

    @Override
    public void viewStep(Step step) {
        ViewStepFragment viewStepFragment = new ViewStepFragment();
        Bundle stepBundle = new Bundle();
        stepBundle.putSerializable("step" , step);
        viewStepFragment.setArguments(stepBundle);
        if (!isTwoPane) {
            fragmentManager.beginTransaction()
                    .replace(R.id.select_container, viewStepFragment).addToBackStack(FRAGMENT_STACK)
                    .commit();
        } else {

            fragmentManager
                    .beginTransaction()
                    .replace(R.id.view_step_container, viewStepFragment).addToBackStack(FRAGMENT_STACK)
                    .commit();

        }
    }
}
