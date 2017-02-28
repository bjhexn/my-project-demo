package com.my.project.study;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.my.encrypt.EncryptUtils;
import com.my.project.R;
import com.my.project.bases.BaseActivity;
import com.my.project.utils.tools.SPUtils;

import org.w3c.dom.Text;

public class EncryptActivity extends BaseActivity {

    private TextView tvSource, tvEncrypt, tvSource2, tv4;
    private String number = "13910733521";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
    }

    @Override
    public void initView() {
        tvSource = (TextView) findViewById(R.id.tv1);
        tvEncrypt = (TextView) findViewById(R.id.tv2);
        tvSource2 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);

        String enrypt = EncryptUtils.encode("源数据： " + number);
        tvSource.setText(number);
        tvEncrypt.setText("加密后数据： " + enrypt);
        tvSource2.setText("解密后数据 " + EncryptUtils.decode(enrypt));

        SPUtils.put(this, "sp_key", "Hello");
        tv4.setText("从本地缓存中读取数据：" + SPUtils.get(this, "sp_key", "default"));
    }
}
