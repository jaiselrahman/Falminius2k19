package app.flaminius.flaminius2k19.event.inner;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import java.util.ArrayList;
import java.util.List;

import app.flaminius.flaminius2k19.R;
import app.flaminius.flaminius2k19.databinding.InnerItemBinding;

public class InnerAdapter extends com.ramotion.garlandview.inner.InnerAdapter<InnerItem> {
    private OnItemClickListener onItemClickListener;

    private final List<InnerData> mData = new ArrayList<>();

    public InnerAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public InnerItem onCreateViewHolder(ViewGroup parent, int viewType) {
        final InnerItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), viewType, parent, false);
        return new InnerItem(binding.getRoot(), onItemClickListener);
    }

    @Override
    public void onBindViewHolder(InnerItem holder, int position) {
        holder.setContent(mData.get(position));
    }

    @Override
    public void onViewRecycled(InnerItem holder) {
        holder.clearContent();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.inner_item;
    }

    public void addData(@NonNull List<InnerData> innerDataList) {
        final int size = mData.size();
        mData.addAll(innerDataList);
        notifyItemRangeInserted(size, innerDataList.size());
    }

    public void clearData() {
        mData.clear();
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(InnerItem innerItem);
    }
}
