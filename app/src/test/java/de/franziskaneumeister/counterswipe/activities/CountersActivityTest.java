package de.franziskaneumeister.counterswipe.activities;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import de.franziskaneumeister.counterswipe.injection.modules.BuildModule;
import roboguice.RoboGuice;

import static org.assertj.android.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class CountersActivityTest {
    
    private CountersActivity sut;
    private ActivityController<CountersActivity> controller;

    @Before
    public void setup(){
        RoboGuice.overrideApplicationInjector(Robolectric.application, new BuildModule(Robolectric.application));
        controller = Robolectric.buildActivity(CountersActivity.class).create().start().resume();
        sut = controller.get();
    }
    
    @Test
    public void activityExists(){
        assertThat(sut).isNotNull();
    }
    
}
