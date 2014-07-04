package com.brainSocket.modules;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.database.Cursor;

import com.brainSocket.data.DBHelper;

public class UserEvent {

	
	private String title;
	private String description ;
	private int id ;
	private int globalId ;
	private int partnerId ;
	private UserEventType type ;
	private Date date ;
	private int extraData1 ;
	private int extraData2 ;
	
	
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


	public void setDate(Date date) {
		this.date = date;
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
			event.setGlobalId(cursor.getInt(cursor.getColumnIndex(DBHelper.EVENT_PARTNER_ID)));
			event.setDescription(cursor.getString(cursor.getColumnIndex(DBHelper.EVENT_DESC)));
			event.setExtraData1(cursor.getInt(cursor.getColumnIndex(DBHelper.EVENT_DATA1)));
			event.setExtraData2(cursor.getInt(cursor.getColumnIndex(DBHelper.EVENT_DATA2)));
			String sDate = cursor.getString(cursor.getColumnIndex(DBHelper.EVENT_date)) ;
			Date date =  UserEvent.getFormatedDate(sDate);
			event.setDate(date);
			String typeS = cursor.getString(cursor.getColumnIndex(DBHelper.EVENT_TYPE));
			UserEventType type = UserEventType.valueOf(typeS); 
			event.setType(type);
			events.add(event);
			cursor.moveToNext();
		}
		
		return events ;
	}
	
	
}
