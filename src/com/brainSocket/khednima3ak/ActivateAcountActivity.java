package com.brainSocket.khednima3ak;



import org.json.JSONObject;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.brainSocket.data.Notifiable;
import com.brainSocket.models.AbstractModel;

public class ActivateAcountActivity extends Activity implements Notifiable<String> 
{
	
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";
	
	private UserActivateTask mAuthTask = null;
	private SendVerificationMessageCallback sendVerificationMessageCallback ;

	// Values for email and password at the time of the login attempt.
	private String mEmail;

	// UI references.
	private EditText mEmailView;

	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;
	
	private Button btnResend ;
	private TextView btnReRegster ;
	private Handler resendHandler ;
	private Runnable resendRunable = new Runnable() {
		@Override
		public void run() {
			btnResend.setEnabled(true);
		}
	} ;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_activate_account);
		String UserId = String.valueOf( KedniApp.getUserID() );
		KedniApp.dataSrc.serverHandler.sendVerificatioMessage(sendVerificationMessageCallback, UserId) ;
		
		// Set up the login form.
		mEmail = getString(R.string.auth_hint) ; //getIntent().getStringExtra(EXTRA_EMAIL);
		mEmailView = (EditText) findViewById(R.id.auth_code);
		//mEmailView.setText(mEmail);

		mLoginFormView = findViewById(R.id.login_form);
		mLoginStatusView = findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
						Toast.makeText(ActivateAcountActivity.this, getString(R.string.verification_msg_resend), Toast.LENGTH_LONG).show();
						resendHandler.postDelayed(resendRunable, 5000 * 5) ;
					}
				});
		btnResend  =(Button ) findViewById(R.id.resend_button) ;
		btnResend.setEnabled(false);
		btnResend.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						btnResend.setEnabled(false);
						String UserId = String.valueOf( KedniApp.getUserID() );
						KedniApp.dataSrc.serverHandler.sendVerificatioMessage(sendVerificationMessageCallback, UserId) ;
						resendHandler.postDelayed(resendRunable, 5000 * 60) ;
					}
				});
		
		
		btnReRegster = (TextView) findViewById(R.id.btn_re_register) ;
		btnReRegster.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						KedniApp app = (KedniApp) getApplicationContext() ;
						app.setIS_SIGNED_IN(false) ;
						app.setIS_ACCOUNT_ACTICATED(false) ;
						app.setIS_IN_TRIAL(false) ;
						app.setUserID(KedniApp.USER_ID_NULL) ;
						
						Intent intent = new Intent(ActivateAcountActivity.this , LoginActivity.class) ;
						startActivity(intent) ;
						ActivateAcountActivity.this.finish() ;
					}
				});
		
		resendHandler = new Handler();
		resendHandler.postDelayed(resendRunable, 5000 * 60) ;
		Toast.makeText(ActivateAcountActivity.this, getString(R.string.verification_msg_resend), Toast.LENGTH_LONG).show();
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
		resendHandler.removeCallbacks(resendRunable);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		mEmailView.setError(null);
		mEmail = mEmailView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		if (TextUtils.isEmpty(mEmail)) {
			mEmailView.setError(getString(R.string.error_field_required));
			focusView = mEmailView;
			cancel = true;
		}
		

		if (cancel) {
			focusView.requestFocus();
		} else {
			
			String authMsg = mEmailView.getText().toString() ;
			int ID = KedniApp.getUserID() ;
			mAuthTask = new UserActivateTask( ID , authMsg );
			mAuthTask.execute((Void) null);
			showProgress(true);

		}
	}


	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			int shortAnimTime = getResources().getInteger(
					android.R.integer.config_shortAnimTime);

			mLoginStatusView.setVisibility(View.VISIBLE);
			mLoginStatusView.animate().setDuration(shortAnimTime)
					.alpha(show ? 1 : 0)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginStatusView.setVisibility(show ? View.VISIBLE
									: View.GONE);
						}
					});

			mLoginFormView.setVisibility(View.VISIBLE);
			mLoginFormView.animate().setDuration(shortAnimTime)
					.alpha(show ? 0 : 1)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							mLoginFormView.setVisibility(show ? View.GONE
									: View.VISIBLE);
						}
					});
		} else {
			// The ViewPropertyAnimator APIs are not available, so simply show
			// and hide the relevant UI components.
			mLoginStatusView.setVisibility(show ? View.VISIBLE : View.GONE);
			mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
		}
	}

	/**
	 * Represents an asynchronous login/registration task used to authenticate
	 * the user.
	 */
	public class UserActivateTask extends AsyncTask<Void, Void, Boolean> {
		
		int userId ;
		String AuthMsg ; 
		
		public UserActivateTask( int userId , String token ) {
			this.userId  = userId ;
			AuthMsg = token ;
		}
		
		
		
		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.

			/////our login methods ;
			/// updated the method to take Acess token instead of  FBID 
			KedniApp.dataSrc.serverHandler.AccesptVerificatioMessage(ActivateAcountActivity.this, userId, AuthMsg);
				
			return true;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			mAuthTask = null;
			//showProgress(false);
			
			
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}

	}

	@Override
	public void onDataReady(String data) {
		try
		{
			mAuthTask = null;
			showProgress(false);
			JSONObject o=new JSONObject(data);
			int result=o.getInt(KedniApp.flag);
			if(result == 0){
				KedniApp app = ((KedniApp)getApplication()) ;
				app.setIS_ACCOUNT_ACTICATED(true);
				//KedniApp.IS_ACCOUNT_ACTICATED = true ;
				
				Intent intent = new Intent(ActivateAcountActivity.this,MainMap.class);
				startActivity(intent);
/*				Intent intent2 = new Intent(ActivateAcountActivity.this,TourActivity.class);
				startActivity(intent2);*/
				finish() ;
				
			}else{
				//Toast.makeText(getApplicationContext(), getString(R.string.error_incorrect_activation), Toast.LENGTH_SHORT).show();
				mEmailView.setError(getString(R.string.error_incorrect_activation));
				((KedniApp)getApplication()).setIS_ACCOUNT_ACTICATED(false);
				//KedniApp.IS_ACCOUNT_ACTICATED = false ;
			}
		}
		catch(Exception c)
		{
			//Log.e("error", c.getMessage());
		}
	}

	@Override
	public void onCursorReady(Cursor cur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDataLoadFail(String msg) {
		// TODO Auto-generated method stub
		
	}
	
	
	public class SendVerificationMessageCallback extends AbstractModel implements Notifiable<String> 
	{
		int failuresCount = 0 ;

		@Override
		public void onDataReady(String data) {
			Toast.makeText(ActivateAcountActivity.this, getString(R.string.activation_msg_is_sent), Toast.LENGTH_SHORT).show();
			
		}

		@Override
		public void onCursorReady(Cursor cur) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onDataLoadFail(String msg) {
			failuresCount ++ ;
			if(failuresCount <= 3){
				KedniApp.dataSrc.serverHandler.doRequest(this, msg) ;
			}else{
				failuresCount = 0 ;
				Toast.makeText(ActivateAcountActivity.this, ActivateAcountActivity.this.getString(R.string.error_failed_to_request_verif_msg), Toast.LENGTH_LONG).show() ;
			}
		}
	}

}
