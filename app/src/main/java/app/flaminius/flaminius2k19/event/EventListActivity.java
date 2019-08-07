package app.flaminius.flaminius2k19.event;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ramotion.garlandview.TailLayoutManager;
import com.ramotion.garlandview.TailRecyclerView;
import com.ramotion.garlandview.TailSnapHelper;
import com.ramotion.garlandview.header.HeaderTransformer;

import java.util.ArrayList;
import java.util.List;

import app.flaminius.flaminius2k19.R;
import app.flaminius.flaminius2k19.details.DetailsActivity;
import app.flaminius.flaminius2k19.event.inner.InnerAdapter;
import app.flaminius.flaminius2k19.event.inner.InnerData;
import app.flaminius.flaminius2k19.event.inner.InnerItem;
import app.flaminius.flaminius2k19.event.outer.OuterAdapter;

public class EventListActivity extends AppCompatActivity implements InnerAdapter.OnItemClickListener {
    public static final String DEFAULT = "DEFAULT";
    public static final String TECHNICAL = "TECHNICAL";
    public static final String NON_TECHNICAL = "NON_TECHNICAL";
    private final static int OUTER_COUNT = 2;
    private final static int INNER_COUNT = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);

        final List<List<InnerData>> outerData = new ArrayList<>();
        for (int i = 0; i < OUTER_COUNT; i++) {
            final List<InnerData> innerData = new ArrayList<>();
            for (int j = 0; j < INNER_COUNT; j++) {
                innerData.add(createInnerData(j));
            }
            outerData.add(innerData);
        }
        initRecyclerView(outerData);
    }


    private void initRecyclerView(List<List<InnerData>> data) {
        final TailRecyclerView rv = findViewById(R.id.recycler_view);
        ((TailLayoutManager) rv.getLayoutManager()).setPageTransformer(new HeaderTransformer());
        rv.setAdapter(new OuterAdapter(data, this));

        new TailSnapHelper().attachToRecyclerView(rv);
    }

    private InnerData createInnerData(int i) {
        return new InnerData(
                "Title " + i,
                "Name " + i,
                "Address " + i,
                "image " + i,
                i + 20
        );
    }

    @Override
    public void onItemClick(InnerItem item) {
        final InnerData itemData = item.getItemData();
        if (itemData == null) {
            return;
        }

        DetailsActivity.start(this,
                item.getItemData().name, item.mAddress.getText().toString(),
                item.getItemData().avatarUrl, item.itemView, item.mAvatarBorder);
    }
}
