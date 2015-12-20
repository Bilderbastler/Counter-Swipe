package de.franziskaneumeister.counterswipe.injection.modules;

import android.app.Activity;

import dagger.Module;

@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }
}
