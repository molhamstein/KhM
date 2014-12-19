package com.brainSocket.data;

import android.content.Context;

public class DataSrc {
	
	public ServerDataSrc serverHandler ;
	public LocalDataSrc localHandler ;
	 Context context ; 
	
	public DataSrc(Context cont) {
		serverHandler = new ServerDataSrc() ;
		localHandler = new LocalDataSrc(cont);
		context = cont ;
	}
	
	
}
