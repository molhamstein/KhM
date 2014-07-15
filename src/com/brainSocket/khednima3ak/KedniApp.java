package com.brainSocket.khednima3ak;

import java.io.File;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.http.HttpResponseCache;
import android.view.LayoutInflater;

import com.brainSocket.data.DataSrc;
import com.brainSocket.data.UserNotificationsMgr;

public class KedniApp extends Application {
	
	public static DataSrc dataSrc ;
	public static String lang = "ENGLISH";
	
	public static Boolean IS_FIRST_RUN ;
	
	public static Context context ;
	public static LayoutInflater inflater ;
	
	public static final String FIRST_RUN_KEY = "FIRSTRUN" ;
	public static final String PRODUCT_INTENT = "prod" ; 
	public static final String ADAPTER_INTENT = "adapter" ;
	
	public static String PREFERENCES="KHM_PREFERENCES";
	public static String flag="flag";
	private Editor editor=null;
	private static int userID; 
	private static int goalID;
	
	public static void SetGoalID(int goalID)
	{
		KedniApp.goalID=goalID;
	}
	public static int getGoalID()
	{
		return KedniApp.goalID;
	}
	private UserNotificationsMgr notificationMgr ;

	@Override
	public void onCreate() {
		super.onCreate();
	
		dataSrc = new DataSrc(this);
		context = getApplicationContext();
		
		//this needs to be checked if it has any bad effect on memory 
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		notificationMgr = new UserNotificationsMgr(this);
		notificationMgr.createNotification();
		SharedPreferences sharedpreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
		editor=sharedpreferences.edit();
		userID=sharedpreferences.getInt("userID", -1);
		
		/*
		enableHttpCaching();
		
		*/
		
		IS_FIRST_RUN = CheckFirstRun() ;
		
		if(IS_FIRST_RUN){
			setFirstRun(false);
		}
		
		IS_FIRST_RUN = CheckFirstRun();
		
	}
	public static int getUserID()
	{
		return userID;
	}
	public void setUserID(int userID)
	{
		editor.putInt("userID", userID);
		editor.commit();
	}
	public static Context getContext()
	{
		return context;
	}
	
	@SuppressLint("NewApi")
	private void enableHttpCaching() {
        
            try {
              File httpCacheDir = new File(getApplicationContext().getCacheDir()  , "http");
              long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
              HttpResponseCache.install(httpCacheDir, httpCacheSize);
            } catch (IOException e) {
              //Log.i(Constants.TAG_REPONSE_CACHING_FAILED, "OVER ICS: HTTP response cache failed:" + e);
            }
    }
	
	private boolean CheckFirstRun(){
		SharedPreferences sharedpreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
		Boolean fRun = sharedpreferences.getBoolean(FIRST_RUN_KEY, true);
		
    	return fRun ;
			
	}
	
	public void setFirstRun(Boolean bool){
    	editor.putBoolean(FIRST_RUN_KEY, bool);
    	editor.commit();
    }

	
}
