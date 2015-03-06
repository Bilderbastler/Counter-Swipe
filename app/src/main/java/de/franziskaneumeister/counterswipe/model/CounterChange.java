package de.franziskaneumeister.counterswipe.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Represents a change in a counter 
 * TODO: save date of change and the numerical change in the counter
 */
public class CounterChange implements Parcelable{

    public CounterChange() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
    
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator<CounterChange>() {
        @Override
        public CounterChange createFromParcel(Parcel source) {
            return new CounterChange();
        }

        @Override
        public CounterChange[] newArray(int size) {
            return new CounterChange[size];
        }
    };
}
