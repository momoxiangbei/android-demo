package com.momo.test.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ViewPager mViewPager;
    private int[] mImagesId = new int[]{R.mipmap.guide_image1,R.mipmap.guide_image2,R.mipmap.guide_image3};
    private List<ImageView> mImages = new ArrayList<ImageView>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
        mViewPager.setAdapter(new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setImageResource(mImagesId[position]);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(imageView);
                mImages.add(imageView);
                return imageView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
               container.removeView(mImages.get(position));
            }

            @Override
            public int getCount() {
                return mImagesId.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
        });


    }
}
