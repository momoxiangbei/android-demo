package activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.transition.CircularPropagation;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.coolweather.app.coolweather.R;

import java.util.ArrayList;
import java.util.List;

import model.City;
import model.CoolWeatherDB;
import model.Country;
import model.Province;
import util.HttpCallbackListener;
import util.HttpUtil;
import util.Utility;

/**
 * Created by mmxb on 2015/3/26.
 */
public class ChooseAreaActivity extends Activity {

    public static final int LEVEL_PROVINCE = 0 ;
    public static final int LEVEL_CITY = 1 ;
    public static final int LEVEL_COUNTRY = 2 ;

    private ProgressDialog progressDialog;
    private TextView titleText;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private CoolWeatherDB coolWeatherDB;
    private List<String> dataList = new ArrayList<String>();

    private List<Province> provinceList;
    private List<City> cityList;
    private List<Country> countryList;

    private Province selectProvince;
    private City selectCity;
    private Country selectCountry;

    private int currentLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choose_area);
        listView = (ListView) findViewById(R.id.list_view);
        titleText = (TextView) findViewById(R.id.title_text);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(adapter);
        coolWeatherDB = CoolWeatherDB.getInstance(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (currentLevel == LEVEL_PROVINCE){
                    selectProvince = provinceList.get(i);
                    queryCities();
                }else if (currentLevel == LEVEL_CITY){
                    selectCity = cityList.get(i);
                    queryCountries();
                }
            }
        });
        queryProvinces();   //加载省级数据
    }

    /**
     * 查询全国所有的省，优先从数据库查询，如果没有，再去服务器上查询
     */

    private void queryProvinces() {
        provinceList = coolWeatherDB.loadProvinces();
        if (provinceList.size()>0){
            dataList.clear();
            for (Province province : provinceList){
                dataList.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            titleText.setText("中国");
            currentLevel = LEVEL_PROVINCE;
        }
        else {
            queryFromServer(null,"province");
        }
    }

    /**
     * 查询全国所有的市，优先从数据库查询，如果没有，再去服务器上查询
     */
    private void queryCities() {
        cityList = coolWeatherDB.loadCities(selectProvince.getId());
            if (cityList.size()>0){
                dataList.clear();
                for (City city : cityList){
                    dataList.add(city.getCityName());
                }
                adapter.notifyDataSetChanged();
                listView.setSelection(0);
                titleText.setText(selectProvince.getProvinceName());
                currentLevel = LEVEL_CITY;
            }else {
                queryFromServer(selectProvince.getProvinceCode(),"city");
            }
    }

    /**
     * 查询全国所有的县，优先从数据库查询，如果没有，再去服务器上查询
     */
    private void queryCountries() {
        countryList = coolWeatherDB.loadCounties(selectCity.getId());
        if (countryList.size()>0){
            dataList.clear();
            for (City city :cityList){
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            titleText.setText(selectProvince.getProvinceName());
            currentLevel = LEVEL_CITY;
        }else {
            queryFromServer(selectCity.getCityCode(),"country");
        }
    }


    /**
     * 根据传入的代号和类型从服务器上查询数据
     */
    private void queryFromServer(final String code, final String type) {
        String address;
        if (!TextUtils.isEmpty(code)){
            address = "http://www.weather.com.cn/data/list3/city"+code+".xml";
        }else {
            address = "http://www.weather.com.cn/data/list3/city.xml";
        }
        showProgressDialog();
        HttpUtil.sendHttpRequest(address,new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                boolean result = false;
                if ("province".equals(type)){
                    result = Utility.handleProvincesResponse(coolWeatherDB,response);

                }else if ("city".equals(type)){
                    result = Utility.handleCitiesResponse(coolWeatherDB,response,selectProvince.getId());

                }else if ("country".equals(type)){
                    result = Utility.handleCountriesResponse(coolWeatherDB,response,selectCity.getId());
                }
                if (result){
                    //通过runOnUiThread返回到主线程处理逻辑
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();
                            if ("province".equals(type)){
                                queryProvinces();
                            }else if ("city".equals(type)){
                                queryCities();
                            }else if ("country".equals(type)){
                                queryCountries();
                            }
                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {
                //通过runOnUiThread返回到主线程处理逻辑
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                        Toast.makeText(ChooseAreaActivity.this,"加载失败",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    private void showProgressDialog() {
        if (progressDialog == null){
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("正在加载...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }

    private void closeProgressDialog() {
        if (progressDialog!= null){
            progressDialog.dismiss();
        }
    }

    /**
     * 捕获Back按键，根据当前级别来判断。此时应该返回市列表，省列表，还是直接退出
     */
    @Override
    public void onBackPressed() {
        if (currentLevel == LEVEL_COUNTRY){
            queryCities();
        }else if (currentLevel == LEVEL_CITY){
            queryProvinces();
        } else {
            finish();
        }
    }
}

















