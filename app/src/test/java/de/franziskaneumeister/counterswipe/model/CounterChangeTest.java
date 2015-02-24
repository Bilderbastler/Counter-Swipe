package de.franziskaneumeister.counterswipe.model;

import android.app.Application;
import android.os.Bundle;

import com.google.inject.Injector;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import roboguice.RoboGuice;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class CounterChangeTest {

    private CounterChange sut;

    @Before
    public void setup(){
        Application application = RuntimeEnvironment.application;
        Injector mInjector = RoboGuice.getOrCreateBaseApplicationInjector(application);
        sut = mInjector.getInstance(CounterChange.class);
    }
    
    @Test
    public void changeObjectExists(){
        assertThat(sut).isNotNull();
    }

    @Test
    public void testCanBePutInParcel() throws Exception {
        Bundle bundle = new Bundle();
        final String arg_key = "ARG_KEY";
        bundle.putParcelable(arg_key, sut);
        CounterChange extracted = bundle.getParcelable(arg_key);
        assertThat(extracted).isNotNull();
    }
}
