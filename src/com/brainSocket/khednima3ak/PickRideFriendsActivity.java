package com.brainSocket.khednima3ak;

import com.brainSocket.Listners.getVisibilityAuthorizeUsersListListner;
import com.brainSocket.adapters.RideFriendsListAdapter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

public class PickRideFriendsActivity extends ActionBarActivity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pick_friends);
		ListView listView = (ListView) findViewById(R.id.friends_list);
		listView.setAdapter(new RideFriendsListAdapter(this));
	}
	
}
