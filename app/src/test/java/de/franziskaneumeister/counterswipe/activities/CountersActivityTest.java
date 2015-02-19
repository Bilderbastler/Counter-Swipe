package de.franziskaneumeister.counterswipe.activities;

import android.app.Fragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import de.franziskaneumeister.counterswipe.R;
import de.franziskaneumeister.counterswipe.fragments.CounterFragment;
import de.franziskaneumeister.counterswipe.injection.modules.BuildModule;
import roboguice.RoboGuice;

import static org.assertj.android.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
//@Config(emulateSdk = 18, manifest = "./src/main/AndroidManifest.xml")
public class CountersActivityTest {
    
    private CountersActivity sut;
    private ActivityController<CountersActivity> controller;

    @Before
    public void setup(){
        RoboGuice.overrideApplicationInjector(RuntimeEnvironment.application, new BuildModule(RuntimeEnvironment.application));
        controller = Robolectric.buildActivity(CountersActivity.class).create().start().resume();
        sut = controller.get();
    }
    
    @Test
    public void activityExists(){
        assertThat(sut).isNotNull();
    }
    
    @Test
    public void hasCounterFragment(){
        controller.visible();
        Fragment fragment = (CounterFragment) sut.getFragmentManager().findFragmentById(R.id.container);
        assertThat(fragment).isInstanceOf(CounterFragment.class);
    }
    
}
