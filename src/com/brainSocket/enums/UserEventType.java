package com.brainSocket.enums;

import com.brainSocket.adapters.RideFriendsListAdapter;

public enum UserEventType {

	
	MESSAGE_SENT(3),
	MESSAGE_REC(4),
	
	RATING_SENT(5),
	RATING_REC(6),
	
	RECHARG_REMINDER(7),
	
	RIDE_REQ_SENT(8) ,
	RIDE_REQ_REC(9) ,
	RIDE_INV_SENT(10) ,
	RIDE_INV_REC(11) ,
	RIDE_RESP_SENT(12) ,
	RIDE_RESP_REC(13) ,
	
	SHARE_REQ_SENT(14) ,
	SHARE_REQ_REC(15) ,
	SHARE_RESP_SENT(16) ,
	SHARE_RESP_REC(17) ,
	
	FAILED_TO_DELEVER(18),

	RIDE_INV_RESP_SENT(19) ,
	RIDE_INV_RESP_REC(20) ;

	
	int value ; 
	
	UserEventType (int val ){
		value = val;
	}
	public int getValue(){
		return value ;
	}
	
	
	public static UserEventType fromValue(int value) {
		// need to switch to a faster algorithm ...... maybe using a switch for all enums will be better
        for (UserEventType my: UserEventType.values()) {  
            if (my.value == value) {  
                return my;  
            }  
        }  
  
        return null;  
    }  
	
}
