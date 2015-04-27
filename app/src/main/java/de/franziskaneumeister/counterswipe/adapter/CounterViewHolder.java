package de.franziskaneumeister.counterswipe.adapter;

import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import de.franziskaneumeister.counterswipe.R;
import de.franziskaneumeister.counterswipe.model.Counter;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by franziskaneumeister on 17.04.15.
 */
public class CounterViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {
    private Counter mCounter;
    private ImageButton mMinusButton;
    private Button mPlusButton;
    private Subscription mCounterSubscribtion;

    public CounterViewHolder(View itemView) {
        super(itemView);
        setupPlusButton(itemView);
        setupMinusButton(itemView);
        itemView.setOnTouchListener(this);
        itemView.setOnTouchListener(this);
    }

    private void setupMinusButton(View fragmentView) {
        mMinusButton = (ImageButton) fragmentView.findViewById(R.id.button_minus);
        mMinusButton.setOnClickListener(new View.OnClickListener() {
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
    public boolean onTouch(View v, MotionEvent event) {
        mSwipeHandler.setView(targetView);
        if(event.getActionMasked() == MotionEvent.ACTION_UP){
            ObjectAnimator.ofFloat(getView(), View.TRANSLATION_X, 0).start();
        }
        return mGestureDetector.onTouchEvent(event);
    }

    public void setCounter(Counter counter) {
        mCounter = counter;
        if (mCounterSubscribtion != null){
            mCounterSubscribtion.unsubscribe();
        }
        TextView counterName = (TextView) itemView.findViewById(R.id.counter_value);
        counterName.setText(mCounter.getName());
        mCounterSubscribtion = mCounter.observeChanges()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer count) {
                        mPlusButton.setText(count.toString());
                    }
                });

    }
}
