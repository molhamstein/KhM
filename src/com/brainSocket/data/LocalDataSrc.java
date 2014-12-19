package com.brainSocket.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.brainSocket.enums.UserEventType;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.models.UserEvent;


public class LocalDataSrc {
	
	DBHelper helper ;
	SQLiteDatabase db ;
	
	
	public LocalDataSrc( Context context) {
		helper = new DBHelper(context);
		db = helper.getWritableDatabase();
	}
	
	public void insertArea( int serverAreaId, String title , int parentid  ){
		
		ContentValues val = new ContentValues();
		val.put(DBHelper.AREA_NAME, title);
		val.put(DBHelper.AREA_ID, serverAreaId);
		val.put(DBHelper.AREA_PARENT, parentid);
		
		long temp = db.insert(DBHelper.TABLE_AREA, null, val);
	}
	
	public List<String> getAreas(String searchTerm){
		
		List<String> vals = new ArrayList<String>();
		String q ;
		if (searchTerm != null){
			 q = "SELECT * FROM "+DBHelper.TABLE_AREA + " WHERE "+DBHelper.AREA_NAME + " LIKE '%" +searchTerm+"%'" ;
		}else{
			 q = "SELECT * FROM "+DBHelper.TABLE_AREA +" WHERE "+DBHelper.AREA_ID +" != 33 " + ";" ;
		}
		Cursor cur = db.rawQuery(q, null);
		cur.moveToFirst();
		while(!cur.isAfterLast()){
			String tmp = cur.getString(cur.getColumnIndex(DBHelper.AREA_NAME));
			vals.add(tmp);
			cur.moveToNext();
		}
		return vals ;
	}
	
	
	public List<String> getAllDsitenctAreas(){
		
		List<String> vals = new ArrayList<String>();
		String q ;

		q = "SELECT MAX(" + DBHelper.AREA_NAME + ") FROM " + DBHelper.TABLE_AREA + " GROUP BY " + DBHelper.AREA_ID ;
		//q = "SELECT * FROM "+DBHelper.TABLE_AREA + " WHERE "+DBHelper.AREA_NAME + " LIKE '%" +searchTerm+"%'" ; 

		Cursor cur = db.rawQuery(q, null);
		cur.moveToFirst();
		while(!cur.isAfterLast()){
			String tmp = cur.getString(0);
			vals.add(tmp);
			cur.moveToNext();
		}
		return vals ;
	}
	
	
	
	public int getAreaID(String areaName){
		
		String  q = "SELECT " + DBHelper.AREA_ID + " FROM "+DBHelper.TABLE_AREA + " WHERE "+DBHelper.AREA_NAME + " = '" +areaName+"'" ;

		Cursor cur = db.rawQuery(q, null);
		cur.moveToFirst();
		int id = 0 ;
		if(!cur.isAfterLast()){
			id = cur.getInt(cur.getColumnIndex(DBHelper.AREA_ID));
		}
		return id ;
	}
	
	
	public String getAreaByID(int id){
		
		String  q = "SELECT " + DBHelper.AREA_NAME + " FROM "+DBHelper.TABLE_AREA + " WHERE "+DBHelper.AREA_ID + " = " +id+" ;" ;

		Cursor cur = db.rawQuery(q, null);
		cur.moveToFirst();
		String name = " " ;
		if(!cur.isAfterLast()){
			name = cur.getString(cur.getColumnIndex(DBHelper.AREA_NAME));
		}
		return name;
	}


	
	public void insertUserEvent(UserEvent event){
	
		if(event.getTitle() != null){
			
		}
		int isActive = (event.isEventActive())? 1 : 0 ; 
		insertUserEvent(
				event.getTitle(),
				event.getDescription(),
				event.getType(),
				event.getDate(),
				event.getPartnerId(),
				event.getPartnerName(),
				event.getGlobalId(),
				isActive,
				event.getPartnerFBId()
				
				);
	}

	public void insertUserEvent(String title ,String desc , UserEventType type , Date date , int partnerID , String partnerName , int globalId , int isActive , String FBID){
		
		ContentValues val = new ContentValues();
		val.put(DBHelper.EVENT_TITLE, title);
		val.put(DBHelper.EVENT_DESC, desc);
		val.put(DBHelper.EVENT_PARTNER_ID, partnerID); 
		val.put(DBHelper.EVENT_PARTNER_NAME, partnerName); 
		val.put(DBHelper.EVENT_TYPE, type.name());
		val.put(DBHelper.EVENT_GLOBAL_ID, globalId);
		val.put(DBHelper.EVENT_date, UserEvent.getFormatedDate(date));
		val.put(DBHelper.EVENT_IS_ACTIVE, isActive);
		val.put(DBHelper.EVENT_PARTNER_FB_ID, FBID);
		
		long temp = db.insert(DBHelper.TABLE_EVENT, null, val);
	}
	
	public List<UserEvent> getUserEvents(){
		
		List<UserEvent> vals = new ArrayList<UserEvent>();
		String q = "SELECT * FROM "+DBHelper.TABLE_EVENT + " ;" ;
		Cursor cur = db.rawQuery(q, null);
		
		vals = UserEvent.getListFromCursor(cur);
		return vals ;
	}
	
	
	public List<UserEvent> getNoneMessagingUserEvents(){
		
		List<UserEvent> vals = new ArrayList<UserEvent>();
		String q = "SELECT * FROM "+DBHelper.TABLE_EVENT + " WHERE "+DBHelper.EVENT_TYPE +" != '"+UserEventType.MESSAGE_REC.name() +"' ;" ;
		Cursor cur = db.rawQuery(q, null);
		
		vals = UserEvent.getListFromCursor(cur);
		return vals ;
	}

	public void setEventActiveByGlobalId(int globalId , int active){
		ContentValues v = new ContentValues() ;
		v.put(DBHelper.EVENT_IS_ACTIVE, active);
		
		long temp = db.update(DBHelper.TABLE_EVENT, v, DBHelper.EVENT_GLOBAL_ID + " = " + globalId, null);
	}
	
	
	/*
	public void insertProd(Prod prod ){
		
		ContentValues val = Prod.toContentValues(prod);
		long temp = db.insert(DBHelper.TABLE_PROD, null, val);
	}
	
	public void addProdToWish(Prod prod ){
		Wish wish = new Wish();
		int tmp = prod.getId() ;
		wish.setProdId(tmp);
		insertProd(prod);
		ContentValues val = Wish.toContentValues(wish);
		long temp = db.insert(DBHelper.TABLE_WISHLIST, null, val);
	}
	
	public void getWishs (Notifiable caller){
		
		String Query = "select * from " + DBHelper.TABLE_PROD+ " , "+DBHelper.TABLE_WISHLIST+" WHERE "+ DBHelper.TABLE_PROD+"._id = "+ DBHelper.TABLE_WISHLIST+"."+DBHelper.WISH_PID +";" ;
		Cursor cur = db.rawQuery(Query,null);
		cur.moveToFirst();
		caller.onCursorReady(cur);
		
	}
	
	
	public void tempEraser(){
		db.execSQL("DROP TABLE "+DBHelper.TABLE_WISHLIST+";");
		db.execSQL("CREATE TABLE "+DBHelper.TABLE_WISHLIST+" (_id integer primary key , "+DBHelper.WISH_PID+" integer not null );");
		
	}

	public void removeFromById (String tableName , int recordId){
		db.execSQL("delete from "+tableName+" where "+DBHelper.ID+" = '"+recordId+"' ;");

	}
	
*/
}
