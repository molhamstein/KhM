package com.brainSocket.data;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.brainSocket.khednima3ak.MainMap;
import com.brainSocket.khednima3ak.R;

public class UserNotificationsMgr {
	
	Context context ;
	
	public UserNotificationsMgr(Context con ) {

		context = con ;
		//Context context = con.getApplicationContext();
		//NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		
	}
	
	
	public void createNotification(){
		
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
		
		Intent intent = new Intent(context, MainMap.class);
		PendingIntent pIntent = PendingIntent.getActivity(context, 0, intent, 0);
			 

        Notification mNotification = new NotificationCompat.Builder(context) 
            .setContentTitle("New Post!")
            .setContentText("Here's an awesome update for you!")
            .setSmallIcon(R.drawable.car)
            .setContentIntent(pIntent)
            .addAction(R.drawable.car, "View", pIntent)
            .addAction(0, "Remind", pIntent)
            .build();
        
 
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
 
        // If you want to hide the notification after it was selected, do the code below
        // myNotification.flags |= Notification.FLAG_AUTO_CANCEL;
 
        notificationManager.notify(0, mNotification);
        
	
	}

}
