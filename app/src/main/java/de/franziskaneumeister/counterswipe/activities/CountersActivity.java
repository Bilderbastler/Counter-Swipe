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
    Injector mInjector;
    @Inject
    private CounterFragment fragment;

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
        Counter counter = mInjector.getInstance(Counter.class);
        args.putParcelable(CounterFragment.ARG_COUNTER, counter);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_counters, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
