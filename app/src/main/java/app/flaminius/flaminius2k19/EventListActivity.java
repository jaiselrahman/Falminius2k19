package app.flaminius.flaminius2k19;

import android.os.Bundle;

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
    private final static int OUTER_COUNT = 3;
    private final static int INNER_COUNT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        final List<List<Event>> outerData = new ArrayList<>();
        for (int i = 0; i < OUTER_COUNT; i++) {
            final List<Event> innerData = new ArrayList<>();
            for (int j = 0; j < INNER_COUNT; j++) {
                innerData.add(createInnerData(j));
            }
            outerData.add(innerData);
        }
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

    private Event createInnerData(int i) {
        return new Event(
                "Name " + i,
                "TagLine " + i,
                "Description " + i,
                "Rules " + i,
                "Contact Details " + i, R.drawable.details_top_background
        );
    }

    @Override
    public void onItemClick(EventAdapter.EventItem item) {
        final Event event = item.getEvent();
        if (event == null) {
            return;
        }

        EventDetailsActivity.show(this, item, event);
    }
}
