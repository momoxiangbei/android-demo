package com.example.maidou.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


/**
 * Created by maidou on 2015/4/26.
 */
public class NewsContentActivity extends Activity{

    // 启动此活动要传入标题和内容

    public static void startActivity(Context context,String title,String body){
        Intent intent = new Intent(context,NewsContentActivity.class);
        intent.putExtra("newsTitle",title);
        intent.putExtra("newsBody",body);
        context.startActivity(intent);            // 静态方法 用上下文启动活动
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        String title = getIntent().getStringExtra("newsTitle");
        String body = getIntent().getStringExtra("newsBody");

        // 获取到news_content_fragment  ,然后刷新

        NewsContentFragment fragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
        fragment.refresh(title,body);
    }
}

