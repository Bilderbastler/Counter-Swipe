package de.franziskaneumeister.counterswipe.model;

import android.app.Application;
import android.os.Bundle;

import com.google.inject.Injector;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;


import java.util.List;

import roboguice.RoboGuice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class CounterTest {

    private Counter sut;
    private static Injector mInjector;

    @Before
    public void setup(){
        Application application = RuntimeEnvironment.application;
        mInjector = RoboGuice.getOrCreateBaseApplicationInjector(application);
        sut = new Counter();
        mInjector.injectMembers(sut);
    }
    
    @After
    public void tearDown(){
        RoboGuice.Util.reset();
    }

    @Test
    public void counterExists(){
        assertThat(sut).isNotNull();
    }
    
    @Test
    public void counterIsInitializedAtZero(){
        assertThat(sut.getCount()).isZero();
    }
    
    @Test
    public void counterCanBeIncremented(){
        int startValue = sut.getCount();
        sut.increment();
        int incrementedValue = sut.getCount();
        assertThat(incrementedValue).isGreaterThan(startValue);
    }
    
    @Test
    public void counterCanBeDecremented(){
        sut.increment();
        sut.increment();
        int startValue = sut.getCount();
        sut.decrement();
        int decrementedValue = sut.getCount();
        assertThat(decrementedValue).isLessThan(startValue);
    }
    
    @Test
    public void initializedCounterHasAnEmptyListOfChangeEvents(){
        List events = sut.getChanges();
        assertThat(events).isNotNull();
    }
               
    @Test
    public void anIncrementAddsACounterChange(){
        List events = sut.getChanges();
        sut.increment();
        assertThat(events).hasSize(1);
    }

    @Test
    public void aDecrementAddsACounterChange(){
        List events = sut.getChanges();
        sut.decrement();
        assertThat(events).hasSize(1);
    }

    @Test
    public void testCanBeDeserialized() throws Exception {
        sut.increment();
        Bundle bundle = new Bundle();
        final String bundle_key = "ARG_KEY";
        bundle.putParcelable(bundle_key, sut);
        Counter result = (Counter) bundle.getParcelable(bundle_key);
        mInjector.injectMembers(result);
        assertThat(result.getCount()).isEqualTo(sut.getCount());
        try {
            result.increment();
            result.decrement();
        }catch (Exception e){
            fail("Incrementing or decrementing should not throw an exception");
        }
    }
}