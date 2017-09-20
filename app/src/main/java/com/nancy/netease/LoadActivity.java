package com.nancy.netease;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nancy.netease.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 导航引导页
 */
public class LoadActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private ViewPager viewPager;
    private ViewPagerAdapter vPadapter;
    private LinearLayout ll_dot;
    //存放图片的数组
    int[] imgs={R.drawable.a,R.drawable.b,R.drawable.c};
    //创建一个圆点图片的集合
    List<ImageView> dot_list=new ArrayList<>();
    private Button but_into;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = getSharedPreferences("config",MODE_PRIVATE);
        initView();
        initViewPager();
        initDot();
        initRemember();
    }

    /**
     * 记住引导页面
     */
    private void initRemember() {
        if(sp.getBoolean("login",false)){
            Intent intent=new Intent(LoadActivity.this,StartimgActivity.class);
            startActivity(intent);
            finish();
        }
    }


    /**
     * 初始化
     */
    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        //存放小圆点的控件
        ll_dot = (LinearLayout) findViewById(R.id.ll_dot);
        //viewPager的点击事件
        viewPager.setOnPageChangeListener(this);
        //点击进入的按钮
        but_into = (Button) findViewById(R.id.but_into);
        but_into.setOnClickListener(this);
    }

    /**
     * 关联适配器的方法
     */
    private void initViewPager() {
        //导航页的ViewPager适配器
        vPadapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(vPadapter);
    }


    /**
     * 绘制小圆点
     */
    private void initDot() {
        for (int i = 0; i <imgs.length ; i++) {
            //创建一个控件存放单个小圆点
            ImageView iv=new ImageView(this);
            if(i==0)
            {
                iv.setImageResource(R.drawable.red_dot);
            }else{
                iv.setImageResource(R.drawable.blue_dot);
            }
            //参数为圆的大小
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(25,25);
            params.setMargins(10,5,10,5);
            //把内容投射到控件上
            ll_dot.addView(iv,params);
            dot_list.add(iv);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if(position==2)
        {
            but_into.setVisibility(View.VISIBLE);
        }
        else{
            but_into.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 当页面选中时调用的方法
     * @param position
     */
    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i <dot_list.size() ; i++) {
            if(i==position)
            {
                dot_list.get(i).setImageResource(R.drawable.red_dot);
            }
            else{
                dot_list.get(i).setImageResource(R.drawable.blue_dot);
            }
        }


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * 按钮的点击事件
     * @param view
     */
    @Override
    public void onClick(View view) {
        //记住登录状态
        sp.edit().putBoolean("login",true).commit();
        Intent intent=new Intent(this,HomeActivity.class);
        startActivity(intent);
        //关闭页面
        finish();
    }
}
