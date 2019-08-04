package app.flaminius.flaminius2k19;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomMenuButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();

        setUpFollowButtons();

        BoomMenuButton boom = findViewById(R.id.boom_menu);
        for (int i = 0; i < boom.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .normalImageRes(R.drawable.ic_launcher_background)
                    .normalText("Butter Doesn't fly!")
                    .subNormalText("Little butter Doesn't fly, either!")
                    .pieceColor(Color.WHITE);
            boom.addBuilder(builder);
        }

        findViewById(R.id.venue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.venue_location)));
                startActivity(intent);
            }
        });
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

    private void setUpFollowButtons() {
        findViewById(R.id.follow_facebook).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.facebook_page)));
                startActivity(intent);
            }
        });

        findViewById(R.id.follow_instagram).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.instagram_page)));
                startActivity(intent);
            }
        });

        findViewById(R.id.follow_twitter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.twitter_page)));
                startActivity(intent);
            }
        });

        findViewById(R.id.follow_website).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.website_page)));
                startActivity(intent);
            }
        });
    }
}
