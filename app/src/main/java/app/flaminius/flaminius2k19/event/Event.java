package app.flaminius.flaminius2k19.event;

import androidx.annotation.DrawableRes;

public class Event {
    public final String title;
    public final String tagLine;
    public final String description;
    public final String rules;
    public final String contactDetails;
    public final @DrawableRes
    int image;

    public Event(String title, String tagLine, String description, String rules, String contactDetails, int img) {
        this.title = title;
        this.tagLine = tagLine;
        this.description = description;
        this.rules = rules;
        this.contactDetails = contactDetails;
        this.image = img;
    }
}
