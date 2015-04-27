package de.franziskaneumeister.counterswipe.injection.modules;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import de.franziskaneumeister.counterswipe.adapter.CounterViewHolder;

/**
 * Dependecy module for the main app.
 */
public class BuildModule extends AbstractModule {
    private Application mAppContext;
    public BuildModule(Application appContext) {
        mAppContext = appContext;
    }

    @Provides
    public CounterViewHolder provideCounterViewHolder(){
        View view = LayoutInflater.from(mAppContext).inflate();
        return new CounterViewHolder(view);
    }

    @Override
    protected void configure() {

    }

}

