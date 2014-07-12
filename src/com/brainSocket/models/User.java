package com.brainSocket.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.TextView;

import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.khednima3ak.R;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class User {
	
	UserType type;
	long id ;
	String name ;
	LatLng location ;
	String destination ;
	private float price;
	private String facebookID;
	private float rate;
	boolean isBlockd ;
	
	//temp constructor
	public User(int id , String name , LatLng loc , int destId){
		this.id = id ;
		this.name = name;
		this.location = loc ;
		this.destination = String.valueOf( destId );
	}
	
	public User(int id ,String facebookID, String name , LatLng loc ,float price,UserType userType,float rate)
	{
		this.id = id ;
		this.name = name;
		this.location = loc ;
		
		this.price=price;
		this.facebookID=facebookID;
		this.type=userType;
		this.rate=rate;
	}
	
	public float getPrice()
	{
		return this.price;
	}
	public float getRate()
	{
		return this.rate;
	}
	public String getPictureLink()
	{
		String pictureLink="http://graph.facebook.com/"+this.facebookID+"/picture?type=square";
		return pictureLink; 
	}
	
	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LatLng getLocation() {
		return location;
	}

	public void setLocation(LatLng location) {
		this.location = location;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	public boolean getisBlockd() {
		return isBlockd;
	}

	public void setIsBlockd(boolean isBlkd) {
		this.isBlockd = isBlkd;
	}

	public MarkerOptions getMarkerOptions(){
		
		View markerview = KedniApp.inflater.inflate(R.layout.map_marker, null);
		TextView name =  (TextView) markerview.findViewById(R.id.name);
		name.setText(this.name);
		
		markerview.setDrawingCacheEnabled(true);
		markerview.buildDrawingCache();
		Bitmap bm = createClusterBitmap();
		
		MarkerOptions markerOptins = new MarkerOptions().position(location).icon( BitmapDescriptorFactory.fromBitmap(bm)).title(this.name);
		
		//Icon from resources
		//MarkerOptions markerOptins = new MarkerOptions().position(location).icon( BitmapDescriptorFactory.fromResource(R.drawable.car)).title(name);
		return markerOptins ;
	}
	
	
	private Bitmap createClusterBitmap() {
		
	    View cluster = KedniApp.inflater.inflate(R.layout.map_marker, null);
	    TextView name =  (TextView) cluster.findViewById(R.id.name);
		name.setText(this.name);
		
	    cluster.measure( MeasureSpec.makeMeasureSpec(400, MeasureSpec.AT_MOST) ,  MeasureSpec.makeMeasureSpec(400, MeasureSpec.AT_MOST));
	    cluster.layout(0, 0, cluster.getMeasuredWidth(),cluster.getMeasuredHeight());

	    final Bitmap clusterBitmap = Bitmap.createBitmap(cluster.getMeasuredWidth(), cluster.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

	    Canvas canvas = new Canvas(clusterBitmap);
	    cluster.draw(canvas);

	    return clusterBitmap;
	}
	
	
	
}
