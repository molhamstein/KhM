package com.brainSocket.data;

import android.database.Cursor;

public interface Notifiable<T> {
	static int failuresAllowed = 3 ;
	void onDataReady(T data);
	void onCursorReady(Cursor cur) ;
	void onDataLoadFail(String msg);
	
}
