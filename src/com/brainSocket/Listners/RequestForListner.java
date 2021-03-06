package com.brainSocket.Listners;

import java.util.Calendar;
import java.util.Date;

import org.json.JSONObject;

import android.database.Cursor;

import android.widget.Toast;

import com.brainSocket.data.Notifiable;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.khednima3ak.R;
import com.brainSocket.models.AbstractModel;
import com.brainSocket.models.UserEvent;

public class RequestForListner extends AbstractModel implements Notifiable<String> 
{
	public static final String AREA_ID_KEY = "AreaID";
	
	public RequestForListner()
	{
		errors.put(-1, "try to request more than one time for the same user and the same goal .");
		errors.put(-2, "try to request him self ( a bear)");
	}

	@Override
	public void onDataReady(String data) {
		// TODO Auto-generated method stub
		try
		{
			JSONObject o=new JSONObject(data);
			int flag=o.getInt(KedniApp.flag);
			//int areaID = o.getInt(AREA_ID_KEY);
			if(flag<0)
			{
				errorIndex=flag;
				Toast.makeText(KedniApp.getContext(), errors.get(flag), Toast.LENGTH_SHORT).show();
			}else{
				/// need to handle this response
				UserEvent requestRegister = new UserEvent();
				requestRegister.setTitle(UserEvent.TITLE_RIDE_REQ);
				requestRegister.setDescription("Ride request Is sent");
				requestRegister.setGlobalId(flag);
				Date dt = new Date(System.currentTimeMillis());
				requestRegister.setDate(dt);
				//KedniApp.registerUserEvent(requestRegister);
				//KedniApp.SetGoalID(areaID);
				//KedniApp.setDestinationID(areaID);
			}
			
		}
		catch(Exception c)
		{
	//		Log.e("error", c.getMessage());
		}
	}

	@Override
	public void onCursorReady(Cursor cur) {
		// TODO Auto-generated method stub
		
	}

	int failuresCount = 0 ;
	@Override
	public void onDataLoadFail(String msg) {
		failuresCount ++ ;
		if(failuresCount <= failuresAllowed){
			KedniApp.dataSrc.serverHandler.doRequest(this, msg) ;
		}else{
			//TODO we should notefy the user about the failure
			failuresCount = 0 ;
			KedniApp.promptMgr.showToast(KedniApp.appContext.getString(R.string.error_repeated_connectivity_failure), Toast.LENGTH_LONG) ;
		}
	}
	
}
