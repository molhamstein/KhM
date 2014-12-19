package com.brainSocket.adapters;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.brainSocket.khednima3ak.EventsActivity.ResponseForCallback;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.khednima3ak.R;
import com.brainSocket.models.ChatMessage;
import com.brainSocket.models.User;
import com.squareup.picasso.Picasso;

public class ChatListAdapter extends BaseAdapter {

	Context context ;
	List<ChatMessage> messages ;
	ResponseForCallback responceListener ;
	EditText inputView ; 
	
	
	public ChatListAdapter(Context con , List<ChatMessage> list) {
		
		context = con ;
		messages = list ; 
	}
	
	@Override
	public int getCount() {		
		return messages.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	View inflate(boolean isMine , ViewGroup parent){
		View rowView ;
		ViewHolder holder ;
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if (isMine) {
			rowView = (View) inflater.inflate(R.layout.chat_list_element_me, parent, false);
		}else{
			rowView = (View) inflater.inflate(R.layout.chat_list_element_peer, parent, false);
		}
		
		holder = new ViewHolder() ;
		holder.img = (ImageView) rowView.findViewById(R.id.img_chat) ;
		holder.txt = (TextView) rowView.findViewById(R.id.msg_txt) ;
		holder.isMine = isMine ;
		rowView.setTag(holder) ;
		return rowView ;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		ChatMessage msg = messages.get(position) ;
		
		View rowView = convertView ;
		ViewHolder holder ;
		if(convertView == null ){  
			rowView = inflate(msg.isFromMe(), parent) ;
			holder  = (ViewHolder) rowView.getTag() ;
		}else{
			holder = (ViewHolder) rowView.getTag() ;
			if(msg.isFromMe() == holder.isMine){
				rowView = (View) convertView ;
			}else{
				rowView = inflate(msg.isFromMe(), parent);
				holder = ( ViewHolder) rowView.getTag() ;
			}
		}
		
		if(!holder.isMine){
			String fbImg = User.getPictureLink(msg.getPeerFBid()) ;
			Picasso.with(context).load(fbImg).into(holder.img) ;
		}else{
			KedniApp app = (KedniApp) context.getApplicationContext() ;
			String fbImg = User.getPictureLink(app.getFacebookID() );
			Picasso.with(context).load(fbImg).into(holder.img) ;
		}
		
		holder.txt.setText(msg.getContent()) ;
		
		return rowView ;
	}
	
	
	public class ViewHolder {
		public ImageView img; 
		public TextView txt ;
		public boolean isMine ;
	}

}
