package com.brainSocket.khednima3ak;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;

import com.brainSocket.Listners.GetNotificationsListner;
import com.brainSocket.data.Notifiable;
import com.brainSocket.models.AbstractModel;
import com.brainSocket.models.UserEvent;
import com.google.android.gms.internal.da;

import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class CheckUpdatesService extends Service {

	public static String UPDATES_AVAILABLE_ACTION_KEY = "updatesAvail" ;
	private NotificationsReceivedCallback newUpdatesCallback ;
	
	 private Timer timer;
	   
	  private TimerTask updateTask = new TimerTask() {
	    @Override
	    public void run() {
		      Log.i("KhedniService", "Timer task doing work");
		      //KedniApp app = (KedniApp) getApplication();
		      //app.notificationMgr.createNotification() ;
		      /*
		      Intent intent = new Intent();
		      intent.setAction(UPDATES_AVAILABLE_ACTION_KEY);
		      intent.putExtra("DATAPASSED", "data from service");
		      sendBroadcast(intent);
		      */
	    	//Toast.makeText(CheckUpdatesService.this, "khedni service is runnning", Toast.LENGTH_LONG).show() ;
		      KedniApp.dataSrc.serverHandler.getNotifications(newUpdatesCallback);
	    }
	  };
	   
	  @Override
	  public IBinder onBind(Intent intent) {
	    // TODO Auto-generated method stub
	    return null;
	  }
	 
	  @Override
	  public void onCreate() {
	    super.onCreate();
	    //android.os.Debug.waitForDebugger();
	    
	    timer = new Timer();
	    timer.schedule(updateTask, 1000L, 60 * 1000L);
	    newUpdatesCallback = new NotificationsReceivedCallback() ;
	  }
	 
	  @Override
	  public void onDestroy() {
	    super.onDestroy();
	     
	    timer.cancel();
	    timer = null;
	  }
	  
	  public void broadcast(String jsonData){
		  
		  Intent intent = new Intent();
	      intent.setAction(UPDATES_AVAILABLE_ACTION_KEY);
	      intent.putExtra("DATAPASSED", jsonData);
	      sendBroadcast(intent);
	  }
	  
	  
	  
	  public class NotificationsReceivedCallback extends AbstractModel implements Notifiable<String>
	  {
	  	
	  	public NotificationsReceivedCallback()
	  	{
	  		errors.put(-1, "there is no users around you");
	  		errors.put(-2, "the < wantedUsersStateID > is wrong");
	  	}
	  	

	  	@Override
	  	public void onDataReady(String data) {
	  		/// no need to parse data ....just check if notifs are available
	  		// need a better way to check if data is valid
	  		try
	  		{
	  			JSONArray usersNotification=new JSONArray(data);
	  			// if its a Json array and no exeption is fired then make a broadcast
	  			CheckUpdatesService.this.broadcast(data) ;

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
}
