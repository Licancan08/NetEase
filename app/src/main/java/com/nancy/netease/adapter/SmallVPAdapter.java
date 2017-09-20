package com.nancy.netease.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nancy.netease.R;


/**
 * 导航引导页的适配器类
 * Created by robot on 2017/9/13.
 */

public class SmallVPAdapter extends PagerAdapter {

    //存放图片的数组
    int[] imgs={R.drawable.aa,R.drawable.bb,R.drawable.cc};
    Context context;
    public SmallVPAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view=View.inflate(context,R.layout.vp_item,null);

        ImageView img=view.findViewById(R.id.iv_img);
        img.setImageResource(imgs[position%imgs.length]);
        container.addView(view);
        return view;
    }


}
