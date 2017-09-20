package com.nancy.netease.utils;

import com.google.gson.Gson;
import com.nancy.netease.bean.News;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by robot on 2017/9/14.
 */

public class NewsDao {

    private NewsListInterface newsListInterface;
    /**
     * 请求数据的方法
     * @param url
     */
    public void getNews(String url)
    {
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        final Call call=new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                newsListInterface.onNewsListFailure(call,e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful())
                {
                    String result = response.body().string();
                    System.out.println("=========="+result);
                    Gson gson=new Gson();
                    News bean = gson.fromJson(result, News.class);
                    newsListInterface.onNewsListResponse(call,bean.getResult().getData());
                }
            }
        });
    }

    public void setNewsListInterface(NewsListInterface newsListInterface) {
        this.newsListInterface = newsListInterface;
    }
}
