package com.momo.criminaintent.criminaintent;

import android.provider.ContactsContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by mmxb on 2015/4/3.
 */

/**
 *  Crime类 含有 Crime的id ,title ,solved 基本信息
 *  可以进行设置或读取
 */

public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mDate;
//    private String mDate;
    private Boolean mSolved;


    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_SOLVED = "solved";
    private static final String JSON_DATE = "date";

    public Crime(){
        mId = UUID.randomUUID();
        mDate = new Date();
//        Date date = new Date();
//        Locale locale = new Locale("en", "US");
//        DateFormat fullDateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);
//        System.out.println(fullDateFormat.format(date));
//        mDate = fullDateFormat.format(date);

    }

//    public Crime(JSONObject json) throws JSONException {
//        mId = UUID.fromString(json.getString(JSON_ID));
//        if (json.has(JSON_TITLE)){
//            mTitle = json.getString(JSON_TITLE);
//        }
//        mSolved = json.getBoolean(JSON_SOLVED);
//        mDate = new Date(json.getLong(JSON_DATE));
//    }


//    public JSONObject toJSON() throws JSONException {
//        JSONObject json = new JSONObject();
//        json.put(JSON_ID,mId.toString());
//        json.put(JSON_TITLE,mTitle);
//        json.put(JSON_SOLVED,mSolved);
//        json.put(JSON_DATE,mDate.getTime());
//        return json;
//    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public Date getDate() {
        return mDate;
    }

    public Boolean getSolved() {
        return mSolved;
    }


    public void setTitle(String title) {
        mTitle = title;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

//    public String getDate() {
//        return mDate;
//    }

//    public void setDate(String date) {
//        mDate = date;
//    }

    public void setSolved(Boolean solved) {
        this.mSolved = solved;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
