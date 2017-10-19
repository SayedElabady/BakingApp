package sayed.com.bakeryapp.idlingresource;

import android.support.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Sayed on 10/19/2017.
 */

public class SimpleIdlingResource implements IdlingResource {
    private volatile ResourceCallback resourceCallback;

    private AtomicBoolean isIDleNow = new AtomicBoolean(true);
    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return isIDleNow.get();
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
            resourceCallback = callback;
    }
    public void setIdleState(boolean isIdleNow) {
        isIDleNow.set(isIdleNow);
        if (isIdleNow && resourceCallback != null) {
            resourceCallback.onTransitionToIdle();
        }
    }
}
