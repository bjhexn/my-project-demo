package com.my.project.study.recyclerview.recycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.project.R;
import com.my.project.utils.LogUtil;

import java.util.ArrayList;

import static com.my.project.study.recyclerview.recycle.RecycleAdapter.ITEM_TYPE.ITEM_TYPE_CONTENT;
import static com.my.project.study.recyclerview.recycle.RecycleAdapter.ITEM_TYPE.ITEM_TYPE_TITLE;

/**
 * Created by hexiaoning on 2017/3/1.
 */

public class RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private LayoutInflater mLayoutInflater;
    private ArrayList<SecondTypeBean> lists;
    private OnItemClickListener listener;
    private Context mContext;

    public RecycleAdapter(Context mContext, ArrayList<SecondTypeBean> list) {
        this.lists = list;
        mLayoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        LogUtil.log("mTitles size = " + lists.size());
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == ITEM_TYPE_CONTENT.ordinal()) {
            return new ContentViewHolder(mLayoutInflater.inflate(R.layout.layout_recycle_item, null));
        } else if(viewType == ITEM_TYPE_TITLE.ordinal()) {
            return new TitleViewHolder(mLayoutInflater.inflate(R.layout.layout_custom_type_item_title, null));
        } else {
            return null;
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        LogUtil.log("调用绑定数据");

        SecondTypeBean typeBean = lists.get(position);

        if (holder instanceof ContentViewHolder) {
            ContentViewHolder cViewHolder =  ((ContentViewHolder) holder);
            final String typeName = typeBean.getSecondTypeName();
            boolean isSelect = typeBean.isSelected();
            boolean ifEdit = typeBean.isEditEnable();

            if(isSelect) {
                holder.itemView.setBackgroundResource(R.drawable.bg_item_selected2);
            } else {
                holder.itemView.setBackgroundResource(R.drawable.icon_bg_r_d);
            }

            if(SecondTypeBean.TEXT_ZDY == typeName) {
                cViewHolder.mTextView.setTextColor(mContext.getResources().getColor(android.R.color.holo_blue_bright));
            } else {
                cViewHolder.mTextView.setTextColor(mContext.getResources().getColor(android.R.color.black));
            }

            cViewHolder.mTextView.setText(typeName);

            if(ifEdit && !SecondTypeBean.TEXT_ZDY.equalsIgnoreCase(typeName)) {
                cViewHolder.ig_delete.setVisibility(View.VISIBLE);
            } else {
                cViewHolder.ig_delete.setVisibility(View.GONE);
            }


        } else if (holder instanceof TitleViewHolder) {

            TitleViewHolder titleViewHolder = (TitleViewHolder)holder;
            titleViewHolder.tv_title.setText(typeBean.getFirstTypeName());
        }

        /**
         * 设置事件监听
         */
        if(listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position2 = holder.getAdapterPosition();
                    listener.onItemClick(v, position2);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return lists.get(position).getItemType();
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    /**
     * 显示内容的ViewHolder
     */
    public static class ContentViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ImageView ig_delete;

        public ContentViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text_view);
            ig_delete = (ImageView) itemView.findViewById(R.id.ig_delete);
        }
    }

    /**
     * 显示为title的ViewHolder
     */
    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_title;

        public TitleViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
        }
    }

    public void removeItem(int i) {

    }

    public interface OnItemClickListener {

        void onItemClick(View view, int position);

    }

    public static enum ITEM_TYPE {
        ITEM_TYPE_TITLE,
        ITEM_TYPE_CONTENT
    }
}
