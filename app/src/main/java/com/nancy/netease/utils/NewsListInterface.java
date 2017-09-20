package com.nancy.netease.utils;

import com.nancy.netease.bean.News;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;

/**
 * Created by robot on 2017/9/14.
 */

public interface NewsListInterface {
    void onNewsListFailure(Call call, IOException e);
    void onNewsListResponse(Call call, List<News.ResultBean.DataBean> list);
}
