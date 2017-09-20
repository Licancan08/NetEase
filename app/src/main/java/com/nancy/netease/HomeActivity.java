package com.nancy.netease;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.example.kson.tablayout.widget.HorizontalScollTabhost;
import com.example.kson.tablayout.widget.bean.CategoryBean;
import com.kson.slidingmenu.SlidingMenu;
import com.nancy.netease.bean.News;
import com.nancy.netease.bean.Pin;
import com.nancy.netease.dao.MySqlDao;
import com.nancy.netease.fragment.Fragment1;
import com.nancy.netease.fragment.LeftFragment;
import com.nancy.netease.fragment.MainFragment;
import com.nancy.netease.fragment.MyFragment;
import com.nancy.netease.fragment.RightFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton but_main;
    private RadioButton but_my;
    private MainFragment main;
    private MyFragment my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        main = new MainFragment();
        my = new MyFragment();

        but_main = (RadioButton) findViewById(R.id.but_main);
        but_my = (RadioButton) findViewById(R.id.but_my);

        but_main.setOnClickListener(this);
        but_my.setOnClickListener(this);

        //添加fragment
        getSupportFragmentManager().beginTransaction().add(R.id.fl, main).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fl, my).commit();

        //默认展示
        getSupportFragmentManager().beginTransaction().show(main).commit();
        getSupportFragmentManager().beginTransaction().hide(my).commit();
        but_main.setChecked(true);
        but_my.setChecked(false);
        but_main.setTextColor(Color.RED);
        but_my.setTextColor(Color.GRAY);
    }

    /**
     * 按钮的点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.but_main:
                but_main.setChecked(true);
                but_my.setChecked(false);
                but_main.setTextColor(Color.RED);
                but_my.setTextColor(Color.GRAY);
                getSupportFragmentManager().beginTransaction().show(main).commit();
                getSupportFragmentManager().beginTransaction().hide(my).commit();
                break;
            case R.id.but_my:
                but_my.setChecked(true);
                but_main.setChecked(false);
                but_my.setTextColor(Color.RED);
                but_main.setTextColor(Color.GRAY);
                getSupportFragmentManager().beginTransaction().hide(main).commit();
                getSupportFragmentManager().beginTransaction().show(my).commit();
                break;
        }
    }
}
