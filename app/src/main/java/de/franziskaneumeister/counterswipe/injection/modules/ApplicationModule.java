package de.franziskaneumeister.counterswipe.injection.modules;

import android.app.Application;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {
    private Application mAppContext;
    public ApplicationModule(Application appContext) {
        mAppContext = appContext;
    }



}

