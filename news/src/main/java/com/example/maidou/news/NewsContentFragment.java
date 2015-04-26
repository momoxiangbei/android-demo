package com.example.maidou.news;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by maidou on 2015/4/26.
 */

//加载布局 刷新布局

public class NewsContentFragment extends Fragment{

    private View view;  // 定义全局变量加载布局 可以使刷新的时候也使用此套布局

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.news_content_fragment,container);
        return view;
    }

    // 刷新标题和内容
    public void refresh(String title,String body){
        View contentLayout = view.findViewById(R.id.content_fragment_layout);
        contentLayout.setVisibility(View.VISIBLE);
        TextView t = (TextView) view.findViewById(R.id.content_title);
        TextView b = (TextView) view.findViewById(R.id.content_body);
        t.setText(title);
        b.setText(body);
    }
}
