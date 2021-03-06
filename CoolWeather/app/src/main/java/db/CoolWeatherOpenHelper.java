package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mmxb on 2015/3/26.
 */
public class CoolWeatherOpenHelper extends SQLiteOpenHelper{

    /**
     * Province表 建表语句
     */
    public static final String CREATE_PROVINCE = "create table Province ("
            +"id integer primary key autoincrement, "
            +"province_name text ,"
            +"province_code text )";

    /**
     *City表 建表语句
     */
    public static final String CREATE_CITY = "create table City ("
            +"id integer primary key autoincrement, "
            +"city_name text ,"
            +"city_code text ,"
            +"province_id integer )";

    /**
     *Country表 建表语句
     */
    public static final String CREATE_COUNTRY = "create table Country ("
            +"id integer primary key autoincrement, "
            +"country_name text ,"
            +"country_code text ,"
            +"city_id integer )";


    public CoolWeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /**
         * 创建这三张表
         */
        sqLiteDatabase.execSQL(CREATE_PROVINCE);
        sqLiteDatabase.execSQL(CREATE_CITY);
        sqLiteDatabase.execSQL(CREATE_COUNTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
