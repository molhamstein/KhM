package com.brainSocket.enums;

public enum UserEventType {

	
	MESSAGE_SENT(0),
	MESSAGE_REC(1),
	
	RATING_SENT(2),
	RATING_REC(3),
	
	RECHARG_REMINDER(4),
	
	RIDE_REQ_SENT(5) ,
	RIDE_REQ_REC(6) ,
	RIDE_INV_SENT(7) ,
	RIDE_INV_REC(8) ,
	RIDE_RESP_SENT(9) ,
	RIDE_RESP_REC(10) ,
	
	SHARE_REQ_SENT(11) ,
	SHARE_REQ_REC(12) ,
	SHARE_RESP_SENT(13) ,
	SHARE_RESP_REC(14) ,
	
	FAILED_TO_DELEVER(15);
	
	int value ; 
	
	UserEventType (int val ){
		value = val;
	}
	public int getValue(){
		return value ;
	}
	
}
