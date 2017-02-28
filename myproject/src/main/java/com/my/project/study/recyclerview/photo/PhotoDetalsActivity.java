package com.my.project.study.recyclerview.photo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.my.project.R;
import com.my.project.utils.LogUtil;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class PhotoDetalsActivity extends AppCompatActivity {

    private LinearLayout ll_toobar1, ll_toobar2,ll_title_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detals);
        initToolBar();
    }

    private void initToolBar() {

        ll_toobar1 = (LinearLayout) findViewById(R.id.ll_toobar1);
        ll_toobar2 = (LinearLayout) findViewById(R.id.ll_toobar2);
        ll_title_content = (LinearLayout) findViewById(R.id.ll_title_content);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar action = getSupportActionBar();
        action.setTitle("我的标题");
        action.setDisplayShowTitleEnabled(false);


        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        //collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));

        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                LogUtil.log("offset = " + verticalOffset);

                int max = collapsingToolbarLayout.getHeight() - toolbar.getHeight();
                updateView(max, verticalOffset);
            }
        });


        LogUtil.log("collapsing_toolbar = " + collapsingToolbarLayout.getHeight());
        LogUtil.log("toolbar = " + toolbar.getHeight());


//        String itemTitle = getIntent().getStringExtra(RecyclerViewPhotoActivity.EXTRA_TITLE);
//        final ImageView image = (ImageView) findViewById(R.id.image);
//        Picasso.with(this).load(getIntent().getStringExtra(RecyclerViewPhotoActivity.EXTRA_IMAGE)).into(image, new Callback() {
//            @Override
//            public void onSuccess() {
//                Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
//                Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//                    public void onGenerated(Palette palette) {
//                        //applyPalette(palette);
//                    }
//                });
//            }
//
//            @Override
//            public void onError() {
//
//            }
//        });
    }

    public void updateView(int max, int offset) {
        float num = (float)(max + offset)/max;
        LogUtil.log("num = " + num);

        updateToobarView(num);
        updateCollapsingToolbarLayout(num);

    }

    public void updateToobarView(float f) {

        if(f < 0.7) {
            ll_toobar1.setVisibility(View.GONE);
            ll_toobar2.setVisibility(View.VISIBLE);
        } else {
            ll_toobar1.setVisibility(View.VISIBLE);
            ll_toobar2.setVisibility(View.GONE);
        }

        ll_toobar1.setAlpha(f);
        ll_toobar2.setAlpha(1 - f);
    }

    public void updateCollapsingToolbarLayout(float f) {
        ll_title_content.setAlpha(f);
    }
}
