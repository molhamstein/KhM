package com.brainSocket.models;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.TextView;


import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.khednima3ak.R;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ScheduledTrip {
	
	public static String timePrefix = "«·«‰ÿ·«ﬁ »⁄œ" ;
	
	private int minutesToDepart ;
	private String addressDescription ;
	private LatLng departloc ;
	private int destId ;
	private int price = -1 ;
	private long userId = -1 ;
	
	public ScheduledTrip(){
		
	}
	
	public ScheduledTrip(User usr){
		this.price = (int) usr.getPrice();
		this.addressDescription = usr.getExtraDesc();
		this.departloc = usr.getLocation() ;
		this.destId = usr.getDestId();
		this.userId = usr.getId() ;
		
	}
	
	public long getUserId() {
		return userId;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getMinutesToDepart() {
		return minutesToDepart;
	}
	public void setMinutesToDepart(int minutesToDepart) {
		this.minutesToDepart = minutesToDepart;
	}
	public int getdestId() {
		return destId;
	}
	public void setDestId(int destId) {
		this.destId = destId;
	}
	public String getAddressDescription() {
		return addressDescription;
	}
	public void setAddressDescription(String addressDescription) {
		this.addressDescription = addressDescription;
	}
	public LatLng getDepartloc() {
		return departloc;
	}
	public void setDepartloc(LatLng departloc) {
		this.departloc = departloc;
	}
	
	private String getTripTime(){
		return String.format("%02d:%02d", this.minutesToDepart/60,this.minutesToDepart%60);
	}
	
	/// used to display a marker for the trip creator only
	public MarkerOptions getMarkerOptions(){
		
		//View markerview = KedniApp.inflater.inflate(R.layout.map_marker, null);
		//TextView name =  (TextView) markerview.findViewById(R.id.name);
		//name.setText(this.getTripTime());
		
		//markerview.setDrawingCacheEnabled(true);
		//markerview.buildDrawingCache();
		Bitmap bm = createClusterBitmap();
		
		MarkerOptions markerOptins = new MarkerOptions().position(this.departloc).icon( BitmapDescriptorFactory.fromBitmap(bm));
		return markerOptins ;
	}
	
	
	private Bitmap createClusterBitmap() {
		
	    View cluster = KedniApp.inflater.inflate(R.layout.map_marker_minimal, null);
	    TextView name =  (TextView) cluster.findViewById(R.id.name);
		name.setText( getTripTime() );
		
		TextView dest =  (TextView) cluster.findViewById(R.id.destenation);
		TextView price =  (TextView) cluster.findViewById(R.id.price_req);
		
		String destination  = KedniApp.dataSrc.localHandler.getAreaByID( getdestId() ) ;
		dest.setText(destination) ;
		
		if(getPrice() <0){
			price.setVisibility(View.GONE);
		}else
			price.setText(   getPrice()+ " " + KedniApp.UNIT );
			
		ImageView markerIcon = (ImageView) cluster.findViewById(R.id.marker);
		markerIcon.setImageResource(R.drawable.trip);
		
	    cluster.measure( MeasureSpec.makeMeasureSpec(User.markerWidth, MeasureSpec.AT_MOST) ,  MeasureSpec.makeMeasureSpec(User.markerHeight, MeasureSpec.AT_MOST));
	    cluster.layout(0, 0, cluster.getMeasuredWidth(),cluster.getMeasuredHeight());

	    final Bitmap clusterBitmap = Bitmap.createBitmap(cluster.getMeasuredWidth(), cluster.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

	    Canvas canvas = new Canvas(clusterBitmap);
	    cluster.draw(canvas);

	    return clusterBitmap;
	}

}
