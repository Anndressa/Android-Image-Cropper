package com.theartofdev.edmodo.cropper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by anndressa on 02/12/16.
 */

public class AspectRatioOptions implements Parcelable {

    public enum AspectRatioType {
        FREE,
        FIXED,
        LIMITED
    }

    public final AspectRatio minAspectRatio;
    public final AspectRatio maxAspectRatio;

    public AspectRatioOptions() {
        this(null, null);
    }

    public AspectRatioOptions(int fixedX, int fixedY) {
        this(new AspectRatio(fixedX, fixedY), null);
    }

    public AspectRatioOptions(int minX, int minY, int maxX, int maxY) {
        this(new AspectRatio(minX, minY), new AspectRatio(maxX, maxY));
    }

    public AspectRatioOptions(AspectRatio minAspectRatio, AspectRatio maxAspectRatio) {
        this.minAspectRatio = minAspectRatio;
        this.maxAspectRatio = maxAspectRatio;
    }

    protected AspectRatioOptions(Parcel in) {
        minAspectRatio = in.readParcelable(AspectRatio.class.getClassLoader());
        maxAspectRatio = in.readParcelable(AspectRatio.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(minAspectRatio, flags);
        dest.writeParcelable(maxAspectRatio, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AspectRatioOptions> CREATOR = new Creator<AspectRatioOptions>() {
        @Override
        public AspectRatioOptions createFromParcel(Parcel in) {
            return new AspectRatioOptions(in);
        }

        @Override
        public AspectRatioOptions[] newArray(int size) {
            return new AspectRatioOptions[size];
        }
    };

    public void validate(AspectRatio aspectRatio) {
        if (aspectRatio != null) aspectRatio.validate();
    }

    public void validate() {
        validate(minAspectRatio);
        validate(maxAspectRatio);
        if (getAspectRatioType() == AspectRatioType.LIMITED && minAspectRatio.value() >= maxAspectRatio.value())
            throw new IllegalArgumentException("Cannot set min aspect ratio value to a value less than or equal to max aspect ratio");
    }

    public AspectRatioType getAspectRatioType() {
        if (minAspectRatio == null && maxAspectRatio == null) return AspectRatioType.FREE;
        else if (maxAspectRatio == null) return AspectRatioType.FIXED;
        return AspectRatioType.LIMITED;
    }

    public boolean isFixedAspectRatio() {
        return getAspectRatioType() == AspectRatioType.FIXED;
    }

    public boolean isLimitedAspectRatio() {
        return getAspectRatioType() == AspectRatioType.LIMITED;
    }

    public boolean isFreeAspectRatio() {
        return getAspectRatioType() == AspectRatioType.FREE;
    }

    public float getFixedAspectRatio() {
        return getMinAspectRatio();
    }

    public float getMinAspectRatio() {
        return minAspectRatio != null ? minAspectRatio.value() : 1;
    }

    public float getMaxAspectRatio() {
        return maxAspectRatio != null ? maxAspectRatio.value() : 1;
    }

}
