package com.brainSocket.Listners;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.brainSocket.data.Notifiable;
import com.brainSocket.khednima3ak.CheckUpdatesService;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.khednima3ak.MainMap;
import com.brainSocket.models.AbstractModel;
import com.brainSocket.models.User;
import com.brainSocket.models.UserEvent;
import com.brainSocket.enums.UserType;
import com.google.android.gms.maps.model.LatLng;

import android.database.Cursor;
import android.util.Log;

public class GetNotificationsListner extends AbstractModel implements Notifiable<String>
{
	private CheckUpdatesService caller ;
	
	public GetNotificationsListner(CheckUpdatesService caller)
	{
		errors.put(-1, "there is no users around you");
		errors.put(-2, "the < wantedUsersStateID > is wrong");
		this.caller=caller;
	}
	

	@Override
	public void onDataReady(String data) {
		
		try
		{
			//no need to parse data .... just check if there is new data 
			JSONArray usersNotification=new JSONArray(data);
			JSONObject ob=null;
			List<UserEvent> userEvents=new ArrayList<UserEvent>();
			UserEvent userEvent=null;
	
			//broadcast the event
			//caller.broadcast(userEvents) ;
			//mainMap.refreshUsersAroundMe(users);
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
