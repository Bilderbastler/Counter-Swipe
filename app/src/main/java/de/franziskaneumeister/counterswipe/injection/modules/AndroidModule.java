package de.franziskaneumeister.counterswipe.injection.modules;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;

/**
 * Provides Dependecies from the Android SDK that are not bound to the activity/fragment lifecycle
 */
@Module
public class AndroidModule {
    private Context mContext;

    public AndroidModule(Context context) {
        mContext = context;
    }

    @Provides
    public LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(mContext.getApplicationContext(), LinearLayoutManager.VERTICAL, false);
    }
}
