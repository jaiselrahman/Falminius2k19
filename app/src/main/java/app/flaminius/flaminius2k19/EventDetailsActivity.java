package app.flaminius.flaminius2k19;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import app.flaminius.flaminius2k19.event.Event;
import app.flaminius.flaminius2k19.event.EventAdapter;

public class EventDetailsActivity extends AppCompatActivity {
    private static final String EVENT = "EVENT";

    public static void show(final AppCompatActivity activity, EventAdapter.EventItem eventItem, Event event) {
        Intent intent = new Intent(activity, EventDetailsActivity.class);
        intent.putExtra(EVENT, event);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final Pair<View, String> p1 = Pair.create(eventItem.eventNameCard, activity.getString(R.string.transition_name));
            final Pair<View, String> p2 = Pair.create(eventItem.eventCard, activity.getString(R.string.transition_card));
            final Pair<View, String> p3 = Pair.create(eventItem.eventImage, activity.getString(R.string.transition_image));
            final Pair<View, String> p4 = Pair.create(eventItem.eventDetails, activity.getString(R.string.transition_details));

            final ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, p1, p2, p3, p4);
            activity.startActivity(intent, options.toBundle());
        } else {
            activity.startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Event event = getIntent().getParcelableExtra(EVENT);

        if (event == null) {
            finish();
            return;
        }

        ((TextView) findViewById(R.id.event_name)).setText(event.name);
        ((TextView) findViewById(R.id.event_tagline)).setText(event.tagLine);

        if (!TextUtils.isEmpty(event.description)) {
            ((TextView) findViewById(R.id.event_description)).setText(Html.fromHtml(event.description));
        } else {
            findViewById(R.id.event_description).setVisibility(View.GONE);
            findViewById(R.id.event_description_header).setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(event.rules)) {
            ((TextView) findViewById(R.id.event_rules)).setText(event.rules);
        } else {
            findViewById(R.id.event_rules).setVisibility(View.GONE);
            findViewById(R.id.event_rules_header).setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(event.contacts)) {
            ((TextView) findViewById(R.id.event_contacts)).setText(event.contacts);
        } else {
            findViewById(R.id.event_contacts).setVisibility(View.GONE);
            findViewById(R.id.event_contact_header).setVisibility(View.GONE);
        }
    }
}
