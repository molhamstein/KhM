package com.brainSocket.adapters;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class SuggestionsAdapter extends ArrayAdapter<String> {

	List<String> suggestions ;
	Context context ;
	int resid ; 
	
	
	public SuggestionsAdapter(Context context, int resource, List<String> list) {
		super(context, resource , list);
		this.context = context ;
		suggestions = list ;
		resid = resource ;
	}
	
	
	 @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		  
		TextView rowView ;
		if(convertView == null){  
		  LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		  rowView = (TextView) inflater.inflate(resid, parent, false);
		}else{
		  rowView = (TextView) convertView ;
		}
		
		
	    String title = suggestions.get(position) ;
	    
	    rowView.setText(title);
	    
	    return rowView;
	  }
	  
	  
	@Override
	public int getCount() {
		return suggestions.size();
	}

	@Override
	public String getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	

}
