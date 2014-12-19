package com.brainSocket.khednima3ak;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.TextView;

import com.brainSocket.Listners.RatingListner;
import com.brainSocket.Listners.RequestForListner;
import com.brainSocket.enums.UserType;
import com.brainSocket.models.User;
import com.squareup.picasso.Picasso;

public class RequestDiag extends Dialog {

	public static final String TIME_PREFIX = "ÇáÅäØáÇÞ ÈÚÏ"  ;
	public static final String DESTINATION_PREFIX = "Çáì" ;
	
	User targetUser ;
	Context context ;
	RequestForListner requestCallback ;
	RatingListner ratingCallback ;
	
	//Views
	private View tripInfoHolder ;
	private TextView txtTime, txtDest, txtDesc, txtTripPrice ;
	
	public RequestDiag(Context context , User targrtUser ) {

		super(context,android.R.style.Theme_Holo_Dialog_NoActionBar);
		setContentView(R.layout.dialog_request_trans);
		this.context = context ;
		targetUser = targrtUser ;
		requestCallback = new RequestForListner() ;
		ratingCallback = new RatingListner() ;
		
		
		Button btnReq = (Button) findViewById(R.id.btn_request);
		
		btnReq.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//TODO need to change type of UserId to something bigger
				if (KedniApp.getDestinationID() != 0 ){
					KedniApp.dataSrc.serverHandler.requestFor(requestCallback, (int) targetUser.getId(), KedniApp.getCurrentloc(), String.valueOf(targetUser.getPrice() ) );
					dismiss();
				}else{
					Toast.makeText(RequestDiag.this.context, "ÇáÑÌÇÁ ÅÎÊíÇÑ ÇáæÌåÉ ÃæáÇ", Toast.LENGTH_LONG).show() ;
				}
				
			}
		});
		updateViews();
		
		ImageButton btnPhone = (ImageButton) findViewById(R.id.btn_phone);
		btnPhone.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				/*Intent intent   = new Intent(Intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:" + targetUser.getPhone()));
				RequestDiag.this.context.startActivity(intent);*/
				
				try{
				    Intent callIntent = new Intent("android.intent.action.CALL_PRIVILEGED");
				    callIntent.setData(Uri.parse("tel:" + targetUser.getPhone()));
				    RequestDiag.this.context.startActivity(callIntent);
				    }
				    catch (Exception e) {
				        Intent callIntent = new Intent(Intent.ACTION_CALL);
				        callIntent.setData(Uri.parse("tel:" + targetUser.getPhone()));
				        RequestDiag.this.context.startActivity(callIntent);
				    }
			}
		});
		
		ImageButton btnMsg = (ImageButton) findViewById(R.id.btn_msg);
		btnMsg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent   = new Intent(RequestDiag.this.context , ChatActivity.class);
				intent.putExtra(ChatActivity.PEER_ID_KEY, (int)targetUser.getId()) ;
				RequestDiag.this.context.startActivity(intent);
			}
		});
		
	}
	
	void updateViews (){
		
		int minutesToDepart = targetUser.getMinutesLeftToDepart() ;
		
		txtTime = (TextView) findViewById(R.id.time) ;
		txtDest = (TextView) findViewById(R.id.dest) ;
		txtDesc = (TextView) findViewById(R.id.description) ;
		txtTripPrice = (TextView) findViewById(R.id.trip_cost);
		
		tripInfoHolder = findViewById(R.id.trip_info_holder) ;
		
		TextView name = (TextView) findViewById(R.id.name);
		TextView destination = (TextView) findViewById(R.id.destenation);
		RatingBar rate = (RatingBar) findViewById(R.id.rating) ;
		
		TextView cost = (TextView) findViewById(R.id.cost);
		ImageView pic= (ImageView) findViewById(R.id.profile_img) ;
		name.setText(targetUser.getName());
		String desnme = KedniApp.dataSrc.localHandler.getAreaByID(targetUser.getDestId());
		destination.setText(desnme) ;
		Picasso.with(context).load(targetUser.getPictureLink()).into(pic) ;
		//destination.setText(targetUser.getDestination());
		rate.setRating(targetUser.getRate()) ;
		rate.setRating(targetUser.getRate());
		rate.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {	
			@Override
			public void onRatingChanged(RatingBar arg0, float arg1, boolean arg2) {
				// need to handle long ID
				int id = (int) targetUser.getId()  ;
				KedniApp.dataSrc.serverHandler.rate(ratingCallback, arg1, id ,KedniApp.getGoalID()) ;
			}
		});
		//cost.setText(String.valueOf(targetUser.getPrice())) ;
		
		Button btnReq  = (Button) findViewById(R.id.btn_request);
		if( KedniApp.getCurrentStatus() == UserType.DRIVER && targetUser.getType() == UserType.PASSENGER){
			btnReq.setText("ÏÚæÉ ÊæÕíáå") ;
		}else if( KedniApp.getCurrentStatus() == UserType.PASSENGER && targetUser.getType() == UserType.PASSENGER){
			btnReq.setText("ÏÚæÉ ãÔÇÑßÉ ãæÇÕáÇÊ") ;
		}else if( KedniApp.getCurrentStatus() == UserType.PASSENGER && targetUser.getType() == UserType.DRIVER){
			btnReq.setText("ØáÈ ÊæÕíáÉ") ;
		}else if( KedniApp.getCurrentStatus() == UserType.DRIVER && targetUser.getType() == UserType.DRIVER){
			btnReq.setText("ØáÈ ÊæÕíáÉ") ;
			btnReq.setEnabled(false);
		}
			
		if( (targetUser.getType() == UserType.PASSENGER) && targetUser.getType() == KedniApp.getCurrentStatus() ){
			cost.setText("---");
		}else{
			String costTxt = ( (int) targetUser.getPrice()  ) +" "+ KedniApp.UNIT; 
			cost.setText( costTxt);
			//cost.setVisibility(View.GONE);
		}
		
		
		// for scheduledTrip
		if(minutesToDepart >= 0){ // is scheduled trip
			String dest = KedniApp.dataSrc.localHandler.getAreaByID(targetUser.getDestId());
			
			txtTime.setText(String.format(TIME_PREFIX + " %02d:%02d", (int) targetUser.getMinutesLeftToDepart()/60,(int) targetUser.getMinutesLeftToDepart()%60));
			txtDest.setText(DESTINATION_PREFIX + " "+ dest) ;
			txtDesc.setText(targetUser.getExtraDesc());
			txtTripPrice.setText( ( (int) targetUser.getPrice()  ) +" "+ KedniApp.UNIT ) ;
			
			cost.setVisibility(View.GONE);
		}else{
			tripInfoHolder.setVisibility(View.GONE) ;
		}
		
		
		
	}

	
	
	

}
