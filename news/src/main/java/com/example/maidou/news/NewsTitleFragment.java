package com.example.maidou.news;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maidou on 2015/4/26.
 */
public class NewsTitleFragment extends Fragment implements AdapterView.OnItemClickListener{

    private List<News> newsList;
    private NewsAdapter newsAdapter;
    private Boolean isTwoPane;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        newsList = getNews();
        newsAdapter = new NewsAdapter(activity,R.layout.news_item,newsList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_fregment,container);
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(newsAdapter);         // 给lisVIew添加Adapter
        listView.setOnItemClickListener(this);   //给lisVIew添加监听事件
        return view;
    }


    //判断activity.xml中是否含有content_fragment_layout 从而判断加载的是一套布局 还是两套布局

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.content_fragment_layout)!=null){
         isTwoPane = true;
        }else {
            isTwoPane = false;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news = newsList.get(position);
        if (isTwoPane){
            // 如果是双页模式 ， 刷新NewsContentFragment中的内容
//            NewsContentFragment fragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.layout.news_content_layout);
//            fragment.refresh(news.getTitle(),news.getContent());
        }else {
            // 如果是单页模式 ， 则启动NewsContentActivity
            NewsContentActivity.startActivity(getActivity(),news.getTitle(),news.getContent());
        }
    }


    private List<News> getNews(){
        List<News> newsList = new ArrayList<News>();
        News news1 = new News();
        news1.setTitle("momo");
        news1.setContent("墨墨是个好姑娘");
        News news2 = new News();
        news2.setTitle("zhuzhu");
        news2.setContent("猪猪是个大胖子");

        newsList.add(news1);
        newsList.add(news2);
        return newsList;
    }


}
