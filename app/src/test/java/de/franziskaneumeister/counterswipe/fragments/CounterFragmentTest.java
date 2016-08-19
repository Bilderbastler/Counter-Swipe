package de.franziskaneumeister.counterswipe.fragments;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import de.franziskaneumeister.counterswipe.BuildConfig;
import de.franziskaneumeister.counterswipe.R;
import de.franziskaneumeister.counterswipe.activities.CountersActivity;
import de.franziskaneumeister.counterswipe.gestures.SwipeOverCounterHandler;
import de.franziskaneumeister.counterswipe.model.Counter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
 public class CounterFragmentTest {

    private CounterFragment sut;
    private Counter mCounter;
    private Button plusButton;
    private ImageButton minusButton;
    private SwipeOverCounterHandler mHandlerMock;

    @Before
    public void setUp() throws Exception {
        mCounter = spy(Counter.class);
        Bundle args = new Bundle();
        // only works because the bundle is a shadow implementation
        args.putParcelable(CounterFragment.ARG_COUNTER, mCounter);
        sut = new CounterFragment();
        sut.setArguments(args);
    }

    @After
    public void tearDown() throws Exception {
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

    @Test
    public void fragmentDisplaysNameFromCounter() throws Exception {
        String testName = "this is a Test";
        when(mCounter.getName()).thenReturn(testName);
        showFragment();
        TextView text = (TextView) sut.getView().findViewById(R.id.counter_value);
        assertThat(text.getText()).isEqualTo(testName);
    }

    @Test
    public void FragmentConnectsCounterToTouchHandler() throws Exception {
        showFragment();
        verify(mHandlerMock).setCounter(mCounter);
    }

    private void showFragment() {
        SupportFragmentTestUtil.startVisibleFragment(sut, CountersActivity.class, R.id.container);
        plusButton = (Button) sut.getView().findViewById(R.id.button_plus);
        minusButton = (ImageButton) sut.getView().findViewById(R.id.button_minus);
    }

}