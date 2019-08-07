package app.flaminius.flaminius2k19.event.inner;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.flaminius.flaminius2k19.R;

public class InnerItem extends com.ramotion.garlandview.inner.InnerItem {
    private final View mInnerLayout;

    public final TextView mHeader;
    public final TextView mName;
    public final TextView mAddress;
    public final ImageView mAvatar;
    public final View mAvatarBorder;
    public final View mLine;

    private InnerData mInnerData;

    public InnerItem(View itemView, InnerAdapter.OnItemClickListener onItemClickListener) {
        super(itemView);
        mInnerLayout = ((ViewGroup) itemView).getChildAt(0);

        mHeader = (TextView) itemView.findViewById(R.id.tv_header);
        mName = (TextView) itemView.findViewById(R.id.tv_name);
        mAddress = (TextView) itemView.findViewById(R.id.tv_address);
        mAvatar = (ImageView) itemView.findViewById(R.id.avatar);
        mAvatarBorder = itemView.findViewById(R.id.avatar_border);
        mLine = itemView.findViewById(R.id.line);

        mInnerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(InnerItem.this);
            }
        });
    }

    @Override
    protected View getInnerLayout() {
        return mInnerLayout;
    }

    public InnerData getItemData() {
        return mInnerData;
    }

    public void clearContent() {
        mInnerData = null;
    }

    void setContent(InnerData data) {
        mInnerData = data;

        mHeader.setText(data.title);
        mName.setText(String.format("%s %s", data.name, itemView.getContext().getString(R.string.answer_low)));
        mAddress.setText(String.format("%s %s · %s",
                data.age, mAddress.getContext().getString(R.string.years), data.address));
        mAvatar.setImageResource(R.drawable.avatar_placeholder);
    }

}
