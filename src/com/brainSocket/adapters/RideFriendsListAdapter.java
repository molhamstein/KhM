package com.brainSocket.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.brainSocket.data.Notifiable;
import com.brainSocket.khednima3ak.R;
import com.brainSocket.modules.User;

public class RideFriendsListAdapter extends BaseAdapter implements Notifiable<String>{
	
	
	
	  protected final Context context;
	  protected Boolean selectable ;
	  protected List<Integer> approvedFriendsList ;
	  protected List<User> friends ;
	  protected ListView list ;
	  

	  
	  public RideFriendsListAdapter(Context context, ListView view ) {
	    super();
	    this.context = context;
	    list = view ;
	    friends = new ArrayList<User>() ;

	    // here we call the server to get the list of approved ride Friends 
	    
	  }

	 
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		  
		View rowView ;
		if(convertView == null){  
		  LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); 
		  rowView = inflater.inflate(R.layout.friend_list_elem, parent, false);
		}else{
		  rowView = convertView ;
		}
		
	    
	    return rowView;
	  }
	  
	  
	@Override
	public int getCount() {
		return elements.length;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}



	@Override
	public void onDataReady(String data) {
		
		
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
