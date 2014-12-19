package com.brainSocket.khednima3ak;

import java.util.Collections;
import java.util.List;

import org.json.JSONObject;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.brainSocket.adapters.EventsListAdapter;
import com.brainSocket.data.Notifiable;
import com.brainSocket.models.AbstractModel;
import com.brainSocket.models.UserEvent;

public class EventsActivity extends ActionBarActivity {

	ListView listView ;
	EventsListAdapter adapter ;
	List<UserEvent> list ;
	ResponseForCallback responceforCalback ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);
		listView = (ListView) findViewById(R.id.events_list);
		responceforCalback = new ResponseForCallback() ;
		list = KedniApp.dataSrc.localHandler.getNoneMessagingUserEvents();
		Collections.reverse(list);
		adapter = new EventsListAdapter(this, list ,responceforCalback);
		listView.setAdapter(adapter);
		
		if(list.size() == 0) {
			View msg = findViewById(R.id.msg_holder) ;
			msg.setVisibility(View.VISIBLE) ;
		}
	}
	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		KedniApp.setCurrentActivity(this);
	}
	@Override
	protected void onPause() {
		super.onPause();
		KedniApp.setCurrentActivity(null);
	}
	
	
	int getEventById( int globalId){
		for (int i=0 ; i < list.size() ;i++ ) {
			if (list.get(i).getGlobalId() == globalId){
				return  i ;
			}
		}
		return -1 ;
	}
	
	public class ResponseForCallback extends AbstractModel implements Notifiable<String> 
	{
		int failuresCount = 0 ; 
		
		public ResponseForCallback( )
		{
			errors.put(-1, "the <requestID> is not valid");
			errors.put(-2, "you are not allowed to response for this request cause you are not a car owner");
		
		}

		@Override
		public void onDataReady(String data) {
			try
			{
				JSONObject o=new JSONObject(data);
				int flag=o.getInt(KedniApp.flag);
				int originalReqId=o.getInt("OriginalRequestId");
				
				if(flag<0)
				{
					errorIndex=flag;
					Toast.makeText(KedniApp.getContext(), errors.get(flag), Toast.LENGTH_SHORT).show();
				}else{
					KedniApp.dataSrc.localHandler.setEventActiveByGlobalId(originalReqId, 0) ;
					int requestIndex = EventsActivity.this.getEventById(originalReqId) ;
					EventsActivity.this.list.get(requestIndex).setEventActive(false) ;
					adapter.notifyDataSetChanged() ;
					
				}
			}
			catch(Exception c)
			{
				//Log.e("error",c.getMessage());
			}
		}

		@Override
		public void onCursorReady(Cursor cur) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onDataLoadFail(String msg) {
			failuresCount ++ ;
			if(failuresCount <= failuresAllowed){
				KedniApp.dataSrc.serverHandler.doRequest(this, msg) ;
			}else{
				failuresCount = 0 ;
				Toast.makeText(EventsActivity.this, EventsActivity.this.getString(R.string.error_repeated_connectivity_failure), Toast.LENGTH_LONG).show() ;
			}
		}
	}
}
