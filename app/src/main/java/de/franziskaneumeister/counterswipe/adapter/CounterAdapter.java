package de.franziskaneumeister.counterswipe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.inject.Inject;
import com.google.inject.Injector;

import java.util.ArrayList;

import de.franziskaneumeister.counterswipe.R;
import de.franziskaneumeister.counterswipe.gestures.SwipeOverCounterHandler;
import de.franziskaneumeister.counterswipe.model.Counter;

/**
 * Adapter for a collection of countable items
 */
public class CounterAdapter extends RecyclerView.Adapter<CounterViewHolder>{

    private final ArrayList<Counter> mCounters;
    private Injector mInjector;


    @Inject
    public CounterAdapter(Counter aCounter, Injector injector) {
        mCounters = new ArrayList<Counter>();
        mCounters.add(aCounter);
        mInjector = injector;
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
        Context context = mInjector.getInstance(Context.class);
        SwipeOverCounterHandler swipeCounterHandler = mInjector.getInstance(SwipeOverCounterHandler.class);
        holder.connectToCounter(counter, context, swipeCounterHandler);
    }

    @Override
    public int getItemCount() {
        return mCounters.size();
    }
}
