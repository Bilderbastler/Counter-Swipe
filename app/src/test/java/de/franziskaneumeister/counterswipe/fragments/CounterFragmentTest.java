package de.franziskaneumeister.counterswipe.fragments;

import android.app.Application;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.inject.Injector;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.util.FragmentTestUtil;

import de.franziskaneumeister.counterswipe.R;
import de.franziskaneumeister.counterswipe.model.Counter;
import roboguice.RoboGuice;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(RobolectricTestRunner.class)
 public class CounterFragmentTest {

    private CounterFragment sut;
    private Counter counter;

    @Before
    public void setUp() throws Exception {
        Application application = RuntimeEnvironment.application;
        Injector injector = RoboGuice.getOrCreateBaseApplicationInjector(application);
        counter = injector.getInstance(Counter.class);
        Bundle args = new Bundle();
        args.putParcelable(CounterFragment.ARG_COUNTER, counter);
        sut = new CounterFragment();
        sut.setArguments(args);
    }

    @Test
    public void testFragmentShowsCounterValue() throws Exception {
        counter.increment();
        FragmentTestUtil.startVisibleFragment(sut);
        Button plusButton = (Button) sut.getView().findViewById(R.id.button_plus);
        int count = Integer.parseInt((String) plusButton.getText());
        assertThat(count).isEqualTo(counter.getCount());
    }
}