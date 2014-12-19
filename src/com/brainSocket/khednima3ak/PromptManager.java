package com.brainSocket.khednima3ak;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.provider.Settings;
import android.view.ContextThemeWrapper;
import android.widget.Toast;


public class PromptManager {
	
	private Context context ;
	private static ProgressDialog progressDialog;
	
	public PromptManager(Context con ) {
		context = con ;
	}
	
	public void setContext (Context con){
		context = con ;
	}
	
	
	public void promptForGoogleServicesUpdate (){
		
		if(context == null)
			return ;
		
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(new ContextThemeWrapper(context,  R.style.Theme_AppCompat ));
		alertBuilder.setTitle(R.string.google_services_update_title).setMessage(R.string.google_services_update_msg);
		alertBuilder.setPositiveButton("OK", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				try {
					//context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "com.google.android.gms")));
					context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.1mobile.com/search.html?keywords=Google+Play+services")));
				} catch (android.content.ActivityNotFoundException anfe) {
					anfe.printStackTrace() ;
					context.startActivity(new Intent(	Intent.ACTION_VIEW,	Uri.parse("https://play.google.com/store/apps/details?id=" + "com.google.android.gms")));
				}
			}
		});
		AlertDialog diag = alertBuilder.create();
		diag.show();
	}
	
	public void showProgressDiag(String msg) {
		if (progressDialog != null) {
			progressDialog.show();
		} else
			progressDialog = ProgressDialog.show(context, context.getString(R.string.pleas_wait), msg);

	}
	
	public void removeProgressDiag(String msg) {
		if (progressDialog != null)
			progressDialog.dismiss();
	}
	
	public void PopDialog( String msg , String title) {

		if(context == null)
			return ;
		
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(new ContextThemeWrapper(context,  R.style.Theme_AppCompat ));
		alertBuilder.setTitle(title).setMessage(msg);
		alertBuilder.setPositiveButton("OK", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss() ;
			}
		});
		AlertDialog diag = alertBuilder.create();
		diag.show();
	}
	
	public void promptNoUsers() {
		if(context == null)
			return ;
		
		PopDialog( context.getString(R.string.error_no_users), context.getString(R.string.error_no_users_title)) ;	
	}
	
	void promptForGPS() {
		
		if(context == null)
			return ;
		
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(new ContextThemeWrapper(context,  R.style.Theme_AppCompat ));
		alertBuilder
				.setTitle(context.getString(R.string.error_location_service_title))
				.setMessage(context.getString(R.string.error_location_service))
				.setPositiveButton(
						context.getString(R.string.btn_activate_location_service),
						new OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
								context.startActivity(myIntent);
							}
						})
				.setNegativeButton(
						context.getString(R.string.btn_activate_location_service_no),
						new OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
							}
						});

		AlertDialog diag = alertBuilder.create();
		diag.show();
	}
	
	
	void promptForIntrnet() {

		if(context == null)
			return ;
		
		PopDialog( context.getString(R.string.error_intrnet), context.getString(R.string.error_intrnet_title)) ;
		
		/*AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
		alertBuilder
				.setTitle(context.getString(R.string.error_intrnet_title))
				.setMessage(context.getString(R.string.error_intrnet))
				.setPositiveButton(
						context.getString(R.string.btn_activate_intrnet),
						new OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								Intent myIntent = new Intent(
										Settings.ACTION_LOCATION_SOURCE_SETTINGS);
								KedniApp.context.startActivity(myIntent);
							}
						})
				.setNegativeButton(
						context.getString(R.string.btn_activate_intrnet_no),
						new OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
							}
						});

		AlertDialog diag = alertBuilder.create();
		diag.show();*/
	}
	
	public void promptForAPKUpdate( final String URL) {
		
		if(context == null)
			return ;
		
		AlertDialog.Builder alertBuilder = new AlertDialog.Builder(new ContextThemeWrapper(context,  R.style.Theme_AppCompat ));
		alertBuilder
				.setTitle(context.getString(R.string.error_update_app_title))
				.setMessage(context.getString(R.string.error_update_app_desc))
				.setPositiveButton(
						context.getString(R.string.ok),
						new OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								Intent i = new Intent(Intent.ACTION_VIEW);
								i.setData(Uri.parse(URL)); 
								context.startActivity(i);
							}
						})
				.setNegativeButton(
						context.getString(R.string.reject),
						new OnClickListener() {
							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								arg0.dismiss() ;
							}
						});

		AlertDialog diag = alertBuilder.create();
		diag.show();
	}
	
	public void showToast(String msg , int Duration){
		if(context == null)
			return;
		Toast.makeText(context,msg,Duration).show();
	}
	
}
