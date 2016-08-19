package de.franziskaneumeister.counterswipe.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.os.Build;
import android.os.Bundle;

import de.franziskaneumeister.counterswipe.BuildConfig;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
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
