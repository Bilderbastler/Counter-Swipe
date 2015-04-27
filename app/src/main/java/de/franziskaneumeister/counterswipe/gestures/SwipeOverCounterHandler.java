package de.franziskaneumeister.counterswipe.gestures;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import de.franziskaneumeister.counterswipe.model.Counter;

public class SwipeOverCounterHandler implements GestureDetector.OnGestureListener{

    private static final double TRANSLATION_LIMIT = 200;
    private View mView;
    private Counter mCounter;

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float xOffset =  e2.getX() - e1.getX();
        if (Math.abs(xOffset) > TRANSLATION_LIMIT){
            if (xOffset > 0){
                mCounter.increment();
            }else{
                mCounter.decrement();
            }
        }
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {}

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        float xOffset =  e2.getX() - e1.getX();
        if (Math.abs(xOffset) < TRANSLATION_LIMIT){
            mView.setTranslationX(xOffset);
        }
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {}

    public void setView(View view) {
        mView = view;
    }

    public void setCounter(Counter counter) {
        this.mCounter = counter;
    }

}
