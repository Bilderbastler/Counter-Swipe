package de.franziskaneumeister.counterswipe.fragments;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.util.FragmentTestUtil;

import de.franziskaneumeister.counterswipe.R;
import de.franziskaneumeister.counterswipe.model.Counter;
import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;


@RunWith(RobolectricTestRunner.class)
 public class CounterFragmentTest {

    private CounterFragment sut;
    private Counter mCounter;
    private Button plusButton;
    private ImageButton minusButton;

    @Before
    public void setUp() throws Exception {
        mCounter = spy(Counter.class);
        Context application = RuntimeEnvironment.application;
        RoboInjector injector = RoboGuice.getInjector(application);
        injector.injectMembers(mCounter);
        Bundle args = new Bundle();
        // only works because the bundle is a shadow implementation
        args.putParcelable(CounterFragment.ARG_COUNTER, mCounter);
        sut = new CounterFragment();
        sut.setArguments(args);
    }

    @Test
    public void fragmentShowsCounterValue() throws Exception {
        mCounter.increment();
        showFragment();
        int count = Integer.parseInt((String) plusButton.getText());
        assertThat(count).isEqualTo(mCounter.getCount());
    }

    @Test
    public void pressingPrimaryActionButtonIncrementsCounterObject() throws Exception {
        showFragment();
        plusButton.performClick();
        verify(mCounter).increment();
    }

    @Test
    public void anIncrementOfTheCounterIsDisplayedByTheFragment() throws Exception {
        int oldCount = mCounter.getCount();
        showFragment();
        mCounter.increment();
        int count = Integer.parseInt((String) plusButton.getText());
        assertThat(count).isGreaterThan(oldCount);
    }

    @Test
    public void pressingSecondaryActionButtonIncrementsCounterObject() throws Exception {
        showFragment();
        minusButton.performClick();
        verify(mCounter).decrement();
    }

    private void showFragment() {
        FragmentTestUtil.startVisibleFragment(sut);
        plusButton = (Button) sut.getView().findViewById(R.id.button_plus);
        minusButton = (ImageButton) sut.getView().findViewById(R.id.button_minus);
    }


}