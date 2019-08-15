package app.flaminius.flaminius2k19.event;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ramotion.garlandview.TailAdapter;
import com.ramotion.garlandview.header.HeaderDecorator;
import com.ramotion.garlandview.header.HeaderItem;
import com.ramotion.garlandview.inner.InnerLayoutManager;
import com.ramotion.garlandview.inner.InnerRecyclerView;

import java.util.List;

import app.flaminius.flaminius2k19.R;

public class EventCategoryAdapter extends TailAdapter<EventCategoryAdapter.EventCategory> {

    private final List<List<Event>> eventLists;
    private final RecyclerView.RecycledViewPool viewPool;
    private EventAdapter.OnItemClickListener onItemClickListener;

    public EventCategoryAdapter(List<List<Event>> eventLists, EventAdapter.OnItemClickListener onItemClickListener) {
        this.eventLists = eventLists;
        this.onItemClickListener = onItemClickListener;

        viewPool = new RecyclerView.RecycledViewPool();
    }

    @Override
    public EventCategory onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new EventCategory(view, viewPool, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(EventCategory holder, int position) {
        holder.setContent(eventLists.get(position));
    }

    @Override
    public void onViewRecycled(EventCategory holder) {
        holder.clearContent();
    }

    @Override
    public int getItemCount() {
        return eventLists.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.event_category;
    }


    public class EventCategory extends HeaderItem {
        private final View header;
        private final View headerAlpha;

        private final TextView headerText;
        private final ImageView headerImage;

        private final InnerRecyclerView recyclerView;

        private boolean isScrolling;

        public EventCategory(View itemView, RecyclerView.RecycledViewPool pool, EventAdapter.OnItemClickListener onItemClickListener) {
            super(itemView);

            header = itemView.findViewById(R.id.header);
            headerAlpha = itemView.findViewById(R.id.header_alpha);

            headerText = itemView.findViewById(R.id.header_text);
            headerImage = itemView.findViewById(R.id.header_image);

            recyclerView = itemView.findViewById(R.id.recycler_view);
            recyclerView.setRecycledViewPool(pool);
            recyclerView.setAdapter(new EventAdapter(onItemClickListener));

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    isScrolling = newState != RecyclerView.SCROLL_STATE_IDLE;
                }
            });

            recyclerView.addItemDecoration(new HeaderDecorator(
                    itemView.getContext().getResources().getDimensionPixelSize(R.dimen.outer_header_height),
                    itemView.getContext().getResources().getDimensionPixelSize(R.dimen.inner_item_offset)));
        }

        @Override
        public boolean isScrolling() {
            return isScrolling;
        }

        @Override
        public InnerRecyclerView getViewGroup() {
            return recyclerView;
        }

        @Override
        public View getHeader() {
            return header;
        }

        @Override
        public View getHeaderAlphaView() {
            return headerAlpha;
        }

        void setContent(@NonNull List<Event> eventList) {
            int position = getAdapterPosition();

            switch (position) {
                case 0:
                    headerText.setText(R.string.nontechnical_events);
                    headerImage.setImageResource(R.drawable.ic_nontechnical_event);
                    break;
                case 1:
                    headerText.setText(R.string.technical_events);
                    headerImage.setImageResource(R.drawable.ic_technical_event);
                    break;
                case 2:
                    headerText.setText(R.string.online_events);
                    headerImage.setImageResource(R.drawable.ic_online_event);
                    break;
            }

            recyclerView.setLayoutManager(new InnerLayoutManager());
            ((EventAdapter) recyclerView.getAdapter()).addData(eventList);
        }

        void clearContent() {
            ((EventAdapter) recyclerView.getAdapter()).clearData();
        }
    }
}
