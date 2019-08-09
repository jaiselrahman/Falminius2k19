package app.flaminius.flaminius2k19;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Util;

public class MainActivity extends AppCompatActivity {
    private final static int LAUNCH_DELAY = 120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();

        setUpContacts();

        setUpFollowButtons();

        setUpBoomMenu();

        findViewById(R.id.registerShimmer).setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.venue).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.venue_location)));
            startActivity(intent);
        });
    }

    private void setUpBoomMenu() {
        BoomMenuButton boom = findViewById(R.id.boom_menu);

        int buttonCornerRadius = Util.dp2px(50);
        int textSize = 18;
        Rect textPadding = new Rect(0, Util.dp2px(5.5f), 0, 0);

        boom.addBuilder(new HamButton.Builder()
                .normalTextRes(R.string.nontechnical_events)
                .textSize(textSize)
                .textPadding(textPadding)
                .normalColorRes(R.color.event_icon_bg)
                .normalImageRes(R.drawable.ic_nontechnical_event)
                .containsSubText(false)
                .buttonCornerRadius(buttonCornerRadius)
                .pieceColor(Color.WHITE)
                .listener(index -> boom.postDelayed(() -> {
                    Intent intent = new Intent(this, EventListActivity.class);
                    intent.putExtra(EventListActivity.DEFAULT, EventListActivity.NON_TECHNICAL);
                    startActivity(intent);
                }, LAUNCH_DELAY)));

        boom.addBuilder(new HamButton.Builder()
                .normalTextRes(R.string.technical_events)
                .textSize(textSize)
                .textPadding(textPadding)
                .normalColorRes(R.color.event_icon_bg)
                .normalImageRes(R.drawable.ic_technical_event)
                .containsSubText(false)
                .buttonCornerRadius(buttonCornerRadius)
                .pieceColor(Color.WHITE)
                .listener(index -> boom.postDelayed(() -> {
                    Intent intent = new Intent(this, EventListActivity.class);
                    intent.putExtra(EventListActivity.DEFAULT, EventListActivity.TECHNICAL);
                    startActivity(intent);
                }, LAUNCH_DELAY)));

        boom.addBuilder(new HamButton.Builder()
                .normalTextRes(R.string.online_events)
                .textSize(textSize)
                .textPadding(textPadding)
                .normalColorRes(R.color.event_icon_bg)
                .normalImageRes(R.drawable.ic_online_event)
                .containsSubText(false)
                .buttonCornerRadius(buttonCornerRadius)
                .pieceColor(Color.WHITE)
                .listener(index -> boom.postDelayed(() -> {
                    Intent intent = new Intent(this, EventListActivity.class);
                    intent.putExtra(EventListActivity.DEFAULT, EventListActivity.ONLINE);
                    startActivity(intent);
                }, LAUNCH_DELAY)));

        boom.addBuilder(new HamButton.Builder()
                .normalTextRes(R.string.coordinators)
                .textSize(textSize)
                .textPadding(textPadding)
                .normalColorRes(R.color.coordinators)
                .normalImageRes(R.drawable.ic_coordinators)
                .containsSubText(false)
                .buttonCornerRadius(buttonCornerRadius)
                .pieceColor(Color.WHITE));

        boom.addBuilder(new HamButton.Builder()
                .normalTextRes(R.string.gallery)
                .textSize(textSize)
                .textPadding(textPadding)
                .normalColorRes(R.color.gallery)
                .normalImageRes(R.drawable.ic_gallery)
                .containsSubText(false)
                .buttonCornerRadius(buttonCornerRadius)
                .pieceColor(Color.WHITE));
    }

    private void setUpToolbar() {
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final CollapsingToolbarLayout collapsingToolbar = findViewById(R.id.toolbar_layout);

        final AppBarLayout appBar = findViewById(R.id.app_bar);
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            private int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }

                if (scrollRange + i == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                } else if (!TextUtils.isEmpty(collapsingToolbar.getTitle())) {
                    collapsingToolbar.setTitle("");
                }
            }
        });
    }

    private void setUpContacts() {
        findViewById(R.id.contact_us_email).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + getString(R.string.contact_email)));
            startActivity(intent);
        });

        findViewById(R.id.contact_us_president).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + getString(R.string.contact_president)));
            startActivity(intent);
        });

        findViewById(R.id.contact_us_secretary).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + getString(R.string.contact_secretary)));
            startActivity(intent);
        });

        findViewById(R.id.contact_us_event_coordinator).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + getString(R.string.contact_event_coordinator)));
            startActivity(intent);
        });
    }

    private void setUpFollowButtons() {
        findViewById(R.id.follow_facebook).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.facebook_page)));
            startActivity(intent);
        });

        findViewById(R.id.follow_instagram).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.instagram_page)));
            startActivity(intent);
        });

        findViewById(R.id.follow_twitter).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.twitter_page)));
            startActivity(intent);
        });

        findViewById(R.id.follow_website).setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.website_page)));
            startActivity(intent);
        });
    }
}
