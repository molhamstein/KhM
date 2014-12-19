package com.brainSocket.khednima3ak;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.brainSocket.Views.AnimationHelper;

public class TourNewActivity extends Activity{

	private ImageView ic1 ;
	private ImageView ic2 ;
	private ImageView ic3 ;
	private ImageView ic4 ;
	private TextView title;
	ScrollView svScroll ;
	
	
	private ImageView[] ics ; 
	private int[] appshouts = {R.drawable.appshout1 ,R.drawable.appshout2,R.drawable.appshout3,R.drawable.appshout1};
	private int[] apptitles = {R.string.tour_new_title1,R.string.tour_new_title2,R.string.tour_new_title3,R.string.tour_new_title4} ;
	private ImageView appShoutHolder ;
	private Button btnCloseTut ;
	
	private int stageNumber = 0;
	private static final int stagesMaxCount = 3 ;
	private Handler handler ;
	private Runnable appshoutRunnable = new Runnable() {
		@Override
		public void run() {
			appShoutHolder.setImageResource(appshouts[stageNumber-1]); // TODO : poor design here ..... when executing this runnable the stageNumber would be alerady incremented
			
			AnimationHelper.popup(appShoutHolder);
			
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tour_new);
		ic1 = (ImageView) findViewById(R.id.ic_stage1);
		ic2 = (ImageView) findViewById(R.id.ic_stage2);
		ic3 = (ImageView) findViewById(R.id.ic_stage3);
		ic4 = (ImageView) findViewById(R.id.ic_stage4);
		btnCloseTut = (Button) findViewById(R.id.btnCloseTut);
		svScroll = (ScrollView) findViewById(R.id.svScroll);
		appShoutHolder = (ImageView) findViewById(R.id.appshut);
		title = (TextView) findViewById(android.R.id.title);
		
		ics = new ImageView[] {ic1,ic2,ic3,ic4} ;
		appShoutHolder.setVisibility(View.VISIBLE);
		btnCloseTut.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish() ;
			}
		});
		
		
	}
	
	
	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		KedniApp.setCurrentActivity(this);
	}
	@Override
	protected void onPause() {
		super.onPause();
		KedniApp.setCurrentActivity(null);
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		
		handler = new Handler();
		handler.post(new Runnable() {
			@Override
			public void run() {
				if(stageNumber < stagesMaxCount){
					AnimationHelper.popup(ics[stageNumber]) ;
					ics[stageNumber].setVisibility(View.VISIBLE);
					title.setText(getString(apptitles[stageNumber]));
					stageNumber++ ;
					handler.postDelayed(this, 4500);
					handler.post(appshoutRunnable);
				}else if(stageNumber == stagesMaxCount){
					btnCloseTut.setVisibility(View.VISIBLE);
					svScroll.post(new Runnable() {
				        @Override
				        public void run() {
				            svScroll.fullScroll(ScrollView.FOCUS_DOWN);
				        }
				    });
				}
			}
		});
		
	}
	
}
