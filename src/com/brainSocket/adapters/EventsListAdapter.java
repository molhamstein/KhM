package com.brainSocket.adapters;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.brainSocket.enums.UserEventType;
import com.brainSocket.khednima3ak.EventsActivity.ResponseForCallback;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.khednima3ak.R;
import com.brainSocket.models.User;
import com.brainSocket.models.UserEvent;
import com.squareup.picasso.Picasso;

public class EventsListAdapter extends BaseAdapter {

	Context context ;
	List<UserEvent> events ;
	OnClickListener approvementClickListener ;
	OnClickListener declineClickListener ;
	ResponseForCallback responceListener ;
	
	
	public EventsListAdapter(Context con , List<UserEvent> lst , ResponseForCallback callback) {
		context = con ;
		events = lst ;
		responceListener = callback ;
		approvementClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				int element = (Integer) v.getTag();
		        UserEvent event = events.get(element);
		        KedniApp.dataSrc.serverHandler.responseFor(responceListener,event.getPartnerId(), event.getGlobalId(), 1, KedniApp.getCurrentloc()) ;			
			}
		};
		
		declineClickListener = new OnClickListener( ) {
			
			@Override
			public void onClick(View v) {
				int element = (Integer) v.getTag();
		        UserEvent event = events.get(element);
				KedniApp.dataSrc.serverHandler.responseFor(responceListener, event.getPartnerId(),event.getGlobalId(), 0, KedniApp.getCurrentloc()) ;
				
			}
		};
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return events.size();
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

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View rowView ;
		if(convertView == null){  
		  LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		  rowView = (View) inflater.inflate(R.layout.events_list_element_new, parent, false);
		}else{
		  rowView = (View) convertView ;
		}
		
		UserEvent event = events.get(position);
		UserEventType type = event.getType() ;
		View buttonsHolder = rowView.findViewById(R.id.btn_holder);
		
		if( 	(type == UserEventType.RIDE_INV_REC ||
				 type == UserEventType.RIDE_REQ_REC ||
				 type == UserEventType.SHARE_REQ_REC  ) && 
				 event.isEventActive()){
			 
			buttonsHolder.setVisibility(View.VISIBLE);
			Button btn_ok = (Button) rowView.findViewById(R.id.btn_ok);
			Button btn_no = (Button) rowView.findViewById(R.id.btn_no);
			btn_ok.setOnClickListener(approvementClickListener);
			btn_no.setOnClickListener(declineClickListener);
			btn_ok.setTag(position);
			btn_no.setTag(position);
			
		//txt.setText(events.get(position).getPartnerId());
		}else{
			buttonsHolder.setVisibility(View.GONE);
		}
		
		TextView txt = (TextView) rowView.findViewById(R.id.partner_name);
		txt.setText(event.getTitle());
		
		TextView txtDesc = (TextView) rowView.findViewById(R.id.description);
		txtDesc.setText(event.getDescription());
		
		TextView txtDate  = (TextView) rowView.findViewById(R.id.tvdate);
		txtDate.setText(UserEvent.getFormatedDate(event.getDate() ) );
		
		ImageView img = (ImageView) rowView.findViewById(R.id.img_event) ;
		String imgPath = User.getPictureLink(event.getPartnerFBId()) ;
		Picasso.with(context).load(imgPath).into(img);
		
		int colroNum = position % 4 ;
		int colorRes = R.color.drawer_stripe1 ;
		switch (colroNum) {
		case 1:
			colorRes = R.color.drawer_stripe2 ;
			break;
		case 2:
			colorRes = R.color.drawer_stripe3 ;
			break;
		case 3:
			colorRes = R.color.drawer_stripe4 ;
			break;
			}
		View stripe = rowView.findViewById(R.id.side_stripe) ;
		stripe.setBackgroundColor(context.getResources().getColor(colorRes));
		
		return rowView ;
	}

}
