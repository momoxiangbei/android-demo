package com.momo.test.baseadaptertest;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<ItemBean> itemBeanList = new ArrayList<>();
        for (int i=1;i<=20;i++){
            itemBeanList.add(new ItemBean(R.mipmap.ic_launcher,"我是标题"+i,"我是内容"+i));
            ListView listView = (ListView) findViewById(R.id.lv_main);
            listView.setAdapter(new MyAdapter(this,itemBeanList));

        }
    }



}
