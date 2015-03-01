package de.franziskaneumeister.counterswipe.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.inject.Inject;
import com.google.inject.Injector;

import de.franziskaneumeister.counterswipe.R;
import de.franziskaneumeister.counterswipe.model.Counter;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Represents a single countable item. This fragment displays the state of a counter instance and
 * provides interaction logic for changing the state of a counter
 */
public class CounterFragment extends RoboFragment {
    public static final String ARG_COUNTER = "de.franziskaneumeister.counterswipe.argument_counter";
    private Counter mCounter;
    @Inject
    Injector mInjector;
    private Button plusButton;
    private Subscription mCounterSubscribtion;
    private ImageButton minusButton;

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
        plusButton = (Button) fragmentView.findViewById(R.id.button_plus);
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter.increment();
            }
        });
        mCounterSubscribtion = mCounter.observeChanges()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer count) {
                        plusButton.setText(count.toString());
                    }
                });
        minusButton = (ImageButton) fragmentView.findViewById(R.id.button_minus);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter.decrement();
            }
        });
        return fragmentView;
    }

    @Override
    public void onDestroy() {
        mCounterSubscribtion.unsubscribe();
        super.onDestroy();
    }
}
