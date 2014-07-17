package com.brainSocket.khednima3ak;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.brainSocket.Listners.RequestForListner;
import com.brainSocket.enums.UserType;
import com.brainSocket.models.Driver;
import com.brainSocket.models.User;

public class RequestDiag extends Dialog {

	User targetUser ;
	Context context ;
	RequestForListner requestCallback ;
	
	public RequestDiag(Context context , User targrtUser ) {

		super(context,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
		setContentView(R.layout.dialog_request);
		this.context = context ;
		targetUser = targrtUser ;
		requestCallback = new RequestForListner() ;
		
		Button btnReq = (Button) findViewById(R.id.btn_request);
		
		btnReq.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//need to change type of UserId to some thing bigger
				KedniApp.dataSrc.serverHandler.requestFor(requestCallback, (int) targetUser.getId(), KedniApp.getCurrentloc());
			}
		});
	}
	
	void updateViews (){
		
		TextView name = (TextView) findViewById(R.id.name);
		TextView destination = (TextView) findViewById(R.id.destenation);
		TextView cost = (TextView) findViewById(R.id.cost);
		name.setText(targetUser.getName());
		destination.setText(targetUser.getDestination());
		
		if(targetUser.getType() == UserType.DRIVER){
			String costTxt = String.valueOf(  ((Driver) targetUser).getCost()  ); 
			cost.setText( costTxt);
		}else{
			cost.setVisibility(View.GONE);
		}
	}

	
	
	

}
