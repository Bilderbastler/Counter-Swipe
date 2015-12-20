package de.franziskaneumeister.counterswipe.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import android.os.Bundle;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
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

    @Test
    public void testCanBePutInParcel() throws Exception {
        Bundle bundle = new Bundle();
        final String arg_key = "ARG_KEY";
        bundle.putParcelable(arg_key, sut);
        CounterChange extracted = bundle.getParcelable(arg_key);
        assertThat(extracted).isNotNull();
    }
}
