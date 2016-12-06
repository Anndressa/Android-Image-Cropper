package com.theartofdev.edmodo.cropper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anndressa on 02/12/16.
 */

public class AspectRatio implements Parcelable {
    public final int x;
    public final int y;

    public AspectRatio(int x, int y) {
        this.x = x;
        this.y = y;
    }

    protected AspectRatio(Parcel in) {
        x = in.readInt();
        y = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(x);
        dest.writeInt(y);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AspectRatio> CREATOR = new Creator<AspectRatio>() {
        @Override
        public AspectRatio createFromParcel(Parcel in) {
            return new AspectRatio(in);
        }

        @Override
        public AspectRatio[] newArray(int size) {
            return new AspectRatio[size];
        }
    };

    public void validate() {
        if (x <= 0 || y <= 0)
            throw new IllegalArgumentException("Cannot set aspect ratio value to a number less than or equal to 0.");
    }

    public float value() {
        return x / (float) y;
    }
}
