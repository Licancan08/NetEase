package com.nancy.netease.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nancy.netease.R;
import com.nancy.netease.bean.News;

import java.util.List;

/**
 * Created by robot on 2017/9/14.
 */

public class MyXListAdapter extends BaseAdapter {

    public static final int atype=0;
    public static final int btype=1;

    Context context;
    List<News.ResultBean.DataBean> list;

    public MyXListAdapter(Context context, List<News.ResultBean.DataBean> list) {
        this.context=context;
        this.list=list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        News.ResultBean.DataBean bean=list.get(position);

        if(bean.getThumbnail_pic_s02()!=null&&bean.getThumbnail_pic_s03()!=null)
        {
            return btype;
        }
        return atype;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public News.ResultBean.DataBean getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        int type=getItemViewType(i);
        ViewHolder1 holder1=null;
        ViewHolder2 holder2=null;
        if(view==null)
        {
            switch (type)
            {
                case atype:
                    holder1=new ViewHolder1();
                    view= LayoutInflater.from(context).inflate(R.layout.item1,null);
                    holder1.title1=view.findViewById(R.id.tv_title1);
                    holder1.category1=view.findViewById(R.id.tv_category1);
                    holder1.time1=view.findViewById(R.id.tv_time1);
                    holder1.img1=view.findViewById(R.id.iv_img1);

                    holder1.title1.setText(list.get(i).getTitle());
                    holder1.time1.setText(list.get(i).getDate());
                    holder1.category1.setText(list.get(i).getCategory());
                    Glide.with(context).load(list.get(i).getThumbnail_pic_s()).into(holder1.img1);

                    view.setTag(holder1);
                    break;
                case btype:
                    holder2=new ViewHolder2();
                    view= LayoutInflater.from(context).inflate(R.layout.item2,null);
                    holder2.title2=view.findViewById(R.id.tv_title2);
                    holder2.category2=view.findViewById(R.id.tv_category2);
                    holder2.img21=view.findViewById(R.id.iv_img21);
                    holder2.img22=view.findViewById(R.id.iv_img22);
                    holder2.img23=view.findViewById(R.id.iv_img23);

                    holder2.title2.setText(list.get(i).getTitle());
                    holder2.category2.setText(list.get(i).getCategory());
                    Glide.with(context).load(list.get(i).getThumbnail_pic_s()).into(holder2.img21);
                    Glide.with(context).load(list.get(i).getThumbnail_pic_s02()).into(holder2.img22);
                    Glide.with(context).load(list.get(i).getThumbnail_pic_s03()).into(holder2.img23);

                    view.setTag(holder2);
                    break;
            }
        }
        else{
            switch (type)
            {
                case atype:
                    holder1= (ViewHolder1) view.getTag();

                    holder1.title1.setText(list.get(i).getTitle());
                    holder1.time1.setText(list.get(i).getDate());
                    holder1.category1.setText(list.get(i).getCategory());
                    Glide.with(context).load(list.get(i).getThumbnail_pic_s()).into(holder1.img1);
                    break;
                case btype:
                    holder2= (ViewHolder2) view.getTag();

                    holder2.title2.setText(list.get(i).getTitle());
                    holder2.category2.setText(list.get(i).getCategory());
                    Glide.with(context).load(list.get(i).getThumbnail_pic_s()).into(holder2.img21);
                    Glide.with(context).load(list.get(i).getThumbnail_pic_s02()).into(holder2.img22);
                    Glide.with(context).load(list.get(i).getThumbnail_pic_s03()).into(holder2.img23);
                    break;
            }

        }

        return view;
    }

    class ViewHolder1{
        TextView title1,time1,category1;
        ImageView img1;
    }
    class ViewHolder2{
        TextView title2,category2;
        ImageView img21,img22,img23;
    }
}
