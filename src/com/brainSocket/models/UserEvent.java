package com.brainSocket.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.database.Cursor;

import com.brainSocket.data.DBHelper;
import com.brainSocket.enums.UserEventType;
import com.google.android.gms.internal.da;

public class UserEvent {

	//keys
	public static final String TITLE_RIDE_REQ = "RideReq" ;
	
	//need to handle float price
	
	// extra1 : value 
	//extra2 : price 
	private String title;
	private String description ;
	private int id ;
	private int globalId ;
	private int partnerId ;
	private String partnerFBId ;
	private String partnerName ;
	private UserEventType type ;
	private Date date ; 
	private int extraData1 ;
	private int extraData2 ;
	private boolean isEventActive ;
	
	public boolean isEventActive() {
		return isEventActive;
	}


	public void setEventActive(boolean isEventActive) {
		this.isEventActive = isEventActive;
	}
	
	public void setEventActive(int isEventActive) {
		if( isEventActive !=0 ){
			this.isEventActive = true;
			return ;
		}
		this.isEventActive = false;
	}
	
	
	public String getPartnerFBId() {
		return partnerFBId;
	}


	public void setPartnerFBId(String partnerFBId) {
		this.partnerFBId = partnerFBId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getGlobalId() {
		return globalId;
	}


	public void setGlobalId(int globalId) {
		this.globalId = globalId;
	}


	public int getPartnerId() {
		return partnerId;
	}


	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}


	public UserEventType getType() {
		return type;
	}


	public void setType(UserEventType type) {
		this.type = type;
	}


	public int getExtraData1() {
		return extraData1;
	}


	public void setExtraData1(int extraData1) {
		this.extraData1 = extraData1;
	}


	public int getExtraData2() {
		return extraData2;
	}


	public void setExtraData2(int extraData2) {
		this.extraData2 = extraData2;
	}
	public String getPartnerName() {
		return partnerName;
	}


	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	// need to handle time deference between server and the phone
	public void setDate(Date date) {
		Date dateNow = Calendar.getInstance().getTime() ;
		long diff = dateNow.getTime() - date.getTime() ;
		this.date = date;
		if(diff >= 0){
			if(diff < 849505L) // approx 3 minutes
				isEventActive = true ;
			return ;
		}
		isEventActive = false ;
		
	}


	
	public static String DATE_FORMAT_FULL =  "yyyy-MM-dd HH:mm:ss" ;
	
	
	public static String getFormatedDate(Date date){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(UserEvent.DATE_FORMAT_FULL, Locale.getDefault());
		String tmp =dateFormat.format(date) ;
        return tmp;
	}
	
	
	public static Date getFormatedDate(String date){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat(UserEvent.DATE_FORMAT_FULL, Locale.getDefault());
		Date tmp = new Date();
		dateFormat.format(tmp);
		try {
			tmp = dateFormat.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
        return tmp;
	}


	public Date getDate() {
		return date;
	}
	
	
	public static List<UserEvent> getListFromCursor (Cursor cursor){
		
		List<UserEvent> events = new ArrayList<UserEvent>();
		UserEvent event ;
		cursor.moveToFirst();
		
		while (!cursor.isAfterLast()){
			event = new UserEvent();
			String evTitle = cursor.getString(cursor.getColumnIndex(DBHelper.EVENT_TITLE)) ;
			event.setTitle(evTitle);
			event.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.ID)));
			event.setGlobalId(cursor.getInt(cursor.getColumnIndex(DBHelper.EVENT_GLOBAL_ID)));
			event.setPartnerId(cursor.getInt(cursor.getColumnIndex(DBHelper.EVENT_PARTNER_ID)));
			event.setDescription(cursor.getString(cursor.getColumnIndex(DBHelper.EVENT_DESC)));
			event.setExtraData1(cursor.getInt(cursor.getColumnIndex(DBHelper.EVENT_DATA1)));
			event.setExtraData2(cursor.getInt(cursor.getColumnIndex(DBHelper.EVENT_DATA2)));
			event.setPartnerFBId(cursor.getString(cursor.getColumnIndex(DBHelper.EVENT_PARTNER_FB_ID)));
			String sDate = cursor.getString(cursor.getColumnIndex(DBHelper.EVENT_date)) ;
			Date date =  UserEvent.getFormatedDate(sDate);
			event.setDate(date);
			String typeS = cursor.getString(cursor.getColumnIndex(DBHelper.EVENT_TYPE));
			UserEventType type = UserEventType.valueOf(typeS); 
			event.setType(type);
			int isActive = cursor.getInt(cursor.getColumnIndex(DBHelper.EVENT_IS_ACTIVE)) ;
			if (isActive == 0 )///////////// take "EVENT_IS_ACTIVE" in consideratin only if its false else let the dessision for the date 
				event.setEventActive(false);
			events.add(event);
			cursor.moveToNext();
		}
		
		return events ;
	}
	
	
}
