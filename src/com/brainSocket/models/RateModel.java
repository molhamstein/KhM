package com.brainSocket.models;

import org.json.JSONObject;

import com.brainSocket.khednima3ak.KedniApp;

import android.util.Log;

public class RateModel extends AbstractModel 
{
	private float rateValue;
	
	private RateModel(float rateValue)
	{
		errors.put(-1, "error occur");
		errors.put(-2, "there is no user with id <rateForUserID >");
		errors.put(-3, "the user try to rate him self");
		
		this.rateValue=rateValue;
	}
	
	public static RateModel createModel(String jsonData)
	{
		try
		{
			RateModel model=null;
			JSONObject o=new JSONObject(jsonData);
			float flag=o.getInt(KedniApp.flag);
			if(flag<0)
				errorIndex=(int)flag;
			else
				model=new RateModel(flag);
			return model;
			
		}
		catch(Exception c)
		{
			Log.e("error", c.getMessage());
			return null;
		}
	}
}
