package com.brainSocket.Listners;

import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.Toast;

import com.brainSocket.data.Notifiable;
import com.brainSocket.khednima3ak.CheckUpdatesService;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.khednima3ak.MainMap;
import com.brainSocket.khednima3ak.R;
import com.brainSocket.models.AbstractModel;

public class SetGoalListner extends AbstractModel implements Notifiable<String> 
{	
	Context context ;
	
	public static boolean pendingGoal ;
	
	public SetGoalListner(Context con)
	{
		errors.put(-1, "there is no place corresponding to <placeID>");
		pendingGoal = false ;
		context = con ;
	}

	@Override
	public void onDataReady(String data) {
		try
		{
			JSONObject o=new JSONObject(data);
			int flag=o.getInt(KedniApp.flag);
			int destId =o.getInt("placeID");
			if(flag<0)
				KedniApp.promptMgr.showToast("error setGoal:"+errors.get(flag), Toast.LENGTH_SHORT) ;
			else{
				KedniApp.SetGoalID(flag);
				KedniApp.setDestinationID(destId) ;
				pendingGoal = false ;
				KedniApp.dataSrc.serverHandler.getUsersAroundMe(MainMap.getUsersCallback, KedniApp.getFilterType() , KedniApp.getCurrentloc() );
				Intent Action = new Intent(CheckUpdatesService.NEW_GOAL_SET_ACTION_KEY);
				context.sendBroadcast(Action);
			}
		}
		catch(Exception c)
		{
	//		Log.e("set goal error", c.getMessage());
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
			pendingGoal = false ;
			failuresCount = 0 ;
			
			KedniApp.promptMgr.showToast(KedniApp.appContext.getString(R.string.error_repeated_connectivity_failure), Toast.LENGTH_LONG);
		}
	}
		
}
