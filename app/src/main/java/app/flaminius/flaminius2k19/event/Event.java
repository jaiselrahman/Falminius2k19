package app.flaminius.flaminius2k19.event;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.LinearLayout;

import androidx.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.List;

import app.flaminius.flaminius2k19.R;

public class Event implements Parcelable {
    public final String name;
    public final String tagLine;
    public final String description;
    public final String rules;
    public final String contacts;
    public final @DrawableRes
    int image;

    public Event(String name, String tagLine, String description, String rules, String contacts, int img) {
        this.name = name;
        this.tagLine = tagLine;
        this.description = description;
        this.rules = rules;
        this.contacts = contacts;
        this.image = img;
    }

    protected Event(Parcel in) {
        name = in.readString();
        tagLine = in.readString();
        description = in.readString();
        rules = in.readString();
        contacts = in.readString();
        image = in.readInt();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(tagLine);
        parcel.writeString(description);
        parcel.writeString(rules);
        parcel.writeString(contacts);
        parcel.writeInt(image);
    }
}
