package de.franziskaneumeister.counterswipe.activities;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.util.ActivityController;

import android.support.v4.app.Fragment;

import de.franziskaneumeister.counterswipe.R;
import de.franziskaneumeister.counterswipe.fragments.CounterFragment;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(RobolectricTestRunner.class)
//@Config(emulateSdk = 18, manifest = "./src/main/AndroidManifest.xml")
public class CountersActivityTest {
    
    private CountersActivity sut;
    private ActivityController<CountersActivity> controller;

    @Before
    public void setup(){
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
        Fragment fragment = sut.getSupportFragmentManager().findFragmentById(R.id.container);
        assertThat(fragment).isInstanceOf(CounterFragment.class);
    }
    
}
