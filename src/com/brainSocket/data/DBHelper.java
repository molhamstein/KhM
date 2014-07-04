package com.brainSocket.data;

import java.sql.Date;

import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.modules.UserEvent;
import com.brainSocket.modules.UserEventType;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;

public class DBHelper extends SQLiteOpenHelper {

	private static final int ver = 1 ;
	
	
	public static final String DB_NAME = "TakeME"; 
	
	public static final String TABLE_AREA = "area";
	public static final String TABLE_TRIP = "trips";
	public static final String TABLE_UESRS = "user";
	public static final String TABLE_EVENT = "event";

	
	public static final String ID = "_id";
	
	
	public static final String AREA_NAME = "areaname" ; 
	
	
	public static final String USER_FB_ID = "facebookid";
	public static final String USER_NAME = "name";
	
	
	public static final String TRIP_USER_ID = "ID";
	public static final String TRIP_DEST = "destination";
	public static final String TRIP_COMPANIAN_ID  = "companian";
	public static final String TRIP_DATE = "date";
	public static final String TRIP_COST = "cost";
	public static final String TRIP_RATE = "RATE";
	
	
	
	public static final String EVENT_TITLE = "eventtitle" ;
	public static final String EVENT_DESC = "eventdesc";
	public static final String EVENT_GLOBAL_ID = "eventglobalid";
	public static final String EVENT_PARTNER_ID = "eventpartnerid";
	public static final String EVENT_TYPE = "eventtype";
	public static final String EVENT_date = "eventdate";
	public static final String EVENT_DATA1 = "eventdata1";
	public static final String EVENT_DATA2 = "eventdata2";

	
	
	

	public DBHelper ( Context context){
		super(context, "malle", null, ver);
		
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		//db.execSQL("CREATE TABLE phd (_id integer primary key autoincrement , name text not null)"); FOREIGN KEY(trackartist) REFERENCES artist(artistid)
		String q1 = "CREATE TABLE "+TABLE_AREA+" ("+ID+" integer primary key not null , "+AREA_NAME+" text not null  );" ;
		String q2 ="CREATE TABLE "+TABLE_UESRS+" ("+ID+" integer primary key  , "+USER_FB_ID+" integer not null , "+USER_NAME+" text not null );" ;
		String q3 ="CREATE TABLE "+TABLE_TRIP+" ("+ID+" integer primary key Autoincrement , "+TRIP_DEST+" integer , "+TRIP_COMPANIAN_ID+" integer not null , "+TRIP_DATE+" DATETIME not null , "+TRIP_RATE+" integer , "+TRIP_COST+" integer not null , FOREIGN KEY("+TRIP_COMPANIAN_ID+") REFERENCES "+TABLE_UESRS+"("+ID+"));" ;
		String q4 ="CREATE TABLE "+TABLE_EVENT+" ("+ID+" integer primary key Autoincrement , "+EVENT_GLOBAL_ID+" integer , "+EVENT_TITLE+" text not null , "+EVENT_DESC+" text not null , "+EVENT_PARTNER_ID+" integer , "+EVENT_date+" DATETIME not null , "+EVENT_TYPE+" text not null , "+EVENT_DATA1+" integer , "+EVENT_DATA2+" integer );" ;
		
		
		db.execSQL(q1);
		db.execSQL(q2);
		db.execSQL(q3);
		db.execSQL(q4);

		
        new Handler().postDelayed(new Runnable() {
    		@Override
    		public void run() {
    			KedniApp.dataSrc.localHandler.insertArea(1, "arnnos");
    			KedniApp.dataSrc.localHandler.insertArea(2, "akraba");
    			KedniApp.dataSrc.localHandler.insertArea(3, "salehieh");
    			KedniApp.dataSrc.localHandler.insertArea(4, "jessr abiad");
    			KedniApp.dataSrc.localHandler.insertArea(5, "Jaramana");
    			
    			KedniApp.dataSrc.localHandler.insertUserEvent("RideReq", "abdo is reqesting ride", UserEventType.RIDE_REQ_REC, UserEvent.getFormatedDate("2014-06-24 11:34:20"));
    			KedniApp.dataSrc.localHandler.insertUserEvent("RideReq", "fathy is reqesting ride", UserEventType.RIDE_REQ_REC, UserEvent.getFormatedDate("2014-06-25 10:10:25"));
    		}
    	},5000);
        

        
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}


}
