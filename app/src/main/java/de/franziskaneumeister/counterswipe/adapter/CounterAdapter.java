package de.franziskaneumeister.counterswipe.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.inject.Inject;

import java.util.ArrayList;

import de.franziskaneumeister.counterswipe.R;
import de.franziskaneumeister.counterswipe.model.Counter;

/**
 * Created by franziskaneumeister on 17.04.15.
 */
public class CounterAdapter extends RecyclerView.Adapter<CounterViewHolder>{

    private final ArrayList<Counter> mCounters;

    @Inject
    public CounterAdapter(Counter aCounter) {
        mCounters = new ArrayList<Counter>();
        mCounters.add(aCounter);
    }

    @Override
    public CounterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.fragment_counter, parent, false);
        return new CounterViewHolder(view); // no idea how to do this with injection
    }

    @Override
    public void onBindViewHolder(CounterViewHolder holder, int position) {
        Counter counter = mCounters.get(position);
        holder.setCounter(counter);
    }

    @Override
    public int getItemCount() {
        return mCounters.size();
    }
}
