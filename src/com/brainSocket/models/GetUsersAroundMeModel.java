package com.brainSocket.models;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


import com.google.android.gms.maps.model.LatLng;

import com.brainSocket.enums.UserType;
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
	
	public static List<User> parsingJson(String jsonData)
	{
		try
		{
			JSONArray usersAroundMe=new JSONArray(jsonData);
			JSONObject ob=null;
			List<User> users=new ArrayList<User>();
			User user=null;
			for(int i=0;i<usersAroundMe.length();i++)
			{
				// need to resolve userType
				ob=usersAroundMe.getJSONObject(i);
				user = new User(ob.getInt("userID"), ob.getString("facebookID"), ob.getString("fullName"),
						new LatLng(ob.getDouble("posX"), ob.getDouble("posY")), (float)ob.getDouble("price")
						, UserType.values()[i], (float) ob.getDouble("rate") );
				
				
				users.add(user);
			}
			return users;
		}
		catch(Exception c)
		{
			Log.e("error",c.getMessage());
			return null;
		}
	}
}
