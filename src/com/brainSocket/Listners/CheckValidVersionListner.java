package com.brainSocket.Listners;

import org.json.JSONObject;

import android.database.Cursor;
import android.os.Handler;
import android.widget.Toast;

import com.brainSocket.data.KedniConfig;
import com.brainSocket.data.Notifiable;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.khednima3ak.R;
import com.brainSocket.models.AbstractModel;

public class CheckValidVersionListner extends AbstractModel implements Notifiable<String> 
{	
	
	@Override
	public void onDataReady(String data) {
		try
		{
			JSONObject o=new JSONObject(data);
			int flag=o.getInt(KedniApp.flag);
			
			if(flag == 0){
				KedniApp.setVersionValid(true);
				//Toast.makeText(KedniApp.getContext(), KedniApp.context.getString(R.string.confirm_set_price), Toast.LENGTH_SHORT).show();
			}else{
				
				switch (flag) {
				case -1:  // OutDated App Version
					final String apkLink = o.getString("val") ;
					if(apkLink != null && !apkLink.isEmpty()){
						
						Handler handler = new Handler() ;
						handler.postDelayed(new Runnable() {
							@Override
							public void run() {						
								KedniApp.promptMgr.promptForAPKUpdate(apkLink) ;
							}
						}, 7000) ;

						KedniApp.setVersionValid(false);
					}
					
					break;
				case -2:
									
					break;
				case -3:
					KedniApp.promptMgr.showToast(KedniApp.appContext.getString(R.string.error_service_temp_off), Toast.LENGTH_LONG) ;
					break;
				case -4:    /// service URL changed 
					String serviceLink = o.getString("val") ;
					String serviceUDPLink = o.getString("udpVal") ;
					int updateMyPosPort = o.getInt("port1");
					int getPeoplePort = o.getInt("port2") ;
					if( (serviceLink != null && !serviceLink.isEmpty())   &&
							(serviceUDPLink != null && !serviceUDPLink.isEmpty()) &&
							(updateMyPosPort > 0) &&
							(getPeoplePort>0)) {
						KedniApp.configs.setBaseServiceURL(serviceLink);
						KedniApp.configs.setBaseServiceURL_FOR_UDP(serviceUDPLink) ;
						KedniApp.configs.setGET_USERS_ARROUND_ME_PORT(getPeoplePort);
						KedniApp.configs.setUPDATE_MY_POS_PORT(updateMyPosPort) ;
					}
					break;
				}
				
				//KedniApp.setVersionValid(false);
				//KedniApp.PopDialog(app.getString(R.string.error_invalid_version), app.getString(R.string.error_invalid_version_title)) ;
				//KedniApp.PopDialog(context, msg, title);
				//KedniApp.promptMgr.showToast(KedniApp.appContext.getString(R.string.error_invalid_version), Toast.LENGTH_SHORT);
			}

		}
		catch(Exception c)
		{
			c.printStackTrace();
//			Log.e("error", c.getMessage());
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
