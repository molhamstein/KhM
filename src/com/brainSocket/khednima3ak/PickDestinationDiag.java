package com.brainSocket.khednima3ak;


import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.brainSocket.Listners.SetGoalListner;

public class PickDestinationDiag extends Dialog implements OnItemClickListener{

	
	Context context ;
	SetGoalListner goalCalback ;
	ListView list ; 
	List<String> areaNames ;
	private onDestinationChoosenListener callback ;
	
	public PickDestinationDiag(Context context , onDestinationChoosenListener callback ) {

		super(context,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
		setContentView(R.layout.diag_pick_destination);
		setTitle(R.string.pick_dest);
		setCancelable(false);
		setCanceledOnTouchOutside(false);
		this.context = context ;
		areaNames = KedniApp.dataSrc.localHandler.getAllDsitenctAreas() ;
		goalCalback = new SetGoalListner(context) ;
		list = (ListView) findViewById(R.id.destination_list) ;
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, areaNames);
		list.setAdapter(adapter);
		list.setOnItemClickListener(this);
		this.callback =callback ;  
		
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		String area = (String) ((TextView)arg1).getText() ;
		int areaServerId = 	KedniApp.dataSrc.localHandler.getAreaID(area);
		//KedniApp.dataSrc.serverHandler.setGoal(goalCalback,areaServerId , KedniApp.getCurrentloc()) ;
		dismiss() ;
		callback.onDestinationChosen(areaServerId) ;
		
	}
	

	public interface onDestinationChoosenListener {
		public void onDestinationChosen (int destinationId);
	}
	
	
	

}
