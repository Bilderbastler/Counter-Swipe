package de.franziskaneumeister.counterswipe.model;

import com.google.inject.Inject;
import com.google.inject.Injector;

import java.util.ArrayList;
import java.util.List;

public class Counter {

    private int mCount;
    private List mChanges;

    @Inject
    protected Injector injector;
    
    public Counter(){
        mChanges = new ArrayList();
    }

    public int getCount() {
        return mCount;
    }

    public void increment() {
        mCount++;
        addChange();
        
    }

    private void addChange() {
        CounterChange change = injector.getInstance(CounterChange.class);
        mChanges.add(change);
    }

    public void decrement() {
        mCount--;
        addChange();
    }

    public List getChanges() {
        return mChanges;
    }
}
