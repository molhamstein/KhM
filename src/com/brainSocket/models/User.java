package com.brainSocket.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.TextView;

import com.brainSocket.enums.UserType;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.khednima3ak.R;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class User {
	
	public static int markerWidth ; 
	public static int markerHeight ; 
	
	
	UserType type;
	long id ;
	String name ;
	LatLng location ;
	String destination ;
	private int destId ;
	private float price;
	private String facebookID;
	private float rate;
	private boolean isBlocked;
	private int gender;
	private String  phone ;
	private int requestsCount ; // indicates if this user was already requested 
	
	// used only in scheduled trips
	private int minutesLeftToDepart = -1 ;
	private String extraDesc = null ; 
	
	public int getMinutesLeftToDepart() {
		return minutesLeftToDepart;
	}

	public String getExtraDesc() {
		return extraDesc;
	}

	//temp constructor
	public User(int id , String name , LatLng loc , int destId){
		this.id = id ;
		this.name = name;
		this.location = loc ;
		this.destination = String.valueOf( destId );
	}
	
	public User(int id ,String facebookID, String name , LatLng loc ,float price,UserType userType,float rate , int isMale , int destID , String phone , int minutes2Depart , String desc, int previusRequestsCount )
	{
		this.id = id ;
		this.name = name;
		this.location = loc ;
		
		this.price=price;
		this.facebookID=facebookID;
		this.type=userType;
		this.rate=rate;
		this.gender  =isMale ;
		this.destId = destID ;
		this.phone = phone ;
		this.minutesLeftToDepart = minutes2Depart ;
		this.extraDesc  = desc ;
		this.requestsCount = previusRequestsCount ;
	}
	public User(int id,String name,boolean isBlocked)
	{
		this.id=id;
		this.name=name;
		this.isBlocked=isBlocked;
	}
	
	
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getDestId() {
		return destId;
	}

	public void setDestId(int destId) {
		this.destId = destId;
	}

	public int isMale() {
		return gender;
	}

	public void setMale(int isMale) {
		this.gender = isMale;
	}

	public boolean getIsBlocked()
	{
		return this.isBlocked;
	}
	public float getPrice()
	{
		return this.price;
	}
	public float getRate()
	{
		return this.rate;
	}
	//need to check the effects of using HTTPS on the traffic 
	public String getPictureLink()
	{
		String pictureLink="https://graph.facebook.com/"+this.facebookID+"/picture?type=normal";
		return pictureLink; 
	}
	public static String getPictureLink(String FBID){
		String pictureLink="https://graph.facebook.com/"+FBID+"/picture?type=normal";
		return pictureLink;
	}
	
	public static String getNameById(int id){
		String pictureLink="http://graph.facebook.com/"+id+"/picture?fields=name";
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
	
	public boolean getisBlockd() {
		return isBlocked;
	}

	public void setIsBlockd(boolean isBlkd) {
		this.isBlocked = isBlkd;
	}
	
	public String getFacebookID() {
		return facebookID;
	}

	public void setFacebookID(String facebookID) {
		this.facebookID = facebookID;
	}

	public MarkerOptions getMarkerOptions(){
		
		View markerview = KedniApp.inflater.inflate(R.layout.map_marker, null);
		TextView name =  (TextView) markerview.findViewById(R.id.name);
		name.setText(this.name);
		
		markerview.setDrawingCacheEnabled(true);
		markerview.buildDrawingCache();
		Bitmap bm = createClusterBitmap();
		
		MarkerOptions markerOptins = new MarkerOptions().position(location).icon( BitmapDescriptorFactory.fromBitmap(bm));//.title(this.name);
		
		//Icon from resources
		//MarkerOptions markerOptins = new MarkerOptions().position(location).icon( BitmapDescriptorFactory.fromResource(R.drawable.car)).title(name);
		return markerOptins ;
	}
	
	
	private Bitmap createClusterBitmap() {
		
	    View cluster = KedniApp.inflater.inflate(R.layout.map_marker_minimal, null);
	    
	    TextView name =  (TextView) cluster.findViewById(R.id.name);
		name.setText(this.name);
		
		TextView dest =  (TextView) cluster.findViewById(R.id.destenation);
		TextView price =  (TextView) cluster.findViewById(R.id.price_req);
		
		String destination  = "Çáì "  +  KedniApp.dataSrc.localHandler.getAreaByID( getDestId() ) ;
		dest.setText(destination) ;
		
		// we dont show price if both users are Passengers or Drivers
		if( KedniApp.getCurrentStatus() ==  type ){
			price.setVisibility(View.GONE);
		}else{
			price.setText(  (int) this.price + " " + KedniApp.UNIT );
		}
		
		 
		if(type == UserType.PASSENGER){
			ImageView markerIcon = (ImageView) cluster.findViewById(R.id.marker);
			if(  isMale() == 1){
				markerIcon.setImageResource(R.drawable.man);
			}else
				markerIcon.setImageResource(R.drawable.girl);
		}else if(type == UserType.DRIVER && isMale() == 0){
			ImageView markerIcon = (ImageView) cluster.findViewById(R.id.marker);
			markerIcon.setImageResource(R.drawable.girlcar);
		}
		
		if(type == UserType.DRIVER && minutesLeftToDepart > 0){
			ImageView markerIcon = (ImageView) cluster.findViewById(R.id.marker);
			markerIcon.setImageResource(R.drawable.trip);
		}
		
	    cluster.measure( MeasureSpec.makeMeasureSpec(markerWidth, MeasureSpec.AT_MOST) ,  MeasureSpec.makeMeasureSpec(markerHeight, MeasureSpec.AT_MOST));
	    cluster.layout(0, 0, cluster.getMeasuredWidth(),cluster.getMeasuredHeight());

	    final Bitmap clusterBitmap = Bitmap.createBitmap(cluster.getMeasuredWidth(), cluster.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

	    Canvas canvas = new Canvas(clusterBitmap);
	    cluster.draw(canvas);

	    return clusterBitmap;
	}
	
	
	
}
