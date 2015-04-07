package de.franziskaneumeister.counterswipe.gestures;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class SwipeOverCounterHandler implements GestureDetector.OnGestureListener{

    private static final double TRANSLATION_LIMIT = 100;
    private View mView;

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float xOffset =  e2.getX() - e1.getX();
        mView.setTranslationX(xOffset);
        if (Math.abs(xOffset) < TRANSLATION_LIMIT){
        }
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    public void setView(View view) {
        mView = view;
    }
}
