package com.my.project.study.recyclerview.photo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.project.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hexiaoning on 2016/8/8.
 */
public class RecyclerViewPhotoAdapter extends RecyclerView.Adapter<RecyclerViewPhotoAdapter.ViewHolder>{

    private List<ViewModel> lists;
    private Context mContext;
    private OnItemClickListener listener;

    public RecyclerViewPhotoAdapter(Context mContext, List<ViewModel> lists) {
        this.lists = lists;
        this.mContext = mContext;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerViewPhotoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler, null);
        RecyclerViewPhotoAdapter.ViewHolder viewHolder = new RecyclerViewPhotoAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerViewPhotoAdapter.ViewHolder holder, final int position) {
        holder.tv.setText(lists.get(position).getText());
        Picasso.with(mContext).load(lists.get(position).getImage()).into(holder.image);

        if(listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(holder.itemView, lists.get(position));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.text);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

    public interface OnItemClickListener {

        void onItemClick(View view, ViewModel viewModel);

    }
}
