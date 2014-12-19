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
		String insert = "INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(33, 'œ„‘ﬁ', NULL); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(34, '„⁄—»«', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(35, '«· ·', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(36, '÷«ÕÌ… «·√”œ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(37, 'Õ—” «', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(38, 'œÊ„«', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(39, '„”—«»«', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(40, '»Ì  ”Ê«', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(41, 'Õ„Ê—Ì…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(42, '”ﬁ»«', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(43, '“„·ﬂ«', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(44, 'ÃÊ»—', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(45, '«·ﬁ’Ê—', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(46, '—ﬂ‰ «·œÌ‰', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(47, '«·’«·ÕÌ…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(48, '«·„“…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(49, '„‘—Ê⁄ œ„—', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(50, '«·Â«„…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(51, 'ﬁœ”Ì«', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(52, '÷«ÕÌ… ﬁœ”Ì«', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(53, 'ﬁ—Ï «·√”œ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(54, '«·’»Ê—…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(55, 'Ì⁄›Ê—', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(56, '«·‘«€Ê—', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(57, '«·ÕÃ— «·√”Êœ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(58, '«·”Ìœ… “Ì‰»', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(59, '«·œÌ«»Ì…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(60, '”»Ì‰…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(61, 'œ«—Ì«', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(62, '√‘—›Ì… ’Õ‰«Ì«', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(63, '’Õ‰«Ì«', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(64, '«·„⁄÷„Ì…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(65, 'ÃœÌœ… ⁄—ÿÊ“', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(66, '»—“…', 34); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(67, '⁄—»Ì‰', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(68, '«·ﬁ«»Ê‰', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(69, 'Ì·œ«', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(70, '»»Ì·«', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(71, '‘»⁄«', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(72, 'ÃœÌœ… Ê«œÌ »—œÏ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(73, '«·»—«„ﬂ…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(74, '«·œÌ„«”', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(75, '√‘—›Ì… Ê«œÌ »—œÏ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(76, 'œÌ— „ﬁ—‰', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(77, '«·“»œ«‰Ì', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(78, '«·œ—ÌÃ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(79, '»”Ì„…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(80, '⁄Ì‰ „‰Ì‰', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(81, 'Ã»· ﬁ«”ÌÊ‰', 46); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(83, '«·„Â«Ã—Ì‰', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(84, 'Ã«„⁄ «·≈Ì„«‰', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(85, 'ﬂ›—”Ê”…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(86, '«·ﬁ‰Ê« ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(87, 'œ„‘ﬁ «·ﬁœÌ„…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(88, '«·ÿ»«·…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(89, '⁄Ì‰  —„«', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(90, '«»‰ ⁄”«ﬂ—', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(91, '«·“«Â—…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(92, 'Õ“…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(93, '«·„·ÌÕ…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(94, '”«Õ… «·⁄»«”ÌÌ‰', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(95, '”«Õ… «·ﬁ’Ê—', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(96, '”Êﬁ «·Â«· «·ÃœÌœ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(97, '»«»  Ê„«', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(98, '«·Õ„ÌœÌ…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(99, '«·Õ—Ìﬁ…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(100, '⁄—‰Ê”', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(101, '„“—⁄…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(102, '”«—ÊÃ…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(103, '„Õÿ… «·ÕÃ«“', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(104, '«·Ã«„⁄ «·√„ÊÌ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(105, '”«Õ… «·√„ÊÌÌ‰', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(106, '„” ‘›Ï «·„Ê«”«…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(107, 'ÕœÌﬁ…  ‘—Ì‰', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(108, 'ﬂ·Ì… «·ÿ»', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(109, 'ﬂ·Ì… «·¬œ«»', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(110, '»«» «·Ã«»Ì…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(111, '«·„Ìœ«‰', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(112, '«·ﬁ«⁄…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(113, 'Ã«„⁄ «·Õ”‰', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(114, 'ﬂ·Ì… «·Â‰œ”… «·„Ìﬂ«‰ÌﬂÌ… Ê«·ﬂÂ—»«∆Ì…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(115, '‰«œÌ «·‰÷«·', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(116, '√”Ê«ﬁ «·ŒÌ—', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(117, '‘—ﬂ… ÃÊœ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(118, '”ÌœÌ „ﬁœ«œ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(119, '«· ÷«„‰', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(120, '„ŒÌ„ «·Ì—„Êﬂ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(121, '„Õÿ… «·ﬁœ„', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(122, '‰Â— ⁄Ì‘…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(123, '„Ã·” «·Ê“—«¡', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(124, '«·‘ÌŒ ”⁄œ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(125, '«·—»Ê…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(126, '»«» ‘—ﬁÌ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(127, 'Ã—„«‰«', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(128, '«·⁄œÊÌ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(129, '√»Ê —„«‰…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(130, 'ﬂ·Ì… «·“—«⁄…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(131, '«·„Ì”« ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(132, '”«Õ… «·„Ì”« ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(133, '‘ÌŒ „ÕœÌ‰', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(134, '‘ÌŒ „ÕÌÌ «·œÌ‰', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(135, '√»Ê «·‰Ê—', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(136, 'Ã«„⁄ √»Ê «·‰Ê—', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(137, '»—‰Ì…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(138, '„”»Õ «·›ÌÕ«¡', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(139, 'Ã«„⁄ «·⁄À„«‰', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(140, 'Ê“«—… «· —»Ì…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(141, '”«Õ… «·‘Â»‰œ—', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(142, '«·Ã”— «·√»Ì÷', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(143, '„” ‘›Ï «·ÿ·Ì«‰Ì', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(144, '”»⁄ »Õ—« ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(145, '”«Õ… «·”»⁄ »Õ—« ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(146, 'Ê“«—… «·„«·Ì…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(147, '”«Õ… ⁄—‰Ê”', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(148, '«·—Ê÷…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(149, 'Õ„—«', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(150, '‘«—Õ «·Õ„—«¡', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(151, '«·‘⁄·«‰', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(152, '«·”»ﬂÌ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(153, 'ÕœÌﬁ… «·”»ﬂÌ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(154, 'œ«— «·”·«„', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(155, '”«Õ… ÌÊ”› «·⁄Ÿ„…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(156, '«·»Õ’…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(157, '”«Õ… «·‰Ã„…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(158, 'ÕœÌﬁ… «·„œ›⁄', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(159, 'ÕœÌﬁ… «·Ã«ÕŸ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(160, 'Ã«„⁄ »œ—', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(161, '”«Õ… «·—Ê÷…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(162, 'Ã”— «·—∆Ì”', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(163, 'ÕœÌﬁ… «·Ã·«¡', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(164, '‘«—⁄ «·ÀÊ—…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(165, '«·Õ·»Ê‰Ì', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(166, 'Ã«„⁄… œ„‘ﬁ', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(167, '”«Õ… «·„—Ã…', 33); INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(168, '»«» ’€Ì—', 33);" ;
		
/*		String insert2 = "INSERT INTO "+ TABLE_AREA + " (`id`, `name`, `parentPlaceID`) VALUES (36, 'dam', NULL) " ;
		String insert3 = "INSERT INTO "+ TABLE_AREA + " (`id`, `name`, `parentPlaceID`) VALUES (37, 'dam', 1) ,(33, 'dama', 33)" ;
		String insert4= "INSERT INTO "+ TABLE_AREA + " (`id`, `name`, `parentPlaceID`) VALUES (39, 'œ„‘ﬁ', 5 )";
		
		String ins = "INSERT INTO " +TABLE_AREA+ " ('id','name', 'parentPlaceID') VALUES (33,'dam', null) ;";
		String mulIns = "INSERT INTO " +TABLE_AREA+ " ('id','name', 'parentPlaceID') VALUES (33,'dam', null) , (34,'dam', null);";
		String ins2 = "INSERT INTO " +TABLE_AREA+ " ('id','name', 'parentPlaceID') VALUES (34,'œ„‘ﬁ', null) ;";
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
		
		
		//String ins1 = "INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES (36, '÷«ÕÌ… «·√”œ', 33)" ;
		//String ins2 = "INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES (45, '«·ﬁ’Ê—', 33)" ;
		
		// need to handle failure 
		db.beginTransaction() ;
		//db.execSQL(insert);
		//db.execSQL(ins1);
		//db.execSQL(ins2);
		
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(33, 'œ„‘ﬁ', 0);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(36, '÷«ÕÌ… «·√”œ', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(45, '«·ﬁ’Ê—', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(46, '—ﬂ‰ «·œÌ‰', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(47, '«·’«·ÕÌ…', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(48, '«·„“…', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(49, '„‘—Ê⁄ œ„—', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(50, '«·Â«„…', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(51, 'ﬁœ”Ì«', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(53, 'ﬁ—Ï «·√”œ', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(54, '«·’»Ê—…', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(55, 'Ì⁄›Ê—', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(63, '’Õ‰«Ì«', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(65, 'ÃœÌœ… ⁄—ÿÊ“', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(66, '»—“…', 34);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(73, '«·»—«„ﬂ…', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(77, '«·“»œ«‰Ì', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(83, '«·„Â«Ã—Ì‰', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(85, 'ﬂ›—”Ê”…', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(88, '«·ÿ»«·…', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(89, '⁄Ì‰  —„«', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(91, '«·“«Â—…', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(93, '«·„·ÌÕ…', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(94, '”«Õ… «·⁄»«”ÌÌ‰', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(97, '»«»  Ê„«', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(98, '«·Õ„ÌœÌ…', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(100, '⁄—‰Ê”', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(101, '„“—⁄…', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(102, '”«—ÊÃ…', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(105, '”«Õ… «·√„ÊÌÌ‰', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(106, '„” ‘›Ï «·„Ê«”«…', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(107, 'ÕœÌﬁ…  ‘—Ì‰', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(111, '«·„Ìœ«‰', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(120, '„ŒÌ„ «·Ì—„Êﬂ', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(125, '«·—»Ê…', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(126, '»«» ‘—ﬁÌ', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(127, 'Ã—„«‰«', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(128, '«·⁄œÊÌ', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(129, '√»Ê —„«‰…', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(131, '«·„Ì”« ', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(142, '«·Ã”— «·√»Ì÷', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(144, '”»⁄ »Õ—« ', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(148, '«·—Ê÷…', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(150, '‘«—Õ «·Õ„—«¡', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(151, '«·‘⁄·«‰', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(156, '«·»Õ’…', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(164, '‘«—⁄ «·ÀÊ—…', 33);");
		db.execSQL("INSERT INTO `area` (`id`, `name`, `parentPlaceID`) VALUES(167, '”«Õ… «·„—Ã…', 33);");
		
		db.setTransactionSuccessful() ;
		db.endTransaction() ;
		
        /*new Handler().postDelayed(new Runnable() {
    		@Override
    		public void run() {
    			KedniApp.dataSrc.localHandler.insertArea( 6, "arnnos", 1);
    			KedniApp.dataSrc.localHandler.insertArea( 1, "salehiah", -1);
    			KedniApp.dataSrc.localHandler.insertArea( 1, "’«·ÕÌ…", -1);
    			
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
