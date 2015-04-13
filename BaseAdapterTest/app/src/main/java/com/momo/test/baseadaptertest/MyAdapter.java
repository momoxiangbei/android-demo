package com.momo.test.baseadaptertest;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by mmxb on 2015/4/13.
 */
public class MyAdapter extends BaseAdapter {

    private List<ItemBean> mList;
    private LayoutInflater mInflater;

    //在构造方法中将数据传进来
    //mInflater 从一个Context中，获得一个布局填充器，可以使用这个填充器来把xml布局文件转为View对象了。
    public MyAdapter(Context context,List<ItemBean> list) {
        mList = list;
        mInflater = LayoutInflater.from(context);
    }


    //返回ListView需要显示的数据个数
    @Override
    public int getCount() {
        return mList.size();
    }

    //获取与指定position对应的数据
    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    //获取指定行对应的ID
    @Override
    public long getItemId(int position) {
        return position;
    }

    //getView获取position处的一个Item
    //convertView可以缓存Item布局
    //ViewHolder可以获取布局中的组件，通过setTag()将convertView和ViewHolder绑定起来
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item,null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.lv_image);
            viewHolder.title = (TextView) convertView.findViewById(R.id.lv_title);
            viewHolder.content = (TextView) convertView.findViewById(R.id.lv_content );
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ItemBean item = mList.get(position);
        viewHolder.imageView.setImageResource(item.getItemImageResId());
        viewHolder.title.setText(item.getItemTitle());
        viewHolder.content.setText(item.getItemContent());

        return convertView;

    }

    class ViewHolder{
        public ImageView imageView;
        public TextView title;
        public TextView content;
    }

}
