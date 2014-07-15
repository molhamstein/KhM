package com.brainSocket.Views;

import com.brainSocket.Listners.SetGoalListner;
import com.brainSocket.khednima3ak.KedniApp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AutoCompleteTextView;
 
public class SearchAutoComplete extends AutoCompleteTextView implements OnItemClickListener {
 
	SetGoalListner goalChangeCallback ;
	
    public SearchAutoComplete(Context context) {
        super(context);
        setOnItemClickListener(this) ;
        goalChangeCallback = new SetGoalListner() ;
    }
     
    public SearchAutoComplete(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnItemClickListener(this);
        goalChangeCallback = new SetGoalListner() ;
    }
 
    public SearchAutoComplete(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnItemClickListener(this);
        goalChangeCallback = new SetGoalListner() ;
    }

    @Override
    protected void performFiltering(final CharSequence text, final int keyCode) {
        String filterText = "";
        super.performFiltering(filterText, keyCode);
    }
 
    @Override
    protected void replaceText(final CharSequence text) {
        super.replaceText(text);
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		////need to fill params
		//KedniApp.dataSrc.serverHandler.setGoal(goalChangeCallback, placeID, position);
	}
 
}