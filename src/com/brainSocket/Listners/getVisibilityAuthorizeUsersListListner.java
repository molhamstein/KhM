package com.brainSocket.Listners;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.brainSocket.data.Notifiable;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.models.AbstractModel;
import com.brainSocket.models.User;
import com.google.android.gms.maps.model.LatLng;

public class getVisibilityAuthorizeUsersListListner extends AbstractModel implements Notifiable<String> 
{

	public getVisibilityAuthorizeUsersListListner()
	{
		errors.put(-1, "there is no list");
	}
	
	@Override
	public void onDataReady(String data) {
		// TODO Auto-generated method stub
		try
		{
			JSONArray usersAroundMe=new JSONArray(data);
			JSONObject ob=null;
			List<User> users=new ArrayList<User>();
			User user=null;
			for(int i=0;i<usersAroundMe.length();i++)
			{
				ob=usersAroundMe.getJSONObject(i);
				user=new User(ob.getInt("userID"), ob.getString("fullName"),ob.getBoolean("isBlocked"));
				users.add(user);
			}
			//mainMap.RefreshUsersAroundMe(users);
		}
		catch(Exception c)
		{
			Log.e("error",c.getMessage());
		}
	}

	@Override
	public void onCursorReady(Cursor cur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDataLoadFail(String msg) {
		// TODO Auto-generated method stub
		
	}
	
}
