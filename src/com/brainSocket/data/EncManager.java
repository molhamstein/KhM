package com.brainSocket.data;

public class EncManager {

	public static int getDigest(String msgString , int key){
		int digest = 0 ;
		if(key >=0){
			byte[] msg = msgString.getBytes() ;
			int KeyCharIndex  = key % msg.length ;
			//digest =  msg[KeyCharIndex] ;
			
			for(int i = 0 ; i < KeyCharIndex ; i++ ){
				digest += msg[i] ;
			}
			
			for(int i = KeyCharIndex ; i < msg.length ; i++ ){
				digest -= msg[i] ;
			}
		}
		return digest ;
	}
	
	public static boolean checkDigest (String msg , int digest , int key){
		int calculatedDigest = getDigest(msg , key) ;
		boolean isDigestCorrect = false ;
		if (digest == calculatedDigest)
			isDigestCorrect = true ;
		return isDigestCorrect ;
	}
}
