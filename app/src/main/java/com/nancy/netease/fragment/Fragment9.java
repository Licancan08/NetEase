package com.nancy.netease.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nancy.netease.DetailActivity;
import com.nancy.netease.R;
import com.nancy.netease.adapter.MyXListAdapter;
import com.nancy.netease.adapter.SmallVPAdapter;
import com.nancy.netease.api.API;
import com.nancy.netease.bean.News;
import com.nancy.netease.utils.NewsDao;
import com.nancy.netease.utils.NewsListInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import view.xlistview.XListView;

/**
 * Created by robot on 2017/9/14.
 */

public class Fragment9 extends Fragment implements NewsListInterface, AdapterView.OnItemClickListener, ViewPager.OnPageChangeListener, XListView.IXListViewListener {

    private View view;
    private XListView xlv_list;
    private List<News.ResultBean.DataBean> list;

    private MyXListAdapter adapter;
    private String url;
    private ViewPager small_vp;
    private LinearLayout small_ll;
    List<ImageView> dot_list=new ArrayList<>();
    //存放图片的数组
    int[] imgs={R.drawable.aa,R.drawable.bb,R.drawable.cc};
    private SmallVPAdapter vPadapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = View.inflate(getActivity(), R.layout.fragment1,null);
        url = API.URL_GET+API.TYPE9;
        initView();
        initDao();
        initDot();
        return view;
    }

    private void initDao() {
        NewsDao dao = new NewsDao();
        dao.getNews(url);
        dao.setNewsListInterface(this);

        list = new ArrayList<>();
    }

    /**
     * 初始化
     */
    private void initView() {
        xlv_list = view.findViewById(R.id.xlv_list);
        xlv_list.setOnItemClickListener(this);
        xlv_list.setXListViewListener(this);
        //设置支持刷新和加载
        xlv_list.setPullRefreshEnable(true);
        xlv_list.setPullLoadEnable(true);

        //设置header
        View v=LayoutInflater.from(getActivity()).inflate(R.layout.header_view,null);
        small_vp=v.findViewById(R.id.small_vp);
        small_ll=v.findViewById(R.id.small_ll);
        small_vp.setOnPageChangeListener(this);
        //关联viewPager的适配器
        vPadapter = new SmallVPAdapter(getActivity());
        small_vp.setAdapter(vPadapter);
        xlv_list.addHeaderView(v);
    }


    /**
     * 绘制小圆点
     */
    private void initDot() {
        for (int i = 0; i <imgs.length ; i++) {
            //创建一个控件存放单个小圆点
            ImageView iv = new ImageView(getActivity());
            if (i == 0) {
                iv.setImageResource(R.drawable.red_dot);
            } else {
                iv.setImageResource(R.drawable.blue_dot);
            }
            //参数为圆的大小
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
            params.setMargins(10, 5, 10, 5);
            //把内容投射到控件上
            small_ll.addView(iv, params);
            dot_list.add(iv);
        }
    }


    @Override
    public void onNewsListFailure(Call call, IOException e) {

    }

    @Override
    public void onNewsListResponse(Call call, final List<News.ResultBean.DataBean> list) {

        getActivity().runOnUiThread(new Runnable() {


            @Override
            public void run() {
                if(adapter==null)
                {
                    adapter = new MyXListAdapter(getActivity(),list);
                    xlv_list.setAdapter(adapter);
                }else{
                    adapter.notifyDataSetChanged();
                }

            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        //跳转到详情页面

        News.ResultBean.DataBean item = adapter.getItem(i-2);

        Intent intent=new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("url",item.getUrl());
        System.out.println("url=========="+item.getUrl());
        getActivity().startActivity(intent);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i <dot_list.size() ; i++) {
            if(i==position%imgs.length)
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
     * 刷新
     */
    @Override
    public void onRefresh() {
        //重新请求数据
        initDao();
        //停止刷新和加载
        xlv_list.stopLoadMore();
        xlv_list.stopRefresh();
        Toast.makeText(getActivity(), "刷新成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMore() {
        list.addAll(list);
        //停止刷新和加载
        xlv_list.stopLoadMore();
        xlv_list.stopRefresh();
        Toast.makeText(getActivity(), "数据加载成功", Toast.LENGTH_SHORT).show();
    }
}
