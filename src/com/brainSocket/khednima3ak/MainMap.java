package com.brainSocket.khednima3ak;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.brainSocket.Listners.GetUsersAroundMeListner;
import com.brainSocket.Listners.SetGoalListner;
import com.brainSocket.Views.Map;
import com.brainSocket.Views.SearchAutoComplete;
import com.brainSocket.adapters.DrawerAdapter;
import com.brainSocket.adapters.SuggestionsAdapter;
import com.brainSocket.enums.UserType;
import com.brainSocket.models.ScheduledTrip;
import com.brainSocket.models.User;
import com.brainSocket.models.UserEvent;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.squareup.picasso.Picasso;


public class MainMap extends ActionBarActivity implements OnMarkerClickListener{

	// Google Map
    private Map map;
    private DrawerLayout drawer ;
    private ActionBarDrawerToggle drawerToggle ;
    private ListView drawerList ;
    public ArrayAdapter<String> autoCompleteAdapter ;
    private SearchAutoComplete autoComplete ;
    public static GetUsersAroundMeListner getUsersCallback ; 
    public List<String> suggestions ;
    ArrayList<User> users ;
    private MenuItem statusIndicator ;
    private ScheduleDiag schedulingDiag = null ;

	static boolean tutorialMode = false ;
	
	public static ScheduledTrip scheduledTrip = null;  
	
	// threading
	Handler mapReadyTraker ;
    private Handler updateHandler ;
    private UpdateRunnable updateRunnable ;
	private Runnable mapReadyTrakerRunnable = new Runnable() {
		@Override
		public void run() {
			if(map == null){
				initilizeMap() ;
				mapReadyTraker.postDelayed(this, 5000);
			}else{
				mapReadyTrakerRunnable = null ;
			}
		}
	} ;

	
	//corenre notification
	private FrameLayout cornerNotofocationHolder ;
	private TextView cornerNotificationText ;
	public static boolean isActivvityActive = false ;
	private static MainMap self= null ;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init (); 
        mapReadyTraker =  new Handler();
    	//float den = getResources().getDisplayMetrics().density ;
    }
    
    
    @Override
    public void onAttachedToWindow() {
    	super.onAttachedToWindow();
    	if(KedniApp.isFirstRun){
    		new ChangeStateDiag(this).show();
    	}
    	KedniApp.setCurrentActivity(this);
    	int googlePlayStatusCode = KedniApp.CheckValidity(this);
    	if(googlePlayStatusCode != ConnectionResult.SUCCESS){
    		//Dialog diag = GooglePlayServicesUtil.getErrorDialog(googlePlayStatusCode,this,0);
    		//if(diag != null)
    			//diag.show();
    		KedniApp.promptMgr.promptForGoogleServicesUpdate() ;
    	}
    	
    }
    
    
    void init(){    	
       	users = new ArrayList<User>();
       	initCornerNotification() ;
        initDrawer() ;
    	try {
            initilizeMap(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    	self = this ;
    }
   
    private void initCornerNotification(){
       	cornerNotofocationHolder = (FrameLayout) findViewById(R.id.corner_notification_container);
       	cornerNotificationText = (TextView) findViewById(R.id.corner_notification_text);
       	cornerNotofocationHolder.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainMap.this,EventsActivity.class);
				MainMap.this.startActivity(i);
				cornerNotofocationHolder.setVisibility(View.GONE);
			}
		});
    }
	/***
	 * Responsible for showing and hiding the corner notification
	 */
	public static void refreshCornerNotification(){
    	if (self !=null){
    	self.runOnUiThread(new Runnable() {
			
			@Override
			public void run(){	    	
			    	List<UserEvent> events = KedniApp.dataSrc.localHandler.getNoneMessagingUserEvents() ;
			    	int activeEventsCount = 0 ; 
			    	for (UserEvent userEvent : events) {
						if(userEvent.isEventActive())
							activeEventsCount++;
					}
			    	 
					if (activeEventsCount > 0){	
			    		self.cornerNotificationText.setText(String.valueOf( activeEventsCount));
			    		self.cornerNotofocationHolder.setVisibility(View.VISIBLE);
			    	}else{
			    		self.cornerNotofocationHolder.setVisibility(View.GONE);
			    	}
				}
			});
    	}
    }
 
    public void refreshUsersAroundMe(List<User> users)
	{
    	this.users.clear();
    	this.users.addAll(users) ;
    	if(map != null){
    		map.refreshMap(users);
    		map.drawScheduledTripMarker(scheduledTrip);
    	}
    	
	}
    
    private void initData(){
    	getUsersCallback = new GetUsersAroundMeListner(this);
    	updateHandler = new Handler() ;//getWindow().getDecorView().getHandler() ;
    	updateRunnable = new UpdateRunnable() ;
    	updateHandler.postDelayed(updateRunnable, 5000);
    }
    
	private void initAutocomplete(){
		autoComplete = (SearchAutoComplete) findViewById(R.id.autocomplete) ;
        suggestions =  KedniApp.dataSrc.localHandler.getAreas(null);
        ArrayAdapter<String> adapterStatic2 = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,suggestions);
        autoCompleteAdapter = new SuggestionsAdapter(this, android.R.layout.simple_dropdown_item_1line, suggestions);       
        autoComplete.setAdapter(adapterStatic2);
        //autoComplete.addTextChangedListener(new AutoCompleteListener(this));
        autoComplete.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
				TextView tv = (TextView) arg1 ;
				String areaName = (String) tv.getText();
				int areaId = KedniApp.dataSrc.localHandler.getAreaID(areaName);
				KedniApp.dataSrc.serverHandler.setGoal(new SetGoalListner(MainMap.this), areaId, KedniApp.getCurrentloc());
				
				InputMethodManager in = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			    in.hideSoftInputFromWindow(arg1.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);   
			}
		});
	}
	
    private void initilizeMap() {
        if (map == null) {
            GoogleMap gMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            if (gMap != null){
	            map = new Map(this,gMap);
	            map.setMyLocationEnabled(true);
            }
        }
    }
    
    private void initDrawer (){
    	
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);            
        drawerToggle = new ActionBarDrawerToggle (this, drawer, R.drawable.ic_drawer, R.string.drawer_open , R.string.drawer_close);
        drawer.setDrawerListener(drawerToggle);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new DrawerAdapter(MainMap.this,drawerList));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        
        //user Data
        ImageView pic  = (ImageView) findViewById(R.id.user_pic);
        TextView displayName = (TextView) findViewById(R.id.user_name);
        KedniApp app = (KedniApp) getApplicationContext() ;
        String userFBID = app.getFacebookID() ;
        String userName = app.getUserName() ;
        Picasso.with(this).load(User.getPictureLink(userFBID)).into(pic) ;
        displayName.setText(userName);
    }
 
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
    	super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
    @Override
    protected void onResume() {
        super.onResume();
        self = this ;
        if(mapReadyTrakerRunnable != null){ // will be Null after the Map is done initializing 
        	mapReadyTraker.postDelayed(mapReadyTrakerRunnable,5000); 
        }
        
        initData();
        initAutocomplete();
        
        initilizeMap();
        isActivvityActive = true ;
        
        updateHandler.postDelayed(updateRunnable, 5000);
		//mapReadyTraker.postDelayed(mapReadyTrakerRunnable, 3000) ;
    }
    @Override
    protected void onPause() {
    	isActivvityActive= false ;
    	KedniApp.isFirstRun = false ;
    	KedniApp.setCurrentActivity(null);
    	self = null ;
    	
    	updateHandler.removeCallbacks(updateRunnable);
		mapReadyTraker.removeCallbacks(mapReadyTrakerRunnable) ;
		
    	super.onPause();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_map, menu);
		statusIndicator = menu.findItem(R.id.action_ststus);
		statusChanged() ;
		return true;
	}
	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
		
	    switch (item.getItemId()) {
	    case R.id.action_ststus:
	    	//map.centerMapOnMyLocation();
	    	statusIndicator = item ;
	    	new ChangeStateDiag(this).show();
	    	break;
	    default :
	    	return drawerToggle.onOptionsItemSelected(item);

	    }
	    return true ;
	}
	
	public void statusChanged( ){
		if(statusIndicator != null){
			if (KedniApp.getCurrentStatus() == UserType.DRIVER)
				statusIndicator.setIcon(R.drawable.ic_status_dri);
			else
				statusIndicator.setIcon(R.drawable.ic_status_pass);
		}
	}
	public void enterShedulingMode(ScheduledTrip trip){ // trip == null when creating a new scheduled trip 
		schedulingDiag = new ScheduleDiag(this , trip);
		KedniApp.isInSchedulingMode = true ;
		schedulingDiag.show() ;
	}
	
	public void enterShedulingModePhase2(LatLng location ){
		if(schedulingDiag != null && location != null){
			map.EnableUserClick(false);
			schedulingDiag.enterShedulingModePhase2(location) ;
			ScheduledTrip trip =schedulingDiag.getTripInfo() ; 
			map.drawScheduledTripMarker(trip);
			schedulingDiag.show();
		}
	}
	
	public void getLocationForScheduling (boolean useMap){
		if(useMap){
			hideSoftKeypad();
			drawer.closeDrawer(GravityCompat.START);
			schedulingDiag.hide() ;
			map.EnableUserClick(true);
		}else{
			Location loc =  map.getCurrentlocation() ;
			if(loc != null)
				enterShedulingModePhase2(new LatLng(loc.getLatitude(), loc.getLongitude()));
			else{
				Toast.makeText(this, "No location available", Toast.LENGTH_LONG).show() ;
			}
		}
	}
	
	
	@Override
	public boolean onMarkerClick(Marker marker) {
		int index = Integer.valueOf(marker.getSnippet());
		if(index >= 0){
			User usr = users.get(index);
			//if(usr.getMinutesLeftToDepart() >=0 ){ /// this means its a scheduled trip
				//enterShedulingMode(new ScheduledTrip(usr));
				RequestDiag diag = new RequestDiag(this, usr); 
				diag.show();
			//}else{ // RealTime trip
				//RequestDiag diag = new RequestDiag(this, usr); 
				//diag.show();
			//}
		}else if(index == Integer.valueOf(Map.SCHEDULED_TRIP_MARKER_SNIPPET))
			enterShedulingMode(scheduledTrip) ;
		return false;
	}
	
	void hideSoftKeypad()
	{
		try {
			InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
		    inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		}
		catch (Exception e) {}
	}	
	
	
	class UpdateRunnable implements Runnable {
		
		boolean posDetected = false ;
		private static final int runPerCheck = 3 ;
		int checkCounter = 0 ;
		
		@Override
		public void run() {
			
			PointF loc= KedniApp.getCurrentloc() ;
			
			if(loc != null){
				if(KedniApp.getDestinationID() != 0){
					KedniApp.dataSrc.serverHandler.getUsersAroundMe(getUsersCallback, KedniApp.getFilterType() , KedniApp.getCurrentloc());
				}else{
					try{
						if(!MainMap.this.drawer.isDrawerOpen(GravityCompat.START) && !SetGoalListner.pendingGoal && !tutorialMode ){
	    					autoComplete.showDropDown() ;
	    					Toast.makeText( MainMap.this ,getString(R.string.choose_destination), Toast.LENGTH_LONG).show() ;
						}
					}catch (Exception e) {
						updateHandler.removeCallbacks(this);
						return;
					}
				}
    			if(!posDetected && map != null){
    				map.centerMapOnMyLocation();
    				posDetected = true ;
    			}
    			
			}else if(!tutorialMode){
				Toast.makeText(MainMap.this, getString(R.string.retriving_location), Toast.LENGTH_LONG).show();
			}
		
			///regular check for Internet And location service ;
			if(checkCounter <= runPerCheck)
				checkCounter++;
			else{
				doChecks();
				checkCounter = 0 ;
			}
			updateHandler.postDelayed(this, 10000);
			
			
		}
		
		void doChecks (){
			try{
				if(!KedniApp.isLocationServiceEnabled()){
					KedniApp.promptMgr.promptForGPS() ;
				}
				
				boolean isConnectionAvailable = KedniApp.dataSrc.serverHandler.isOnline() ;
				if(KedniApp.isConnectionAvailable && !isConnectionAvailable )
					KedniApp.promptMgr.promptForIntrnet() ;
				KedniApp.isConnectionAvailable = isConnectionAvailable ;
				
			}catch (Exception e) {
				//updateHandler.removeCallbacks(this);
			}
	
		}		
	}
}
