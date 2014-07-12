package com.brainSocket.models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.brainSocket.khednima3ak.KedniApp;

import android.util.Log;

public class GetUsersAroundMeModel extends AbstractModel 
{
	List<UserModel> users;
	
	private GetUsersAroundMeModel()
	{
		errors.put(-1, "there is no users around the you");
		errors.put(-2, "the < wantedUsersStateID > is wrong");
		users=new ArrayList<UserModel>();
	}
	
	public static GetUsersAroundMeModel createModel(String jsonData)
	{
		try
		{
			JSONObject o=new JSONObject(jsonData);
			o.getInt(KedniApp.flag);
			o.getJSONObject(name)
		}
		catch(Exception c)
		{
			Log.e("error",c.getMessage());
			return null;
		}
	}
}
