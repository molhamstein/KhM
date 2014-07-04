package com.brainSocket.data;

import java.util.HashMap;
import com.brainSocket.khednima3ak.KedniApp;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class loginManager {
 

    SharedPreferences pref;
    Editor editor;     // Editor for Shared preferences
    Context _context;
    int PRIVATE_MODE = 1;    // Shared pref mode
    
    
    private static final String PREF_NAME = "MALLE";     // Sharedpref file name
    private static final String IS_LOGIN = "IsLoggedIn";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String IS_FIRST_RUN = "firstRun";
    
    private static loginManager instance ;
     
    private loginManager(){
        this._context = KedniApp.context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
     
    public static loginManager getInstance (){
    	if(instance == null){
    		instance = new loginManager();
    	}
    	return instance ;
    }
    
    
    public void createLoginSession(String name, String email){

        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_EMAIL, email);
         
        editor.commit();
    }  
    
    public boolean isFirstRun(){
    	Boolean fRun = pref.getBoolean(IS_FIRST_RUN, true);
    	return fRun ;
    }
    
    public void setFirstRun(Boolean bool){
    	editor.putBoolean(IS_FIRST_RUN, bool);
    	editor.commit();
    }
    
    public HashMap<String, String> getUserDetails(){
    	
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_NAME, pref.getString(KEY_NAME, null));
        user.put(KEY_EMAIL, pref.getString(KEY_EMAIL, null));
 
        return user;
    }
    
    public void logoutUser(){

        editor.clear();
        editor.commit();
    
    }
     
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }


}