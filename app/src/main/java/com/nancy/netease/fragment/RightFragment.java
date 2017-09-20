package com.nancy.netease.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nancy.netease.QQLoginActivity;
import com.nancy.netease.R;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by robot on 2017/9/14.
 */
public class RightFragment extends Fragment implements View.OnClickListener {

    private View view;
    private ImageView iv_qq;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.right_menu,null);
        initView();

        return view;
    }

    /**
     * 初始化
     */
    private void initView() {
        iv_qq = view.findViewById(R.id.iv_qq);
        iv_qq.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        Intent intent=new Intent(getActivity(), QQLoginActivity.class);
        startActivity(intent);
    }
}
