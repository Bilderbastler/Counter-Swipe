package de.franziskaneumeister.counterswipe.injection.modules;

import android.app.Application;

import com.google.inject.AbstractModule;

/**
 * Dependecy module for the main app.
 */
public class BuildModule extends AbstractModule {
    private Application mAppContext;
    public BuildModule(Application appContext) {
        mAppContext = appContext;
    }

    @Override
    protected void configure() {

    }

}

