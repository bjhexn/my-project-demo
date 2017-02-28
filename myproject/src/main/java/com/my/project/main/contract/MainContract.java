package com.my.project.main.contract;

import com.my.project.bases.BasePresenter;
import com.my.project.bases.BaseView;

/**
 * Created by hexiaoning on 2016/8/4.
 */
public interface MainContract {

    interface Presenter extends BasePresenter {
        void showText(String s);
    }

    interface View extends BaseView<Presenter> {
        void setText(String s);
        void showToast(String s);
    }
}
