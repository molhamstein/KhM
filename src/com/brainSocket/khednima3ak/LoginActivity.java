package com.brainSocket.khednima3ak;

import java.util.ArrayList;

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
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.brainSocket.data.Notifiable;
import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;

public class LoginActivity extends Activity implements Notifiable<String> 
{

	/**
	 * The default email to populate the email field with.
	 */
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";
	

	/**
	 * Keep track of the login task to ensure we can cancel it if requested.
	 */
	private UserLoginTask mAuthTask = null;

	// Values for email and password at the time of the login attempt.
	private String mEmail;

	// UI references.
	private EditText mEmailView;
	private EditText mPasswordView;
	private View mLoginFormView;
	private View mLoginStatusView;
	private TextView mLoginStatusMessageView;
	 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_login);
		KedniApp app = (KedniApp) getApplicationContext() ;
		app.setIS_IN_TRIAL(false) ;
		if(KedniApp.getUserID() != KedniApp.USER_ID_NULL && app.getIS_SIGNED_IN() && app.getIS_ACCOUNT_ACTICATED()){
			Intent intent =  new Intent(this,MainMap.class);
			startActivity(intent);
			finish();
			return ;
		}else if (app.getIS_SIGNED_IN() && !app.getIS_ACCOUNT_ACTICATED() ) {
			Intent intent =  new Intent(this,ActivateAcountActivity.class);
			startActivity(intent);
			finish();
			return ;
		}
			
		// Set up the login form.
		mEmail = getString(R.string.phone) ; //getIntent().getStringExtra(EXTRA_EMAIL);
		mEmailView = (EditText) findViewById(R.id.phone_num);
		//mEmailView.setText(mEmail);
/*
		mPasswordView = (EditText) findViewById(R.id.password);
		mPasswordView
				.setOnEditorActionListener(new TextView.OnEditorActionListener() {
					@Override
					public boolean onEditorAction(TextView textView, int id,
							KeyEvent keyEvent) {
						if (id == R.id.login || id == EditorInfo.IME_NULL) {
							attemptLogin();
							return true;
						}
						return false;
					}
				});
*/
		mLoginFormView = findViewById(R.id.login_form);
		mLoginStatusView = findViewById(R.id.login_status);
		mLoginStatusMessageView = (TextView) findViewById(R.id.login_status_message);

		findViewById(R.id.sign_in_button).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						attemptLogin();
					}
				});
	}
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		
		boolean isConnectionAvailable = KedniApp.dataSrc.serverHandler.isOnline() ;
		if( !isConnectionAvailable )
			KedniApp.promptMgr.PopDialog(getString(R.string.error_intrnet), getString(R.string.error_intrnet_title)) ;

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
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	/**
	 * Attempts to sign in or register the account specified by the login form.
	 * If there are form errors (invalid email, missing fields, etc.), the
	 * errors are presented and no actual login attempt is made.
	 */
	public void attemptLogin() {
		if (mAuthTask != null) {
			return;
		}

		// Reset errors.
		mEmailView.setError(null);
		//mPasswordView.setError(null);

		// Store values at the time of the login attempt.
		mEmail = mEmailView.getText().toString();
		//mPassword = mPasswordView.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid email address.
		if (TextUtils.isEmpty(mEmail)) {
			mEmailView.setError(getString(R.string.error_field_required));
			focusView = mEmailView;
			cancel = true;
		}if(mEmail.length() < 10){
			mEmailView.setError(getString(R.string.error_short_phone_num));
			focusView = mEmailView;
			cancel = true;
		}
		if( !(mEmail.startsWith("0") && mEmail.startsWith("9",1)) ){
			mEmailView.setError(getString(R.string.error_incorrect_phone_num));
			focusView = mEmailView;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login
			focusView.requestFocus();
		} else {
			
			// Show a progress spinner, and kick off a background task to
			// perform the user login attempt.
			
			ArrayList<String> perm1=new ArrayList<String>();
			perm1.add("public_profile");
			perm1.add("user_friends");
			perm1.add("email");
			
			//Session.openActiveSession(this, true, permissions, callback)
			Session.StatusCallback callback =  new LoginStatsCallback() ;
			Session.openActiveSession(LoginActivity.this, true, perm1, callback ) ;
			showProgress(true);
			
		}
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
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
	public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {
		String facebookId ;
		String facebookAccessToken ;
		public UserLoginTask(String userId , String token ) {
			this.facebookId  = userId ;
			facebookAccessToken = token ;
		}
		
		
		
		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO: attempt authentication against a network service.

			/////our login methods ;
			/// updated the method to take Acess token instead of  FBID 
			KedniApp.dataSrc.serverHandler.register(LoginActivity.this, facebookAccessToken, mEmail) ;
				
			return true;
		}

		@Override
		protected void onPostExecute(final Boolean success) {
			
		}

		@Override
		protected void onCancelled() {
			mAuthTask = null;
			showProgress(false);
		}

	}

	int failuresCount = 0 ;
	@Override
	public void onDataReady(String data) {
		// TODO Auto-generated method stub
		try
		{
			
			mAuthTask = null;
			showProgress(false);
			JSONObject o=new JSONObject(data);
			int userID=o.getInt(KedniApp.flag);
			
			if(userID<0){
				Toast.makeText(getApplicationContext(), getString(R.string.error_signingin), Toast.LENGTH_SHORT).show();
				//need to handle login errors
				mEmailView.setError(getString(R.string.error_signingin));
				mEmailView.requestFocus();
				
			}else{
				KedniApp app = ((KedniApp)getApplication()) ; 
				app.setUserID(userID);
				app.setIS_SIGNED_IN(true);
				//KedniApp.IS_SIGNED_IN = true ;
				app.setIS_IN_TRIAL(true) ;
				int key = o.getInt(KedniApp.DIGEST_TOKEN_KEY) ;
				app.setEncKey(key) ;
				KedniApp.encKey = key ;
				/*Intent intent = new Intent(LoginActivity.this,ActivateAcountActivity.class);
				LoginActivity.this.finish() ;
				startActivity(intent);*/
				
				Intent intent = new Intent(LoginActivity.this,MainMap.class);
				startActivity(intent);
				Intent intent2 = new Intent(LoginActivity.this,SimpleTourActivity.class);
				startActivity(intent2);
				KedniApp.isFirstRun = true ;
				finish() ;
			}

		}
		catch(Exception c)
		{
			Toast.makeText(getApplicationContext(), getString(R.string.error_signingin), Toast.LENGTH_SHORT).show();
			mEmailView.setError(getString(R.string.error_signingin));
			//mEmailView.requestFocus();
			Toast.makeText(getApplicationContext(), getString(R.string.error_signingin), Toast.LENGTH_SHORT).show();
			//need to handle login errors
			mEmailView.setError(getString(R.string.error_signingin));
			//mEmailView.requestFocus();
			//Log.e("error", c.getMessage());
		}
	}

	@Override
	public void onCursorReady(Cursor cur) {
		mAuthTask = null;
		showProgress(false);
		
	}

	@Override
	public void onDataLoadFail(String msg) {

		failuresCount ++ ;
		if(failuresCount <= failuresAllowed){
			KedniApp.dataSrc.serverHandler.doRequest(this, msg) ;
		}else{
			mAuthTask = null;
			showProgress(false);
			Toast.makeText(getApplicationContext(), getString(R.string.error_connection_failed), Toast.LENGTH_SHORT).show();
			mEmailView.setError(getString(R.string.error_connection_failed));
			mEmailView.requestFocus();
			failuresCount = 0 ;
		}
	}
	
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	}
	
	public class  LoginStatsCallback implements Session.StatusCallback {

		@Override
		public void call(Session session, SessionState state,Exception exception) {
			Request meRequest ;
			Request friendsRequest ;
			
			if (session.isOpened()){
				
	    		meRequest = Request.newMeRequest(session, new Request.GraphUserCallback() {

	    		  // callback after Graph API response with user object
	    		  @Override
	    		  public void onCompleted(GraphUser user, Response response) {
	    			  if (user != null) {
	    				  //Toast.makeText(LoginActivity.this, "Authenticated to facebook ID "+ user.getId(), Toast.LENGTH_LONG).show();
	    				  
  	    				  mLoginStatusMessageView.setText(R.string.login_progress_signing_in);
  	    				  String token = Session.getActiveSession().getAccessToken() ;
  	    				  KedniApp app = (KedniApp) getApplicationContext() ;
  	    				  String name = user.getName();
  	    				  app.setUserName(name) ;
  	    				  app.setFacebokID(user.getId()) ;
	    				  mAuthTask = new UserLoginTask( user.getId() , token );
	    				  mAuthTask.execute((Void) null);
	    				  
	    				}
	    		  }
	    		});
	    		
	    		
	    		friendsRequest = new Request( session,"/me/friends", null, HttpMethod.GET,
	    		    new Request.Callback() {
	    		        public void onCompleted(Response response) {
	    		            /* handle the result */
	    		        	//Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
	    		        	//Log.i("friends", response.toString());
	    		        }
	    		    }
	    		);
	    		
	    		meRequest.executeAsync();
	    		friendsRequest.executeAsync();
	    		
	    	}
			
		}
		
	}
}
