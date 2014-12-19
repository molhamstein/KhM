package com.brainSocket.Views;

import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;

public class AnimationHelper {
	
	public static void popup(View view){
		
		AnimationSet animation = new AnimationSet(true); 
		float pivotX =  view.getWidth() / 2 ;
		float pivotY =  view.getHeight() / 2 ;
		ScaleAnimation anim = new ScaleAnimation(0f, 1.1f, 0f, 1.1f , pivotX, pivotY);
		anim.setInterpolator(new LinearInterpolator());
		anim.setDuration(300);
		animation.addAnimation(anim);
		
		anim = new ScaleAnimation(1.1f, 1f, 1.1f, 1f, pivotX, pivotY);
		anim.setInterpolator(new LinearInterpolator());
		anim.setDuration(100);
		anim.setStartOffset(300);
		animation.addAnimation(anim);
		
		view.startAnimation(animation);
	}
	
	/// not working
	public static void shrinkAndPopup(View view){
		
		AnimationSet animation = new AnimationSet(false); 
		float pivotX =  view.getWidth() / 2 ;
		float pivotY =  view.getHeight() / 2 ;
		
		ScaleAnimation anim = new ScaleAnimation(1f, 0f, 1f, 0f , pivotX, pivotY);
		anim.setInterpolator(new DecelerateInterpolator());
		anim.setDuration(100);
		animation.addAnimation(anim);
		
		anim = new ScaleAnimation(0f, 1.1f, 0f, 1.1f , pivotX, pivotY);
		anim.setInterpolator(new DecelerateInterpolator());
		anim.setDuration(300);
		anim.setStartOffset(100);
		animation.addAnimation(anim);
		
		anim = new ScaleAnimation(1.1f, 1f, 1.1f, 1f, pivotX, pivotY);
		anim.setInterpolator(new LinearInterpolator());
		anim.setDuration(100);
		anim.setStartOffset(400);
		animation.addAnimation(anim);
		
		view.startAnimation(animation);
	}
	
	
}
