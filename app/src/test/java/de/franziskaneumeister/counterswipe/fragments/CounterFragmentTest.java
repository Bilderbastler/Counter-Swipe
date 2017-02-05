package de.franziskaneumeister.counterswipe.fragments;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil;

import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.test.mock.MockContext;

import javax.inject.Provider;

import de.franziskaneumeister.counterswipe.BuildConfig;
import de.franziskaneumeister.counterswipe.adapter.CounterAdapter;
import de.franziskaneumeister.counterswipe.gestures.SwipeOverCounterHandler;
import de.franziskaneumeister.counterswipe.injection.components.DaggerApplicationComponent;
import de.franziskaneumeister.counterswipe.injection.components.FragmentComponent;
import de.franziskaneumeister.counterswipe.injection.modules.ActivityModule;
import de.franziskaneumeister.counterswipe.injection.modules.AndroidModule;
import de.franziskaneumeister.counterswipe.injection.modules.ApplicationModule;
import de.franziskaneumeister.counterswipe.injection.modules.FragmentModule;
import de.franziskaneumeister.counterswipe.model.Counter;

import static org.assertj.android.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
 public class CounterFragmentTest {

    private CounterFragment sut;
    private Counter mCounter;
    private CounterAdapter mCounterAdapter;

    @Before
    public void setUp() throws Exception {
        Application app = RuntimeEnvironment.application;
        mCounter = spy(Counter.class);
        mCounterAdapter = mock(CounterAdapter.class);
        Bundle args = new Bundle();
        // only works because the bundle is a shadow implementation
        args.putParcelable(CounterFragment.ARG_COUNTER, mCounter);
        sut = new CounterFragment();
        sut.setArguments(args);
        FragmentComponent testComponent = DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(app){
                    @Override
                    public CounterAdapter provideCounterListAdapter(Provider<SwipeOverCounterHandler> handler) {
                        return mCounterAdapter;
                    }
                })
                .applicationModule(mock(ApplicationModule.class))
                .build()
                .plus(mock(ActivityModule.class))
                .plus(mock(FragmentModule.class));
        sut.setComponent(testComponent);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testShouldNotBeNull() throws Exception {
        showFragment();
        assertThat(sut.getView()).isNotNull();
    }

    @Test
    public void FragmentConnectsCounterToTouchHandler() throws Exception {
        showFragment();
        verify(mCounterAdapter).addCounter(mCounter);
    }

    private void showFragment() {
        SupportFragmentTestUtil.startVisibleFragment(sut);
    }

}