package app.flaminius.flaminius2k19.event;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

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

        public final TextView eventName;
        public final ImageView eventImage;
        public final CardView eventCard;
        public final CardView eventNameCard;
        public final TextView eventTagLine;
        public final TextView eventDescription;
        public final View eventDetails;

        private Event event;

        public EventItem(View itemView, EventAdapter.OnItemClickListener onItemClickListener) {
            super(itemView);
            innerLayout = ((ViewGroup) itemView).getChildAt(0);

            eventImage = itemView.findViewById(R.id.event_image);
            eventCard = itemView.findViewById(R.id.card);

            eventNameCard = itemView.findViewById(R.id.event_name_card);
            eventName = eventNameCard.findViewById(R.id.event_name);

            eventDetails = itemView.findViewById(R.id.event_details);
            eventTagLine = eventDetails.findViewById(R.id.event_tagline);
            eventDescription = eventDetails.findViewById(R.id.event_description);

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

            eventName.setText(event.name);
            eventTagLine.setText(event.tagLine);
            eventDescription.setText(event.description);
            eventImage.setImageResource(event.image);
        }
    }
}
