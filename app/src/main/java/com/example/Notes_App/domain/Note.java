package com.example.Notes_App.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class Note implements Parcelable {

    private UUID id;
    private String name;
    private String description;
    private long date;

    public Note(UUID id, String name, String description, long date) {
        this.id = id;
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

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void updateNote(String name, String description, long date) {
        if (name != null) {
            setName(name);
        }
        if (description != null) {
            setDescription(description);
        }

        setDate(date);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFromatedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy", Locale.ENGLISH);
        return sdf.format(new Date(date));
    }

    public long getDate(){
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

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
}
