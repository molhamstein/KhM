package com.brainSocket.khednima3ak;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class AboutUsActivity extends Activity implements OnClickListener{

	private static final String FACEBOOK_LINK = "https://www.facebook.com/khednyM3ak" ;
	private static final String KEDNI_LINK = "http://facebook.com./kednima3ak" ;
	private static final String BRAIN_LINK = "http://brain-socket.com" ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_us);
		
		View linkBtn = findViewById(R.id.img_face_link) ;  
		linkBtn.setOnClickListener(this) ;
		
		linkBtn = findViewById(R.id.img_kedni_link) ;  
		linkBtn.setOnClickListener(this) ;
		
		linkBtn = findViewById(R.id.img_brain_link) ;  
		linkBtn.setOnClickListener(this) ;
		
		
	}
	
	@Override
	public void onClick(View arg0) {
		
		
		Intent i = new Intent(Intent.ACTION_VIEW);
		
		
		switch (arg0.getId()) {
		case R.id.img_brain_link:
			i.setData(Uri.parse(BRAIN_LINK));
			startActivity(i);
			break;
		case R.id.img_face_link:
			i.setData(Uri.parse(FACEBOOK_LINK));
			startActivity(i);
			break;
		case R.id.img_kedni_link:
			i.setData(Uri.parse(KEDNI_LINK));
			startActivity(i);
			break;
			
		default:
			break;
		}
		
	}
	
	

}
