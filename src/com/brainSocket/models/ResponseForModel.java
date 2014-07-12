package com.brainSocket.models;

import org.json.JSONObject;

import android.util.Log;

import com.brainSocket.khednima3ak.KedniApp;

public class ResponseForModel extends AbstractModel 
{
	private int responseID;
	
	public ResponseForModel(int responseID)
	{
		errors.put(-1, "the <requestID> is not valid");
		errors.put(-2, "you are not allowed to response for this request cause you are not a car owner");
		this.responseID=responseID;
	}
	
	public static ResponseForModel createModel(String jsonData)
	{
		try
		{
			ResponseForModel model=null;
			JSONObject o=new JSONObject(jsonData);
			int flag=o.getInt(KedniApp.flag);
			if(flag<0)
				errorIndex=flag;
			else
				model=new ResponseForModel(flag);
			return model;
		}
		catch(Exception c)
		{
			Log.e("error",c.getMessage());
			return null;
		}
	}
}
