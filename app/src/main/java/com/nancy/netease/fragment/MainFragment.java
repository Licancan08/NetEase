package com.nancy.netease.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.example.kson.tablayout.widget.HorizontalScollTabhost;
import com.example.kson.tablayout.widget.bean.CategoryBean;
import com.kson.slidingmenu.SlidingMenu;
import com.nancy.netease.HomeActivity;
import com.nancy.netease.R;
import com.nancy.netease.ShezhiActivity;
import com.nancy.netease.bean.Pin;
import com.nancy.netease.dao.MySqlDao;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by robot on 2017/9/14.
 */

public class MainFragment extends Fragment implements View.OnClickListener{


    private HorizontalScollTabhost tabhost_menu;
    private List<CategoryBean> beanList;
    private List<Fragment> fragmentList;
    private SlidingMenu menu;
    private ImageView left;
    private ImageView right;
    private List<ChannelBean> cblist;
    List<Pin> beans;
    private MySqlDao dao;
    private HorizontalScollTabhost myTabHost;
    private View view;
    private ImageView jia;
    private ImageView iv_pp;
    private PopupWindow pop;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.but_1,null);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
        initTabhost();
        initMenu();
    }


    /**
     * 侧拉菜单
     */
    private void initMenu() {
        menu = new SlidingMenu(getActivity());
        //添加左菜单
        menu.setMenu(R.layout.left_content_id);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.left_id,new LeftFragment()).commit();

        //设置属性
        menu.setMode(SlidingMenu.LEFT_RIGHT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setBehindOffsetRes(R.dimen.BehindOffsetRes);

        menu.setSecondaryMenu(R.layout.right_content_id);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.right_id,new RightFragment()).commit();
        //必须写的代码  实现的侧拉的效果
        menu.attachToActivity(getActivity(),SlidingMenu.SLIDING_CONTENT);

    }

    /**
     * 初始化
     */
    private void initView() {
        tabhost_menu = (HorizontalScollTabhost) view.findViewById(R.id.tabhost_menu);
        left = (ImageView) view.findViewById(R.id.left);
        right = (ImageView) view.findViewById(R.id.right);
        iv_pp = view.findViewById(R.id.iv_pp);
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        jia = view.findViewById(R.id.jia);
        jia.setOnClickListener(this);
        iv_pp.setOnClickListener(this);
        dao = new MySqlDao(getActivity());
        myTabHost = (HorizontalScollTabhost) view.findViewById(R.id.tabhost_menu);
    }

    /**
     * 初始化横滑菜单
     */
    private void initTabhost() {
        //存放bean和fragment的集合
        beanList = new ArrayList<>();
        fragmentList = new ArrayList<>();

        //添加需要的数据
        beanList.add(new CategoryBean("1","头条"));
        beanList.add(new CategoryBean("2","社会"));
        beanList.add(new CategoryBean("3","国内"));
        beanList.add(new CategoryBean("4","国际"));
        beanList.add(new CategoryBean("5","娱乐"));
        beanList.add(new CategoryBean("6","体育"));
        beanList.add(new CategoryBean("7","军事"));
        beanList.add(new CategoryBean("8","科技"));
        beanList.add(new CategoryBean("9","时尚"));
        beanList.add(new CategoryBean("10","汽车"));

        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());
        fragmentList.add(new Fragment4());
        fragmentList.add(new Fragment5());
        fragmentList.add(new Fragment6());
        fragmentList.add(new Fragment7());
        fragmentList.add(new Fragment8());
        fragmentList.add(new Fragment9());
        fragmentList.add(new Fragment10());
        //添加布局
        tabhost_menu.diaplay(beanList,fragmentList);

    }


    /**
     * 点击图片显示左右菜单
     * @param view
     */
    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.left:
                menu.showMenu();
                break;
            case R.id.right:
                menu.showSecondaryMenu();
                break;
            case R.id.jia:
                initPinUtils();
                Toast.makeText(getActivity(), "你点击了频道管理", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_pp:
                View view1=View.inflate(getActivity(),R.layout.pop_item,null);
                pop = new PopupWindow(view1,280,490,true);
                //pop.showAtLocation(view.findViewById(R.id.iv_pp), Gravity.TOP,210,160);
                pop.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                pop.setFocusable(true);
                pop.setTouchable(true);
                //点击别处可隐藏PopWindow
                pop.showAsDropDown(iv_pp,0,20);
                pop.setOutsideTouchable(true);
                pop.update();



                RelativeLayout tianqi=view1.findViewById(R.id.tq);
                RelativeLayout lixian=view1.findViewById(R.id.lx);
                RelativeLayout sousou=view1.findViewById(R.id.ss);
                RelativeLayout saoyisao=view1.findViewById(R.id.sys);
                RelativeLayout yejian=view1.findViewById(R.id.yj);
                RelativeLayout shezhi=view1.findViewById(R.id.sz);
                tianqi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pop.dismiss();
                    }
                });
                lixian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pop.dismiss();
                    }
                });
                sousou.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pop.dismiss();
                    }
                });
                saoyisao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pop.dismiss();
                    }
                });
                yejian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        pop.dismiss();
                    }
                });
                shezhi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getActivity(),ShezhiActivity.class);
                        startActivity(intent);
                        pop.dismiss();
                    }
                });
                break;
        }
    }

    /**
     * 频道管理的方法
     */
    private void initPinUtils() {
        cblist = new ArrayList<>();
        String result = dao.select("pindao");
        System.out.println("=======数据库中频道"+result);
            if(result==null)
            {//第一次进入频道时
            for (int i = 0; i <beanList.size() ; i++) {
                CategoryBean categoryBean = beanList.get(i);
                System.out.println("===beans+cb");
                ChannelBean cb=new ChannelBean(categoryBean.name,true);
                cblist.add(cb);
            }
        }else{
            //第二次进入时
            try {
                JSONArray arr=new JSONArray(result);
                for (int i = 0; i <arr.length() ; i++) {
                    JSONObject obj= (JSONObject) arr.get(i);
                    String name=obj.getString("name");
                    boolean state=obj.getBoolean("isSelect");
                    ChannelBean bean1=new ChannelBean(name,state);
                    cblist.add(bean1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //设置频道管理
        ChannelActivity.startChannelActivity((AppCompatActivity) getActivity(), cblist);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==101)
        {
            String result_json_key=data.getStringExtra("json");
            System.out.println("______________________________________________"+result_json_key);
            dao.delete("pindao");//清除数据库
            dao.insert("pindao",result_json_key);//添加到数据库
            //清空之前的集合对象
            beans.clear();
            beanList.clear();
//            fragmentList.clear();
            ArrayList<Fragment> flist=new ArrayList<>();
            try {
                JSONArray arr=new JSONArray(result_json_key);
                for (int i = 0; i <arr.length() ; i++) {
                    JSONObject obj= (JSONObject) arr.get(i);
                    String name=obj.getString("name");
                    boolean state=obj.getBoolean("isSelect");
                    if(state)
                    {
                        CategoryBean bean=new CategoryBean(String.valueOf(i),name);
                        beanList.add(bean);
                        Fragment1 fragment=new Fragment1();
                        flist.add(fragment);
                    }
                    System.out.println("=======tabhost头部"+name+state);
                }

                System.out.println("========"+flist.size());
                //清空横滑内容
                myTabHost.clear();
                //重新添加内容
                myTabHost.diaplay(beanList,flist);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
