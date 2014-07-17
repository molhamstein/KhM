package com.brainSocket.Listners;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.brainSocket.data.Notifiable;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.khednima3ak.MainMap;
import com.brainSocket.models.AbstractModel;
import com.brainSocket.models.User;
import com.brainSocket.enums.UserType;
import com.google.android.gms.maps.model.LatLng;

import android.database.Cursor;
import android.util.Log;

public class GetUsersAroundMeListner extends AbstractModel implements Notifiable<String>
{
	private MainMap mainMap;
	
	public GetUsersAroundMeListner(MainMap mainMap)
	{
		errors.put(-1, "there is no users around the you");
		errors.put(-2, "the < wantedUsersStateID > is wrong");
		this.mainMap=mainMap;
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
				user=new User(ob.getInt("userID"), ob.getString("facebookID"), ob.getString("fullName"),
						new LatLng(ob.getDouble("posX"), ob.getDouble("posY")), (float)ob.getDouble("price")
						, UserType.values()[ob.getInt("userTypeID")],(float)ob.getDouble("rate"));
				users.add(user);
			}
			mainMap.refreshUsersAroundMe(users);
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
