package com.brainSocket.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final int ver = 1 ;
	private static final String ENCODING_SETTING = "PRAGMA encoding ='windows-1256'";
	
	public static final String DB_NAME = "TakeME"; 
	
	public static final String TABLE_AREA = "area";
	public static final String TABLE_TRIP = "trips";
	public static final String TABLE_UESRS = "user";
	public static final String TABLE_EVENT = "event";

	
	public static final String ID = "_id";
	
	
	public static final String AREA_NAME = "name" ; 
	public static final String AREA_ID = "id" ;
	public static final String AREA_PARENT = "parentPlaceID" ;
	
	
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
	public static final String EVENT_PARTNER_NAME = "eventpartnername";
	public static final String EVENT_TYPE = "eventtype";
	public static final String EVENT_date = "eventdate";
	public static final String EVENT_DATA1 = "eventdata1";
	public static final String EVENT_DATA2 = "eventdata2";
	public static final String EVENT_PARTNER_FB_ID = "parnerfbid";
	public static final String EVENT_IS_ACTIVE = "active" ;

	
	
	

	public DBHelper ( Context context){
		super(context, "malle", null, ver);
		
	}
	
	@Override
	public void onOpen(SQLiteDatabase db) {
		
	
		
		super.onOpen(db);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		//db.execSQL("CREATE TABLE phd (_id integer primary key autoincrement , name text not null)"); FOREIGN KEY(trackartist) REFERENCES artist(artistid)
		//String q1 = "CREATE TABLE "+TABLE_AREA+" ("+ID+" integer primary key not null , "+AREA_NAME+" text not null  );" ;
		String q2 ="CREATE TABLE "+TABLE_UESRS+" ("+ID+" integer primary key  , "+USER_FB_ID+" integer not null , "+USER_NAME+" text not null );" ;
		String q3 ="CREATE TABLE "+TABLE_TRIP+" ("+ID+" integer primary key Autoincrement , "+TRIP_DEST+" integer , "+TRIP_COMPANIAN_ID+" integer not null , "+TRIP_DATE+" DATETIME not null , "+TRIP_RATE+" integer , "+TRIP_COST+" integer not null , FOREIGN KEY("+TRIP_COMPANIAN_ID+") REFERENCES "+TABLE_UESRS+"("+ID+"));" ;
		String q4 ="CREATE TABLE "+TABLE_EVENT+" ("+ID+" integer primary key Autoincrement , "+EVENT_GLOBAL_ID+" integer , "+EVENT_TITLE+" text not null , "+EVENT_DESC+" text not null , "+EVENT_PARTNER_ID+" integer , "+EVENT_PARTNER_NAME+" text , "+EVENT_date+" DATETIME not null , "+EVENT_TYPE+" text not null , "+EVENT_DATA1+" integer , "+EVENT_IS_ACTIVE+" integer , "+EVENT_PARTNER_FB_ID+" text , "+EVENT_DATA2+" integer );" ;
		
		//
		//String q5 = "CREATE TABLE "+TABLE_AREA+" ("+ID+" integer primary key Autoincrement , "+AREA_NAME+" text not null,"+AREA_ID+" integer not null,"+AREA_PARENT+" integer "+"  );" ;
		
	    //db.execSQL(ENCODING_SETTING);
		String q5 = "CREATE TABLE "+TABLE_AREA+" ("+ID+" integer primary key Autoincrement , "+AREA_NAME+" text not null,"+AREA_ID+" integer not null,"+AREA_PARENT+" integer "+"  );" ;
		String insert = "INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(33, '����', NULL); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(34, '�����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(35, '����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(36, '����� �����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(37, '�����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(38, '����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(39, '������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(40, '��� ���', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(41, '������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(42, '����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(43, '�����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(44, '����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(45, '������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(46, '��� �����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(47, '��������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(48, '�����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(49, '����� ���', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(50, '������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(51, '�����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(52, '����� �����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(53, '��� �����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(54, '�������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(55, '�����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(56, '�������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(57, '����� ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(58, '������ ����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(59, '��������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(60, '�����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(61, '�����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(62, '������ ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(63, '������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(64, '��������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(65, '����� �����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(66, '����', 34); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(67, '�����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(68, '�������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(69, '����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(70, '�����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(71, '����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(72, '����� ���� ����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(73, '��������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(74, '�������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(75, '������ ���� ����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(76, '��� ����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(77, '��������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(78, '������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(79, '�����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(80, '��� ����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(81, '��� ������', 46); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(83, '���������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(84, '���� �������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(85, '�������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(86, '�������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(87, '���� �������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(88, '�������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(89, '��� ����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(90, '��� �����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(91, '�������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(92, '���', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(93, '�������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(94, '���� ���������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(95, '���� ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(96, '��� ����� ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(97, '��� ����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(98, '��������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(99, '�������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(100, '�����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(101, '�����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(102, '������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(103, '���� ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(104, '������ ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(105, '���� ��������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(106, '������ ��������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(107, '����� �����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(108, '���� ����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(109, '���� ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(110, '��� �������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(111, '�������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(112, '������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(113, '���� �����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(114, '���� ������� ����������� �����������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(115, '���� ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(116, '����� �����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(117, '���� ���', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(118, '���� �����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(119, '�������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(120, '���� �������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(121, '���� �����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(122, '��� ����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(123, '���� �������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(124, '����� ���', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(125, '������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(126, '��� ����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(127, '������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(128, '������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(129, '��� �����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(130, '���� �������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(131, '�������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(132, '���� �������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(133, '��� �����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(134, '��� ���� �����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(135, '��� �����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(136, '���� ��� �����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(137, '�����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(138, '���� �������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(139, '���� �������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(140, '����� �������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(141, '���� ��������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(142, '����� ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(143, '������ ��������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(144, '��� �����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(145, '���� ����� �����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(146, '����� �������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(147, '���� �����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(148, '������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(149, '����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(150, '���� �������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(151, '�������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(152, '������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(153, '����� ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(154, '��� ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(155, '���� ���� ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(156, '������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(157, '���� ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(158, '����� ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(159, '����� ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(160, '���� ���', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(161, '���� ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(162, '��� ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(163, '����� ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(164, '���� ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(165, '��������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(166, '����� ����', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(167, '���� ������', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(168, '��� ����', 33);" ;
		
/*		String insert2 = "INSERT INTO "+ TABLE_AREA + " (`id`, `name`, `parentPlaceID`) VALUES (36, 'dam', NULL) " ;
		String insert3 = "INSERT INTO "+ TABLE_AREA + " (`id`, `name`, `parentPlaceID`) VALUES (37, 'dam', 1) ,(33, 'dama', 33)" ;
		String insert4= "INSERT INTO "+ TABLE_AREA + " (`id`, `name`, `parentPlaceID`) VALUES (39, '����', 5 )";
		
		String ins = "INSERT INTO " +TABLE_AREA+ " ('id','name', 'parentPlaceID') VALUES (33,'dam', null) ;";
		String mulIns = "INSERT INTO " +TABLE_AREA+ " ('id','name', 'parentPlaceID') VALUES (33,'dam', null) , (34,'dam', null);";
		String ins2 = "INSERT INTO " +TABLE_AREA+ " ('id','name', 'parentPlaceID') VALUES (34,'����', null) ;";
*/
		db.execSQL(q5);
		db.execSQL(q2);
		db.execSQL(q3);
		db.execSQL(q4);
		
		
/*		db.execSQL(ins);
		db.execSQL(mulIns);
		db.execSQL(ins2);
		db.execSQL(insert2);
		db.execSQL(insert3);
		db.execSQL(insert4);*/
		
		
		//String ins1 = "INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES (36, '����� �����', 33)" ;
		//String ins2 = "INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES (45, '������', 33)" ;
		
		// need to handle failure 
		db.beginTransaction() ;
		//db.execSQL(insert);
		//db.execSQL(ins1);
		//db.execSQL(ins2);
		
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(33, '����', 0);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(36, '����� �����', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(45, '������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(46, '��� �����', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(47, '��������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(48, '�����', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(49, '����� ���', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(50, '������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(51, '�����', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(53, '��� �����', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(54, '�������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(55, '�����', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(63, '������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(65, '����� �����', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(66, '����', 34);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(73, '��������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(77, '��������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(83, '���������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(85, '�������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(88, '�������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(89, '��� ����', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(91, '�������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(93, '�������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(94, '���� ���������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(97, '��� ����', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(98, '��������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(100, '�����', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(101, '�����', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(102, '������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(105, '���� ��������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(106, '������ ��������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(107, '����� �����', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(111, '�������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(120, '���� �������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(125, '������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(126, '��� ����', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(127, '������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(128, '������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(129, '��� �����', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(131, '�������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(142, '����� ������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(144, '��� �����', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(148, '������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(150, '���� �������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(151, '�������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(156, '������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(164, '���� ������', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(167, '���� ������', 33);");
		
		db.setTransactionSuccessful() ;
		db.endTransaction() ;
		
        /*new Handler().postDelayed(new Runnable() {
    		@Override
    		public void run() {
    			KedniApp.dataSrc.localHandler.insertArea( 6, "arnnos", 1);
    			KedniApp.dataSrc.localHandler.insertArea( 1, "salehiah", -1);
    			KedniApp.dataSrc.localHandler.insertArea( 1, "������", -1);
    			
    			KedniApp.dataSrc.localHandler.insertArea( 2, "akraba", -1);
    			KedniApp.dataSrc.localHandler.insertArea( 4, "jessr abiad", -1);
    			KedniApp.dataSrc.localHandler.insertArea( 5, "Jaramana",-1);
    			
    			//KedniApp.dataSrc.localHandler.insertUserEvent("RideReq", "abdo is reqesting ride", UserEventType.RIDE_REQ_REC, UserEvent.getFormatedDate("2014-06-24 11:34:20"),23,"fat7i");
    			//KedniApp.dataSrc.localHandler.insertUserEvent("RideReq", "fathy is reqesting ride", UserEventType.RIDE_REQ_REC, UserEvent.getFormatedDate("2014-06-25 10:10:25"),35,"samiri fatto7");
    		}
    	},10);*/
                
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}


	
	
}
