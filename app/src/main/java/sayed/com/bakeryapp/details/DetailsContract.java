package sayed.com.bakeryapp.details;

import java.io.Serializable;
import java.util.List;

import sayed.com.bakeryapp.main.MainContract;
import sayed.com.bakeryapp.model.Recipe;
import sayed.com.bakeryapp.model.Step;

/**
 * Created by Sayed on 10/18/2017.
 */

public interface DetailsContract {

    interface View {
        void viewStep(Step step);

    }

    interface Presenter extends Serializable {
        void onStepClicked(Step step);

        void setView(View view);

    }
}
