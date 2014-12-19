package com.brainSocket.khednima3ak;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.ImageButton;

import com.brainSocket.enums.UserType;

public class ChangeStateDiag extends Dialog {

	
	Context context ;
	ImageButton btnDriver ;
	ImageButton btnpassenger ;
	
	
	public ChangeStateDiag(Context context ) {

		super(context,android.R.style.Theme_Holo_Dialog);
		setContentView(R.layout.diag_change_state);
		setTitle(R.string.pick_state);
		this.context = context ;
		
		btnDriver = (ImageButton) findViewById(R.id.btn_driver) ;
		btnDriver.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				KedniApp.dataSrc.serverHandler.changeState(null, UserType.DRIVER);
				//should be moved to the listener
				KedniApp.setCurrentStatus(UserType.DRIVER);
				MainMap main = (MainMap) ChangeStateDiag.this.context ;
				main.statusChanged();
				ChangeStateDiag.this.dismiss();
			}
		});
		
		
		btnpassenger = (ImageButton) findViewById(R.id.btn_passenger) ;
		btnpassenger.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				KedniApp.dataSrc.serverHandler.changeState(null, UserType.PASSENGER) ;
				KedniApp.setCurrentStatus(UserType.PASSENGER);
				MainMap main = (MainMap) ChangeStateDiag.this.context ;
				main.statusChanged();
				ChangeStateDiag.this.dismiss();
			}
		});
		
	}
	

}
