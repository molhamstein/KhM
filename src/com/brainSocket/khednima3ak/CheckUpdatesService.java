package com.brainSocket.khednima3ak;

import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import com.brainSocket.data.Notifiable;
import com.brainSocket.models.AbstractModel;

public class CheckUpdatesService extends Service {

	public static String UPDATES_AVAILABLE_ACTION_KEY = "updatesAvail" ;
	public static String NEW_GOAL_SET_ACTION_KEY = "newgoalset" ;
	
	private NotificationsReceivedCallback newUpdatesCallback ;
	private UpdateMyPosCallback posUpdateCallback ;
	//private WakeLock wakelock ;
	
	 private Timer timer;
	 private ServiceBroadcastReceiver broadcastReceiver ;
	   
	 private ReleaseWakelocktask releaseLockTask ;
	
	private TimerTask updateTask = new TimerTask() {
	    @Override
	    public void run() {
		      if ( KedniApp.getCurrentloc() != null ){
		    	  KedniApp.dataSrc.serverHandler.updateMyPosition(posUpdateCallback, KedniApp.getCurrentloc());
		      }		      
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
	    timer.schedule(updateTask, 8000L, 10 * 1000L);
	    newUpdatesCallback = new NotificationsReceivedCallback() ;
	    posUpdateCallback = new UpdateMyPosCallback() ;
	    broadcastReceiver = new ServiceBroadcastReceiver() ;
	    IntentFilter broadcastIntentFilter = new IntentFilter(NEW_GOAL_SET_ACTION_KEY);
	    registerReceiver(broadcastReceiver, broadcastIntentFilter);
	    //PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
	    //wakelock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "khedniWakelock");
	    //wakelock.setReferenceCounted(false);
	    //wakelock.acquire();
	    
	  }
	 
	  @Override
	  public void onDestroy() {
	    super.onDestroy();
	    timer.cancel();
	    timer = null;
	    //wakelock.release();
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
	  		try
	  		{
	  			
	  			JSONArray usersNotification=new JSONArray(data);
	  			//String useString = usersNotification.toString();
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
	  
	  public class UpdateMyPosCallback extends AbstractModel implements Notifiable<String>
	  {

	  	@Override
	  	public void onDataReady(String data) {
	  		try
	  		{	
	  			JSONObject jsonObj=new JSONObject(data);
	  			int flag=jsonObj.getInt(KedniApp.flag);
				if(flag>0)
				{
					if(KedniApp.getUserID() != KedniApp.USER_ID_NULL){
				    	  KedniApp.dataSrc.serverHandler.getNotifications(newUpdatesCallback);
				      }
				}
	  			
	  			//CheckUpdatesService.this.broadcast(data) ;
	  		}
	  		catch(Exception c){	}
	  	}

	  	@Override
	  	public void onCursorReady(Cursor cur) {	  		
	  	}

	  	@Override
	  	public void onDataLoadFail(String msg) {
	  	}

	  }

	  
	  class ServiceBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent intent) {
			String action = intent.getAction() ;
			if(action.equals(NEW_GOAL_SET_ACTION_KEY)){
				if(releaseLockTask == null)
					releaseLockTask = new ReleaseWakelocktask() ;
				else{
					releaseLockTask.cancel() ;
					int canceled = timer.purge();
					releaseLockTask = new ReleaseWakelocktask() ;
				}
			//	if(wakelock != null)
				//	wakelock.acquire();
				timer.schedule(releaseLockTask, 1800000);
			}
		} 
	  }
	  
	  class ReleaseWakelocktask extends TimerTask {
			
			@Override
			public void run() {
				//if(wakelock != null){
					//String wake = wakelock.toString();
					
					//wakelock.release();
				//}
			}
		};
}
