package sayed.com.bakeryapp.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import sayed.com.bakeryapp.R;
import sayed.com.bakeryapp.model.Ingredient;
import sayed.com.bakeryapp.model.Recipe;

/**
 * Created by Sayed on 10/19/2017.
 */
public class GridWidgetService extends RemoteViewsService {
    Recipe recipeToShow;

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new GridRemoteViewsFactory(this.getApplicationContext(), intent);
    }

    public class GridRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
        Context context;

        public GridRemoteViewsFactory(Context context, Intent intent) {
            this.context = context;
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            recipeToShow = IngredientWidgetProvider.recipe;
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return recipeToShow != null && recipeToShow.getIngredientList() != null ? recipeToShow.getIngredientList().size() : 0;
        }

        @Override
        public RemoteViews getViewAt(int i) {
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_grid_view_item);
            Ingredient ingredient = recipeToShow.getIngredientList().get(i);
            views.setTextViewText(R.id.widget_grid_view_item, ingredient.getIngredientType() + "\n" +
                    "Quantity: " + ingredient.getQuantity() + "\n" +
                    "Measure: " + ingredient.getMeasure() + "\n");
            Intent fillInIntent = new Intent();
            fillInIntent.putExtra("recipe", recipeToShow);
            views.setOnClickFillInIntent(R.id.widget_grid_view_item, fillInIntent);
            return views;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
