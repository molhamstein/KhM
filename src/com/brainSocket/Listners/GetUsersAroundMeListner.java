package com.brainSocket.Listners;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.database.Cursor;
import android.util.Log;

import com.brainSocket.data.Notifiable;
import com.brainSocket.enums.UserType;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.khednima3ak.MainMap;
import com.brainSocket.khednima3ak.R;
import com.brainSocket.models.AbstractModel;
import com.brainSocket.models.User;
import com.google.android.gms.maps.model.LatLng;


public class GetUsersAroundMeListner extends AbstractModel implements Notifiable<String>
{
	private MainMap mainMap;
	static boolean isNooneDisplayed  = false;
	
	public GetUsersAroundMeListner(MainMap mainMap)
	{
		errors.put(-1, "there is no users around you");
		errors.put(-2, "the < wantedUsersStateID > is wrong");
		this.mainMap = mainMap;
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
			if( (usersAroundMe.length() == 0) && ! isNooneDisplayed){
				promptNoUsers();
				isNooneDisplayed = true ;
			}
			for(int i=0;i<usersAroundMe.length();i++)
			{
				ob=usersAroundMe.getJSONObject(i);
				int ismale = 1 ;
				
				user=new User(ob.getInt("userID"), ob.getString("facebookID"), ob.getString("fullName"),
						new LatLng(ob.getDouble("posX"), ob.getDouble("posY")), (float)ob.getDouble("price")
						, UserType.values()[ob.getInt("userTypeID")],(float)ob.getDouble("rate") , ob.getInt("gender"),ob.getInt("destPlaceID"), ob.getString("mobileNumber") ,ob.getInt("goAfter"),ob.getString("description"), ob.getInt("requestsCount"));
				users.add(user);
			}
			mainMap.refreshUsersAroundMe(users);
		}
		catch(Exception c)
		{
			Log.e("error","usersAroundMe :" + c.getMessage());
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
	
	public void promptNoUsers() {
		KedniApp.promptMgr.PopDialog( mainMap.getString(R.string.error_no_users), mainMap.getString(R.string.error_no_users_title)) ;
	}
	
}
