package com.brainSocket.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

import com.brainSocket.Listners.SetGoalListner;
 
public class SearchAutoComplete extends AutoCompleteTextView {
 
	SetGoalListner goalChangeCallback ;
	Context context ;
	
    public SearchAutoComplete(Context context) {
        super(context);
        this.context  = context ;  
        init() ;
    }
     
    public SearchAutoComplete(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
 
    public SearchAutoComplete(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    protected void performFiltering(final CharSequence text, final int keyCode) {
        String filterText = "";
        super.performFiltering(text, keyCode);
    }
 
    @Override
    protected void replaceText(final CharSequence text) {
        super.replaceText(text);
    }

	
	private void init (){
		
        goalChangeCallback = new SetGoalListner(context) ;
       
	}
 
}