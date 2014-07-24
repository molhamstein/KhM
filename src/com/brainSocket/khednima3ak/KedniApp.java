package com.brainSocket.khednima3ak;

import java.io.File;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.PointF;
import android.net.http.HttpResponseCache;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View.OnCreateContextMenuListener;
import android.widget.Toast;

import com.brainSocket.data.DataSrc;
import com.brainSocket.data.UserNotificationsMgr;
import com.brainSocket.enums.FilterType;
import com.brainSocket.enums.UserEventType;
import com.brainSocket.enums.UserType;
import com.brainSocket.models.UserEvent;

public class KedniApp extends Application {

	
	//Keys
	public static final String FIRST_RUN_KEY = "FIRSTRUN" ;
	public static final String IS_DRIVER_KEY = "acount_data_driver" ;
	public static final String USER_ID_KEY = "userID" ;
	public static final int USER_ID_NULL = -1 ; 
	public static final String PRODUCT_INTENT = "prod" ; 
	public static final String ADAPTER_INTENT = "adapter" ;
	public static String flag="flag";
	
	
	//members
	public static DataSrc dataSrc ;
	public static Boolean IS_FIRST_RUN ;
	public static Context context ;
	public static LayoutInflater inflater ;
	private Editor editor=null;
	public UserNotificationsMgr notificationMgr ;
	
	
	
	//global data
	private static int userID; 
	private static int goalID = 5;
	private static int destinationID = 5;
	private static UserType CurrentStatus ;
	private static FilterType filterType ;
	private static PointF currentloc ;
	
	///need to handle inital values for prereence params
	
	
	
	@Override
	public void onCreate() {
		super.onCreate();
	
		dataSrc = new DataSrc(this);
		context = getApplicationContext();
		
		//this needs to be checked if it has any bad effect on memory 
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		notificationMgr = new UserNotificationsMgr(this);
		//notificationMgr.createNotification();
		SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
		editor=sharedpreferences.edit();
		
		//setUserID(41);
		getPreferenceParams () ;

		
		/*
		enableHttpCaching();
		
		*/
		
		IS_FIRST_RUN = CheckFirstRun() ;
		
		if(IS_FIRST_RUN){
			setFirstRun(false);
		}
		
		IS_FIRST_RUN = CheckFirstRun();
		
		
	}
	
	
	
	
	public static  PointF getCurrentloc() {
		return currentloc;
	}
	public static void setCurrentloc(PointF currentloc) {
		KedniApp.currentloc = currentloc;
	}
	
	public static UserType getCurrentStatus() {
		return CurrentStatus;
	}
	public static void setCurrentStatus(UserType currentStatus) {
		CurrentStatus = currentStatus;
	}
	public static void SetGoalID(int goalID)
	{
		KedniApp.goalID=goalID;
	}
	public static int getGoalID()
	{
		return KedniApp.goalID;
	}
	public static int getUserID()
	{
		return userID;
	}
	public void setUserID(int userID)
	{
		KedniApp.userID = userID ;
		editor.putInt(USER_ID_KEY, userID);
		editor.commit();
	}
	public static FilterType getFilterType() {
		return filterType;
	}
	public static void setFilterType(FilterType filterType) {
		KedniApp.filterType = filterType;
	}
	public static int getDestinationID() {
		return destinationID;
	}
	public static void setDestinationID(int destinationID) {
		KedniApp.destinationID = destinationID;
	}

	public static Context getContext()
	{
		return context;
	}
	
	private void getPreferenceParams (){
		
		SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
		userID = sharedpreferences.getInt(USER_ID_KEY, -1); 
		Boolean fRun = sharedpreferences.getBoolean(FIRST_RUN_KEY, true);
		boolean isDriver= sharedpreferences.getBoolean(IS_DRIVER_KEY, true);
		if (isDriver){
			CurrentStatus = UserType.DRIVER;
		}else{
			CurrentStatus = UserType.PASSENGER ;
		}
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
		SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this) ;
		Boolean fRun = sharedpreferences.getBoolean(FIRST_RUN_KEY, true);
		
    	return fRun ;
			
	}
	
	public void setFirstRun(Boolean bool){
    	editor.putBoolean(FIRST_RUN_KEY, bool);
    	editor.commit();

    }
	
	public static void registerUserEvent (UserEvent event ){
		//need to handle the rest of the events
		if(event.getType() == UserEventType.RIDE_REQ_SENT){
			dataSrc.localHandler.insertUserEvent(event);
		}
	
	}

	
}
