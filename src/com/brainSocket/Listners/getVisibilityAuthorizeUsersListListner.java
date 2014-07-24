package com.brainSocket.Listners;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.database.Cursor;
import android.util.Log;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.brainSocket.data.Notifiable;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.models.AbstractModel;
import com.brainSocket.models.User;
import com.google.android.gms.maps.model.LatLng;

public class getVisibilityAuthorizeUsersListListner extends AbstractModel implements Notifiable<String> 
{

	List<User> users ;
	BaseAdapter adapter ; 
	public getVisibilityAuthorizeUsersListListner( List<User> usrs , BaseAdapter adapter )
	{
		errors.put(-1, "there is no list");
		
		users = usrs ;
		this.adapter = adapter ;
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
				String nm = ob.getString("name") ;
				int b = ob.getInt("isBlocked");
				if(b == 0) 
					user=new User(ob.getInt("id"), ob.getString("name"),false);
				else
					user=new User(ob.getInt("id"), ob.getString("name"),true);
				user.setFacebookID(ob.getString("facebookID"));
				users.add(user);
			}
			this.users.clear();
			this.users.addAll(users);
			this.adapter.notifyDataSetChanged() ;
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
