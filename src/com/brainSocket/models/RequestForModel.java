package com.brainSocket.models;

import org.json.JSONObject;

import android.util.Log;

import com.brainSocket.khednima3ak.KedniApp;

public class RequestForModel extends AbstractModel 
{
	private int requestID;
	
	private RequestForModel(int requestID)
	{
		errors.put(-1, "try to request more than one time for the same user and the same goal .");
		errors.put(-2, "try to request him self ( a bear)");
		this.requestID=requestID;
	}
	
	public static RequestForModel createModel(String jsonData)
	{
		try
		{
			JSONObject o=new JSONObject(jsonData);
			RequestForModel m=null;
			int flag=o.getInt(KedniApp.flag);
			if(flag<0)
				errorIndex=flag;
			else
				m=new RequestForModel(flag);
			return m;
				
		}
		catch(Exception c)
		{
			Log.e("error", c.getMessage());
			return null;
		}
	}
	
}
