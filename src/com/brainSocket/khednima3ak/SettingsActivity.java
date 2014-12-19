package com.brainSocket.khednima3ak;

import java.util.List;

import com.brainSocket.Listners.SetPriceListner;
import com.brainSocket.enums.FilterType;
import com.brainSocket.enums.UserSex;
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


public class SettingsActivity extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	
	/*public static String KEY_NOTIFS = "enable_notifs" ; 
	public static String KEY_NOTIFS_SOUND = "enable_notifs_sounds" ;
	public static String KEY_VISIBILITY = "vis_level" ;
	public static String KEY_DRIVER= "acount_data_driver";*/
	
	

	
	@SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {

        	addPreferencesFromResource(R.xml.unified_preferences);
        	
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
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		KedniApp.setCurrentActivity(this);
	}
	@Override
	protected void onPause() {
		super.onPause();
		KedniApp.setCurrentActivity(null);
	}
   
	@Override
    public void onBuildHeaders(List<Header> target) {
        //loadHeadersFromResource(R.xml.pref_headers, target);
    }

	@Override
	public void onSharedPreferenceChanged(SharedPreferences arg0, String key) {

		if (KedniApp.FILTER_TYPE_KEY.equals(key)){
			Intent intent = new Intent(this,PickRideFriendsActivity.class) ;
			startActivity(intent);
		}
		
	}


    @SuppressLint("NewApi")
	public static class Prefs1Fragment extends PreferenceFragment {
    	private SetPriceListner setPriceCallback ;
    	
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            
            setPriceCallback = new SetPriceListner() ;
            addPreferencesFromResource(R.xml.unified_preferences);
            
            /*
            SettingsActivity activity = (SettingsActivity) getActivity() ;
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(activity);
            */
            
            this.findPreference(KedniApp.FILTER_TYPE_KEY).setOnPreferenceChangeListener(
            		
                    new OnPreferenceChangeListener() {
                        @Override
                        public boolean onPreferenceChange(Preference preference,Object newValue) {
                            //TrackerService.updateStats(Long.decode(newValue.toString()));
                        	int val = Integer.valueOf((String) newValue); ;
                        	FilterType filter = FilterType.values()[val] ;
                        	KedniApp.dataSrc.serverHandler.setFilterType(null, filter) ;
                        	if (newValue.equals("2")){
 
                    			Intent intent = new Intent(getActivity(),PickRideFriendsActivity.class) ;
                    			startActivity(intent);
                    		}
                        	
                            return true;
                        }
                    });
            
            this.findPreference(KedniApp.PRICE_KEY).setOnPreferenceChangeListener(
            		
                    new OnPreferenceChangeListener() {
                        @Override
                        public boolean onPreferenceChange(Preference preference,Object newValue) {
                            //TrackerService.updateStats(Long.decode(newValue.toString()));
                        	int val = Integer.valueOf((String) newValue); ;
                        	int price = val * 5 ;
                    		KedniApp.dataSrc.serverHandler.setPrice( Prefs1Fragment.this.setPriceCallback, price) ;
                            return true;
                        }
                    });
            
            
            this.findPreference("accsex").setOnPreferenceChangeListener(
            		
                    new OnPreferenceChangeListener() {
                        @Override
                        public boolean onPreferenceChange(Preference preference,Object newValue) {
                            //TrackerService.updateStats(Long.decode(newValue.toString()));
                        	int val = Integer.valueOf((String) newValue); ;
                        	UserSex sex  = UserSex.values()[val] ;
                    		KedniApp.dataSrc.serverHandler.changeAcceptableSex(null, sex) ;
                            return true;
                        }
                    });
            
            /*this.findPreference("datadriver").setOnPreferenceChangeListener(
            		
                    new OnPreferenceChangeListener() {
                        @Override
                        public boolean onPreferenceChange(Preference preference,Object newValue) {
                            //TrackerService.updateStats(Long.decode(newValue.toString()));
                        
                    		if(newValue.equals(1)){
                    			KedniApp.dataSrc.serverHandler.changeState(null , UserType.DRIVER) ;
                    		}else{
                    			KedniApp.dataSrc.serverHandler.changeState(null , UserType.PASSENGER) ;
                    		}
                    		
                            return true;
                     }
            });
*/
        }
        
    }

    
}