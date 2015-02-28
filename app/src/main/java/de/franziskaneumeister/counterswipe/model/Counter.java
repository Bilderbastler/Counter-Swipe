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
 * Dependencies need to be injected manualy after the object is recreated from a parcel*
 */
public class Counter implements Parcelable{

    private final BehaviorSubject<Integer> mCountChangePublisher;
    private int mCount;
    private List<CounterChange> mChanges;

    @Inject
    protected Injector injector;
    
    public Counter(){
        mChanges = new ArrayList<>();
        mCountChangePublisher = BehaviorSubject.create(0);
    }

    public Counter(int count, ArrayList<CounterChange> changes) {
        mChanges= changes;
        mCount = count;
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
    }
    
    public static final Creator CREATOR = new Creator<Counter>(){

        @Override
        public Counter createFromParcel(Parcel source) {
            int count = source.readInt();
            ArrayList<CounterChange> changes = source.readArrayList(CounterChange.class.getClassLoader());
            return new Counter(count, changes);
        }

        @Override
        public Counter[] newArray(int size) {
            return new Counter[size];
        }
    };
}
