package com.brainSocket.Views;

import java.util.List;

import android.location.Location;
import android.widget.Toast;

import com.brainSocket.khednima3ak.MainMap;
import com.brainSocket.models.User;
import com.brainSocket.adapters.MapInfoWindowAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class Map  {

	GoogleMap map ;
	MainMap activity ; 
	Location currentlocation;
	
	
	
	public Map (MainMap activity, GoogleMap m){
		map  = m ;
		this.activity = activity ;
		init(); 
		 map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

	           @Override
	           public void onMyLocationChange(Location arg0) {
	            // TODO Auto-generated method stub
	        	currentlocation = arg0;   
	            //Toast.makeText(Map.this.activity, "my location is" + arg0.getLongitude() + " , "+arg0.getLatitude(), Toast.LENGTH_LONG).show();
	           }
	     });
		 
	}
	
	public void setMyLocationEnabled(boolean val){
		map.setMyLocationEnabled(val);
	}
	
	
	public void centerMapOnMyLocation() {
		
	    //currentlocation = map.getMyLocation();

	    if (currentlocation != null) {
	        CameraPosition cameraPosition = new CameraPosition.Builder()
            .target(new LatLng(currentlocation.getLatitude(), currentlocation.getLongitude())).zoom(17).tilt(50).build(); 
		    map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		    //Toast.makeText(activity, "lat: "+currentlocation.getLatitude()+" long: "+currentlocation.getLongitude(), Toast.LENGTH_LONG);
	    }

	}
	
	
	public void refreshMap(List<User> users){
		
		for(int i = 0 ; i<users.size() ; i++){
			User usr = users.get(i); 
			Marker marker = map.addMarker(usr.getMarkerOptions());
			marker.setSnippet( String.valueOf( usr.getId() ) );
			
			marker.showInfoWindow();
		}			
	}
	
	public Location  getCurrentlocation (){
		return currentlocation;
	}
	
	public void addMarker (){
		
		    
	}
	
	void init (){
		
		map.setOnMarkerClickListener(activity);
		//map.setInfoWindowAdapter( new MapInfoWindowAdapter(activity));
	}
		
}