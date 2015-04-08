package de.franziskaneumeister.counterswipe.fragments;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.inject.Inject;
import com.google.inject.Injector;

import de.franziskaneumeister.counterswipe.R;
import de.franziskaneumeister.counterswipe.gestures.SwipeOverCounterHandler;
import de.franziskaneumeister.counterswipe.model.Counter;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Represents a single countable item. This fragment displays the state of a counter instance and
 * provides interaction logic for changing the state of a counter
 */
public class CounterFragment extends RoboFragment implements View.OnTouchListener {
    public static final String ARG_COUNTER = "de.franziskaneumeister.counterswipe.argument_counter";
    private Counter mCounter;
    @Inject
    Injector mInjector;
    private Button plusButton;
    private Subscription mCounterSubscribtion;
    private ImageButton minusButton;
    @Inject
    SwipeOverCounterHandler mSwipeHandler;
    private GestureDetector mGestureDetector;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCounter = getArguments().getParcelable(ARG_COUNTER);
            mInjector.injectMembers(mCounter);
        }
        mSwipeHandler.setCounter(mCounter);
        mGestureDetector = new GestureDetector(this.getActivity(), mSwipeHandler);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_counter, container, false);
        TextView counterName = (TextView) fragmentView.findViewById(R.id.counter_value);
        counterName.setText(mCounter.getName());
        setupPlusButton(fragmentView);
        setupMinusButton(fragmentView);
        fragmentView.setOnTouchListener(this);
        return fragmentView;
    }

    private void setupMinusButton(View fragmentView) {
        minusButton = (ImageButton) fragmentView.findViewById(R.id.button_minus);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter.decrement();
            }
        });
    }

    private void setupPlusButton(View fragmentView) {
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
    }

    @Override
    public void onDestroy() {
        mCounterSubscribtion.unsubscribe();
        super.onDestroy();
    }

    @Override
    public boolean onTouch(View targetView, MotionEvent event) {
        mSwipeHandler.setView(targetView);
        if(event.getActionMasked() == MotionEvent.ACTION_UP){
            ObjectAnimator.ofFloat(getView(), View.TRANSLATION_X, 0).start();
        }
        return mGestureDetector.onTouchEvent(event);
    }
}
