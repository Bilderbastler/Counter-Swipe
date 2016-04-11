package de.franziskaneumeister.counterswipe.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Provider;

import de.franziskaneumeister.counterswipe.R;
import de.franziskaneumeister.counterswipe.gestures.SwipeOverCounterHandler;
import de.franziskaneumeister.counterswipe.model.Counter;

/**
 * Adapter for a collection of countable items
 */
public class CounterAdapter extends RecyclerView.Adapter<CounterViewHolder> {

    private final ArrayList<Counter> mCounters;
    Provider<SwipeOverCounterHandler> mOverCounterHandlerProvider;

    @Inject
    public CounterAdapter(Counter aCounter, Provider<SwipeOverCounterHandler> counterHanlderProvider) {
        mOverCounterHandlerProvider = counterHanlderProvider;
        mCounters = new ArrayList<>();
        mCounters.add(aCounter);
    }

    @Override
    public CounterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.listitem_counter, parent, false);
        return new CounterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CounterViewHolder holder, int position) {
        Counter counter = mCounters.get(position);
        SwipeOverCounterHandler swipeCounterHandler = mOverCounterHandlerProvider.get();
        holder.connectToCounter(counter, swipeCounterHandler);
    }

    @Override
    public int getItemCount() {
        return mCounters.size();
    }
}
