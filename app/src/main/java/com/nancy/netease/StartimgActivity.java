package com.nancy.netease;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StartimgActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_jump;
    int num=4;

    //倒计时跳转
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            num--;
            if(num==0)
            {
                Intent intent=new Intent(StartimgActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
            handler.sendEmptyMessageDelayed(0,1000);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startimg);
        handler.sendEmptyMessageDelayed(0,1000);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        tv_jump = (TextView) findViewById(R.id.tv_jump);
        tv_jump.setOnClickListener(this);
    }

    /**
     * 跳过的点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(StartimgActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
