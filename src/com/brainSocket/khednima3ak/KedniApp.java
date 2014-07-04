package com.brainSocket.khednima3ak;

import java.io.File;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
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
	
	
	public static final String PRODUCT_INTENT = "prod" ; 
	public static final String ADAPTER_INTENT = "adapter" ;
	
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
		/*
		enableHttpCaching();
		loginManager log = loginManager.getInstance() ;
		IS_FIRST_RUN = log.isFirstRun();
		
		if(IS_FIRST_RUN){
			log.setFirstRun(false);
		}
		
		IS_FIRST_RUN = log.isFirstRun();
		*/
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

	
}
