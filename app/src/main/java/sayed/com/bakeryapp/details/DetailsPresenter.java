package sayed.com.bakeryapp.details;

import sayed.com.bakeryapp.main.MainContract;
import sayed.com.bakeryapp.model.Step;

/**
 * Created by Sayed on 10/18/2017.
 */

public class DetailsPresenter implements DetailsContract.Presenter {
    DetailsContract.View view;

    @Override
    public void onStepClicked(Step step) {
        view.viewStep(step);
    }

    @Override
    public void setView(DetailsContract.View view) {
        this.view = view;
    }
}
