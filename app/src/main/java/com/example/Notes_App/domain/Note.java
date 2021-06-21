package com.example.Notes_App.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Note implements Parcelable {

    private String name;

    private String description;

    private long date;

    public Note(String name, String description, long date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    protected Note(Parcel in) {
        name = in.readString();
        description = in.readString();
        date = in.readLong();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeLong(date);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return new Date(date);
    }

    public void setDate(long date) {
        this.date = date;
    }
}
