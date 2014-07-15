package com.brainSocket.khednima3ak;

import java.util.ArrayList;
import java.util.List;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.brainSocket.Listners.GetUsersAroundMeListner;
import com.brainSocket.Views.AutoCompleteListener;
import com.brainSocket.Views.Map;
import com.brainSocket.Views.SearchAutoComplete;
import com.brainSocket.adapters.DrawerAdapter;
import com.brainSocket.adapters.SuggestionsAdapter;
import com.brainSocket.models.User;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;


public class MainMap extends ActionBarActivity implements OnMarkerClickListener{

	// Google Map
    private Map map;
    private DrawerLayout drawer ;
    private ActionBarDrawerToggle drawerToggle ;
    private ListView drawerList ;
    public ArrayAdapter<String> autoCompleteAdapter ;
    private SearchAutoComplete autoComplete ;
    private GetUsersAroundMeListner getUsersCallback ; 
    public List<String> suggestions ;
    ArrayList<User> users ;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        try {
        	users = new ArrayList<User>();
            initilizeMap();
            initDrawer() ;
            initData();
                    
            //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            //getSupportActionBar().setHomeButtonEnabled(true);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        generateData();
        map.refreshMap(users);
        
        initAutocomplete();
 
    }
 
    public void refreshUsersAroundMe(List<User> users)
	{
		//not implemented
	}
    
    private void initData(){
    	getUsersCallback = new GetUsersAroundMeListner(this);
    	
    }
    
	private void initAutocomplete(){
		Object ob  = findViewById(R.id.autocomplete) ;
		autoComplete = (SearchAutoComplete) ob ;
        
        autoComplete.addTextChangedListener(new AutoCompleteListener(this));
        suggestions = new ArrayList<String>();
        
        autoCompleteAdapter = new SuggestionsAdapter(this, android.R.layout.simple_dropdown_item_1line, suggestions);
        autoComplete.setAdapter(autoCompleteAdapter);
 
	}
	
    private void initilizeMap() {
        if (map == null) {
            GoogleMap gMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
            map = new Map(this,gMap);
            map.setMyLocationEnabled(true);
            

            new Handler().postDelayed(new Runnable() {
        		@Override
        		public void run() {
        			//KedniApp.dataSrc.serverHandler.getUsersAroundMe(getUsersCallback, 0, position)
        			generateData();
        	        map.refreshMap(users);
        			map.centerMapOnMyLocation();
        		}
        	},9000);
            
            if (map == null) {
                Toast.makeText(getApplicationContext(),"Sorry! unable to create maps", Toast.LENGTH_SHORT).show();
            }
        }
    }
    
    private void initDrawer (){
    	
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);            
        drawerToggle = new ActionBarDrawerToggle(this, drawer, R.drawable.ic_drawer, R.string.drawer_open , R.string.drawer_close);
        drawer.setDrawerListener(drawerToggle);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new DrawerAdapter(MainMap.this,drawerList));
    }
 
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main_map, menu);
		return true;
	}
	
    
    
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
		
	    switch (item.getItemId()) {
	    case R.id.action_pwr:
	      break;
	    case R.id.action_ststus:
	    	map.centerMapOnMyLocation();
	    	break;
	    	
	    }
	    return true ;
	}
	
	
	
	void generateData() {
		
		Location loc = map.getCurrentlocation();
		if(loc != null){
			users.clear();
			for (int i = 0 ; i < 5 ; i++){
				LatLng lnglt = new LatLng(loc.getLatitude()+(i*0.0005), loc.getLongitude()+(i*0.0005));
				User tmp = new User(i, "user"+i, lnglt, 1);
				users.add(tmp);
			}
		}
	}



	@Override
	public boolean onMarkerClick(Marker marker) {
		int index = Integer.valueOf(marker.getSnippet());
		User usr = users.get(index);
		RequestDiag diag = new RequestDiag(this, usr);
		diag.show();

		return false;
	}
	
	
	


}
