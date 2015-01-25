package de.franziskaneumeister.counterswipe.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(emulateSdk = 18)
public class CounterChangeTest {

    private CounterChange sut;

    @Before
    public void setup(){
        sut = new CounterChange();
    }
    
    @Test
    public void changeObjectExists(){
        assertThat(sut).isNotNull();
    }

}
