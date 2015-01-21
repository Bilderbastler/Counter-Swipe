package de.franziskaneumeister.counterswipe.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;


import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class CounterTest {

    private Counter sut;

    @Before
    public void setup(){
        sut = new Counter();
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
}