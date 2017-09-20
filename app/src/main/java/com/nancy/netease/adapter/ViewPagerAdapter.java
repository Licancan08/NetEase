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

public class ViewPagerAdapter extends PagerAdapter {

    //存放图片的数组
    int[] imgs={R.drawable.yin_1,R.drawable.yin_2,R.drawable.yin_3};
    Context context;
    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return imgs.length;
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
        img.setImageResource(imgs[position]);
        container.addView(view);
        return view;
    }


}
