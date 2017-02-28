package com.my.project.study.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.my.project.R;
import com.my.project.study.recyclerview.demo.HomeActivity;
import com.my.project.study.recyclerview.demo.StaggeredGridLayoutActivity;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<String> mDatas;
    private MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new MyRecyclerViewAdapter(mDatas);

//        LinearLayoutManager manger = new LinearLayoutManager(this);
//        manger.setOrientation(OrientationHelper.VERTICAL);
//        mRecyclerView.setLayoutManager(manger);

//        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
//                DividerItemDecoration.VERTICAL_LIST));

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.HORIZONTAL));

//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,4));
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));

        mRecyclerView.setAdapter(adapter);

        initEvent();
    }

    private void initEvent()
    {
        adapter.setOnItemClickLitener(new OnItemClickLitener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                Toast.makeText(RecyclerViewActivity.this, position + " click",
                        Toast.LENGTH_SHORT).show();
                if(position == 0) {
                    startActivity(new Intent(RecyclerViewActivity.this, HomeActivity.class));
                } else {
                    startActivity(new Intent(RecyclerViewActivity.this, StaggeredGridLayoutActivity.class));
                }
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                Toast.makeText(RecyclerViewActivity.this, position + " long click",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHoler> {

        private ArrayList<String> list;
        private OnItemClickLitener mOnItemClickLitener;

        public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
        {
            this.mOnItemClickLitener = mOnItemClickLitener;
        }

        public MyRecyclerViewAdapter(ArrayList<String> list) {
            this.list = list;
        }

        @Override
        public MyRecyclerViewAdapter.MyViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(RecyclerViewActivity.this).inflate(R.layout.item_list1, null);
            MyRecyclerViewAdapter.MyViewHoler viewHolder = new MyViewHoler(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(final MyRecyclerViewAdapter.MyViewHoler holder, int position) {
            holder.tv.setText(list.get(position));

            // 如果设置了回调，则设置点击事件
            if (mOnItemClickLitener != null)
            {
                holder.itemView.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemClick(holder.itemView, pos);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
                {
                    @Override
                    public boolean onLongClick(View v)
                    {
                        int pos = holder.getLayoutPosition();
                        mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                        return false;
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHoler extends RecyclerView.ViewHolder {

            TextView tv;

            public MyViewHoler(View itemView) {
                super(itemView);
                tv = (TextView)itemView.findViewById(R.id.tv1);
            }
        }
    }

    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }
}
