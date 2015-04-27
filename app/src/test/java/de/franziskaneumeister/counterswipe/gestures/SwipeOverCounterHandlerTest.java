package de.franziskaneumeister.counterswipe.gestures;

import android.view.MotionEvent;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import de.franziskaneumeister.counterswipe.model.Counter;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(RobolectricTestRunner.class)
public class SwipeOverCounterHandlerTest  {
    private SwipeOverCounterHandler sut;
    private Counter mCounterMock;

    @Before
    public void setUp() throws Exception {
        sut = new SwipeOverCounterHandler();
        View mockView = mock(View.class);
        sut.setView(mockView);
        mCounterMock = mock(Counter.class);
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