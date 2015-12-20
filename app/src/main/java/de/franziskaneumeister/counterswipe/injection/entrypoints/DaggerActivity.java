package de.franziskaneumeister.counterswipe.injection.entrypoints;

import android.support.v7.app.AppCompatActivity;

import de.franziskaneumeister.counterswipe.injection.components.ActivityComponent;
import de.franziskaneumeister.counterswipe.injection.modules.ActivityModule;

public class DaggerActivity extends AppCompatActivity {
    private ActivityComponent mComponent;

    protected ActivityComponent getComponent() {
        if (mComponent == null) {
            DaggerApplication app = (DaggerApplication) getApplication();
            mComponent = app.getComponent().plus(new ActivityModule(this));
        }
        return mComponent;
    }

    protected void setComponent(ActivityComponent component) {
        mComponent = component;
    }

    @Override
    protected void onDestroy() {
        mComponent = null;
        super.onDestroy();
    }
}
