package com.example.studentportal;

import android.os.Parcel;
import android.os.Parcelable;

public class Portal implements Parcelable {
    private String url;
    private String label;

    //region Constructors
    public Portal(String url, String label) {
        this.url = url;
        this.label = label;
    }
    //endregion

    //region Getters and setters
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    //endregion

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.label);
    }

    protected Portal(Parcel in) {
        this.url = in.readString();
        this.label = in.readString();
    }

    public static final Parcelable.Creator<Portal> CREATOR = new Parcelable.Creator<Portal>() {
        @Override
        public Portal createFromParcel(Parcel source) {
            return new Portal(source);
        }

        @Override
        public Portal[] newArray(int size) {
            return new Portal[size];
        }
    };
}
