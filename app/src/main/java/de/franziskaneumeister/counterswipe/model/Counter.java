package de.franziskaneumeister.counterswipe.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.inject.Inject;
import com.google.inject.Injector;

import java.util.ArrayList;
import java.util.List;

/**
 * Dependencies need to be injected manualy after the object is recreated from a parcel*
 */
public class Counter implements Parcelable{

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mCount);
        dest.writeList(mChanges);
    }
    
    public static final Creator CREATOR = new Creator<Counter>(){

        @Override
        public Counter createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public Counter[] newArray(int size) {
            return new Counter[size];
        }
    };
}
