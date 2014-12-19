package com.brainSocket.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.brainSocket.Listners.BlockFriendListener;
import com.brainSocket.Listners.getVisibilityAuthorizeUsersListListner;
import com.brainSocket.khednima3ak.KedniApp;
import com.brainSocket.khednima3ak.R;
import com.brainSocket.models.User;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

public class RideFriendsListAdapter extends BaseAdapter implements OnCheckedChangeListener{
	
	
	
	  protected final Context context;
	  protected Boolean selectable ;
	  protected List<Integer> approvedFriendsIndexes ;
	  protected List<User> friends ;
	  getVisibilityAuthorizeUsersListListner listCallback ;
	  BlockFriendListener BlockCallback ;
	  


	  public RideFriendsListAdapter(Context context) {
	    super();
	    this.context = context;
	  //  list = view ;
	    friends = new ArrayList<User>() ;
		listCallback = new getVisibilityAuthorizeUsersListListner( friends , this ) ;
		BlockCallback = new BlockFriendListener () ;
	    KedniApp.dataSrc.serverHandler.getVisibilityAuthorizeUsersList(listCallback) ;
	    // here we call the server to get the list of approved ride Friends 
	    //generateStubData();
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
		ImageView im = (ImageView) rowView.findViewById(R.id.friend_picture);
		Picasso.with(context).load(usr.getPictureLink()).into(im) ;
		
		TextView name = (TextView) rowView.findViewById(R.id.friend_name);
		name.setText(usr.getName());
	    
		 CheckBox chk = (CheckBox) rowView.findViewById(R.id.friend_checked);
		 chk.setTag(position);
		 chk.setChecked(!usr.getisBlockd());
		 chk.setOnCheckedChangeListener(this);
		
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
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		
		int element = (Integer) arg0.getTag();
        User friend = friends.get(element);
        friend.setIsBlockd(!arg1);
        long id = friend.getId() ;
        if(arg1){
        	KedniApp.dataSrc.serverHandler.unblockFriend(BlockCallback, friend.getId()) ;
        }else
        	KedniApp.dataSrc.serverHandler.blockFriend(BlockCallback, friend.getId()) ;
	}
	
			  
}
