package com.brainSocket.khednima3ak;

import java.util.List;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.brainSocket.adapters.EventsListAdapter;
import com.brainSocket.modules.UserEvent;

public class EventsActivity extends ActionBarActivity {

	ListView listView ;
	EventsListAdapter adapter ;
	List<UserEvent> list ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_events);
		listView = (ListView) findViewById(R.id.events_list);

		list = KedniApp.dataSrc.localHandler.getUserEvents();
		adapter = new EventsListAdapter(this, list);
		listView.setAdapter(adapter);
	}
}
