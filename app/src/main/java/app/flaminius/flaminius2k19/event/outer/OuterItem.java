package app.flaminius.flaminius2k19.event.outer;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ramotion.garlandview.header.HeaderDecorator;
import com.ramotion.garlandview.header.HeaderItem;
import com.ramotion.garlandview.inner.InnerLayoutManager;
import com.ramotion.garlandview.inner.InnerRecyclerView;

import java.util.List;

import app.flaminius.flaminius2k19.R;
import app.flaminius.flaminius2k19.event.inner.InnerAdapter;
import app.flaminius.flaminius2k19.event.inner.InnerData;

public class OuterItem extends HeaderItem {
    private final View mHeader;
    private final View mHeaderAlpha;

    private final TextView headerText;
    private final ImageView headerImage;

    private final InnerRecyclerView mRecyclerView;

    private boolean mIsScrolling;

    public OuterItem(View itemView, RecyclerView.RecycledViewPool pool, InnerAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);

        mHeader = itemView.findViewById(R.id.header);
        mHeaderAlpha = itemView.findViewById(R.id.header_alpha);

        headerText = itemView.findViewById(R.id.header_text);
        headerImage = itemView.findViewById(R.id.header_image);

        mRecyclerView = itemView.findViewById(R.id.recycler_view);
        mRecyclerView.setRecycledViewPool(pool);
        mRecyclerView.setAdapter(new InnerAdapter(onItemClickListener));

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                mIsScrolling = newState != RecyclerView.SCROLL_STATE_IDLE;
            }
        });

        mRecyclerView.addItemDecoration(new HeaderDecorator(
                itemView.getContext().getResources().getDimensionPixelSize(R.dimen.outer_header_height),
                itemView.getContext().getResources().getDimensionPixelSize(R.dimen.inner_item_offset)));
    }

    @Override
    public boolean isScrolling() {
        return mIsScrolling;
    }

    @Override
    public InnerRecyclerView getViewGroup() {
        return mRecyclerView;
    }

    @Override
    public View getHeader() {
        return mHeader;
    }

    @Override
    public View getHeaderAlphaView() {
        return mHeaderAlpha;
    }

    void setContent(@NonNull List<InnerData> innerDataList) {
        int position = getAdapterPosition();

        headerText.setText(position == 0 ? R.string.nontechnical_events : R.string.technical_events);
        headerImage.setImageResource(position == 0 ? R.drawable.ic_nontechnical_event : R.drawable.ic_technical_event);

        mRecyclerView.setLayoutManager(new InnerLayoutManager());
        ((InnerAdapter) mRecyclerView.getAdapter()).addData(innerDataList);
    }

    void clearContent() {
        ((InnerAdapter) mRecyclerView.getAdapter()).clearData();
    }
}
