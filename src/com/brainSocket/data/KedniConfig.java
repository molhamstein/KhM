package com.brainSocket.data;

import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.khednima3ak.R;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class KedniConfig {
	
	public enum SERVICE_STATE{OK , VERSION_OUTDATED, SERVICE_URL_CHANGED, SERVICE_DOWN, UNEXPECTED_VERSION } ;
	SERVICE_STATE serviceState = SERVICE_STATE.OK;
	public String tempAppDownloadLink  ;
	
	public static String baseServiceURL  ="http://198.38.91.194/5ednym3ak1/ci/index.php/users";
	public static String baseServiceURL_FOR_UDP  = "198.38.91.194" ;
	public static int UPDATE_MY_POS_PORT = 2522; 
	public static int GET_USERS_ARROUND_ME_PORT = 2524;
	
	public static String VERSION_KEY = "0.2";

	private static KedniConfig instance  = null ; 
	
	
	public static KedniConfig getInstance (){
		if(instance == null){
			instance = new KedniConfig() ;
			SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(KedniApp.appContext);
			
			VERSION_KEY = sharedpreferences.getString("versionKey", VERSION_KEY);
			baseServiceURL = sharedpreferences.getString("baseServiceURL", baseServiceURL);
			baseServiceURL_FOR_UDP = sharedpreferences.getString("baseServiceURL_FOR_UDP", baseServiceURL_FOR_UDP);
			UPDATE_MY_POS_PORT = sharedpreferences.getInt("UPDATE_MY_POS_PORT", UPDATE_MY_POS_PORT);
			GET_USERS_ARROUND_ME_PORT = sharedpreferences.getInt("GET_USERS_ARROUND_ME_PORT", GET_USERS_ARROUND_ME_PORT) ;
			
		}
		return instance ;
	}
	
	
	public void setBaseServiceURL(String newURL){
		baseServiceURL = newURL ;
		storeInPref("baseServiceURL" , newURL ); 
	}
	
	public void setBaseServiceURL_FOR_UDP(String newURL){
		baseServiceURL_FOR_UDP = newURL ;
		storeInPref("baseServiceURL_FOR_UDP" , newURL );
	}
	
	
	public void setUPDATE_MY_POS_PORT(int uPDATE_MY_POS_PORT) {
		UPDATE_MY_POS_PORT = uPDATE_MY_POS_PORT;
		storeInPref("UPDATE_MY_POS_PORT" , uPDATE_MY_POS_PORT );
	}


	public void setGET_USERS_ARROUND_ME_PORT(int gET_USERS_ARROUND_ME_PORT) {
		GET_USERS_ARROUND_ME_PORT = gET_USERS_ARROUND_ME_PORT;
		storeInPref("GET_USERS_ARROUND_ME_PORT", gET_USERS_ARROUND_ME_PORT);
	}


	private void storeInPref(String key , String val){
		SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(KedniApp.appContext);
		Editor editor = sharedpreferences.edit();
		editor.putString( key , val ); 
		editor.commit();
	}
	
	private void storeInPref(String key , int val){
		SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(KedniApp.appContext);
		Editor editor = sharedpreferences.edit();
		editor.putInt( key , val ); 
		editor.commit();
	}
	


}
