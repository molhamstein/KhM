package com.brainSocket.Listners;

import org.json.JSONObject;

import android.database.Cursor;
import android.widget.Toast;

import com.brainSocket.data.Notifiable;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.khednima3ak.R;
import com.brainSocket.models.AbstractModel;

public class SetPriceListner extends AbstractModel implements Notifiable<String> 
{	
	
	public SetPriceListner()
	{
		errors.put(-1, ": price can’t be negative ");
	}

	@Override
	public void onDataReady(String data) {
		// TODO Auto-generated method stub
		try
		{
			JSONObject o=new JSONObject(data);
			int flag=o.getInt(KedniApp.flag);
			
			if(flag >= 0){
				KedniApp.promptMgr.showToast(KedniApp.appContext.getString(R.string.confirm_set_price), Toast.LENGTH_SHORT) ;
			}else
				KedniApp.promptMgr.showToast(KedniApp.appContext.getString(R.string.error_set_price), Toast.LENGTH_SHORT) ;
		}
		catch(Exception c)
		{
		//	Log.e("error", c.getMessage());
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
			KedniApp.promptMgr.showToast(KedniApp.appContext.getString(R.string.error_repeated_connectivity_failure), Toast.LENGTH_LONG);
		}
	}
	
	
}
