package de.franziskaneumeister.counterswipe.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.inject.Inject;
import com.google.inject.Injector;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Represents a counter with a count and a name for the counted items
 * Dependencies need to be injected manualy after the object is recreated from a parcel*
 */
public class Counter implements Parcelable{

    private final BehaviorSubject<Integer> mCountChangePublisher;
    private int mCount;
    private List<CounterChange> mChanges;

    @Inject
    protected Injector injector;
    private String mName;

    public Counter(){
        mName = "unnamed Item";
        mChanges = new ArrayList<>();
        mCountChangePublisher = BehaviorSubject.create(0);
    }

    public Counter(int count, ArrayList<CounterChange> changes, String name) {
        mChanges= changes;
        mCount = count;
        mName = name;
        mCountChangePublisher = BehaviorSubject.create(count);
    }

    public Observable<Integer> observeChanges(){
        return mCountChangePublisher.asObservable();
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
        mCountChangePublisher.onNext(mCount);
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
        dest.writeString(mName);
    }
    
    public static final Creator CREATOR = new Creator<Counter>(){

        @Override
        public Counter createFromParcel(Parcel source) {
            int count = source.readInt();
            ArrayList<CounterChange> changes = source.readArrayList(CounterChange.class.getClassLoader());
            String name = source.readString();
            return new Counter(count, changes, name);
        }

        @Override
        public Counter[] newArray(int size) {
            return new Counter[size];
        }
    };

    public String getName() {
        return mName;
    }
}
