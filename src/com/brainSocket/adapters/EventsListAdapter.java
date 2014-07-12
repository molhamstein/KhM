package com.brainSocket.adapters;


import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.brainSocket.khednima3ak.R;
import com.brainSocket.models.UserEvent;

public class EventsListAdapter extends BaseAdapter {

	Context context ;
	List<UserEvent> events ;
	
	public EventsListAdapter(Context con , List<UserEvent> lst) {
		context = con ;
		events = lst ;
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
		  rowView = (View) inflater.inflate(R.layout.events_list_element, parent, false);
		}else{
		  rowView = (View) convertView ;
		}
		
		TextView txt = (TextView) rowView.findViewById(R.id.partner_name);
		//txt.setText(events.get(position).getPartnerId());
		
		return rowView ;
	}

}
