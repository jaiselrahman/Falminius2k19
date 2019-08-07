package app.flaminius.flaminius2k19.event.outer;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.ramotion.garlandview.TailAdapter;

import java.util.List;

import app.flaminius.flaminius2k19.R;
import app.flaminius.flaminius2k19.event.inner.InnerAdapter;
import app.flaminius.flaminius2k19.event.inner.InnerData;

public class OuterAdapter extends TailAdapter<OuterItem> {
    private final int POOL_SIZE = 16;

    private final List<List<InnerData>> mData;
    private final RecyclerView.RecycledViewPool mPool;
    private InnerAdapter.OnItemClickListener onItemClickListener;

    public OuterAdapter(List<List<InnerData>> data, InnerAdapter.OnItemClickListener onItemClickListener) {
        this.mData = data;
        this.onItemClickListener = onItemClickListener;

        mPool = new RecyclerView.RecycledViewPool();
        mPool.setMaxRecycledViews(0, POOL_SIZE);
    }

    @Override
    public OuterItem onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new OuterItem(view, mPool, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(OuterItem holder, int position) {
        holder.setContent(mData.get(position));
    }

    @Override
    public void onViewRecycled(OuterItem holder) {
        holder.clearContent();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.outer_item;
    }

}
