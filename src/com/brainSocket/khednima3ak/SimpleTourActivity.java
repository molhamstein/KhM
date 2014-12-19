package com.brainSocket.khednima3ak;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.brainSocket.Views.AnimationHelper;

public class SimpleTourActivity extends Activity {

	private static final int STAGE_DELAY = 3000 ;
	private static final int TEXT_AFTER_STAGE_DELAY = 600 ;
	
	private ImageView ic;
	private TextView title;
	private Button btnCloseTut ;
	private ScrollView svScroll ;

	private TextView dsc;

	private ImageView[] ics;
	private int[] stagesIcs = { R.drawable.tour_stage1, R.drawable.tour_stage2,
			R.drawable.tour_stage3, R.drawable.tour_stage4 };
	private int[] apptitles = { R.string.tour_new_title1,
			R.string.tour_new_title2, R.string.tour_new_title3,
			R.string.tour_new_title4 };
	// private ImageView appShoutHolder ;
	private View includelayout;

	private int[] includedLayouts = { R.id.stage1, R.id.stage2, R.id.stage3,
			R.id.stage4 };

	private int stageNumber = 0;
	private static final int stagesMaxCount = 3;
	private Handler handler;
	private Runnable appshoutRunnable = new Runnable() {
		@Override
		public void run() {
			TextView text = (TextView) includelayout
					.findViewById(R.id.tut_stage_desc);
			text.setText(getString(apptitles[stageNumber - 1])); // TODO : poor
																	// design
																	// here
																	// .....
																	// when
																	// executing
																	// this
																	// runnable
																	// the
																	// stageNumber
																	// would be
																	// alerady
																	// incremented
			AnimationHelper.popup(text);
			text.setVisibility(View.VISIBLE);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tour_simple);

		btnCloseTut = (Button) findViewById(R.id.btnCloseTut);
		svScroll = (ScrollView) findViewById(R.id.svScroll);
		
		
		btnCloseTut.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish() ;
			}
		});
		
		// includelayout = findViewById(includedLayouts[0]);
		// dsc = includelayout.findViewById(R.id.tut_stage_desc) ;
		// ic1 = includelayout.findViewById(R.id.ic_stage);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		handler = new Handler();
		handler.post(new Runnable() {
			@Override
			public void run() {
				if (stageNumber < stagesMaxCount) {
					includelayout = findViewById(includedLayouts[stageNumber]);
					ImageView ic = (ImageView) includelayout.findViewById(R.id.ic_stage);
					View stripe = includelayout.findViewById(R.id.stripe);
					ic.setImageResource(stagesIcs[stageNumber]);
					AnimationHelper.popup(ic);
					AnimationHelper.popup(stripe);
					ic.setVisibility(View.VISIBLE);
					stripe.setVisibility(View.VISIBLE);
					stageNumber++;
					handler.postDelayed(this, STAGE_DELAY);
					handler.postDelayed(appshoutRunnable,TEXT_AFTER_STAGE_DELAY);
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
	public boolean onTouchEvent(MotionEvent event) {
		if(stageNumber >= stagesMaxCount){
			finish();
		}
		return super.onTouchEvent(event);
	}

}
