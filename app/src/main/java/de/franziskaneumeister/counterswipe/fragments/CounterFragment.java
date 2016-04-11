package de.franziskaneumeister.counterswipe.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import de.franziskaneumeister.counterswipe.R;
import de.franziskaneumeister.counterswipe.adapter.CounterAdapter;
import de.franziskaneumeister.counterswipe.injection.entrypoints.DaggerFragment;
import de.franziskaneumeister.counterswipe.model.Counter;

/**
 * Represents a single countable item. This fragment displays the state of a counter instance and
 * provides interaction logic for changing the state of a counter
 */
public class CounterFragment extends DaggerFragment {
    public static final String ARG_COUNTER = "de.franziskaneumeister.counterswipe.argument_counter";
    private Counter mCounter;
    @Inject
    LinearLayoutManager mLinearLayoutManager;
    @Inject
    CounterAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
        if (getArguments() != null) {
            mCounter = getArguments().getParcelable(ARG_COUNTER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_counter_list, container, false);
        prepareListOfCounters(fragmentView);
        return fragmentView;
    }

    private void prepareListOfCounters(View fragmentView) {
        RecyclerView recyclerView = (RecyclerView) fragmentView.findViewById(R.id.recycler_view_counters);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setAdapter(mAdapter);
    }

}
