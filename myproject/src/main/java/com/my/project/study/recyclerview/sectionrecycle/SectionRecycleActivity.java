package com.my.project.study.recyclerview.sectionrecycle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.my.project.R;

public class SectionRecycleActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_recycle);

        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);

        setupRecycler();
    }

    protected void setupRecycler(){
        CountSectionAdapter adapter = new CountSectionAdapter(this);
//        mRecyclerView .setAdapter(adapter);
//
//        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
//        SectionedSpanSizeLookup lookup = new SectionedSpanSizeLookup(adapter, layoutManager);
//        layoutManager.setSpanSizeLookup(lookup);
//        mRecyclerView .setLayoutManager(layoutManager);

        AgendaSimpleSectionAdapter simpleSectionAdapter = new AgendaSimpleSectionAdapter();
        mRecyclerView.setAdapter(simpleSectionAdapter);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this);
        mRecyclerView .setLayoutManager(layoutManager1);

    }
}
