package com.brainSocket.models;

import org.json.JSONObject;

import com.brainSocket.khednima3ak.KedniApp;

import android.util.Log;

public class SetGoalModel extends AbstractModel 
{
	private SetGoalModel(int goalID)
	{
		errors.put(-1, "there is no place corresponding to <placeID>");
		this.goalID=goalID;
	}
	
	private int goalID;
	
	public int getGoalID()
	{
		return this.goalID;
	}
	
	public static SetGoalModel createModel(String jsonData)
	{
		try
		{
			SetGoalModel m=null;
			JSONObject o=new JSONObject(jsonData);
			int flag=o.getInt(KedniApp.flag);
			if(flag<0)
				errorIndex=flag;
			else
				m=new SetGoalModel(flag);
			return m;
		}
		catch(Exception c)
		{
			Log.e("error", c.getMessage());
			return null;
		}
	}
	
	
}
