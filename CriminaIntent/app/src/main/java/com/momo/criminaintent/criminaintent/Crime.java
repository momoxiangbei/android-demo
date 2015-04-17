package com.momo.criminaintent.criminaintent;

import android.provider.ContactsContract;

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
//    private Date mDate;
    private String mDate;
    private Boolean mSolved;

    public Crime(){
        mId = UUID.randomUUID();
//        mDate = new Date();
        Date date = new Date();
        Locale locale = new Locale("en", "US");
        DateFormat fullDateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);
        System.out.println(fullDateFormat.format(date));
        mDate = fullDateFormat.format(date);

    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

//    public Date getDate() {
//        return mDate;
//    }

    public Boolean getSolved() {
        return mSolved;
    }


    public void setTitle(String title) {
        mTitle = title;
    }

//    public void setDate(Date mDate) {
//        this.mDate = mDate;
//    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public void setSolved(Boolean solved) {
        this.mSolved = solved;
    }

    @Override
    public String toString() {
        return mTitle;
    }
}
