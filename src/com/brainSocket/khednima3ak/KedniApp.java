package com.brainSocket.khednima3ak;


import java.util.Date;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.PointF;
import android.location.LocationManager;
import android.preference.PreferenceManager;
import android.util.Config;
import android.view.LayoutInflater;

import com.brainSocket.Listners.CheckValidVersionListner;
import com.brainSocket.data.DataSrc;
import com.brainSocket.data.KedniConfig;
import com.brainSocket.data.UserNotificationsMgr;
import com.brainSocket.enums.FilterType;
import com.brainSocket.enums.UserType;
import com.brainSocket.models.User;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class KedniApp extends Application {

	// Keys
	public static final String FIRST_RUN_KEY = "FIRSTRUN";
	public static final String IS_ACTIVATED_KEY = "isactivated";
	public static final String IS_SIGNED_KEY = "issigned";
	public static final String IS_IN_TRIAL_KEY = "isintrial";
	public static final String IS_DRIVER_KEY = "datadriver2";
	public static final String IS_NOTIF_SOUND_ENABLED_KEY = "enabsounds";
	public static final String IS_NOTIF_ENABLED_KEY = "enabnotifs";
	public static final String USER_ID_KEY = "userID";
	public static final String USER_FACEBOOK_ID_KEY = "userfbID";
	public static final String USER_NAME_KEY = "userfbName";
	public static final String PRICE_KEY = "dataprice";
	public static final String LAST_ACOUNT_UPDATE_KEY = "lastUpdate";
	public static final int USER_ID_NULL = -1;
	public static final String PRODUCT_INTENT = "prod";
	public static final String ADAPTER_INTENT = "adapter";
	public static final String flag = "flag";
	public static final String DIGEST_TOKEN_KEY = "key" ;
	public static final String ENCRIPTION_KEY = "key" ;
	public static final String UNIT = "á.Ó";
	public static final String FILTER_TYPE_KEY = "vislevel" ;

	// members
	public static DataSrc dataSrc;
	public static PromptManager promptMgr ;
	public static KedniConfig configs ;
	public static Context appContext;
	public static Activity currentActivity ;
	public static LayoutInflater inflater;
	private Editor editor = null;
	public UserNotificationsMgr notificationMgr;
	public static Boolean IS_FIRST_RUN;

	// global data
	private static int userID;
	private static int goalID;
	private static int destinationID;
	private static UserType CurrentStatus;
	private static FilterType filterType;
	private static PointF currentloc;
	private static boolean isVersionValid = true ;
	public static boolean isConnectionAvailable = true ;
	public static boolean isFirstRun = false  ;
	public static int encKey = -1 ;
	public static boolean isInSchedulingMode = false ;
	

	// /need to handle inital values for prereence params

	@Override
	public void onCreate() {
		super.onCreate();

		appContext = getApplicationContext();
		dataSrc = new DataSrc(this);
		promptMgr = new PromptManager(null) ;
		configs = KedniConfig.getInstance() ;
		// this needs to be checked if it has any bad effect on memory
		inflater = (LayoutInflater) appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		notificationMgr = new UserNotificationsMgr(this);
		SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
		PreferenceManager.setDefaultValues(this, R.xml.unified_preferences,	false);
		editor = sharedpreferences.edit();

		getPreferenceParams();
		initParams() ;

		IS_FIRST_RUN = CheckFirstRun();

	 	if (IS_FIRST_RUN) {
			setFirstRun(false);
			setLastUpdateDate(new Date());
		}

		IS_FIRST_RUN = CheckFirstRun();
		//CheckValidity() ;
	}
	
/**
 * sends a CheckValid request to the server and checks for Google Play Services 
 * @param con
 * @return result code of Google Play services check 
 */
	public static int CheckValidity (Context con){
		dataSrc.serverHandler.checkServiceValid(new CheckValidVersionListner(), KedniConfig.VERSION_KEY) ;
		int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(appContext);
		//if (status != ConnectionResult.SUCCESS) {
			//promptManager.promptForGoogleServicesUpdate();
		//}
		return status ;
	}
	
	public static Activity getCurrentActivity() {
	return currentActivity;
}

	public static void setCurrentActivity(Activity currentActivity) {
		KedniApp.currentActivity = currentActivity;
		promptMgr.setContext(currentActivity) ;
	}

	public static boolean isVersionValid() {
		return isVersionValid;
	}

	public static void setVersionValid(boolean isVersionValid) {
		KedniApp.isVersionValid = isVersionValid;
	}

	public static PointF getCurrentloc() {
		return currentloc;
	}

	public static void setCurrentloc(PointF currentloc) {
		KedniApp.currentloc = currentloc;
	}

	public static UserType getCurrentStatus() {
		
		SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(appContext);
		String filter = sharedpreferences.getString(IS_DRIVER_KEY, "1");
		int val = Integer.valueOf(filter) ;
		KedniApp.CurrentStatus = UserType.values()[val] ;
		return CurrentStatus;
	}

	public static void setCurrentStatus(UserType currentStatus) {
		KedniApp app = (KedniApp) appContext ;
		app.editor.putString( IS_DRIVER_KEY, String.valueOf(currentStatus.getValue() ) ); 
		app.editor.commit();
		KedniApp.CurrentStatus = currentStatus;
	}

	public static void SetGoalID(int goalID) {
		KedniApp.goalID = goalID;
	}

	public static int getGoalID() {
		return KedniApp.goalID;
	}

	public static int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		KedniApp.userID = userID;
		editor.putInt(USER_ID_KEY, userID);
		editor.commit();
	}

	public static FilterType getFilterType() { 
		SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(appContext);
		String filter = sharedpreferences.getString(FILTER_TYPE_KEY, "0");
		int ndex = Integer.valueOf(filter);
		KedniApp.filterType = FilterType.values()[ndex] ;
		return filterType;
	}

	public static void setFilterType(FilterType filterType) {
		KedniApp app = (KedniApp) appContext ;
		app.editor.putString( FILTER_TYPE_KEY, String.valueOf(filterType.getValue() ));
		app.editor.commit();
		KedniApp.filterType = filterType;
	}
	
	public static boolean IsNotifSoundEnabled() { 
		SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(appContext);
		Boolean enabled = sharedpreferences.getBoolean(IS_NOTIF_SOUND_ENABLED_KEY, true);
		return enabled;
	}
	public static boolean IsNotifEnabled() { 
		SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(appContext);
		Boolean enabled = sharedpreferences.getBoolean(IS_NOTIF_ENABLED_KEY, true);
		return enabled;
	}
	public static int getDestinationID() {
		return destinationID;
	}

	public static void setDestinationID(int destinationID) {
		KedniApp.destinationID = destinationID;
	}

	public static Context getContext() {
		return appContext;
	}

	private void getPreferenceParams() {

		SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
		userID = sharedpreferences.getInt(USER_ID_KEY, -1);
		Boolean fRun = sharedpreferences.getBoolean(FIRST_RUN_KEY, true);
		KedniApp.CurrentStatus = getCurrentStatus();
		KedniApp.encKey = getEncKey() ;
		
		//CurrentStatus = getCurrentStatus() ;
	}

	private boolean CheckFirstRun() {
		SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
		Boolean fRun = sharedpreferences.getBoolean(FIRST_RUN_KEY, true);
		return fRun;
	}

	public void setFirstRun(Boolean bool) {
		editor.putBoolean(FIRST_RUN_KEY, bool);
		editor.commit();
	}

	public String getFacebookID() {
		SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
		String FBID = sharedpreferences.getString(USER_FACEBOOK_ID_KEY, String.valueOf(USER_ID_NULL));
		return FBID;
	}

	public void setFacebokID(String FBID) {
		editor.putString( USER_FACEBOOK_ID_KEY, FBID );
		editor.commit();
	}
	
	public void setUserName(String FBName) {
		editor.putString( USER_NAME_KEY, FBName );
		editor.commit();
	}
	
	public String getUserName() {
		SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
		String FBID = sharedpreferences.getString(USER_NAME_KEY, "User");
		return FBID;

	}

	public Boolean getIS_SIGNED_IN() {
		SharedPreferences sharedpreferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		Boolean signed = sharedpreferences.getBoolean(IS_SIGNED_KEY, false);
		return signed;
	}

	public void setIS_SIGNED_IN(Boolean iS_SIGNED_IN) {
		editor.putBoolean(IS_SIGNED_KEY, iS_SIGNED_IN);
		editor.commit();
	}
	
	public Boolean getIS_IN_TRIAL() {
		SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
		Boolean signed = sharedpreferences.getBoolean(IS_IN_TRIAL_KEY, false);
		return signed;
	}
	public void setIS_IN_TRIAL(Boolean iS_IN_TRIAL) {
		editor.putBoolean(IS_IN_TRIAL_KEY, iS_IN_TRIAL);
		editor.commit();
	}

	public Boolean getIS_ACCOUNT_ACTICATED() {
		SharedPreferences sharedpreferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		Boolean activated = sharedpreferences.getBoolean(IS_ACTIVATED_KEY,
				false);
		return activated;
	}

	public void setIS_ACCOUNT_ACTICATED(Boolean iS_ACCOUNT_ACTICATED) {
		editor.putBoolean(IS_ACTIVATED_KEY, iS_ACCOUNT_ACTICATED);
		editor.commit();
	}

	public Date getLastUpdateDate() {
		SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
		Long activated = sharedpreferences.getLong(LAST_ACOUNT_UPDATE_KEY, 0);
		Date date = new Date(activated);
		return date;
	}

	public void setLastUpdateDate(Date LastUpdateDate) {
		Long date = LastUpdateDate.getTime();
		editor.putLong(LAST_ACOUNT_UPDATE_KEY, date);
		editor.commit();
	}
	
	public void setEncKey(int key) {
		editor.putInt(ENCRIPTION_KEY, key);
		editor.commit();
	}
	
	public int getEncKey() {
		SharedPreferences sharedpreferences = PreferenceManager.getDefaultSharedPreferences(this);
		int key = sharedpreferences.getInt(ENCRIPTION_KEY, -1);
		return key;
	}

	
	public static boolean isLocationServiceEnabled() {
		LocationManager lm = null;
		boolean gps_enabled = false;
		boolean network_enabled = false;
		if (lm == null)
			lm = (LocationManager) appContext.getSystemService(Context.LOCATION_SERVICE);
		try {
			gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
		} catch (Exception ex) {
		}
		try {
			network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
		} catch (Exception ex) {
		}

		return gps_enabled || network_enabled;
	}
		
		
	void initParams () {
	
		User.markerWidth = getResources().getInteger(R.integer.marker_programatic_width) ;
		User.markerHeight = getResources().getInteger(R.integer.marker_programatic_height) ;
	}
	

}
