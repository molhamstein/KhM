package com.brainSocket.data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.brainSocket.enums.UserEventType;
import com.brainSocket.khednima3ak.ChatActivity;
import com.brainSocket.khednima3ak.CheckUpdatesService;
import com.brainSocket.khednima3ak.EventsActivity;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.khednima3ak.MainMap;
import com.brainSocket.khednima3ak.R;
import com.brainSocket.models.UserEvent;


public class UserNotificationsMgr {
	
	Context context ;
	private UpdatesEventReceiver updatesEventReceiver ;
	
	
	public UserNotificationsMgr(Context con ) {

		context = con ;
		//Context context = con.getApplicationContext();
		//NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		bindBradcastReceiver () ;
		
	}
	
	void bindBradcastReceiver (){

        updatesEventReceiver = new UpdatesEventReceiver() ;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(CheckUpdatesService.UPDATES_AVAILABLE_ACTION_KEY);
        context.registerReceiver(updatesEventReceiver, intentFilter);
        //need to desice the pest place to start the service >>>here or in the MainMap
        Intent intent = new Intent(context.getApplicationContext(),CheckUpdatesService.class);
        context.startService(intent);
        
        
        //need to check the effects of not calling "unregisterReceiver"  
        //unregisterReceiver(updatesEventReceiver);
        
	}
	
	
	public void createNotification(UserEvent event){
		
		/*
		Notification notifcation = new Notification();
		
		notifcation.icon = android.R.drawable.stat_notify_sync;
		notifcation.tickerText = context.getText(R.string.notification_ride_req);
		notifcation.when = System.currentTimeMillis();
		
		Intent notificationIntent = new Intent(context, MainMap.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
			
		notifcation.setLatestEventInfo(context, contentTitle,
			    contentText, contentIntent);
		
		*/
		Intent intent = null ;
		if(event.getType() == UserEventType.MESSAGE_REC){
			intent = new Intent(context, ChatActivity.class);
			intent.putExtra(ChatActivity.PEER_ID_KEY, event.getPartnerId()) ;
			if(ChatActivity.isActivityActive){
				ChatActivity.refreshChat() ;
			}
		} else{
			intent = new Intent(context, EventsActivity.class);
		}
		if(MainMap.isActivvityActive){
			MainMap.refreshCornerNotification() ;
		}
		//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
		PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		

        NotificationCompat.Builder mNotificationBuilder = new NotificationCompat.Builder(context) 
            .setContentTitle(event.getTitle())
            .setContentText(event.getDescription())
            .setSmallIcon(R.drawable.car)
            .setContentIntent(pIntent)
            //.addAction(R.drawable.car, "View", pIntent)
            .setAutoCancel(true);
        
        if(KedniApp.IsNotifSoundEnabled())
        	mNotificationBuilder.setSound(soundUri) ; 
            //.addAction(0, "Remind", pIntent)
        
        Notification mNotification = mNotificationBuilder.build() ;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // If you want to hide the notification after it was selected, do the code below
        // myNotification.flags |= Notification.FLAG_AUTO_CANCEL;
 
        notificationManager.notify(137, mNotification);
        
	
	}
	
	void HandleNewNotification(UserEvent event , String partnerName){
		
		String msg  = "Œœ‰Ì „⁄ﬂ" ;
		String description = " ‰»ÌÂ ÃœÌœ" ;
		boolean storeInDB = true ;
		
		
		switch (event.getType()) {
		case RIDE_REQ_REC:
			msg = partnerName + " " + "Ìÿ·»  Ê’Ì·…" ;
			description = partnerName +  "Ìÿ·» „‰ﬂ «‰  Ê’·Â ⁄·Ï ÿ—Ìﬁﬂ „ﬁ«»· " + event.getExtraData2() ;
			break;
		case RIDE_RESP_REC:
			msg = partnerName + " " + "—œ ⁄·Ï ÿ·» «· Ê’Ì·…" ;
			if(event.getExtraData1() == 0)
				description = "·„ ÌÊ«›ﬁ" ;
			else
				description = "Ê«›ﬁ " ;
			description += " ⁄·Ï ÿ·» «· Ê’Ì·…" ;			
			break;
		case SHARE_RESP_REC:
			msg = partnerName + " "+ "—œ ⁄·Ï ÿ·» „‘«—ﬂ… «·„Ê«’·« " ;
			if(event.getExtraData1() == 0)
				description = "·„ ÌÊ«›ﬁ" ;
			else
				description = "Ê«›ﬁ " ;
			description += " ⁄·Ï ÿ·» «·„‘«—ﬂ…" ;
			break;
		case SHARE_REQ_REC:
			msg = "œ⁄Ê… „‘«—ﬂ… „Ê«’·«  „‰ " + partnerName;
			description =  "  Ìœ⁄Êﬂ · ‘«—ﬂ «·„Ê«’·«  „⁄Â" + partnerName;
			break;
		case RIDE_INV_REC:
			msg = "œ⁄Ê…  Ê’Ì·… „‰ " + partnerName;
			description = partnerName + " Ì⁄—÷ √‰ ÌÊ’·ﬂ ⁄·Ï ÿ—ÌﬁÂ „ﬁ«»· " + event.getExtraData2() ;
			break;
		case RIDE_INV_RESP_REC:
			msg = "—œ ⁄·Ï œ⁄Ê…  Ê’Ì·… „‰ " + partnerName;
			if(event.getExtraData1() == 0)
				description = "·„ ÌÊ«›ﬁ" ;
			else
				description = "Ê«›ﬁ " ;
				description += " ⁄·Ï œ⁄Ê ﬂ · Ê’Ì·Â „ﬁ«»· " + event.getExtraData2();
			break;
		case RATING_REC:
			msg = " ’ÊÌ  „‰ " + partnerName;
			description = "ﬁ«„ " + partnerName + " " + "»≈⁄ÿ«∆ﬂ  ﬁÌ„« " + event.getExtraData1()  + " ‰ÃÊ„";
			break;
		case MESSAGE_REC:
			msg = "—”«·… „‰ " + partnerName;
			description = "Ê’· ﬂ —”«·… „‰ " + partnerName ;
			//storeInDB = false ;
			break;

		}
		event.setTitle(msg);
		event.setDescription(description);
		
		if(storeInDB)
		KedniApp.dataSrc.localHandler.insertUserEvent(event);
		
		UserEventType type = event.getType() ; 
		if(	(	type == UserEventType.RIDE_INV_REC ||
				type == UserEventType.RIDE_INV_RESP_REC ||
				type == UserEventType.RIDE_REQ_REC ||
				type == UserEventType.SHARE_REQ_REC ||
				type == UserEventType.SHARE_RESP_REC ||
				type == UserEventType.MESSAGE_REC ||
				type == UserEventType.RIDE_RESP_REC ) && KedniApp.IsNotifEnabled() )
		createNotification(event);
	}
	
	private class UpdatesEventReceiver extends BroadcastReceiver{
		 
		 @Override
		 public void onReceive(Context arg0, Intent arg1) {
		  // TODO Auto-generated method stub
			 String data = arg1.getExtras().getString("DATAPASSED");
			 
			 try
				{
					JSONArray usersNotification=new JSONArray(data);
					JSONObject ob=null;
					List<UserEvent> userEvents=new ArrayList<UserEvent>();
					UserEvent userEvent=null;
					for(int i=0;i<usersNotification.length();i++)
					{
						//need to handle float price
						//need to fill event type ;
						ob=usersNotification.getJSONObject(i);
						userEvent=new UserEvent();
						userEvent.setGlobalId(ob.getInt("eventID"));
						userEvent.setPartnerId(ob.getInt("userID"));
						userEvent.setType( UserEventType.fromValue(ob.getInt("eventTypeID")) );
						if(userEvent.getType() != UserEventType.MESSAGE_REC){
							userEvent.setExtraData1(ob.getInt("value"));
						}else{
							userEvent.setExtraData1(-1);
						}
						userEvent.setExtraData2(ob.getInt("price"));
						Date date = UserEvent.getFormatedDate(ob.getString("date"));
						userEvent.setDate(date);
						userEvents.add(userEvent) ;
						String  partnerName = ob.getString("userName") ;
						userEvent.setPartnerName(partnerName);
						userEvent.setPartnerFBId(ob.getString("facebookID"));
						
						HandleNewNotification(userEvent , partnerName) ;
						
					}
					//handle the notifications here
					
				}
				catch(Exception c)
				{
					Log.e("error",c.getMessage());
				}
			 
			 //Toast.makeText(context,"Triggered by Service! " +  data ,Toast.LENGTH_LONG).show();
		  
		 }
		 
	}

}
