package com.brainSocket.khednima3ak;


import org.json.JSONObject;

import android.app.Dialog;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.brainSocket.Listners.RequestForListner;
import com.brainSocket.Views.CircularSeekBar;
import com.brainSocket.data.KedniConfig;
import com.brainSocket.data.Notifiable;
import com.brainSocket.khednima3ak.TimeSeekbarDiag.onTimespanSetListener;
import com.brainSocket.models.AbstractModel;
import com.brainSocket.models.ScheduledTrip;
import com.google.android.gms.maps.model.LatLng;
//import com.doomonafireball.betterpickers.radialtimepicker.RadialTimePickerDialog;

public class ScheduleDiag extends Dialog implements /*RadialTimePickerDialog.OnTimeSetListener , */android.view.View.OnClickListener , PickDestinationDiag.onDestinationChoosenListener , onTimespanSetListener  /*TimePickerDialog.OnTimeSetListener*/{
	
	public static String FRAG_TAG_TIME_PICKER = "TIMEPICKER" ;
	public static final String TIME_PREFIX = "«·≈‰ÿ·«ﬁ »⁄œ"  ;
	public static final String DESTINATION_PREFIX = "«·Ï" ;
	

	MainMap mainMap ;
	RequestForListner requestCallback ;
	private LatLng location ;
	private int minutes = -1 ;
	private int hours =-1 ;
	private int destId  =-1 ;
	
	// views
	private Button btnOk ;
	private Button btnCansle ;
	private Button btnDel ;
	private Button btnUseMyLoc ;
	private Button btnUseMapLoc ;
	private Button btnSetDest ;
	private Button btnSetTime ;
	private EditText AddressDesc ;
	private LinearLayout tripInfoHolder;
	private LinearLayout holderPickPlace ;
	
	private TextView txtTime ;
	private TextView txtDest ;
	private TextView txtDescription ; // used when displaying someone's else scheduled trip 
	
/***
 * create new scheduling dialog
 * @param context : Main activity
 * @param trip : trip to edit if editing and null if creating new trip
 */
	public ScheduleDiag(MainMap context, ScheduledTrip trip ) {  

		super(context,android.R.style.Theme_Holo_Dialog_NoActionBar);
		setContentView(R.layout.dialog_schedule);
		this.mainMap = context ;
		initViews(trip);
		
	}
	
	void initViews (ScheduledTrip trip){
		
		btnOk = (Button ) findViewById(R.id.btn_ok);
		btnCansle = (Button) findViewById(R.id.btn_cansle) ;
		btnDel = (Button) findViewById(R.id.btn_del);
		txtTime = (TextView) findViewById(R.id.time) ;
		txtDest = (TextView) findViewById(R.id.dest) ;
		txtDest.setOnClickListener(this);
		btnSetTime = (Button) findViewById(R.id.btn_set_time) ;
		btnSetTime.setOnClickListener(this) ;
		txtDescription = (TextView) findViewById(R.id.description) ;
		
		btnUseMyLoc = (Button) findViewById(R.id.btn_use_my_loc);
		btnUseMyLoc.setOnClickListener(this);
		
		btnOk.setOnClickListener(this);
		btnCansle.setOnClickListener(this);
		btnDel.setOnClickListener(this);
		btnUseMapLoc = (Button) findViewById(R.id.btn_use_map_loc);
		btnUseMapLoc.setOnClickListener(this);
		btnSetDest = (Button) findViewById(R.id.btn_set_dest);
		btnSetDest.setOnClickListener(this);
		
		AddressDesc = (EditText) findViewById(R.id.text_address_desc);
		
		txtTime.setOnClickListener(this);
		
		tripInfoHolder = (LinearLayout) findViewById(R.id.time_holder);
		holderPickPlace = (LinearLayout) findViewById(R.id.place_holder) ;
		
		if(trip == null) { // we are creating new scheduled trip
			btnDel.setVisibility(View.GONE);
		}else{ // we are editing an existing Trip or displaying trip .... so we are filling the trip date in the dialog
			/*if(trip.getUserId() != KedniApp.getUserID() ){ // trip belongs to another user
				btnDel.setVisibility(View.GONE);
				btnSetDest.setVisibility(View.GONE);
				btnSetTime.setVisibility(View.GONE);
				btnUseMapLoc.setVisibility(View.GONE);
				btnUseMyLoc.setVisibility(View.GONE);
				holderPickPlace.setVisibility(View.GONE);
				
				txtDest.setOnClickListener(null);
				txtTime.setOnClickListener(null);
				
				txtDescription.setVisibility(View.VISIBLE) ;
			}else{*/
				btnSetDest.setEnabled(true);
				btnSetTime.setEnabled(true);
				btnUseMapLoc.setEnabled(true);
				btnUseMyLoc.setEnabled(true);
				View confirmationHolder = findViewById(R.id.confirmation_holder) ;
				confirmationHolder.setVisibility(View.VISIBLE);
			//}
			
			
			this.location = trip.getDepartloc();
			this.destId = trip.getdestId() ;
			this.hours = trip.getMinutesToDepart() /60 ;
			this.minutes = trip.getMinutesToDepart() % 60 ;
			AddressDesc.setText( trip.getAddressDescription() ); // visible when editing own trip
			txtDescription.setText(trip.getAddressDescription()) ; // visible when displaying someone's else trip 
			
			String dest = KedniApp.dataSrc.localHandler.getAreaByID(this.destId);
			
			txtTime.setText(String.format(TIME_PREFIX + " %02d:%02d", this.hours, this.minutes));
			txtTime.setVisibility(View.VISIBLE);
			txtDest.setText(dest) ;
			txtDest.setVisibility(View.VISIBLE);
		}
	}
		
	void useMylocation (){
		mainMap.getLocationForScheduling (false);
		Drawable car = mainMap.getResources().getDrawable(R.drawable.car);
		car.setBounds( 20, 20, 20, 20 );
		btnUseMapLoc.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null) ;
		btnUseMyLoc.setCompoundDrawablesWithIntrinsicBounds(car, null, null, null) ;
	}
	void useMapLocation (){
		mainMap.getLocationForScheduling (true);
		Drawable car = mainMap.getResources().getDrawable(R.drawable.car);
		car.setBounds( 20, 20, 20, 20 );
		btnUseMapLoc.setCompoundDrawablesWithIntrinsicBounds(car, null, null, null) ;
		btnUseMyLoc.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null) ;
	}
	
	public void enterShedulingModePhase2(LatLng loc){
		if(loc != null){
			View confirmationHolder = findViewById(R.id.confirmation_holder) ;
			confirmationHolder.setVisibility(View.VISIBLE);
			btnOk.setVisibility(View.VISIBLE);
			btnCansle.setVisibility(View.VISIBLE);
			location = loc ;
			Toast.makeText(mainMap, " „  ÕœÌœ «·„Êﬁ⁄", Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(mainMap, "›‘·  ÕœÌœ «·„Êﬁ⁄", Toast.LENGTH_SHORT).show();
		}
	}

	private ScheduledTrip gatherData(){
		ScheduledTrip trip = new ScheduledTrip() ;
		
		
		trip.setDepartloc(location);
		trip.setAddressDescription( AddressDesc.getText().toString() ) ;
		trip.setMinutesToDepart( 60*hours + minutes );
		trip.setDestId(destId);
		return trip ;
	}
	public ScheduledTrip getTripInfo(){
		return gatherData() ;
	}
	
	private void lunchDatePicker (){
		
		TimeSeekbarDiag diag = new TimeSeekbarDiag(mainMap, this);
		diag.show() ;

	}


	@Override
	public void onClick(View v) {

		int id = v.getId() ;
		switch (id) {
		case R.id.time:
			lunchDatePicker() ;
			break;
		case R.id.btn_use_map_loc : 
			useMapLocation() ;
			break ;
		case R.id.btn_use_my_loc:
			useMylocation() ;
			break ;
		case R.id.btn_ok :
			ScheduledTrip trip = gatherData() ;
			if(trip != null){
				KedniApp.dataSrc.serverHandler.setScheduledTrip(new SetScheduledTripCallback(), trip) ;
				MainMap.scheduledTrip = trip ;
				dismiss() ;
			}
			break ;
		case R.id.btn_cansle:
			dismiss();
			break;
		case R.id.btn_del:
			//TODO : deleteScheduledTrip callback ;
			MainMap.scheduledTrip = null ;
			KedniApp.dataSrc.serverHandler.deleteScheduledTrip(null);
			dismiss();
			break;
		case R.id.btn_set_time:
			lunchDatePicker();
			break;
		case R.id.btn_set_dest:
			lunchDestDiag ();
			break ;
		case R.id.dest :
			lunchDestDiag ();
			break ;
		}
	}

	private void lunchDestDiag (){
		PickDestinationDiag diag  = new PickDestinationDiag(mainMap, this);
		diag.show() ;
	}

	@Override
	public void onTimespannSet(int hours, int minutes) {
		
		this.minutes = minutes;
		this.hours = hours  ;
		// display Time
		txtTime.setVisibility(View.VISIBLE);
		txtTime.setText(  String.format( TIME_PREFIX + " %02d:%02d ", this.hours, this.minutes) ) ;
		
		btnSetTime.setVisibility(View.GONE) ;
		
		if(txtDest.getVisibility() != View.VISIBLE){ // if destination is already picked ..... dont enable btnSetDest 
			btnSetDest.setEnabled(true) ;
			btnSetDest.setVisibility(View.VISIBLE);
		}
	}
	
	@Override
	public void onDestinationChosen(int destinationId) {
		
			destId = destinationId ;			
			String dest = KedniApp.dataSrc.localHandler.getAreaByID(destinationId);
			
			txtDest.setText(DESTINATION_PREFIX + " "+ dest) ;
			txtDest.setVisibility(View.VISIBLE);
			btnSetDest.setVisibility(View.GONE);
			
			// enable Next step Views
			btnUseMyLoc.setEnabled(true) ;
			btnUseMapLoc.setEnabled(true) ;
			//holderPickPlace.setVisibility(View.VISIBLE) ;
	}

	
	public class SetScheduledTripCallback extends AbstractModel implements Notifiable<String> 
	{
		@Override
		public void onDataReady(String data) {
			try {
				JSONObject o=new JSONObject(data);
				int flag=o.getInt(KedniApp.flag);
				if(flag<0){
					errorIndex=flag;
				}
			}
			catch(Exception c){
				Log.e("error",c.getMessage());
			}
		}

		@Override
		public void onCursorReady(Cursor cur) {
		}
		
		int failuresCount = 0 ;
		@Override
		public void onDataLoadFail(String msg) {
			failuresCount ++ ;
			if(failuresCount <= failuresAllowed){
				KedniApp.dataSrc.serverHandler.doRequest(this, msg) ;
			}else{
				failuresCount = 0 ;
				KedniApp.promptMgr.showToast(KedniApp.appContext.getString(R.string.error_repeated_connectivity_failure), Toast.LENGTH_LONG );
			}
		}
		
	}


}
