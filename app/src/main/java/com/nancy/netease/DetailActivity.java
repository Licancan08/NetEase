package com.nancy.netease;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class DetailActivity extends AppCompatActivity {

    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();

        //接收跳转的值
        Intent intent=getIntent();
        String url = intent.getStringExtra("url");
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl(url);
    }

    /**
     * 初始化数据
     */
    private void initView() {
        wv = (WebView) findViewById(R.id.wv);
    }
}
