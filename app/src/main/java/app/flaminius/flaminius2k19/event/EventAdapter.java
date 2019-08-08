package app.flaminius.flaminius2k19.event;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import app.flaminius.flaminius2k19.R;

public class EventAdapter extends com.ramotion.garlandview.inner.InnerAdapter<EventAdapter.EventItem> {
    private OnItemClickListener onItemClickListener;

    private final List<Event> eventList = new ArrayList<>();

    public EventAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public EventItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new EventItem(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(EventItem holder, int position) {
        holder.setContent(eventList.get(position));
    }

    @Override
    public void onViewRecycled(EventItem holder) {
        holder.clearContent();
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.event_item;
    }

    public void addData(@NonNull List<Event> eventList) {
        final int size = this.eventList.size();
        this.eventList.addAll(eventList);
        notifyItemRangeInserted(size, eventList.size());
    }

    public void clearData() {
        eventList.clear();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(EventItem eventItem);
    }


    public class EventItem extends com.ramotion.garlandview.inner.InnerItem {
        private final View innerLayout;

        private final TextView eventName;
        private final TextView eventTagLine;
        private final TextView eventDescription;
        private final ImageView eventImage;

        private Event event;

        public EventItem(View itemView, EventAdapter.OnItemClickListener onItemClickListener) {
            super(itemView);
            innerLayout = ((ViewGroup) itemView).getChildAt(0);

            eventName = itemView.findViewById(R.id.event_name);
            eventTagLine = itemView.findViewById(R.id.event_tagline);
            eventDescription = itemView.findViewById(R.id.event_description);
            eventImage = itemView.findViewById(R.id.event_image);

            innerLayout.setOnClickListener(view -> onItemClickListener.onItemClick(EventItem.this));
        }

        @Override
        protected View getInnerLayout() {
            return innerLayout;
        }

        public void clearContent() {
            this.event = null;
        }

        public Event getEvent() {
            return event;
        }

        void setContent(Event event) {
            this.event = event;

            eventName.setText(event.title);
            eventTagLine.setText(event.tagLine);
            eventDescription.setText(event.description);
            eventImage.setImageResource(event.image);
        }
    }
}
