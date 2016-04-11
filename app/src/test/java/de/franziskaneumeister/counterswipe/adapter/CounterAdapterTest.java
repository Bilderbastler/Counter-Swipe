package de.franziskaneumeister.counterswipe.adapter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import android.os.Build;
import static org.assertj.core.api.Assertions.assertThat;

import de.franziskaneumeister.counterswipe.BuildConfig;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class CounterAdapterTest {

    @Test
    public void testOnCreateViewHolder() throws Exception {

    }

    @Test
    public void testOnBindViewHolder() throws Exception {

    }

    @Test
    public void testGetItemCount() throws Exception {

    }
}