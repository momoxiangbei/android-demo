package com.baidu.baidulocationdemo;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class LocationActivity extends Activity{

    private LocationClient mLocationClient;
    private TextView LocationResult,ModeInfor;
    private Button startLocation;
    private LocationMode tempMode = LocationMode.Hight_Accuracy;
    private String tempcoor="gcj02";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        mLocationClient = ((LocationApplication)getApplication()).mLocationClient;

        LocationResult = (TextView)findViewById(R.id.textView1);

        ((LocationApplication)getApplication()).mLocationResult = LocationResult;

        startLocation = (Button)findViewById(R.id.addfence);
        startLocation.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                InitLocation();

                if(startLocation.getText().equals(getString(R.string.startlocation))){
                    mLocationClient.start();
                    startLocation.setText(getString(R.string.stoplocation));
                }else{
                    mLocationClient.stop();
                    startLocation.setText(getString(R.string.startlocation));
                }


            }
        });

    }
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        mLocationClient.stop();
        super.onStop();
    }

    private void InitLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(tempMode);//设置定位模式
        option.setCoorType(tempcoor);//返回的定位结果是百度经纬度，默认值gcj02
        int span=1000;
        try {
            span = Integer.valueOf(5000);
        } catch (Exception e) {
            // TODO: handle exception
        }
        option.setScanSpan(span);//设置发起定位请求的间隔时间为5000ms
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }
}
