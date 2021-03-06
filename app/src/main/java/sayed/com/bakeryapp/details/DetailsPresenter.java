package sayed.com.bakeryapp.details;

import java.io.Serializable;

import sayed.com.bakeryapp.main.MainContract;
import sayed.com.bakeryapp.model.Step;

/**
 * Created by Sayed on 10/18/2017.
 */

public class DetailsPresenter implements DetailsContract.Presenter, Serializable {
    transient DetailsContract.View view;

    @Override
    public void onStepClicked(Step step) {
        view.viewStep(step);
    }

    @Override
    public void setView(DetailsContract.View view) {
        this.view = view;
    }
}
