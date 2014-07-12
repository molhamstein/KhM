package com.brainSocket.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.brainSocket.models.UserEvent;
import com.brainSocket.models.UserEventType;


public class LocalDataSrc {
	
	DBHelper helper ;
	SQLiteDatabase db ;
	
	
	public LocalDataSrc( Context context) {
		helper = new DBHelper(context);
		db = helper.getWritableDatabase();
	}
	
	public void insertArea(int id , String title ){
		
		ContentValues val = new ContentValues();
		val.put(DBHelper.ID, id);
		val.put(DBHelper.AREA_NAME, title);
		
		long temp = db.insert(DBHelper.TABLE_AREA, null, val);
	}
	
	public List<String> getAreas(String searchTerm){
		
		List<String> vals = new ArrayList<String>();
		String q = "SELECT * FROM "+DBHelper.TABLE_AREA + " WHERE "+DBHelper.AREA_NAME + " LIKE '%" +searchTerm+"%'" ;
		Cursor cur = db.rawQuery(q, null);
		cur.moveToFirst();
		while(!cur.isAfterLast()){
			String tmp = cur.getString(1);
			vals.add(tmp);
			cur.moveToNext();
		}
		return vals ;
	}


	
public void insertUserEvent(String title ,String desc , UserEventType type , Date date ){
		
		ContentValues val = new ContentValues();
		val.put(DBHelper.EVENT_TITLE, title);
		val.put(DBHelper.EVENT_DESC, desc);
		val.put(DBHelper.EVENT_PARTNER_ID, 20); ///// review
		val.put(DBHelper.EVENT_TYPE, type.name());
		val.put(DBHelper.EVENT_date, UserEvent.getFormatedDate(date));
		
		
		long temp = db.insert(DBHelper.TABLE_EVENT, null, val);
	}
	
	public List<UserEvent> getUserEvents(){
		
		List<UserEvent> vals = new ArrayList<UserEvent>();
		String q = "SELECT * FROM "+DBHelper.TABLE_EVENT + " ;" ;
		Cursor cur = db.rawQuery(q, null);
		
		vals = UserEvent.getListFromCursor(cur);
		return vals ;
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
