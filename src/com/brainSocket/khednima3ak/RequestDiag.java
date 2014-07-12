package com.brainSocket.khednima3ak;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.brainSocket.models.Driver;
import com.brainSocket.models.User;
import com.brainSocket.models.UserType;

public class RequestDiag extends Dialog {

	User targetUser ;
	Context context ;
	
	public RequestDiag(Context context , User targrtUser ) {

		super(context,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth);
		setContentView(R.layout.dialog_request);
		this.context = context ;
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
