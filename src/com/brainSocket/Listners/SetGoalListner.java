package com.brainSocket.Listners;

import org.json.JSONObject;

import com.brainSocket.data.Notifiable;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.models.AbstractModel;

import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

public class SetGoalListner extends AbstractModel implements Notifiable<String> 
{	
	
	public SetGoalListner()
	{
		errors.put(-1, "there is no place corresponding to <placeID>");
	}

	@Override
	public void onDataReady(String data) {
		// TODO Auto-generated method stub
		try
		{
			JSONObject o=new JSONObject(data);
			int flag=o.getInt(KedniApp.flag);
			if(flag<0)
				Toast.makeText(KedniApp.getContext(), errors.get(flag), Toast.LENGTH_SHORT).show();
			KedniApp.SetGoalID(flag);
		}
		catch(Exception c)
		{
			Log.e("error", c.getMessage());
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
