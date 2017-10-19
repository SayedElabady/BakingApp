package sayed.com.bakeryapp.widget;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import sayed.com.bakeryapp.model.Recipe;

/**
 * Created by Sayed on 10/19/2017.
 */

public class BakingService extends IntentService {
    public static final String ACTIVITY_INGREDIENTS = "ACTIVITY_INGREDIENTS";
    public BakingService() {
        super("UpdateBakingService");
    }
    public static void startUpdateService(Context context , Recipe recipe){
        Intent intent = new Intent(context, BakingService.class);
        intent.putExtra(ACTIVITY_INGREDIENTS,recipe);
        context.startService(intent);
    }
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent != null){
            handleUpdateBakingService((Recipe) intent.getExtras().get(ACTIVITY_INGREDIENTS));
        }

    }
    private void handleUpdateBakingService(Recipe recipe){
        Intent intent = new Intent("android.appwidget.action.APPWIDGET_UPDATE");
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE");
        intent.putExtra(ACTIVITY_INGREDIENTS,recipe);
        sendBroadcast(intent);
    }
}
