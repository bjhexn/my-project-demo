package com.my.project.study.recyclerview.recycle;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.my.project.R;
import com.my.project.utils.LogUtil;
import com.my.project.utils.ToastUtils;

import java.util.ArrayList;

public class NewRecycleActivity extends AppCompatActivity implements RecycleAdapter.OnItemClickListener, View.OnClickListener{

    private RecyclerView mRecyclerView;
    private RecycleAdapter mRecycleAdapter;
    private ArrayList<SecondTypeBean> list = new ArrayList<>();
    private Button btn_edit;
    private boolean edit_state = false;      //默认为非编辑状态

    /**
     * 默认被选中的选项，默认为-1为未选中状态
     */
    private int defaultSelect = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recycle);

        testData();

        btn_edit = (Button) findViewById(R.id.btn_edit);
        btn_edit.setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);

        //mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        GridLayoutManager mGridLayout = new GridLayoutManager(this, 3);
        /**
         * 设置每列显示几行
         */
        mGridLayout.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if(checkItmeIfTitle(position)) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });

        mRecyclerView.setLayoutManager(mGridLayout);

        mRecycleAdapter = new RecycleAdapter(this, list);
        mRecyclerView.setAdapter(mRecycleAdapter);
        mRecycleAdapter.setOnItemClickListener(this);
    }

    private void testData() {
        SecondTypeBean bean = new SecondTypeBean();
        bean.setFirstTypeName("编程");
        bean.setItemType(RecycleAdapter.ITEM_TYPE.ITEM_TYPE_TITLE.ordinal());

        SecondTypeBean bean1 = new SecondTypeBean();
        bean1.setSecondTypeName("JAVA");
        bean1.setItemType(RecycleAdapter.ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal());

        SecondTypeBean bean2 = new SecondTypeBean();
        bean2.setSecondTypeName("Ruby");
        bean2.setItemType(RecycleAdapter.ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal());

        SecondTypeBean bean3 = new SecondTypeBean();
        bean3.setSecondTypeName("C++");
        bean3.setItemType(RecycleAdapter.ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal());

        SecondTypeBean bean4 = new SecondTypeBean();
        bean4.setSecondTypeName("Android");
        bean4.setItemType(RecycleAdapter.ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal());

        SecondTypeBean bean5 = new SecondTypeBean();
        bean5.setSecondTypeName("IOS");
        bean5.setItemType(RecycleAdapter.ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal());

        SecondTypeBean bean6 = new SecondTypeBean();
        bean6.setSecondTypeName("Haha");
        bean6.setItemType(RecycleAdapter.ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal());

        SecondTypeBean bean7 = new SecondTypeBean();
        bean7.setSecondTypeName("Hehe");
        bean7.setItemType(RecycleAdapter.ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal());

        SecondTypeBean bean8 = new SecondTypeBean();
        bean8.setSecondTypeName("自定义");
        bean8.setItemType(RecycleAdapter.ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal());

        SecondTypeBean bean9 = new SecondTypeBean();
        bean9.setFirstTypeName("歌曲");
        bean9.setItemType(RecycleAdapter.ITEM_TYPE.ITEM_TYPE_TITLE.ordinal());

        SecondTypeBean bean10 = new SecondTypeBean();
        bean10.setSecondTypeName("凤凰传奇");
        bean10.setItemType(RecycleAdapter.ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal());

        SecondTypeBean bean11 = new SecondTypeBean();
        bean11.setSecondTypeName("二手玫瑰");
        bean11.setItemType(RecycleAdapter.ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal());

        SecondTypeBean bean12 = new SecondTypeBean();
        bean12.setSecondTypeName("自定义");
        bean12.setItemType(RecycleAdapter.ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal());

        list.add(bean);

        list.add(bean1);
        list.add(bean2);
        list.add(bean3);
        list.add(bean4);
        list.add(bean5);
        list.add(bean6);
        list.add(bean7);
        list.add(bean8);

        list.add(bean9);
        list.add(bean10);
        list.add(bean11);
        list.add(bean12);

        //默认第一个被选中
        setDefaultSelect(1);
    }

    @Override
    public void onItemClick(View view,  int position) {
        ToastUtils.show(this, position + "");

        /**
         * 新增自定义与删除逻辑
         */
        SecondTypeBean typeBean = list.get(position);
        if(SecondTypeBean.TEXT_ZDY.equals(typeBean.getSecondTypeName())) {
            if (!edit_state) {
                showSecondTypeDialog(position);
            } else {
                LogUtil.log("编辑状态自定义不准点击");
            }

        } else {
            if(edit_state) {
                removeData(position);
            } else {

            }
        }

    }

    /**
     * 判断item是否为标题，如果是则返回true
     * @return
     */
    private boolean checkItmeIfTitle(int index) {
        boolean bool = false;

        SecondTypeBean bean = list.get(index);

        if(RecycleAdapter.ITEM_TYPE.ITEM_TYPE_TITLE.ordinal() == bean.getItemType()) {
            bool = true;
        }

        return bool;
    }

    /**
     * 添加一个增加item数据的方法，posion代表你从主类中传过来的值，这个值对应你添加的item在列表中的位置
     * @param position
     */
    private void addData(int position, String name) {
        //保证列表没有数据时，首先添加
        if(list.size()==0){
            SecondTypeBean bean = new SecondTypeBean();
            bean.setSecondTypeName(name);
            bean.setItemType(RecycleAdapter.ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal());
            list.add(0, bean);
        }else {
            //更新列表
            SecondTypeBean bean = new SecondTypeBean();
            bean.setSecondTypeName(name);
            bean.setItemType(RecycleAdapter.ITEM_TYPE.ITEM_TYPE_CONTENT.ordinal());
            list.add(position, bean);
            mRecycleAdapter.notifyItemInserted(position);
            mRecycleAdapter.notifyItemRangeChanged(position, list.size());
        }
    }

    /**
     * 删除逻辑
     * @param position
     */
    private void removeData(int position) {
        //删除逻辑
        list.remove(position);

        mRecycleAdapter.notifyDataSetChanged();
        mRecycleAdapter.notifyItemRemoved(position);
        mRecycleAdapter.notifyItemRangeChanged(position, list.size());
    }

    /**
     * 设置item的选中状态
     *
     * @param oldSelected
     *          为已经被选中的编号
     * @param newSelected
     *          目前点击的item编号
     */
    private void setItemIfSelected(int oldSelected, int newSelected) {

    }

    /**
     *
     * 得到二级产品index
     *
     */
    private int getSelectedIndex(String name) {
        SecondTypeBean bean;

        for(int i = 0; i < list.size(); i++) {
            bean = list.get(i);
            if(name.equals(bean.getSecondTypeName())) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 设置哪个item被选中, 传-1的话则没有默认的选中
     * @param index
     */
    private void setDefaultSelect(int index) {
        if(index != -1) {
            list.get(index).setSelected(true);
        }
    }

    /**
     * 进入可编辑状态
     */
    private void enableEdit() {
        for(int i = 0; i < list.size(); i++) {
            list.get(i).setEditEnable(true);
        }

        mRecycleAdapter.notifyDataSetChanged();
    }

    /**
     * 进入可编辑状态
     */
    private void noEnableEdit() {
        for(int i = 0; i < list.size(); i++) {
            list.get(i).setEditEnable(false);
        }

        mRecycleAdapter.notifyDataSetChanged();
    }

    private void setEditState() {
        if(edit_state) {
            noEnableEdit();
            edit_state = false;
        } else {
            enableEdit();
            edit_state = true;
        }
    }

    /**
     * 增加二级分类的弹框
     */
    private void showSecondTypeDialog(final int postion) {
        Dialog dialog = null;
        View view = LayoutInflater.from(this).inflate(R.layout.layout_dialog_type_input, null);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(
                this);

        final EditText et = (EditText) view.findViewById(R.id.et_input_name);


        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = et.getText().toString();

                if(!TextUtils.isEmpty(name)) {
                    addData(postion, name);
                }
            }
        });

        builder.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });

        builder.setView(view);
        // 创建一个普通对话框
        dialog = builder.create();
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_edit:
                setEditState();
                break;
        }
    }
}
