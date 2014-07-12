package com.brainSocket.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;

import com.brainSocket.data.Notifiable;
import com.brainSocket.khednima3ak.R;
import com.brainSocket.models.User;
import com.google.android.gms.maps.model.LatLng;

public class RideFriendsListAdapter extends BaseAdapter implements Notifiable<String> ,OnCheckedChangeListener{
	
	
	
	  protected final Context context;
	  protected Boolean selectable ;
	  protected List<Integer> approvedFriendsIndexes ;
	  protected List<User> friends ;

	  
	  public RideFriendsListAdapter(Context context) {
	    super();
	    this.context = context;
	  //  list = view ;
	    friends = new ArrayList<User>() ;

	    // here we call the server to get the list of approved ride Friends 
	    generateStubData();
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
		
		User usr = friends.get(position); 
		TextView name = (TextView) rowView.findViewById(R.id.friend_name);
		name.setText(usr.getName());
	    
		 CheckBox chk = (CheckBox) rowView.findViewById(R.id.friend_checked);
		 chk.setOnCheckedChangeListener(this);
		 chk.setTag(position);
		 chk.setChecked(usr.getisBlockd());
		
	    return rowView;
	  }
	  
	  
	@Override
	public int getCount() {
		return friends.size();
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
	
	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		
		int element = (Integer) arg0.getTag();
        friends.get(element).setIsBlockd(arg1);
        
	}
	
	
	//temp function 
	private void  generateStubData () {
		for (int i = 0 ; i< 15 ; i++ ){
			User usr = new User(i, "ÝÊÍí" + i, new LatLng(36, 36), 2);
			friends.add(usr);
		}
		
	}



		  
}
