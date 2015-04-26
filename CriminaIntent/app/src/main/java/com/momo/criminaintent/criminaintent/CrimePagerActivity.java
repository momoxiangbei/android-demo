package com.momo.criminaintent.criminaintent;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;


import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by mmxb on 2015/4/13.
 */

public class CrimePagerActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private ArrayList<Crime> mCrimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //动态viewPager加载布局
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        //加载mCrimes数据
        mCrimes = CrimeLab.get(this).getCrimes();



        FragmentManager fm = getSupportFragmentManager();

        //设置Adapter，加载CrimePagerActivity托管的所有Fragment
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {

            //获取指定位置的Fragment
            @Override
            public Fragment getItem(int i) {
                Crime crime = mCrimes.get(i);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });


        //ViewPager默认显示PageAdapter的第一个item，需要设置当前要显示的列表项
        UUID crimeId = (UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);
        for (int i= 0; i<mCrimes.size() ;i++){
            if (mCrimes.get(i).getId().equals(crimeId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }

        //标题栏 实时显示
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {}

            @Override
            public void onPageScrollStateChanged(int i) {}

            @Override
            public void onPageSelected(int i) {
                Crime crime = mCrimes.get(i);
                if (crime.getTitle() != null){
                    setTitle(crime.getTitle());
                }
            }

        });

    }
}
