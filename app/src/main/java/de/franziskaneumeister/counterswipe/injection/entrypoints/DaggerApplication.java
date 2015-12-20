package de.franziskaneumeister.counterswipe.injection.entrypoints;

import android.app.Application;

import de.franziskaneumeister.counterswipe.injection.components.ApplicationComponent;
import de.franziskaneumeister.counterswipe.injection.components.DaggerApplicationComponent;
import de.franziskaneumeister.counterswipe.injection.modules.AndroidModule;
import de.franziskaneumeister.counterswipe.injection.modules.ApplicationModule;

/**
 * Base Application to use Dagger for dependcy injection
 */
public class DaggerApplication extends Application {
    private ApplicationComponent mComponent;

    public ApplicationComponent getComponent() {
        if (mComponent == null) {
            mComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .androidModule(new AndroidModule(this))
                    .build();
        }
        return mComponent;
    }

    public void setComponent(ApplicationComponent component) {
        mComponent = component;
    }
}
