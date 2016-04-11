package de.franziskaneumeister.counterswipe.adapter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import de.franziskaneumeister.counterswipe.BuildConfig;
import de.franziskaneumeister.counterswipe.R;
import de.franziskaneumeister.counterswipe.gestures.SwipeOverCounterHandler;
import de.franziskaneumeister.counterswipe.model.Counter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class CounterViewHolderTest {

    private CounterViewHolder sut;
    private SwipeOverCounterHandler mSwipeHandler;
    private Counter mCounter;
    private Button mPlusButton;
    private ImageButton mMinusButton;
    private TextView mValueText;

    @Before
    public void setUp() throws Exception {
        Context app = RuntimeEnvironment.application;
        View view = LayoutInflater.from(app).inflate(R.layout.listitem_counter, null);
        sut = new CounterViewHolder(view);
        mSwipeHandler = mock(SwipeOverCounterHandler.class);
        mCounter = spy(Counter.class);
        connectWithCounter();
        mPlusButton = (Button) view.findViewById(R.id.button_plus);
        mMinusButton = (ImageButton) view.findViewById(R.id.button_minus);
        mValueText = (TextView) view.findViewById(R.id.counter_value);
    }

    private void connectWithCounter() {
        sut.connectToCounter(mCounter, mSwipeHandler);
    }

    @Test
    public void viewShowsCounterValue() throws Exception {
        mCounter.increment();
        connectWithCounter();
        int count = Integer.parseInt((String) mPlusButton.getText());
        assertThat(count).isEqualTo(mCounter.getCount());
    }

    @Test
    public void pressingPrimaryActionButtonIncrementsCounterObject() throws Exception {
        connectWithCounter();
        mPlusButton.performClick();
        verify(mCounter).increment();
    }

    @Test
    public void anIncrementOfTheCounterIsDisplayedByTheFragment() throws Exception {
        int oldCount = mCounter.getCount();
        connectWithCounter();
        mCounter.increment();
        int count = Integer.parseInt((String) mPlusButton.getText());
        assertThat(count).isGreaterThan(oldCount);
    }

    @Test
    public void pressingSecondaryActionButtonIncrementsCounterObject() throws Exception {
        connectWithCounter();
        mMinusButton.performClick();
        verify(mCounter).decrement();
    }

    @Test
    public void viewDisplaysNameFromCounter() throws Exception {
        String testName = "this is a Test";
        when(mCounter.getName()).thenReturn(testName);
        connectWithCounter();
        assertThat(mValueText.getText()).isEqualTo(testName);
    }

}