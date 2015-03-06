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
        Log.d("touch", "Tap went down");
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        double xOffset = e1.getX() - e2.getX();
        xOffset = Math.min(xOffset, TRANSLATION_LIMIT);
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d("touch", "Tap went up");
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
