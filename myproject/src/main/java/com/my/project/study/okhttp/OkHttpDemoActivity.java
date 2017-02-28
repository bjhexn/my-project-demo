package com.my.project.study.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.my.project.R;
import com.my.project.utils.LogUtil;
import com.my.project.utils.OKHttpsUtils3;
import com.my.project.utils.OkHttpUtils;

public class OkHttpDemoActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_demo);
        initView();
    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn_okhttp1);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_okhttp1:
                OKHttpsUtils3.testUrl();

//                LogUtil.log("开始请求");
//                String url = "https://github.com/hongyangAndroid";
//                String url2 = "https://www.hao123.com";
//                String url3 = "http://c.m.163.com/nc/article/C4V9LM9Q0001875N/full.html";
//                OkHttpUtils.get(url2, new OkHttpUtils.ResultCallback<String>() {
//                    @Override
//                    public void onSuccess(String response) {
//                        LogUtil.log("responese = " + response);
//                    }
//
//                    @Override
//                    public void onFailure(Exception e) {
//                        LogUtil.log("responese = " + e.getMessage());
//                    }
//                });
                break;
            default:
                break;
        }
    }
}
