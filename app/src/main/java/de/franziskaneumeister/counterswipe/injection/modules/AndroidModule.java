package de.franziskaneumeister.counterswipe.injection.modules;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import javax.inject.Provider;

import dagger.Module;
import dagger.Provides;
import de.franziskaneumeister.counterswipe.adapter.CounterAdapter;
import de.franziskaneumeister.counterswipe.gestures.SwipeOverCounterHandler;

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

    @Provides
    public CounterAdapter provideCounterListAdapter(Provider<SwipeOverCounterHandler> handler) {
        return new CounterAdapter(handler);
    }
}
