package com.example.maidou.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;



//新闻列表的适配器

public class NewsAdapter extends ArrayAdapter{

    private int resourceId;

    // resource 为子布局id
    public NewsAdapter(Context context, int resource, List<News> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        News news = (News) getItem(position);    // getItem() 返回int型 获取当前新闻的位置
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,null);   //第二个参数为空
        }else {
            view = convertView;
        }
        TextView textView = (TextView) view.findViewById(R.id.news_item);
        textView.setText(news.getTitle());
        return view;
    }
}
