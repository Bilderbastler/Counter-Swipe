package de.franziskaneumeister.counterswipe.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.inject.Inject;
import com.google.inject.Injector;

import de.franziskaneumeister.counterswipe.R;
import de.franziskaneumeister.counterswipe.model.Counter;

/**
 * Represents a single countable item. This fragment displays the state of a counter instance and 
 * provides interaction logic for changing the state of a counter
 */
public class CounterFragment extends RoboFragment {
    public static final String ARG_COUNTER = "de.franziskaneumeister.counterswipe.argument_counter";
    private Counter mCounter;
    @Inject
    Injector mInjector;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCounter = getArguments().getParcelable(ARG_COUNTER);
            mInjector.injectMembers(mCounter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_counter, container, false);
        Button plusButton = (Button) fragmentView.findViewById(R.id.button_plus);
        String count = String.valueOf(mCounter.getCount());
        plusButton.setText(count);
        return fragmentView;
    }

}
