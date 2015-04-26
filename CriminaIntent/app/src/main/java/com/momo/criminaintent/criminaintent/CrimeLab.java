package com.momo.criminaintent.criminaintent;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by mmxb on 2015/4/3.
 */

/**
 *  CrimeLab类 单例模式 用来存储 Crimes
 *
 *  private CrimeLab(Context appContext)    初始化100个crime
 *  CrimeLab get(Context c)    获取实例  c.getApplicationContext()  使得生命周期为整个APP
 *  ArrayList<Crime> getCrimes()    返回包含100个crime的ArrayList
 *  Crime getCrime(UUID id)    通过参数id返回crime
 */
public class CrimeLab  {

    private ArrayList<Crime> mCrimes;

    private static final String TAG = "CrimeLab";
    private static final String FILENAME = "crimes.json";
    private CriminalIntentJSONSerializer mSerializer;
    private static CrimeLab sCrimeLab;
    private Context mAppContext;

    private CrimeLab(Context appContext){
        mAppContext = appContext;
        mCrimes = new ArrayList<Crime>();
        for (int i = 0; i<100; i++){
            Crime c= new Crime();
            c.setTitle("Crime #" + i);
            c.setSolved(i%2 ==0);
            mCrimes.add(c);
        }
//        try {
//            mCrimes = mSerializer.loadCrimes();
//        } catch (IOException e) {
//            mCrimes = new ArrayList<Crime>();
//            Log.e(TAG,"Error load crimes :",e);
//        }
    }


    public static CrimeLab get(Context c){
        if (sCrimeLab == null){
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

//    public boolean saveCrimes(){
//        try {
//            mSerializer.saveCrimes(mCrimes);
//            Log.d(TAG,"crimes saved to file");
//            return true;
//        }catch (Exception e){
//            Log.e(TAG,"Error saving crimes :",e);
//            return false;
//        }
//
//    }

    public ArrayList<Crime> getCrimes(){
        return mCrimes;
    }

    public Crime getCrime(UUID id){
        for (Crime c : mCrimes){
            if (c.getId().equals(id)){
                return  c;
            }
        }
        return null;
    }

    public void addCrime(Crime c){
        mCrimes.add(c);
    }

}
