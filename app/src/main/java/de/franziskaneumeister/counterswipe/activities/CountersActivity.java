package de.franziskaneumeister.counterswipe.activities;

import android.os.Bundle;

import javax.inject.Inject;
import javax.inject.Provider;

import de.franziskaneumeister.counterswipe.R;
import de.franziskaneumeister.counterswipe.fragments.CounterFragment;
import de.franziskaneumeister.counterswipe.injection.entrypoints.DaggerActivity;
import de.franziskaneumeister.counterswipe.model.Counter;

/**
 * This activity holds the fragments for counters.
 */
public class CountersActivity extends DaggerActivity {
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        setContentView(R.layout.activity_counters);
        if (savedInstanceState == null) {
            CounterFragment fragment = createCounterFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment)
                    .commit();
        }
        getFragmentManager().executePendingTransactions();

    }

    private CounterFragment createCounterFragment() {
        Bundle args = new Bundle();
        Counter counter = new Counter();
        args.putParcelable(CounterFragment.ARG_COUNTER, counter);
        CounterFragment fragment = new CounterFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
