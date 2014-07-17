package com.brainSocket.khednima3ak;

import java.util.List;

import com.brainSocket.enums.UserType;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.widget.Button;


public class AcountActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	
	/// not correct /// need to be updated 
	public static String KEY_NOTIFS = "enable_notifs" ; 
	public static String KEY_NOTIFS_SOUND = "enable_notifs_sounds" ;
	public static String KEY_VISIBILITY = "vis_level" ;
	public static String KEY_DRIVER= "acount_data_driver"; 
	

	
	@SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {

        	addPreferencesFromResource(R.xml.settings);
        	
        }else{
        	
	        if (hasHeaders()) {
	            Button button = new Button(this);
	            button.setText("Some action");
	            setListFooter(button);
	        }else {
	        	
	        	getFragmentManager().beginTransaction().replace(android.R.id.content, new Prefs1Fragment()).commit();
	
	        }
	        
        }
        

        
    }

   
	@Override
	public void onSharedPreferenceChanged(SharedPreferences arg0, String key) {

		if (KedniApp.IS_DRIVER_KEY.equals(key)){
			Intent intent = new Intent(this,PickRideFriendsActivity.class) ;
			startActivity(intent);
		}
		
	}



    /**
     * This fragment shows the preferences for the first header.
     */
    @SuppressLint("NewApi")
	public static class Prefs1Fragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //PreferenceManager.setDefaultValues(getActivity(),R.xml.advanced_preferences, false);

            addPreferencesFromResource(R.xml.acount);
            
            /*
            SettingsActivity activity = (SettingsActivity) getActivity() ;
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(activity);
            */
            
            this.findPreference(KedniApp.IS_DRIVER_KEY).setOnPreferenceChangeListener(
            		
                    new OnPreferenceChangeListener() {
                        @Override
                        public boolean onPreferenceChange(Preference preference,Object newValue) {
                            //TrackerService.updateStats(Long.decode(newValue.toString()));
                        	
                    		boolean  val ;
                    		Object ob = newValue ;
                    		if(newValue.equals(true)){
                    			KedniApp.dataSrc.serverHandler.changeState(null , UserType.DRIVER) ;
                    		}else{
                    			KedniApp.dataSrc.serverHandler.changeState(null , UserType.PASSENGER) ;
                    		}
                    		
                            return true;
                     }
            });
            
            

        }
        
    }

    
}