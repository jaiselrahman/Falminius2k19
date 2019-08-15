package app.flaminius.flaminius2k19;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.ArrayRes;
import androidx.appcompat.app.AppCompatActivity;

import com.ramotion.garlandview.TailLayoutManager;
import com.ramotion.garlandview.TailRecyclerView;
import com.ramotion.garlandview.TailSnapHelper;
import com.ramotion.garlandview.header.HeaderTransformer;

import java.util.ArrayList;
import java.util.List;

import app.flaminius.flaminius2k19.event.Event;
import app.flaminius.flaminius2k19.event.EventAdapter;
import app.flaminius.flaminius2k19.event.EventCategoryAdapter;

public class EventListActivity extends AppCompatActivity implements EventAdapter.OnItemClickListener {
    public static final String DEFAULT = "DEFAULT";
    public static final String TECHNICAL = "TECHNICAL";
    public static final String NON_TECHNICAL = "NON_TECHNICAL";
    public static final String ONLINE = "ONLINE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        List<List<Event>> outerData = new ArrayList<>();
        outerData.add(getEvents(R.array.non_technical_events, R.array.non_technical_event_images));
        outerData.add(getEvents(R.array.technical_events, R.array.technical_event_images));
        outerData.add(getEvents(R.array.online_events, R.array.online_event_images));

        initRecyclerView(outerData);
    }

    private void initRecyclerView(List<List<Event>> data) {
        final TailRecyclerView rv = findViewById(R.id.recycler_view);
        ((TailLayoutManager) rv.getLayoutManager()).setPageTransformer(new HeaderTransformer());
        rv.setAdapter(new EventCategoryAdapter(data, this));

        new TailSnapHelper().attachToRecyclerView(rv);

        String eventCategory = getIntent().getStringExtra(DEFAULT);
        if (eventCategory == null || eventCategory.equals(NON_TECHNICAL)) {
            rv.scrollToPosition(0);
        } else if (eventCategory.equals(TECHNICAL)) {
            rv.scrollToPosition(1);
        } else {
            rv.scrollToPosition(2);
        }
    }

    @Override
    public void onItemClick(EventAdapter.EventItem item) {
        final Event event = item.getEvent();
        if (event == null) {
            return;
        }

        EventDetailsActivity.show(this, item, event);
    }

    private List<Event> getEvents(@ArrayRes int eventCategoryId, @ArrayRes int eventImagesId) {
        List<Event> events = new ArrayList<>();

        Resources res = getResources();
        TypedArray ta = res.obtainTypedArray(eventCategoryId);

        TypedArray eventImages = res.obtainTypedArray(eventImagesId);

        int n = ta.length();
        for (int i = 0; i < n; i++) {
            int eventsId = ta.getResourceId(i, 0);
            if (eventsId > 0) {
                String[] event = res.getStringArray(eventsId);
                events.add(new Event(event[0], event[1], event[2], event[3], event[4], eventImages.getResourceId(i, 0)));
            }
        }
        ta.recycle();

        return events;
    }
}
