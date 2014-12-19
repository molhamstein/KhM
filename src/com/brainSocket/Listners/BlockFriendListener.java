package com.brainSocket.Listners;

import org.json.JSONObject;

import android.database.Cursor;
import android.widget.Toast;

import com.brainSocket.data.KedniConfig;
import com.brainSocket.data.Notifiable;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.khednima3ak.R;
import com.brainSocket.models.AbstractModel;

public class BlockFriendListener extends AbstractModel implements Notifiable<String> 
{
	
	public BlockFriendListener()
	{
		errors.put(-1, "the <requestID> is not valid");
		errors.put(-2, "you are not allowed to response for this request cause you are not a car owner");
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
				//Toast.makeText(KedniApp.getContext(), errors.get(flag), Toast.LENGTH_SHORT).show();
			}
		}
		catch(Exception c)
		{
//			Log.e("error",c.getMessage());
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
