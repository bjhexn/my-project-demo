package com.my.project.study.recyclerview.photo;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.my.project.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewPhotoActivity extends AppCompatActivity implements RecyclerViewPhotoAdapter.OnItemClickListener{

    private RecyclerView recyclerView_photo;

    public static final String AVATAR_URL = "http://lorempixel.com/200/200/people/1/";

    private static List<ViewModel> items = new ArrayList<ViewModel>();

    static {
        for (int i = 1; i <= 10; i++) {
            items.add(new ViewModel("图片 " + i, "http://lorempixel.com/500/500/animals/" + i));
        }
    }

    public static final String EXTRA_IMAGE = "extra_image";
    public static final String EXTRA_TITLE = "extra_title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_photo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("我的图片");
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        setSupportActionBar(toolbar);

        recyclerView_photo = (RecyclerView) findViewById(R.id.recyclerView_photo);
        recyclerView_photo.setLayoutManager(new GridLayoutManager(this, 2));
        RecyclerViewPhotoAdapter adapter = new RecyclerViewPhotoAdapter(this, items);
        adapter.setOnItemClickListener(this);
        recyclerView_photo.setAdapter(adapter);
    }

    @Override
    public void onItemClick(View view, ViewModel viewModel) {
        goToDetailsActivity(viewModel);
    }

    private void showToast(String ss) {
        Toast.makeText(this, ss, Toast.LENGTH_SHORT).show();
    }

    private void goToDetailsActivity(ViewModel viewModel) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_IMAGE, viewModel.getImage());
        intent.putExtra(EXTRA_TITLE, viewModel.getText());
        intent.setClass(this, PhotoDetalsActivity.class);
        this.startActivity(intent);
    }

    public static void navigate(AppCompatActivity activity, View transitionImage, ViewModel viewModel) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(EXTRA_IMAGE, viewModel.getImage());
        intent.putExtra(EXTRA_TITLE, viewModel.getText());

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, transitionImage, EXTRA_IMAGE);
        ActivityCompat.startActivity(activity, intent, options.toBundle());
    }
}
