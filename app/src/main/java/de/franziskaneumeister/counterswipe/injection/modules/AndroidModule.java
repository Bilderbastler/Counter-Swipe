package de.franziskaneumeister.counterswipe.injection.modules;

import android.content.Context;

import dagger.Module;

/**
 * Provides Dependecies from the Android SDK that are not bound to the activity/fragment lifecycle
 */
@Module
public class AndroidModule {
    private Context mContext;

    public AndroidModule(Context context) {
        mContext = context;
    }
}
