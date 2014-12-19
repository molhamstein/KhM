package com.brainSocket.Views;

import java.util.List;

import android.content.Context;
import android.graphics.PointF;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.khednima3ak.MainMap;
import com.brainSocket.models.ScheduledTrip;
import com.brainSocket.models.User;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class Map  implements LocationListener{

	GoogleMap map ;
	MainMap activity ;
	Location currentlocation;
	LocationManager locationManager ;
	
	private Marker scheduledmarker ;
	
	public static String SCHEDULED_TRIP_MARKER_SNIPPET = "-4" ;

	
	
	public Map (MainMap activity, GoogleMap m){
		map  = m ;
		this.activity = activity ;
		init(); 
		
		locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
		
		map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

	           @Override
	           public void onMyLocationChange(Location arg0) {
	        	setLocation(arg0) ;
	            //Toast.makeText(Map.this.activity, "my location is" + arg0.getLongitude() + " , "+arg0.getLatitude(), Toast.LENGTH_LONG).show();
	           }
	    });          
		

		Location oldLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		
		if(oldLocation == null )
			oldLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		if(oldLocation!= null)
			setLocation(oldLocation);
			
		 
	}
	
	private void setLocation (Location arg0){
		currentlocation = arg0;
    	KedniApp.setCurrentloc( new PointF( (float) arg0.getLatitude(),(float) arg0.getLongitude()));
	}
	
	public void setMyLocationEnabled(boolean val){
		map.setMyLocationEnabled(val);
	}
	
	public void EnableUserClick (boolean enabel){
		if(enabel){
			map.setOnMapClickListener(new OnMapClickListener() {
				@Override
				public void onMapClick(LatLng point) {
					//drawScheduledTripMarker(trip);
					activity.enterShedulingModePhase2(point) ;
				}
			});
		}else{
			map.setOnMapClickListener(null);
		}
	}
	
	
	public void centerMapOnMyLocation() {
		
	    //currentlocation = map.getMyLocation();

	    if (currentlocation != null) {
	        CameraPosition cameraPosition = new CameraPosition.Builder()
            .target(new LatLng(currentlocation.getLatitude(), currentlocation.getLongitude())).zoom(15).tilt(50).build(); 
		    map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		    //Toast.makeText(activity, "lat: "+currentlocation.getLatitude()+" long: "+currentlocation.getLongitude(), Toast.LENGTH_LONG);
	    }

	}
	
	
	public void refreshMap(List<User> users){
		map.clear() ;
		for(int i = 0 ; i<users.size() ; i++){
			User usr = users.get(i); 
			Marker marker = map.addMarker(usr.getMarkerOptions());
			marker.setSnippet( String.valueOf( i ) );
			//marker.showInfoWindow();
		}
		
	}
	
	public void drawScheduledTripMarker( ScheduledTrip trip ){
		if(trip != null){
			if(scheduledmarker != null)
				scheduledmarker.remove() ;
			scheduledmarker = map.addMarker(trip.getMarkerOptions());
			scheduledmarker.setSnippet( SCHEDULED_TRIP_MARKER_SNIPPET );
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

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		currentlocation = location;
    	//// new to check the prerfomance loss of the casting
    	KedniApp.setCurrentloc( new PointF( (float) location.getLatitude(),(float) location.getLongitude()));
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
		
}