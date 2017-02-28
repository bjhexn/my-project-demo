package com.my.project.utils;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by hexiaoning on 2016/11/3.
 */
public class OKHttpsUtils3 {

    public static void testUrl() {
        //创建HttpClient对象
        OkHttpClient mOKHttpClient = new OkHttpClient();
        //创建一个Request
        Request request = new Request.Builder().url("http://c.m.163.com/nc/article/C4V9LM9Q0001875N/full.html").build();
        Call call = mOKHttpClient.newCall(request);

        //请求加入调度
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                LogUtil.log("request string" + request.toString());
                LogUtil.log("url = " + request.url().toString());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String str = response.body().string();

                LogUtil.log("response: " + str);
            }
        });
    }
}
