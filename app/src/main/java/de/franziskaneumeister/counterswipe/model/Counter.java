package de.franziskaneumeister.counterswipe.model;

import java.util.ArrayList;
import java.util.List;

public class Counter {

    private int mCount;
    private List mChanges;
    
    public Counter(){
        mChanges = new ArrayList();
    }

    public int getCount() {
        return mCount;
    }

    public void increment() {
        mCount++;
    }

    public void decrement() {
        mCount--;
    }

    public List getChanges() {
        return mChanges;
    }
}
