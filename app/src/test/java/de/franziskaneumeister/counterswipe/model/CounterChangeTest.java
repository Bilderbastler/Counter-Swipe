package de.franziskaneumeister.counterswipe.model;

import android.app.Application;

import com.google.inject.Injector;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import roboguice.RoboGuice;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class CounterChangeTest {

    private CounterChange sut;

    @Before
    public void setup(){
        Application application = Robolectric.application;
        Injector mInjector = RoboGuice.getOrCreateBaseApplicationInjector(application);
        sut = mInjector.getInstance(CounterChange.class);
    }
    
    @Test
    public void changeObjectExists(){
        assertThat(sut).isNotNull();
    }
    
}
