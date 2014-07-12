package com.brainSocket.Listners;

import org.json.JSONObject;

import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.brainSocket.data.Notifiable;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.models.AbstractModel;

public class GetPlacesStartsWithListner extends AbstractModel implements Notifiable<String> 
{

	public GetPlacesStartsWithListner()
	{
		errors.put(-1, "there is no list");
	}
	
	@Override
	public void onDataReady(String data) {
		// TODO Auto-generated method stub
		try
		{
			JSONObject o=new JSONObject(data);
			int flag=o.getInt(KedniApp.flag);
			if(flag<0)
			{
				errorIndex=flag;
				Toast.makeText(KedniApp.getContext(), errors.get(flag), Toast.LENGTH_SHORT).show();
				return;
			}
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
