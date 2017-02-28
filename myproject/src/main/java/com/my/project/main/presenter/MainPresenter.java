package com.my.project.main.presenter;

import com.my.project.main.contract.MainContract;
import com.my.project.utils.LogUtil;

/**
 * Created by hexiaoning on 2016/8/4.
 */
public class MainPresenter implements MainContract.Presenter{

    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        view.setPresenter(this);
    }

    @Override
    public void showText(String s) {
        view.setText(s);
        view.showToast(s);
    }

    @Override
    public void start() {
        LogUtil.log("Presenter 开始工作");
    }
}
