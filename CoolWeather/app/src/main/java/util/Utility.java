package util;

import android.text.TextUtils;

import org.w3c.dom.Text;

import model.City;
import model.CoolWeatherDB;
import model.Country;
import model.Province;

/**
 * Created by mmxb on 2015/3/26.
 */
public class Utility {
    /**
     * 解析和处理返回的省级数据
     */
    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB,String response){
        if(!TextUtils.isEmpty(response)){
            String[] allProvinces = response.split(",");
            if (allProvinces != null && allProvinces.length >0){
                for (String p: allProvinces){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //将解析的数据存储到表中
                    coolWeatherDB.saveProvince(province);

                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理返回的市级数据
     */
    public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB,String response,int provinceId){
        if (!TextUtils.isEmpty(response)){
            String[] allCities = response.split(",");
            if (allCities!= null & allCities.length>0){
                for (String c:allCities){
                    String[] array = c.split("\\|");
                    City city = new City();
                    city.setCityCode(array[0]);
                    city.setCityName(array[0]);
                    city.setProvinceId(provinceId);
                    //将解析出来的数据存到数据中
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountriesResponse(CoolWeatherDB coolWeatherDB,String response,int CityId){
        if (!TextUtils.isEmpty(response)){
            String[] allCountries = response.split(",");
            if (allCountries != null && allCountries.length>0){
                for (String c: allCountries){
                    String[] array = c.split("\\|");
                    Country country = new Country();
                    country.setCountryCode(array[0]);
                    country.setCountryName(array[1]);
                    country.setCityId(CityId);
                    //将解析出来的数据存到数据库中
                    coolWeatherDB.saveCountry(country);
                }
                return true;
            }
        }
        return false;
    }
}
