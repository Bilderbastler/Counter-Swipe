package de.franziskaneumeister.counterswipe.injection.modules;

import android.app.Application;

import dagger.Module;

@Module
public class ApplicationModule {
    private Application mAppContext;
    public ApplicationModule(Application appContext) {
        mAppContext = appContext;
    }


}

