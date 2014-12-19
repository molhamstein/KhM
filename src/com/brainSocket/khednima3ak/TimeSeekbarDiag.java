package com.brainSocket.khednima3ak;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.brainSocket.Views.CircularSeekBar;
import com.brainSocket.Views.CircularSeekBar.OnSeekChangeListener;

public class TimeSeekbarDiag extends Dialog  implements OnSeekChangeListener{

	 private onTimespanSetListener callback;
	 private TextView timeDisplay ;
	 private String titlePrefix = "" ;
	 private int hours  = -1 ;
	 private int miutes = -1 ;
	 Activity activity ;
	
	public TimeSeekbarDiag(Context con , onTimespanSetListener callback ) {
		super(con ,android.R.style.Theme_Holo_Dialog);
		this.callback = callback ;
		activity = (Activity) con ;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_pick_timespan);
		
		CircularSeekBar timeSeekbar  =(CircularSeekBar) findViewById(R.id.radial_seekbar);
		timeSeekbar.setMaxProgress(180);
		timeSeekbar.setProgress(15);
		timeSeekbar.invalidate() ;
		timeSeekbar.setSeekBarChangeListener(this);
		
		timeDisplay  = (TextView) findViewById(R.id.time_display);
		
		Button btnOk = (Button) findViewById(R.id.btn_ok);
		btnOk.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(TimeSeekbarDiag.this.hours >= 0 || TimeSeekbarDiag.this.miutes >= 0 ){
					callback.onTimespannSet(TimeSeekbarDiag.this.hours, TimeSeekbarDiag.this.miutes);
					dismiss() ;
				} 
				
			}
		});
		
		titlePrefix = activity.getString(R.string.choose_time_dialog_title);
		setTitle(titlePrefix);
		
	}

	
	@Override
	public void onProgressChange(CircularSeekBar view, int newProgress) {
		this.hours = newProgress/60; 
		this.miutes= newProgress % 60; 
		
		String formatedTime = String.format("%02d:%02d", this.hours,this.miutes) ;
		setTitle(titlePrefix + " " +formatedTime);
		timeDisplay.setText(formatedTime) ;
	}
	
	
	
	public interface onTimespanSetListener {
		public void onTimespannSet(int hours , int minutes) ;
	}
	
}
