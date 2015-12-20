package de.franziskaneumeister.counterswipe.activities;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;

import de.franziskaneumeister.counterswipe.R;
import de.franziskaneumeister.counterswipe.fragments.CounterFragment;
import de.franziskaneumeister.counterswipe.model.Counter;
import roboguice.activity.RoboActionBarActivity;
import roboguice.fragment.RoboFragment;
import roboguice.inject.ContentView;

/**
 * This activity holds the fragments for counters.
 */
@ContentView(R.layout.activity_counters)
public class CountersActivity extends RoboActionBarActivity {
    
    @Inject
    protected CounterFragment fragment;
    @Inject
    Provider<Counter> mCounterProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            CounterFragment fragment = createCounterFragment();
            getFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }
        getFragmentManager().executePendingTransactions();

    }

    private CounterFragment createCounterFragment() {
        Bundle args = new Bundle();
        Counter counter = mCounterProvider.get();
        args.putParcelable(CounterFragment.ARG_COUNTER, counter);
        fragment.setArguments(args);
        return fragment;
    }
}
