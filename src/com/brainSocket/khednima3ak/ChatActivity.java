package com.brainSocket.khednima3ak;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.brainSocket.adapters.ChatListAdapter;
import com.brainSocket.data.Notifiable;
import com.brainSocket.models.AbstractModel;
import com.brainSocket.models.ChatMessage;

public class ChatActivity extends ActionBarActivity {

	public static String PEER_ID_KEY = "peerid" ;
	public static boolean isActivityActive=false ;
	
	ListView listView ;
	static GetChatCallback getChatCallback ;
	SendMessageCallback sendMessageCallback ;
	ChatListAdapter adapter ;
	List<ChatMessage> list ;
	EditText inpuView ;
	ImageButton btnSend ; 
	public static  long peerID ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		peerID = getIntent().getExtras().getInt(PEER_ID_KEY) ;
		
		setContentView(R.layout.activity_chat);
		listView = (ListView) findViewById(R.id.chat_list);
		getChatCallback = new GetChatCallback() ;
		sendMessageCallback = new SendMessageCallback() ;
		
		KedniApp.dataSrc.serverHandler.getMessagesRelatedTo(getChatCallback , String.valueOf(peerID )) ;
		list = new ArrayList<ChatMessage> () ;
		adapter = new ChatListAdapter(this , list) ;
		listView.setAdapter(adapter);
		inpuView = (EditText) findViewById(R.id.chat_input) ;
		btnSend = (ImageButton) findViewById(R.id.btn_send) ;
		btnSend . setOnClickListener( new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String msg = inpuView.getText().toString() ;
				KedniApp.dataSrc.serverHandler.sendMessage(sendMessageCallback, msg, String.valueOf(ChatActivity.this.peerID )) ;
				inpuView.setText("");
			}
		});
		
		isActivityActive = true ;
		//inpuView.setFocusable(false);
	}
	
	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		KedniApp.setCurrentActivity(this);
	}
	@Override
	protected void onResume() {
		super.onResume();
		KedniApp.setCurrentActivity(null);
		isActivityActive = true ;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		isActivityActive = false ;
	}
	public static void refreshChat(){
		if(getChatCallback!= null ){
			KedniApp.dataSrc.serverHandler.getMessagesRelatedTo(getChatCallback , String.valueOf(peerID )) ;
		}
	}
	
	public class GetChatCallback extends AbstractModel implements Notifiable<String> 
	{
		int failuresCount = 0 ;
		
		public GetChatCallback( )
		{
			errors.put(-1, "the <requestID> is not valid");
			errors.put(-2, "you are not allowed to response for this request cause you are not a car owner");	
		}

		@Override
		public void onDataReady(String data) {
			try
			{
				JSONObject o=new JSONObject(data);
				boolean success = o.isNull(KedniApp.flag) ;
				if(success)
				{
					List<ChatMessage> lst = list ;
					list.clear() ;
					String peerName = o.getString("userName") ;
					String peerFBID = o.getString("facebookID") ;
					JSONArray messages = o.getJSONArray("msgs") ;
					for (int i = 0 ; i < messages.length() ; i++){
						JSONObject jsonMsg = messages.getJSONObject(i);
						ChatMessage msg = new ChatMessage() ;
						msg.setContent(jsonMsg.getString("msg")) ; 
						msg.setDate(jsonMsg.getString("time")) ;
						msg.setFromMe(jsonMsg.getBoolean("isMsgFromMe")) ;
						msg.setPeerFBid(peerFBID) ;
						msg.setPeerName(peerName) ;
						
						list.add(msg) ;
					}
					//inpuView.setFocusable(false);
					Collections.reverse(list);
					//int scrollOffset = ((View) list).getHeight() ;
					adapter.notifyDataSetChanged() ;
					listView.post(new Runnable() {
				        @Override
				        public void run() {
				            //listView.smoothScrollToPosition(list.size()-15);
				        	int listSize = list.size();
				        	if(listSize == 0){
				        		ChatActivity.this.findViewById(R.id.chat_warning).setVisibility(View.VISIBLE);
				        		
				        	}else{
				        		listView.setSelection(list.size()-1);
				        		ChatActivity.this.findViewById(R.id.chat_warning).setVisibility(View.GONE);
				        	}
				        	ChatActivity.this.findViewById(R.id.progress_bar).setVisibility(View.GONE);
				        	//inpuView.setFocusable(true);
				        }
				    });
					//listView.smoothScrollToPosition(list.size()-1-scrollOffset); //scrollTo(0, scrollOffset);
					
				}else{
					//errorIndex=flag;
					//Toast.makeText(KedniApp.getContext(), errors.get(flag), Toast.LENGTH_SHORT).show();	
				}
			}
			catch(Exception c)
			{
			}
		}

		@Override
		public void onCursorReady(Cursor cur) {
			
		}

		@Override
		public void onDataLoadFail(String msg) {
			failuresCount ++ ;
			if(failuresCount <= failuresAllowed){
				KedniApp.dataSrc.serverHandler.doRequest(this, msg) ;
			}else{
				failuresCount = 0 ;
				Toast.makeText(ChatActivity.this, ChatActivity.this.getString(R.string.error_repeated_connectivity_failure), Toast.LENGTH_LONG).show() ;
				//inpuView.setFocusable(true);
			}
		}
	}
	
	
	public class SendMessageCallback extends AbstractModel implements Notifiable<String> 
	{
		int failuresCount = 0 ;
		public SendMessageCallback( )
		{
			errors.put(-1, "<toUserID> is not correct");
			errors.put(-2, "<msg> is empty");	
		}
		@Override
		public void onDataReady(String data) {
			try
			{
				JSONObject o=new JSONObject(data);
				int flag=o.getInt(KedniApp.flag);		
				if(flag<0)
				{
					errorIndex=flag;
					Toast.makeText(KedniApp.getContext(), errors.get(flag), Toast.LENGTH_SHORT).show();
				}else{
					KedniApp.dataSrc.serverHandler.getMessagesRelatedTo(getChatCallback , String.valueOf(peerID )) ;
				}
			}
			catch(Exception c)
			{
			}
		}

		@Override
		public void onCursorReady(Cursor cur) {
			
		}

		@Override
		public void onDataLoadFail(String msg) {
			failuresCount ++ ;
			if(failuresCount <= failuresAllowed){
				KedniApp.dataSrc.serverHandler.doRequest(this, msg) ;
			}else{
				failuresCount = 0 ;
				Toast.makeText(ChatActivity.this, ChatActivity.this.getString(R.string.error_repeated_connectivity_failure), Toast.LENGTH_LONG).show() ;
			}
		}
	}
	
}
