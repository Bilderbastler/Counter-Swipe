package de.franziskaneumeister.counterswipe.gestures;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import android.os.Build;
import android.view.MotionEvent;
import android.view.View;

import de.franziskaneumeister.counterswipe.BuildConfig;
import de.franziskaneumeister.counterswipe.model.Counter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class SwipeOverCounterHandlerTest  {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    private SwipeOverCounterHandler sut;
    @Mock Counter mCounterMock;
    @Mock View mView;

    @Before
    public void setUp() throws Exception {
        sut = new SwipeOverCounterHandler();
        sut.setView(mView);
        sut.setCounter(mCounterMock);
    }

    @Test
    public void testRightFlingIncrementsCounter() throws Exception {
        MotionEvent start = MotionEvent.obtain(0, 0, MotionEvent.ACTION_DOWN, 0, 0, 0);
        MotionEvent stop = MotionEvent.obtain(0, 100, MotionEvent.ACTION_UP, 300, 0, 0);
        sut.onFling(start, stop, 200, 0);
        verify(mCounterMock).increment();
    }

    @Test
    public void testRightFlingDecrementsCounter() throws Exception {
        MotionEvent start = MotionEvent.obtain(0, 0, MotionEvent.ACTION_DOWN, 300, 0, 0);
        MotionEvent stop = MotionEvent.obtain(0, 100, MotionEvent.ACTION_UP, 0, 0, 0);
        sut.onFling(start, stop, -200, 0);
        verify(mCounterMock).decrement();
    }

    @Test
    public void testSmallSwipeDoesNothing() throws Exception {
        MotionEvent start = MotionEvent.obtain(0, 0, MotionEvent.ACTION_DOWN, 50, 0, 0);
        MotionEvent stop = MotionEvent.obtain(0, 100, MotionEvent.ACTION_UP, 0, 0, 0);
        sut.onFling(start, stop, -200, 0);
        verifyZeroInteractions(mCounterMock);
    }
}