package app.flaminius.flaminius2k19.details;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import app.flaminius.flaminius2k19.R;

public class DetailsActivity extends AppCompatActivity {

    private static final int ITEM_COUNT = 4;

    private static final String BUNDLE_NAME = "BUNDLE_NAME";
    private static final String BUNDLE_INFO = "BUNDLE_INFO";
    private static final String BUNDLE_AVATAR_URL = "BUNDLE_AVATAR_URL";

    private final ArrayList<DetailsData> mListData = new ArrayList<>();

    public static void start(final AppCompatActivity activity,
                             final String name, final String address, final String url,
                             final View card, final View avatar) {
        Intent starter = new Intent(activity, DetailsActivity.class);

        starter.putExtra(BUNDLE_NAME, name);
        starter.putExtra(BUNDLE_INFO, address);
        starter.putExtra(BUNDLE_AVATAR_URL, url);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final Pair<View, String> p1 = Pair.create(card, activity.getString(R.string.transition_card));
            final Pair<View, String> p2 = Pair.create(avatar, activity.getString(R.string.transition_avatar_border));

            final ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity, p1, p2);
            activity.startActivity(starter, options.toBundle());
        } else {
            activity.startActivity(starter);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ((TextView) findViewById(R.id.tv_name)).setText(getIntent().getStringExtra(BUNDLE_NAME));
        ((TextView) findViewById(R.id.tv_info)).setText(getIntent().getStringExtra(BUNDLE_INFO));

        ((ImageView) findViewById(R.id.avatar)).setImageResource(R.drawable.avatar_placeholder);

        for (int i = 0; i < ITEM_COUNT; i++) {
            mListData.add(new DetailsData("Title " + i, "Name " + i));
        }

        ((RecyclerView)findViewById(R.id.recycler_view)).setAdapter(new DetailsAdapter(mListData));
    }


    public void onCloseClick(View v) {
        super.onBackPressed();
    }

    public void onDetailsClick(View v) {
//        ProfileActivity.start(this,
//                getIntent().getStringExtra(BUNDLE_AVATAR_URL),
//                getIntent().getStringExtra(BUNDLE_NAME),
//                getIntent().getStringExtra(BUNDLE_INFO),
//                ((TextView) findViewById(R.id.tv_status)).getText().toString(),
//                findViewById(R.id.avatar),
//                findViewById(R.id.card),
//                findViewById(R.id.iv_background),
//                findViewById(R.id.recycler_view),
//                mListData);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            finishAfterTransition();
//        } else {
//            finish();
//        }
    }

}
