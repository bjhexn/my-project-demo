package com.my.project.main.fragmet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.my.encrypt.EncryptUtils;
import com.my.project.R;
import com.my.project.main.contract.MainContract;
import com.my.project.study.EncryptActivity;
import com.my.project.study.asynctask.AsyncTaskActivity;
import com.my.project.study.okhttp.OkHttpDemoActivity;
import com.my.project.study.recyclerview.photo.RecyclerViewPhotoActivity;
import com.my.project.study.recyclerview.recycle.NewRecycleActivity;
import com.my.project.study.recyclerview.sectionrecycle.SectionRecycleActivity;
import com.my.project.update.activity.UpdateActivity;

public class MainFragment extends Fragment implements View.OnClickListener, MainContract.View{

    private Button btn, btn_async, btn_recycler, btn_recycler2, btn_okhttp,btn_goto_update,btn_goto_encry_save, btn_recycler_section;
    private MainContract.Presenter presenter;
    private TextView tv1, tv_jni;

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private OnFragmentInteractionListener mListener;

    public MainFragment() {

    }

    public static MainFragment newInstance(String param1) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        btn = (Button) view.findViewById(R.id.btn);
        btn_async = (Button) view.findViewById(R.id.btn_async);
        btn_recycler = (Button) view.findViewById(R.id.btn_recycler);
        btn_recycler2 = (Button) view.findViewById(R.id.btn_recycler2);
        btn_okhttp = (Button) view.findViewById(R.id.btn_goto_okhttp);
        btn_goto_update = (Button) view.findViewById(R.id.btn_goto_update);
        btn_goto_encry_save = (Button) view.findViewById(R.id.btn_goto_encry_save);
        btn_recycler_section = (Button) view.findViewById(R.id.btn_recycler_section);

        btn.setOnClickListener(this);
        btn_async.setOnClickListener(this);
        btn_recycler.setOnClickListener(this);
        btn_recycler2.setOnClickListener(this);
        btn_okhttp.setOnClickListener(this);
        btn_goto_update.setOnClickListener(this);
        btn_goto_encry_save.setOnClickListener(this);
        btn_recycler_section.setOnClickListener(this);

        tv1 = (TextView) view.findViewById(R.id.tv1);
        tv_jni = (TextView) view.findViewById(R.id.tv_jni);
        //tv_jni.setText(JniUtils.getStringFromC());    使用编译的JNI所以注释掉该行否则报错
        tv_jni.setText(EncryptUtils.encode("13910733521"));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setText(String s) {
        tv1.setText(s);
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                presenter.showText("我的输入");
                break;
            case R.id.btn_async:
                startActivity(new Intent(getActivity(), AsyncTaskActivity.class));
                break;
            case R.id.btn_recycler:
                //startActivity(new Intent(getActivity(), RecyclerViewActivity.class));
                startActivity(new Intent(getActivity(), NewRecycleActivity.class));
                break;
            case R.id.btn_recycler2:
                startActivity(new Intent(getActivity(), RecyclerViewPhotoActivity.class));
                break;
            case R.id.btn_recycler_section:
                startActivity(new Intent(getActivity(), SectionRecycleActivity.class));
                break;
            case R.id.btn_goto_okhttp:
                startActivity(new Intent(getActivity(), OkHttpDemoActivity.class));
                break;
            case R.id.btn_goto_update:
                startActivity(new Intent(getActivity(), UpdateActivity.class));
                break;
            case R.id.btn_goto_encry_save:
                startActivity(new Intent(getActivity(), EncryptActivity.class));
                break;
            default:
                break;
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String s);
    }

}
