package com.brainSocket.data;

import java.util.List;

import org.apache.http.NameValuePair;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

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
