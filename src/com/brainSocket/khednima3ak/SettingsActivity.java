package com.brainSocket.khednima3ak;

import java.util.List;

import android.annotation.SuppressLint;
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
	
	public static String KEY_NOTIFS = "enable_notifs" ; 
	public static String KEY_NOTIFS_SOUND = "enable_notifs_sounds" ;
	public static String KEY_VISIBILITY = "vis_level" ; 
	

	
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

    /**
     * Populate the activity with the top-level headers.
     */

	@Override
    public void onBuildHeaders(List<Header> target) {
        //loadHeadersFromResource(R.xml.pref_headers, target);
    }

	@Override
	public void onSharedPreferenceChanged(SharedPreferences arg0, String key) {

		if (KEY_VISIBILITY.equals(key)){
			
			

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

            // Make sure default values are applied.  In a real app, you would
            // want this in a shared function that is used to retrieve the
            // SharedPreferences wherever they are needed.
            //PreferenceManager.setDefaultValues(getActivity(),R.xml.advanced_preferences, false);

            addPreferencesFromResource(R.xml.settings);
            
            /*
            SettingsActivity activity = (SettingsActivity) getActivity() ;
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(activity);
            */
            
            this.findPreference(KEY_VISIBILITY).setOnPreferenceChangeListener(
            		
                    new OnPreferenceChangeListener() {
                        @Override
                        public boolean onPreferenceChange(Preference preference,Object newValue) {
                            //TrackerService.updateStats(Long.decode(newValue.toString()));
                            return true;
                        }
                    });

        }
        
    }

    
}