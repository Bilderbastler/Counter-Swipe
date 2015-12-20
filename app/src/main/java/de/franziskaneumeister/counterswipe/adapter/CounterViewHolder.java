package de.franziskaneumeister.counterswipe.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import de.franziskaneumeister.counterswipe.R;
import de.franziskaneumeister.counterswipe.gestures.SwipeOverCounterHandler;
import de.franziskaneumeister.counterswipe.model.Counter;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 *
 */
public class CounterViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {
    private Counter mCounter;
    private Button mPlusButton;

    private Subscription mCounterSubscribtion;
    GestureDetector mGestureDetector;
    SwipeOverCounterHandler mSwipeHandler;


    public CounterViewHolder(View itemView) {
        super(itemView);
        itemView.setOnTouchListener(this);
        setupMinusButton(itemView);
        setupPlusButton(itemView);
    }

    private void setupMinusButton(View fragmentView) {
        ImageButton minusButton = (ImageButton) fragmentView.findViewById(R.id.button_minus);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter.decrement();
            }
        });
    }

    private void setupPlusButton(View fragmentView) {
        mPlusButton = (Button) fragmentView.findViewById(R.id.button_plus);
        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter.increment();
            }
        });
    }

    @Override
    public boolean onTouch(View targetView, MotionEvent event) {
        mSwipeHandler.setView(targetView);
        if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            ObjectAnimator.ofFloat(targetView, View.TRANSLATION_X, 0).start();
        }
        return mGestureDetector.onTouchEvent(event);
    }

    public void connectToCounter(Counter counter, SwipeOverCounterHandler swipeCounterHandler) {
        mCounter = counter;
        setupSwipeLogic(swipeCounterHandler);
        prepareViews();
        registerToChangesOfTheModel();
    }

    private void prepareViews() {
        TextView counterName = (TextView) itemView.findViewById(R.id.counter_value);
        counterName.setText(mCounter.getName());
    }

    private void registerToChangesOfTheModel() {
        if (mCounterSubscribtion != null) {
            mCounterSubscribtion.unsubscribe();
        }
        mCounterSubscribtion = mCounter.observeChanges()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer count) {
                        mPlusButton.setText(count.toString());
                    }
                });
    }

    private void setupSwipeLogic(SwipeOverCounterHandler swipeCounterHandler) {
        mSwipeHandler = swipeCounterHandler;
        mSwipeHandler.setCounter(mCounter);
        mGestureDetector = new GestureDetector(itemView.getContext(), mSwipeHandler);
    }
}
